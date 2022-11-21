package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@AutoConfigureMockMvc
@Transactional
public class GroupControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void showGroups_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("olivertaylor")
    public void showGroups_shouldReturnViewGroupsAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Group> allGroups = getAllGroups();
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk())
                .andExpect(view().name("groups"))
                .andExpect(model().attribute("groups", allGroups));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void groupManager_shouldReturnViewGroupManagerAndStatus200_whenUserHasAdminRole() throws Exception {
        List<Group> allGroups = getAllGroups();
        mockMvc.perform(get("/groups/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("groupManager"))
                .andExpect(model().attribute("groups", allGroups));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void addGroup_shouldReturnStatus302RedirectionAndParamSuccessAddTrue_whenInputGroupNameParamExist() throws Exception {
        String groupName = "NEW_GROUP";

        mockMvc.perform(post("/groups/add").param("groupName", groupName).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?successAdd=true"));

        Group group = entityManager.find(Group.class, 3L);
        assertEquals(groupName, group.getGroupName());
        assertNotNull(group);
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateGroup_shouldReturnStatus302RedirectionWithParamSuccessUpdateTrue_whenUpdateGroupReturnTrue() throws Exception {
        String groupName = "groupName";
        mockMvc.perform(patch("/groups/update")
                        .param("groupName", groupName)
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?successUpdate=true"));
        Group group = entityManager.find(Group.class, 1L);
        assertEquals(groupName, group.getGroupName());
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateGroup_shouldReturnStatus302RedirectionWithParamFailUpdateTrue_whenUpdateGroupReturnTrue() throws Exception {
        String groupName = "HR-32";
        mockMvc.perform(patch("/groups/update")
                        .param("groupName", groupName)
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/manager?failUpdate=true"));
        Group group = entityManager.find(Group.class, 1L);
        assertEquals(groupName, group.getGroupName());
    }

    @Test
    @WithUserDetails("olivertaylor")
    void deleteGroup_shouldReturnStatus302RedirectionWithParamSuccessDeleteTrue_whenInputParamGroupIdExists() throws Exception {
        mockMvc.perform(delete("/groups/delete")
                        .param("groupId", "1").with(csrf()))
                .andExpect(redirectedUrl("/groups/manager?successDelete=true"));
        Group group = entityManager.find(Group.class, 1L);
        assertNull(group);
    }

    @Test
    @WithUserDetails("olivertaylor")
    void showStudentsOfGroup_shouldReturnViewSelectStudentsForGroupAndStatus200_whenInputGroupIdExists() throws Exception {
        Group group = new Group(1L, "group");
        Set<Student> students = getStudentsWhoNotInGroupWithIdOne();

        mockMvc.perform(get("/groups/students")
                        .param("groupId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("group", group))
                .andExpect(model().attribute("students", students))
                .andExpect(view().name("selectStudentsForGroup"));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void addStudentToGroup_shouldReturnStatus302RedirectionWithParamSuccessAddTrue_whenInputHasStudentIdAndGroupId() throws Exception {
        mockMvc.perform(patch("/groups/students/add")
                        .param("studentId", "3")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/students?successAdd=true&groupId=1"));
        Student student = entityManager.find(Student.class, 3L);
        assertEquals(new Group(1L, "HR-32"), student.getGroup());
    }

    @Test
    @WithUserDetails("olivertaylor")
    void deleteStudentFromGroup_shouldReturnStatus302RedirectionWithParamSuccessDeleteTrue_whenInputHasGroupIdAndStudentId() throws Exception {
        mockMvc.perform(delete("/groups/students/delete")
                        .param("studentId", "1")
                        .param("groupId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/groups/students?successDelete=true&groupId=1"));
        Group group = entityManager.find(Group.class, 1L);

        boolean actual =
                group.getStudents().contains(new Student("James",
                                                         "Smith",
                                                         "607 Derek Drive",
                                                         "Streetsboro",
                                                         "44241",
                                                         "United States",
                                                         1L,
                                                         "8201296"));
        assertFalse(actual);
    }

    private Set<Student> getStudentsWhoNotInGroupWithIdOne() {
        return Set.of(
                new Student("Robert", "Taylor", "4232 Pick Street", "Denver", "80202", "United States", 3L, "7219310"),
                new Student("Patricia", "Brown", "1348 Mesa Drive", "Laughlin", "89046", "United States", 4L, "6190802"));
    }

    private List<Group> getAllGroups() {
        return List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-32"));
    }
}
