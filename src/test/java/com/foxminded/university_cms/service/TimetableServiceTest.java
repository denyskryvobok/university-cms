package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimetableServiceTest extends TestcontainersInitializer {
    @Autowired
    private TimetableService timetableService;

    @Test
    void getGroupTimetableForOneDay_shouldReturnListOfTimetable_whenThereIsTimetableForInputDate() {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        Timetable fifth = new Timetable(5L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 5);

        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(first, second, third, fourth, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnMapDateToListOfSubject_whenThereIsTimetableForInputMonth() {
        Timetable t1 = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);

        Timetable t2 = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);

        Timetable t3 = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);

        Timetable t4 = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);

        Timetable t5 = new Timetable(5L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);

        Timetable t6 = new Timetable(6L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);

        Timetable t7 = new Timetable(7L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);

        Timetable t8 = new Timetable(8L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);

        Timetable t9 = new Timetable(9L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 4);

        Timetable t10 = new Timetable(10L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);

        Timetable t11 = new Timetable(11L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 6);

        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(t1, t2, t3, t4, t5));
        expected.put(LocalDate.parse("2022-10-04"), List.of(t6, t7, t8, t9, t10, t11));
        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnListOfTimetables_whenThereIsTimetableForInputDate() {
        Timetable firstTimetable = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable secondTimetable = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);

        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(firstTimetable, secondTimetable);

        assertEquals(expected, actual);

    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnMapDateToListOfTimetable_whenThereIsTimetableForInputDate() {
        Timetable firstTimetableMonday = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable thirdTimetableMonday = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        Timetable firstTimetableTuesday = new Timetable(10L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);

        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(firstTimetableMonday, thirdTimetableMonday));
        expected.put(LocalDate.parse("2022-10-04"), List.of(firstTimetableTuesday));

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }
}
