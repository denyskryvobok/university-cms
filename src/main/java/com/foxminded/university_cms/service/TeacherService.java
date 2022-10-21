package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Timetable;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface TeacherService {

    List<Timetable> findTimetableForOneDay(Long studentId, LocalDate date);

    Map<LocalDate, List<Timetable>> findTimetableForMonth(Long studentId, Month month);
}
