package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.Timetable;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface TeacherService {

    List<Teacher> getAllTeachers();

    List<Timetable> getTimetableForOneDay(Long teacherId, LocalDate date);

    Map<LocalDate, List<Timetable>> getTimetablesForMonth(Long teacherId, Month month);
}
