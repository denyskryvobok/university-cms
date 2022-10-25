package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Timetable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class GroupServiceImp implements GroupService {
    private final GroupDAO groupDAO;

    public GroupServiceImp(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Group> getAllGroupsWithStudents() {
        return groupDAO.findAll();
    }

    @Override
    public List<Timetable> getTimetableForOneDay(Long groupId, LocalDate date) {
        log.info("GetTimetableForOneDay start with groupId:{}, date:{}", groupId, date);
        Set<Timetable> timetableForOneDay = groupDAO.findTimetableForOneDay(groupId, date);
        return timetableForOneDay.stream()
                                 .sorted(comparing(Timetable::getSubjectOrder))
                                 .collect(toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getTimetablesForMonth(Long groupId, Month month) {
        log.info("GetTimetablesForMonth start with groupId:{}, month:{}", groupId, month);
        Set<Timetable> timetableForMonth = groupDAO.findTimetableForMonth(groupId, month);
        return timetableForMonth.stream()
                                .sorted(comparing(Timetable::getDateOfDay))
                                .collect(groupingBy(Timetable::getDateOfDay, LinkedHashMap::new,
                                        collectingAndThen(toList(),
                                                timetables -> timetables.stream()
                                                                        .sorted(comparing(Timetable::getSubjectOrder))
                                                                        .collect(toList()))));
    }
}
