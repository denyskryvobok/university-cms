package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ProfileControllerTest extends SpringSecurityConfig {
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void profileManager_shouldRedirectToAdminProfileAndReturnStatus300() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile"));
    }

    @Test
    @WithMockUser(roles = {"STUDENT", "TEACHER"})
    void profileManager_shouldRedirectToUserProfileAndReturnStatus300() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/userProfile"));
    }

    @Test
    @WithMockUser(username = "username", roles = "STUDENT")
    void getUserProfile_shouldReturnStatus200_whenUserIsStudent() throws Exception {
        Student student = new Student("James", "Smith", "607 Derek Drive", "Streetsboro", "44241", "United States", 1L, "8201296");
        student.setGroup(new Group(1L, "HR-32"));
        when(userService.getPersonByUsername("username")).thenReturn(student);
        mockMvc.perform(get("/profile/userProfile"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("student", student))
                .andExpect(view().name("studentProfile"));
    }

    @Test
    @WithMockUser(username = "username", roles = "TEACHER")
    void getUserProfile_shouldReturnStatus200_whenUserIsTeacher() throws Exception {
        Teacher teacher = new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States", 1L, "Lecturer in Accounting");
        when(userService.getPersonByUsername("username")).thenReturn(teacher);
        mockMvc.perform(get("/profile/userProfile"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teacher", teacher))
                .andExpect(view().name("teacherProfile"));
    }
}
