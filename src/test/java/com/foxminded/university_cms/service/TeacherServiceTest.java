package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TeacherDAO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @InjectMocks
    private TeacherServiceImp teacherService;

    @Mock
    private TeacherDAO teacherDAO;

    @Test
    void findTimetableForOneDay_shouldReturnListOfTimetables_whenThereIsTimetableForInputDate() {
        Timetable firstTimetable = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable secondTimetable = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);


        when(teacherDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-10-03")))
                .thenReturn(List.of(secondTimetable, firstTimetable));

        List<Timetable> actual = teacherService.findTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        List<Timetable> expected = List.of(firstTimetable, secondTimetable);

        assertEquals(expected, actual);

    }

    @Test
    void findTimetableForOneDay_shouldReturnEmptyList_whenThereIsNotTimetableForInputDate() {
        when(teacherDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-09-03"))).thenReturn(Collections.emptyList());

        List<Timetable> actual = teacherService.findTimetableForOneDay(1L, LocalDate.parse("2022-09-03"));

        List<Timetable> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

    @Test
    void findTimetableForMonth_shouldReturnMapDateToListOfSubject_whenThereIsTimetableForInputDate() {
        Timetable firstTimetableMonday = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable secondTimetableMonday = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        Timetable thirdTimetableMonday = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);

        Timetable firstTimetableTuesday = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);
        Timetable secondTimetableTuesday = new Timetable(5L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);
        Timetable thirdTimetableTuesday = new Timetable(6L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);

        when(teacherDAO.findTimetableForMonth(1L, Month.OCTOBER)).thenReturn(List.of(secondTimetableMonday,
                                                                                            firstTimetableMonday,
                                                                                            thirdTimetableTuesday,
                                                                                            thirdTimetableMonday,
                                                                                            firstTimetableTuesday,
                                                                                            secondTimetableTuesday));

        Map<LocalDate, List<Timetable>> actual = teacherService.findTimetableForMonth(1L, Month.OCTOBER);

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();
        expected.put(LocalDate.parse("2022-10-03"), List.of(firstTimetableMonday, secondTimetableMonday, thirdTimetableMonday));
        expected.put(LocalDate.parse("2022-10-04"), List.of(firstTimetableTuesday, secondTimetableTuesday, thirdTimetableTuesday));

        assertEquals(expected, actual);
    }

    @Test
    void findTimetableForMonth_shouldReturnEmptyMap_whenThereIsNotTimetableForInputMonth() {
        when(teacherDAO.findTimetableForMonth(1L, Month.NOVEMBER)).thenReturn(Collections.emptyList());

        Map<LocalDate, List<Timetable>> actual = teacherService.findTimetableForMonth(1L, Month.NOVEMBER);

        Map<LocalDate, List<Timetable>> expected = Collections.emptyMap();

        assertEquals(expected, actual);
    }

}