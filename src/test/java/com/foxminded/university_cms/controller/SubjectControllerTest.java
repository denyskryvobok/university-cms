package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SubjectControllerTest extends SpringSecurityConfig {
    @MockBean
    private SubjectService subjectService;

    @Test
    void showSubject_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void showSubject_shouldReturnViewSubjectsAndStatus200() throws Exception {
        when(subjectService.getAllSubjects()).thenReturn(List.of(
                new Subject(1L, "Art"),
                new Subject(2L, "Maths")
        ));

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjects"))
                .andExpect(model().attribute("subjects", List.of(
                        new Subject(1L, "Art"),
                        new Subject(2L, "Maths")
                )));
    }
}