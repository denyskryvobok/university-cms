package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private GroupService groupService;

    @Test
    void showTeachers_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void showSubject_shouldReturnViewSubjectsAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Subject> subjects = getSubjects();

        when(subjectService.getAllSubjects()).thenReturn(subjects);

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjects"))
                .andExpect(model().attribute("subjects", subjects));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void subjectsManager_shouldReturnViewSubjectsManagerAndStatus200_whenUserHasAdminRole() throws Exception {
        List<Subject> subjects = getSubjects();
        List<Group> groups = getGroups();

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
    void addSubject_shouldReturnStatus300WithParamSuccessAdd_whenThereIsNoSubjectWithInputSubjectName() throws Exception {
        when(subjectService.addSubject("subjectName")).thenReturn(true);

        mockMvc.perform(post("/subjects/add")
                        .param("subjectName", "subjectName").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successAdd=subjectName"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addSubject_shouldReturnStatus300WithPAramFailAdd_whenSubjectWithInputNameAlreadyExists() throws Exception {
        when(subjectService.addSubject("subjectName")).thenReturn(false);

        mockMvc.perform(post("/subjects/add")
                        .param("subjectName", "subjectName").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?failAdd=subjectName"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteSubject_shouldReturnStatus300_whenInputHasSubjectIdParam() throws Exception {
        mockMvc.perform(delete("/subjects/delete")
                        .param("subjectId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successDelete=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateSubject_shouldReturnStatus300_whenInputHasSubjectIdAndSubjectNameParam() throws Exception {
        when(subjectService.updateSubject(anyLong(), anyString())).thenReturn(true);

        mockMvc.perform(patch("/subjects/update")
                        .param("subjectId", "1")
                        .param("subjectName", "name")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successUpdate=true"));
    }

    private List<Subject> getSubjects() {
        return List.of(
                new Subject(1L, "Art"),
                new Subject(2L, "Maths")
        );
    }

    private List<Group> getGroups() {
        return List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"),
                new Group(3L, "HN-12"));
    }
}
