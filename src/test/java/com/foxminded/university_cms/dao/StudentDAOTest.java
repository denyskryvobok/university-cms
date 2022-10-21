package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class StudentDAOTest extends TestcontainersInitializer {
    @Autowired
    private StudentDAO studentDAO;

    @Test
    void findTimetableForOneDay_shouldReturnTimetableListForOneDay() {
        List<Timetable> actual = studentDAO.findTimetableForOneDay(1L, LocalDate.parse("2022-10-03"));

        Timetable first = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));
        Timetable second = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));
        Timetable third = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));
        Timetable fourth = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));

        List<Timetable> expected = List.of(first, second, third, fourth);

        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

        assertAll(
                () -> assertEquals(expected.get(0).getTimetableId(), actual.get(0).getTimetableId()),
                () -> assertEquals(expected.get(0).getMonth(), actual.get(0).getMonth()),
                () -> assertEquals(expected.get(0).getNameOfDay(), actual.get(0).getNameOfDay()),
                () -> assertEquals(expected.get(0).getDateOfDay(), actual.get(0).getDateOfDay()),
                () -> assertEquals(expected.get(0).getSubjectOrder(), actual.get(0).getSubjectOrder())
        );

        assertAll(
                () -> assertEquals(expected.get(1).getTimetableId(), actual.get(1).getTimetableId()),
                () -> assertEquals(expected.get(1).getMonth(), actual.get(1).getMonth()),
                () -> assertEquals(expected.get(1).getNameOfDay(), actual.get(1).getNameOfDay()),
                () -> assertEquals(expected.get(1).getDateOfDay(), actual.get(1).getDateOfDay()),
                () -> assertEquals(expected.get(1).getSubjectOrder(), actual.get(1).getSubjectOrder())
        );

        assertAll(
                () -> assertEquals(expected.get(2).getTimetableId(), actual.get(2).getTimetableId()),
                () -> assertEquals(expected.get(2).getMonth(), actual.get(2).getMonth()),
                () -> assertEquals(expected.get(2).getNameOfDay(), actual.get(2).getNameOfDay()),
                () -> assertEquals(expected.get(2).getDateOfDay(), actual.get(2).getDateOfDay()),
                () -> assertEquals(expected.get(2).getSubjectOrder(), actual.get(2).getSubjectOrder())
        );

        assertAll(
                () -> assertEquals(expected.get(3).getTimetableId(), actual.get(3).getTimetableId()),
                () -> assertEquals(expected.get(3).getMonth(), actual.get(3).getMonth()),
                () -> assertEquals(expected.get(3).getNameOfDay(), actual.get(3).getNameOfDay()),
                () -> assertEquals(expected.get(3).getDateOfDay(), actual.get(3).getDateOfDay()),
                () -> assertEquals(expected.get(3).getSubjectOrder(), actual.get(3).getSubjectOrder())
        );
    }

    @Test
    void findTimetableForMonth_shouldReturnTimetablesForMonth() {
        List<Timetable> actual = studentDAO.findTimetableForMonth(1L, Month.OCTOBER);

        Timetable e1 = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        Timetable e2 = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        Timetable e3 = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        Timetable e4 = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        Timetable e5 = new Timetable(5L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 1);
        Timetable e6 = new Timetable(6L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 2);
        Timetable e7 = new Timetable(7L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 3);
        Timetable e8 = new Timetable(8L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 4);
        Timetable e9 = new Timetable(9L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 5);
        Timetable e10 = new Timetable(10L, Month.OCTOBER, LocalDate.parse("2022-10-04"), DayOfWeek.TUESDAY, 6);

        List<Timetable> expected = List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}
