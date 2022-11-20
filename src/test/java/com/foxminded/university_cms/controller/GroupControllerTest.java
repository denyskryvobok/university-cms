package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GroupControllerTest extends SpringSecurityConfig {
    @MockBean
    private GroupService groupService;
    @MockBean
    private StudentService studentService;

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

    @Test
    @WithMockUser(roles = "ADMIN")
    void groupManager_shouldReturnViewGroupManagerAndStatus200() throws Exception {
        List<Group> groups = List.of(new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"));
        when(groupService.getAllGroups()).thenReturn(groups);

        mockMvc.perform(get("/groups/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("groupManager"))
                .andExpect(model().attribute("groups", groups));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addGroup_shouldReturnStatus302Redirection() throws Exception {
        mockMvc.perform(post("/groups/add").param("groupName", "group").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?successAdd=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateGroup_shouldReturnStatus302RedirectionWithParamSuccessUpdateTrue_whenUpdateGroupReturnTrue() throws Exception {
        when(groupService.updateGroup(1L, "name")).thenReturn(true);
        mockMvc.perform(patch("/groups/update")
                        .param("groupName", "name")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?successUpdate=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateGroup_shouldReturnStatus302RedirectionWithParamFailUpdateTrue_whenUpdateGroupReturnTrue() throws Exception {
        when(groupService.updateGroup(1L, "name")).thenReturn(false);
        mockMvc.perform(patch("/groups/update")
                        .param("groupName", "name")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?failUpdate=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteGroup_shouldReturnStatus302RedirectionWithParamSuccessDeleteTrue() throws Exception {
        mockMvc.perform(delete("/groups/delete")
                        .param("groupId", "1").with(csrf()))
                .andExpect(redirectedUrl("/groups/manager?successDelete=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void showStudentsOfGroup_shouldReturnViewSelectStudentsForGroupAndStatus200() throws Exception {
        Group group = new Group(1L, "group");
        Set<Student> students = Set.of(new Student("James", "Smith", "607 Derek Drive", "Streetsboro", "44241", "United States", 1L, "8201296"),
                                        new Student("Robert", "Taylor", "4232 Pick Street", "Denver", "80202", "United States", 2L, "7219310"));

        when(groupService.getGroupWithStudents(1L)).thenReturn(group);
        when(studentService.getStudentsWhoAreNotInGroupByGroupId(1L)).thenReturn(students);

        mockMvc.perform(get("/groups/students")
                        .param("groupId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("group", group))
                .andExpect(model().attribute("students", students))
                .andExpect(view().name("selectStudentsForGroup"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addStudentToGroup_shouldReturnStatus302RedirectionWithParamSuccessAddTrue() throws Exception {
        mockMvc.perform(patch("/groups/students/add")
                        .param("studentId", "1")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/students?successAdd=true&groupId=1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteStudentFromGroup_shouldReturnStatus302RedirectionWithParamSuccessDeleteTrue() throws Exception {
        mockMvc.perform(delete("/groups/students/delete")
                        .param("studentId", "1")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/students?successDelete=true&groupId=1"));
    }
}
