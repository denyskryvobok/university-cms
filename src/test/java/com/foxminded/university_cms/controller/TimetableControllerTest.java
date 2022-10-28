package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.TimetableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(controllers = TimetableController.class)
class TimetableControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimetableService timetableService;

    @Test
    void getGroupTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        Map<LocalDate, List<Timetable>> map = Map.of(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));

        when(timetableService.getGroupTimetableForMonth(1L, "2022-10")).thenReturn(map);

        mockMvc.perform(get("/timetable/groupMonth").param("id", "1")
                        .param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", map)
                );
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        List<Timetable> list = List.of(first, second, third, fourth);

        when(timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(list);

        mockMvc.perform(get("/timetable/groupDate").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", list)
                );
    }

    @Test
    void getTeacherTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        Map<LocalDate, List<Timetable>> map = Map.of(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));

        when(timetableService.getTeacherTimetableForMonth(1L, "2022-10")).thenReturn(map);

        mockMvc.perform(get("/timetable/teacherMonth").param("id", "1")
                        .param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", map)
                );
    }

    @Test
    void getTeacherTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200() throws Exception {
        Timetable first = new Timetable(1L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));

        Timetable second = new Timetable(2L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 2);
        second.setSubject(new Subject(2L, "Computer Science"));

        Timetable third = new Timetable(3L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 3);
        third.setSubject(new Subject(5L, "English"));

        Timetable fourth = new Timetable(4L, LocalDate.parse("2022-10-03"), DayOfWeek.MONDAY, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        List<Timetable> list = List.of(first, second, third, fourth);

        when(timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(list);

        mockMvc.perform(get("/timetable/teacherDate").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", list)
                );
    }
}
