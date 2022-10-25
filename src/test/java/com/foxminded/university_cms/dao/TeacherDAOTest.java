package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
public class TeacherDAOTest extends TestcontainersInitializer {
    @Autowired
    private TeacherDAO teacherDAO;

    @Test
    void findTimetableForOneDay_shouldReturnTimetableForOneDay() {
        Set<Timetable> timetables = teacherDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));
        List<Timetable> actual = new ArrayList<>(timetables);
        Timetable timetable = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);

        List<Timetable> expected = List.of(timetable);

        assertAll(
                () -> assertEquals(expected.get(0).getTimetableId(), actual.get(0).getTimetableId()),
                () -> assertEquals(expected.get(0).getMonth(), actual.get(0).getMonth()),
                () -> assertEquals(expected.get(0).getNameOfDay(), actual.get(0).getNameOfDay()),
                () -> assertEquals(expected.get(0).getDateOfDay(), actual.get(0).getDateOfDay()),
                () -> assertEquals(expected.get(0).getSubjectOrder(), actual.get(0).getSubjectOrder()),
                () -> assertTrue(
                        expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected)
                )
        );
    }

    @Test
    void findTimetableForMonth_shouldReturnTimetablesForMonth() {
        Set<Timetable> timetables = teacherDAO.findTimetableForMonth(1L, Month.OCTOBER);
        List<Timetable> actual = new ArrayList<>(timetables);
        Timetable e1 = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);

        Timetable e2 = new Timetable(9L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);
        List<Timetable> expected = List.of(e1, e2);

        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        assertAll(
                () -> assertEquals(expected.get(0).getTimetableId(), actual.get(0).getTimetableId()),
                () -> assertEquals(expected.get(0).getMonth(), actual.get(0).getMonth()),
                () -> assertEquals(expected.get(0).getNameOfDay(), actual.get(0).getNameOfDay()),
                () -> assertEquals(expected.get(0).getDateOfDay(), actual.get(0).getDateOfDay()),
                () -> assertEquals(expected.get(0).getSubjectOrder(), actual.get(0).getSubjectOrder()));

        assertAll(
                () -> assertEquals(expected.get(1).getTimetableId(), actual.get(1).getTimetableId()),
                () -> assertEquals(expected.get(1).getMonth(), actual.get(1).getMonth()),
                () -> assertEquals(expected.get(1).getNameOfDay(), actual.get(1).getNameOfDay()),
                () -> assertEquals(expected.get(1).getDateOfDay(), actual.get(1).getDateOfDay()),
                () -> assertEquals(expected.get(1).getSubjectOrder(), actual.get(1).getSubjectOrder()));
    }
}
