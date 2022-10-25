package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void showTeachers_shouldReturnViewTeachersAndStatus200() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(List.of(
                new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States",1L ,"Lecturer in Accounting"),
                new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville", "72454", "United States",2L ,"Senior Lecturer in Architecture")
        ));

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers"))
                .andExpect(model().attribute("teachers", List.of(
                        new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States",1L ,"Lecturer in Accounting"),
                        new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville", "72454", "United States",2L ,"Senior Lecturer in Architecture")
                )));
    }

    @Test
    void getTimetablesForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        Map<LocalDate, List<Timetable>> map = Map.of(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));

        when(teacherService.getTimetablesForMonth(1L, Month.OCTOBER)).thenReturn(map);

        mockMvc.perform(get("/teachers/month").param("id", "1")
                        .param("month", "OCTOBER"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", map)
                );
    }

    @Test
    void getTimetablesForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, Month.OCTOBER, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        List<Timetable> list = List.of(first, second, third, fourth);

        when(teacherService.getTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(list);

        mockMvc.perform(get("/teachers/date").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", list)
                );
    }
}
