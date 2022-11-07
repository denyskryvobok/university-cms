package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TimetableDAO;
import com.foxminded.university_cms.entity.Timetable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
public class TimetableServiceImp implements TimetableService {
    private final TimetableDAO timetableDAO;

    public TimetableServiceImp(TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }


    @Override
    public List<Timetable> getGroupTimetableForOneDay(Long groupId, LocalDate date) {
        log.info("GetGroupScheduleForOneDay start with groupId:{}, date:{}", groupId, date);
        Set<Timetable> timetableForOneDay = timetableDAO.findGroupTimetableForOneDay(groupId, date);
        return timetableForOneDay.stream()
                                 .sorted(comparing(Timetable::getSubjectOrder))
                                 .collect(toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getGroupTimetableForMonth(Long groupId, String yearMonth) {
        log.info("GetGroupScheduleForMonth start with groupId:{}, month:{}", groupId, yearMonth);
        List<Integer> dates = parseYearMonth(yearMonth);
        Set<Timetable> timetableForMonth = timetableDAO.findGroupTimetableForMonth(groupId, dates.get(0), dates.get(1));
        return getTimetablesByDate(timetableForMonth);
    }

    @Override
    public List<Timetable> getTeacherTimetableForOneDay(Long teacherId, LocalDate date) {
        log.info("GetTeacherScheduleForOneDay start with groupId:{}, date:{}", teacherId, date);
        Set<Timetable> timetableForOneDay = timetableDAO.findTeacherTimetableForOneDay(teacherId, date);
        return timetableForOneDay.stream()
                                 .sorted(comparing(Timetable::getSubjectOrder))
                                 .collect(toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getTeacherTimetableForMonth(Long teacherId, String yearMonth) {
        log.info("GetTeacherScheduleForMonth start with groupId:{}, month:{}", teacherId, yearMonth);
        List<Integer> dates = parseYearMonth(yearMonth);
        Set<Timetable> timetableForMonth = timetableDAO.findTeacherTimetableForMonth(teacherId, dates.get(0), dates.get(1));
        return getTimetablesByDate(timetableForMonth);
    }

    private Map<LocalDate, List<Timetable>> getTimetablesByDate(Set<Timetable> timetables) {
        log.info("GetTimetablesByDate start with timetables:{}", timetables);
        return timetables.stream()
                         .sorted(comparing(Timetable::getDateOfDay))
                         .collect(groupingBy(Timetable::getDateOfDay, LinkedHashMap::new,
                                collectingAndThen(toList(),
                                        groupTimetables -> groupTimetables.stream()
                                                .sorted(comparing(Timetable::getSubjectOrder))
                                                .collect(toList()))));
    }

    private List<Integer> parseYearMonth(String yearMonth) {
        log.info("ParseYearMonth start with yearMoth:{}", yearMonth);
        Integer year = Integer.valueOf(yearMonth.substring(0, yearMonth.indexOf("-")));
        Integer month = Integer.parseInt(yearMonth.substring(yearMonth.indexOf("-") + 1));
        return List.of(month, year);
    }
}
