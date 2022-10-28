package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Timetable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TimetableService {

    List<Timetable> getGroupTimetableForOneDay(Long groupId, LocalDate date);

    Map<LocalDate, List<Timetable>> getGroupTimetableForMonth(Long groupId, String yearMonth);

    List<Timetable> getTeacherTimetableForOneDay(Long groupId, LocalDate date);

    Map<LocalDate, List<Timetable>> getTeacherTimetableForMonth(Long groupId, String yearMonth);
}
