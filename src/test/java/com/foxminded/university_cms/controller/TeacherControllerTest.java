package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.service.SubjectService;
import com.foxminded.university_cms.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private SubjectService subjectService;

    @Test
    void showTeachers_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void showTeachers_shouldReturnViewTeachersAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Teacher> teachers = getTeachers();

        when(teacherService.getAllTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers"))
                .andExpect(model().attribute("teachers", teachers));
    }

    @Test
    @WithMockUser(roles = "TEACHER")
    void getSubjects_shouldReturnStatus200WithSubjects_whenUserHasTeacherRole() throws Exception {
        when(subjectService.getTeacherSubjects(1L)).thenReturn(Set.of());

        mockMvc.perform(get("/teachers/subjects")
                        .param("teacherId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subjects", Set.of()))
                .andExpect(view().name("teacherSubjects"));
    }

    private List<Teacher> getTeachers() {
        return List.of(
                new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna",
                        "55060", "United States", 1L, "Lecturer in Accounting"),
                new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville",
                        "72454", "United States", 2L, "Senior Lecturer in Architecture")
        );
    }
}
