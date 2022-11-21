package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@Transactional
class StudentControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showStudents_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("jamessmith")
    void showStudents_shouldReturnViewStudentsAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Student> students = getAllStudents();
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attribute("students", students));
    }

    private List<Student> getAllStudents() {
        return List.of(
                new Student("James", "Smith", "607 Derek Drive", "Streetsboro", "44241", "United States", 1L, "8201296"),
                new Student("Susan", "Walker", "8 Terra Street", "Renton", "98055", "United States", 2L, "2873192"),
                new Student("Robert", "Taylor", "4232 Pick Street", "Denver", "80202", "United States", 3L, "7219310"),
                new Student("Patricia", "Brown", "1348 Mesa Drive", "Laughlin", "89046", "United States", 4L, "6190802")
        );
    }
}
