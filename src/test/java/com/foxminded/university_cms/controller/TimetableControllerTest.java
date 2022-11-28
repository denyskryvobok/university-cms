package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.dto.TimetableDTO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.CalendarService;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.SubjectService;
import com.foxminded.university_cms.service.TeacherService;
import com.foxminded.university_cms.service.TimetableService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TimetableControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TimetableService timetableService;
    @MockBean
    private CalendarService calendarService;
    @MockBean
    private GroupService groupService;
    @MockBean
    private SubjectService subjectService;
    @MockBean
    private TeacherService teacherService;

    @Test
    void getGroupTimetableForMonth_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/timetable/groupMonth"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/timetable/groupDate"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void getGroupTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200_whenUserIsAuthenticated() throws Exception {
        Map<LocalDate, List<Timetable>> map = getDateToTableMapForMonth();

        when(timetableService.getGroupTimetableForMonth(1L, "2022-10")).thenReturn(map);

        mockMvc.perform(get("/timetable/groupMonth").param("id", "1")
                        .param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", map));
    }

    @Test
    @WithMockUser
    void getGroupTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Timetable> timetables = getTimetablesForOneDay();

        when(timetableService.getGroupTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(timetables);

        mockMvc.perform(get("/timetable/groupDate").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", timetables));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getTeacherTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200_whenUserHasRoleTeacher() throws Exception {
        Map<LocalDate, List<Timetable>> map = getDateToTableMapForMonth();

        when(timetableService.getTeacherTimetableForMonth(1L, "2022-10")).thenReturn(map);

        mockMvc.perform(get("/timetable/teacherMonth").param("id", "1")
                        .param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", map)
                );
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getTeacherTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200_whenUserHasRoleTeacher() throws Exception {
        List<Timetable> timetables = getTimetablesForOneDay();

        when(timetableService.getTeacherTimetableForOneDay(1L, LocalDate.parse("2022-10-03"))).thenReturn(timetables);

        mockMvc.perform(get("/timetable/teacherDate").param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", timetables));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void showTimetableManager_shouldReturnStatus200_whenUserHasRoleAdminAndInputHasGroupIdAndYearMonthParam() throws Exception {
        List<Subject> subjects = List.of(new Subject(1L, "firstSub"), new Subject(2L, "secondSub"));
        when(calendarService.getTimetablesForEachDayOfMonth(anyLong(), anyString())).thenReturn(Map.of());
        when(groupService.getGroupById(1L)).thenReturn(new Group(1L, "group"));
        when(subjectService.getAllSubjects()).thenReturn(subjects);
        when(teacherService.getAllTeachers()).thenReturn(List.of());
        mockMvc.perform(get("/timetable/manager")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10"))
                .andExpect(view().name("timetableManager"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("calendarToTimetables", Map.of()))
                .andExpect(model().attribute("month", "October"))
                .andExpect(model().attribute("group", new Group(1L, "group")))
                .andExpect(model().attribute("subjects", subjects))
                .andExpect(model().attribute("teachers", List.of()))
                .andExpect(model().attribute("yearMonth", "2022-10"))
                .andExpect(model().attribute("timetableDTO", new TimetableDTO()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteTimetable_shouldReturnStatus300_whenUserHasRoleAdminAndInputHasRequiredParams() throws Exception {
        mockMvc.perform(delete("/timetable/delete")
                        .param("timetableId", "1")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?groupId=1&yearMonth=2022-10&date=2022-10-03&deleteSuccess=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTimetable_shouldReturnStatus300AndSetAttributeUpdateSuccess_whenUpdateTimetableReturnTrue() throws Exception {
        when(timetableService.updateTimetable(any())).thenReturn(true);

        mockMvc.perform(patch("/timetable/update")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?updateSuccess=true&groupId=1&yearMonth=2022-10&date=2022-10-03"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTimetable_shouldReturnStatus300AndSetAttributeUpdateFail_whenUpdateTimetableReturnFalse() throws Exception {
        when(timetableService.updateTimetable(any())).thenReturn(false);

        mockMvc.perform(patch("/timetable/update")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?updateFail=true&groupId=1&yearMonth=2022-10&date=2022-10-03"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addTimetable_shouldReturnStatus300_whenUserGasRoleAdminAndAllRequiredParametersExist() throws Exception {
        mockMvc.perform(post("/timetable/add")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?groupId=1&yearMonth=2022-10&date=2022-10-03&addSuccess=true"));
    }

    private Map<LocalDate, List<Timetable>> getDateToTableMapForMonth() {
        Timetable first = new Timetable(1L, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));
        first.setCalendar(new Calendar(LocalDate.now()));

        Timetable second = new Timetable(2L, 2);
        second.setSubject(new Subject(2L, "Computer Science"));
        second.setCalendar(new Calendar(LocalDate.now()));

        Timetable third = new Timetable(3L, 3);
        third.setSubject(new Subject(5L, "English"));
        third.setCalendar(new Calendar(LocalDate.now()));

        Timetable fourth = new Timetable(4L, 4);
        fourth.setSubject(new Subject(6L, "Art"));
        fourth.setCalendar(new Calendar(LocalDate.now()));

        return Map.of(LocalDate.parse("2022-10-03"), List.of(first, second, third, fourth));
    }

    private List<Timetable> getTimetablesForOneDay() {
        Timetable first = new Timetable(1L, 1);
        first.setSubject(new Subject(1L, "Accounting and Finance"));
        first.setCalendar(new Calendar(LocalDate.now()));

        Timetable second = new Timetable(2L, 2);
        second.setSubject(new Subject(2L, "Computer Science"));
        second.setCalendar(new Calendar(LocalDate.now()));

        return List.of(first, second);
    }
}
