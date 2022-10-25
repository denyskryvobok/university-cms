package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @InjectMocks
    private GroupServiceImp groupService;

    @Mock
    private GroupDAO groupDAO;

    @Test
    void getTimetableForOneDay_shouldReturnListOfTimetable_whenThereIsTimetableForInputDate() {
        Timetable first = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));
        Timetable second = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));
        Timetable third = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));
        Timetable fourth = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));

        when(groupDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(Set.of(first, second, third, fourth));

        List<Timetable> actual = groupService.getTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(first, second, third, fourth);

        assertEquals(expected, actual);
    }

    @Test
    void getTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        when(groupDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-09-03"))).thenReturn(Collections.emptySet());

        List<Timetable> actual = groupService.getTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getTimetablesForMonth_shouldReturnMapDateToListOfSubject_whenThereIsTimetableForInputMonth() {
        Timetable first = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));

        Timetable fifth = new Timetable(5L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);
        fifth.setSubject(new Subject(2L, "Computer Science"));

        Timetable sixth = new Timetable(6L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);
        sixth.setSubject(new Subject(7L, "Psychology"));

        Timetable seventh = new Timetable(7L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);
        seventh.setSubject(new Subject(9L, "Law"));

        Timetable eighth = new Timetable(8L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 4);
        eighth.setSubject(new Subject(10L, "Economics"));

        Timetable ninth = new Timetable(9L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);
        ninth.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable tenth = new Timetable(10L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 6);
        tenth.setSubject(new Subject(3L, "Architecture"));

        when(groupDAO.findTimetableForMonth(1L, Month.OCTOBER)).thenReturn(Set.of(first, second, third,
                fourth, fifth, sixth, seventh, eighth, ninth, tenth));

        Map<LocalDate, List<Timetable>> actual = groupService.getTimetablesForMonth(1L, Month.OCTOBER);

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));
        expected.put(LocalDate.parse("2022-10-04"), List.of(fifth, sixth, seventh, eighth, ninth, tenth));
        assertEquals(expected, actual);
    }

    @Test
    void getTimetablesForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        when(groupDAO.findTimetableForMonth(1L, Month.JANUARY)).thenReturn(Collections.emptySet());

        Map<LocalDate, List<Timetable>> actual = groupService.getTimetablesForMonth(1L, Month.JANUARY);

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }
}
