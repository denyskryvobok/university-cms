package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.entity.Timetable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;


@Service
@Slf4j
@Transactional
public class TeacherServiceImp implements TeacherService {
    private final TeacherDAO teacherDAO;


    public TeacherServiceImp(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Timetable> findTimetableForOneDay(Long teacherId, LocalDate date) {
        log.info("FindTimetableForOneDay start with teacherId:{}, date:{}", teacherId, date);
        List<Timetable> timetableForOneDay = teacherDAO.findTimetableForOneDay(teacherId, date);

        if (timetableForOneDay.isEmpty()) {
            return Collections.emptyList();
        }
        return timetableForOneDay.stream()
                .sorted(comparing(Timetable::getSubjectOrder))
                .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> findTimetableForMonth(Long teacherId, Month month) {
        log.info("FindTimetableForMonth start with teacherId:{}, month:{}", teacherId, month);
        List<Timetable> timetableForMonth = teacherDAO.findTimetableForMonth(teacherId, month);
        if (timetableForMonth.isEmpty()) {
            return Collections.emptyMap();
        }
        return timetableForMonth.stream()
                .collect(groupingBy(Timetable::getDateOfDay,
                                    LinkedHashMap::new,
                                    mapping(Function.identity(),
                                            collectingAndThen(toList(),
                                                              timetables -> timetables.stream()
                                                              .sorted(comparing(Timetable::getSubjectOrder))
                                                              .collect(toList())))));

    }
}
