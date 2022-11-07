package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GroupControllerTest extends SpringSecurityConfig {
    @MockBean
    private GroupService groupService;

    @Test
    void showGroups_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void showGroups_shouldReturnViewGroupsAndStatus200() throws Exception {
        when(groupService.getAllGroupsWithStudents()).thenReturn(List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"),
                new Group(3L, "HN-12")));

        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attribute("groups", List.of(
                        new Group(1L, "HR-32"),
                        new Group(2L, "YJ-56"),
                        new Group(3L, "HN-12")
                )));
    }
}
