package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.service.TeacherService;
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

@WebMvcTest(controllers = TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    void showTeachers_shouldReturnViewTeachersAndStatus200() throws Exception {
        when(teacherService.getAllTeachers()).thenReturn(List.of(
                new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States", 1L, "Lecturer in Accounting"),
                new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville", "72454", "United States", 2L, "Senior Lecturer in Architecture")
        ));

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers"))
                .andExpect(model().attribute("teachers", List.of(
                        new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States", 1L, "Lecturer in Accounting"),
                        new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville", "72454", "United States", 2L, "Senior Lecturer in Architecture")
                )));
    }
}
