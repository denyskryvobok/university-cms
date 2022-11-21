package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
class ProfileControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("olivertaylor")
    void profileManager_shouldRedirectToAdminProfileAndReturnStatus300_whenUserHasAdminRole() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile"));
    }

    @Test
    @WithUserDetails("jamessmith")
    void profileManager_shouldRedirectToUserProfileAndReturnStatus300_whenUserDoseNotHaveAdminRole() throws Exception {
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile/userProfile"));
    }

    @Test
    @WithUserDetails("jamessmith")
    void getUserProfile_shouldReturnStatus200_whenUserIsStudent() throws Exception {
        Student student = getStudent();
        mockMvc.perform(get("/profile/userProfile"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("student", student))
                .andExpect(view().name("studentProfile"));
    }

    @Test
    @WithUserDetails("jackdavies")
    void getUserProfile_shouldReturnStatus200_whenUserIsTeacher() throws Exception {
        Teacher teacher = getTeacher();
        mockMvc.perform(get("/profile/userProfile"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("teacher", teacher))
                .andExpect(view().name("teacherProfile"));
    }

    private Student getStudent() {
        Student student = new Student("James",
                                      "Smith",
                                      "607 Derek Drive",
                                      "Streetsboro",
                                      "44241",
                                      "United States",
                                      1L,
                                      "8201296");

        student.setGroup(new Group(1L, "HR-32"));
        return student;
    }

    private  Teacher getTeacher() {
        return new Teacher("Jack",
                           "Davies",
                           "2830 Elliot Avenue",
                           "Seattle",
                           "98119",
                           "United States",
                           2L,
                           "Associate Professorship of Computer Science");
    }
}
