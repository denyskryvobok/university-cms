package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TimetableDAO;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    @InjectMocks
    private TimetableServiceImp timetableService;

    @Mock
    private TimetableDAO timetableDAO;

    @Test
    void getGroupTimetableForOneDay_shouldReturnListOfTimetable_whenThereIsTimetableForInputDate() {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));
        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));
        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));
        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));

        when(timetableDAO.findGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(Set.of(first, second, third, fourth));

        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(first, second, third, fourth);

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        when(timetableDAO.findGroupTimetableForOneDay(1L, LocalDate.parse("2022-09-03"))).thenReturn(Collections.emptySet());

        List<Timetable> actual = timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnMapDateToListOfSubject_whenThereIsTimetableForInputMonth() {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));

        Timetable fifth = new Timetable(5L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);
        fifth.setSubject(new Subject(2L, "Computer Science"));

        Timetable sixth = new Timetable(6L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);
        sixth.setSubject(new Subject(7L, "Psychology"));

        Timetable seventh = new Timetable(7L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);
        seventh.setSubject(new Subject(9L, "Law"));

        Timetable eighth = new Timetable(8L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 4);
        eighth.setSubject(new Subject(10L, "Economics"));

        Timetable ninth = new Timetable(9L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);
        ninth.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable tenth = new Timetable(10L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 6);
        tenth.setSubject(new Subject(3L, "Architecture"));

        when(timetableDAO.findGroupTimetableForMonth(1L, 10, 2022)).thenReturn(Set.of(first, second, third,
                fourth, fifth, sixth, seventh, eighth, ninth, tenth));

        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));
        expected.put(LocalDate.parse("2022-10-04"), List.of(fifth, sixth, seventh, eighth, ninth, tenth));
        assertEquals(expected, actual);
    }

    @Test
    void getGroupTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        when(timetableDAO.findGroupTimetableForMonth(1L, 9, 2022)).thenReturn(Collections.emptySet());

        Map<LocalDate, List<Timetable>> actual = timetableService.getGroupTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnListOfTimetables_whenThereIsTimetableForInputDate() {
        Timetable firstTimetable = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable secondTimetable = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);


        when(timetableDAO.findTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03")))
                .thenReturn(Set.of(secondTimetable, firstTimetable));

        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(firstTimetable, secondTimetable);

        assertEquals(expected, actual);

    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        when(timetableDAO.findTeacherTimetableForOneDay(1L, LocalDate.parse("2022-09-03"))).thenReturn(Collections.emptySet());

        List<Timetable> actual = timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnMapDateToListOfTimetable_whenThereIsTimetableForInputDate() {
        Timetable firstTimetableMonday = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable secondTimetableMonday = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        Timetable thirdTimetableMonday = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);

        Timetable firstTimetableTuesday = new Timetable(4L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);
        Timetable secondTimetableTuesday = new Timetable(5L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);
        Timetable thirdTimetableTuesday = new Timetable(6L, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);

        when(timetableDAO.findTeacherTimetableForMonth(1L, 10, 2022)).thenReturn(Set.of(secondTimetableMonday,
                firstTimetableMonday,
                thirdTimetableTuesday,
                thirdTimetableMonday,
                firstTimetableTuesday,
                secondTimetableTuesday));

        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-10");

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(firstTimetableMonday, secondTimetableMonday, thirdTimetableMonday));
        expected.put(LocalDate.parse("2022-10-04"), List.of(firstTimetableTuesday, secondTimetableTuesday, thirdTimetableTuesday));

        assertEquals(expected, actual);
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        when(timetableDAO.findTeacherTimetableForMonth(1L, 9, 2022)).thenReturn(Collections.emptySet());

        Map<LocalDate, List<Timetable>> actual = timetableService.getTeacherTimetableForMonth(1L, "2022-09");

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }
}
