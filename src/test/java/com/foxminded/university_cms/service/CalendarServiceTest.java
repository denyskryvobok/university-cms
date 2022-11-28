package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarServiceTest extends TestcontainersInitializer {
    @Autowired
    private CalendarService calendarService;

    @Test
    void getTimetablesForEachDayOfMonth_shouldReturnMapOfCalendarToListOfTimetable_whenGroupExistAndCalendarForInputDataExist() {
        Map<Calendar, List<Timetable>> actual = calendarService.getTimetablesForEachDayOfMonth(1L, "2022-10");

        Map<Calendar, List<Timetable>> expected = getCalendarToTimetableMap();

        assertEquals(actual, expected);
    }

    private Map<Calendar, List<Timetable>> getCalendarToTimetableMap() {
        Map<Calendar, List<Timetable>> calendarToTimetable = new LinkedHashMap<>();

        Calendar c1 = new Calendar();
        c1.setCalendarId(1L);

        Timetable t1 = new Timetable(1L, 1);

        Timetable t2 = new Timetable(2L, 2);

        Timetable t3 = new Timetable(3L, 3);

        Timetable t4 = new Timetable(4L, 3);

        Timetable t5 = new Timetable(5L, 4);

        calendarToTimetable.put(c1, List.of(t1, t2, t3, t4, t5));

        Calendar c2 = new Calendar();
        c2.setCalendarId(2L);

        Timetable t6 = new Timetable(6L, 1);

        Timetable t7 = new Timetable(7L, 2);

        Timetable t8 = new Timetable(8L, 3);

        Timetable t9 = new Timetable(9L, 4);

        Timetable t10 = new Timetable(10L, 5);

        Timetable t11 = new Timetable(11L, 6);

        calendarToTimetable.put(c2, List.of(t6, t7, t8, t9, t10, t11));

        return calendarToTimetable;
    }
}
