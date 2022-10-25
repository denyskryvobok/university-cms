package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Timetable;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface GroupService {

    List<Group> getAllGroupsWithStudents();

    List<Timetable> getTimetableForOneDay(Long groupId, LocalDate date);

    Map<LocalDate, List<Timetable>> getTimetablesForMonth(Long groupId, Month month);
}
