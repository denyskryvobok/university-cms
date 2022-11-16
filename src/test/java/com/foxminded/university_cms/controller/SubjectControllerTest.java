package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

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

class SubjectControllerTest extends SpringSecurityConfig {
    @MockBean
    private SubjectService subjectService;

    @MockBean
    private GroupService groupService;

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

    @Test
    @WithMockUser(roles = "ADMIN")
    void subjectsManager_shouldReturnViewSubjectsManagerAndStatus200() throws Exception {
        List<Subject> subjects = List.of(
                new Subject(1L, "Art"),
                new Subject(2L, "Maths")
        );
        List<Group> groups = List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"),
                new Group(3L, "HN-12"));
        when(subjectService.getAllSubjects()).thenReturn(subjects);
        when(groupService.getAllGroups()).thenReturn(groups);

        mockMvc.perform(get("/subjects/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjectsManager"))
                .andExpect(model().attribute("subjects", subjects))
                .andExpect(model().attribute("groups", groups));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addSubject_shouldReturnStatus300() throws Exception {
        mockMvc.perform(post("/subjects/add")
                        .param("subjectName", "subjectName").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successAdd=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteSubject_shouldReturnStatus300() throws Exception {
        mockMvc.perform(delete("/subjects/delete")
                        .param("subjectId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successDelete=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateSubject_shouldReturnStatus300() throws Exception {
        when(subjectService.updateSubject(anyLong(), anyString())).thenReturn(true);
        mockMvc.perform(patch("/subjects/update")
                        .param("subjectId", "1")
                        .param("subjectName", "name")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successUpdate=true"));
    }
}
