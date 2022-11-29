package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Timetable;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;


public interface CalendarService {
    Map<Calendar, List<Timetable>> getTimetablesForEachDayOfMonth(Long groupId, YearMonth yearMonth);
}
