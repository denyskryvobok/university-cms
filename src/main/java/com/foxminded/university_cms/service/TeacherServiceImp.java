package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.entity.Teacher;
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
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class TeacherServiceImp implements TeacherService {
    private final TeacherDAO teacherDAO;

    public TeacherServiceImp(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDAO.findAll();
    }

    @Override
    public List<Timetable> getTimetableForOneDay(Long teacherId, LocalDate date) {
        log.info("GetTimetableForOneDay start with teacherId:{}, date:{}", teacherId, date);
        Set<Timetable> timetableForOneDay = teacherDAO.findTimetableForOneDay(teacherId, date);
        return timetableForOneDay.stream()
                                 .sorted(comparing(Timetable::getSubjectOrder))
                                 .collect(Collectors.toList());
    }

    @Override
    public Map<LocalDate, List<Timetable>> getTimetablesForMonth(Long teacherId, Month month) {
        log.info("GetTimetablesForMonth start with teacherId:{}, month:{}", teacherId, month);
        Set<Timetable> timetableForMonth = teacherDAO.findTimetableForMonth(teacherId, month);
        return timetableForMonth.stream()
                                .sorted(comparing(Timetable::getDateOfDay))
                                .collect(groupingBy(Timetable::getDateOfDay, LinkedHashMap::new,
                                                    collectingAndThen(toList(),
                                                            timetables -> timetables.stream()
                                                                    .sorted(comparing(Timetable::getSubjectOrder))
                                                                    .collect(toList()))));
    }
}
