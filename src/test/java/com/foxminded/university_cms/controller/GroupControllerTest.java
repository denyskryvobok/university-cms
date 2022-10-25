package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.GroupService;
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


@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Test
    void showGroups_shouldReturnViewGroupsAndStatus200() throws Exception {
        when(groupService.getAllGroupsWithStudents()).thenReturn(List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"),
                new Group(3L, "HN-12")));

        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attribute("groups", List.of(
                        new Group(1L, "HR-32"),
                        new Group(2L, "YJ-56"),
                        new Group(3L, "HN-12")
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

        when(groupService.getTimetablesForMonth(1L, Month.OCTOBER)).thenReturn(map);

        mockMvc.perform(get("/groups/month").param("id", "1")
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

        when(groupService.getTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(list);

        mockMvc.perform(get("/groups/date").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", list)
                );
    }
}
