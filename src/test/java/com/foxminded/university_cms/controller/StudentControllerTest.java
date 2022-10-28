package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void showStudents_shouldReturnViewStudentsAndStatus200() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(
                new Student("James", "Smith", "607 Derek Drive", "Streetsboro", "44241", "United States", 1L, 8201296),
                new Student("Robert", "Taylor", "4232 Pick Street", "Denver", "80202", "United States", 2L, 7219310)
        ));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attribute("students", List.of(
                        new Student("James", "Smith", "607 Derek Drive", "Streetsboro", "44241", "United States", 1L, 8201296),
                        new Student("Robert", "Taylor", "4232 Pick Street", "Denver", "80202", "United States", 2L, 7219310)
                )));
    }
}