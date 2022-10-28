package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.service.GroupService;
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

@WebMvcTest(controllers = GroupController.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Test
    void showGroups_shouldReturnViewGroupsAndStatus200() throws Exception {
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