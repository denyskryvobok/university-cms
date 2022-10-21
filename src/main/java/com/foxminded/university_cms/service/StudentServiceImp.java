package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.StudentDAO;
import com.foxminded.university_cms.entity.Subject;
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

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
public class StudentServiceImp implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImp(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Map<LocalDate, List<Subject>> findTimetableForOneDay(Long studentId, LocalDate date) {
        log.info("FindTimetableForOneDay start with studentId:{}, date:{}", studentId, date);
        List<Timetable> timetableForOneDay = studentDAO.findTimetableForOneDay(studentId, date);

        if (timetableForOneDay.isEmpty()) {
            return Collections.emptyMap();
        }
        return timetableForOneDay.stream()
                .sorted(comparing(Timetable::getSubjectOrder))
                .collect(groupingBy(Timetable::getDateOfDay,
                        mapping(Timetable::getSubject, toList())));
    }

    @Override
    public Map<LocalDate, List<Subject>> findTimetableForMonth(Long studentId, Month month) {
        log.info("FindTimetableForMonth start with studentId:{}, month:{}", studentId, month);
        List<Timetable> timetableForMonth = studentDAO.findTimetableForMonth(studentId, month);
        if (timetableForMonth.isEmpty()) {
            return Collections.emptyMap();
        }

        Function<List<Timetable>, List<Subject>> sortedSubjects =
                timetables -> timetables.stream()
                        .sorted(comparing(Timetable::getSubjectOrder))
                        .map(Timetable::getSubject)
                        .collect(toList());

        return timetableForMonth.stream()
                .collect(groupingBy(Timetable::getDateOfDay,
                        LinkedHashMap::new,
                        mapping(Function.identity(),
                                collectingAndThen(toList(), sortedSubjects))));
    }
}
