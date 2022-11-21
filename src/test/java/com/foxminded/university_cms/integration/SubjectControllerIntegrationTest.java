package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
class SubjectControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void showSubject_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("jamessmith")
    void showSubject_shouldReturnViewSubjectsAndStatus200() throws Exception {
        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjects"))
                .andExpect(model().attribute("subjects", List.of(
                        new Subject(1L, "Accounting and Finance"),
                        new Subject(2L, "Computer Science"),
                        new Subject(3L, "Architecture"),
                        new Subject(4L, "Chemistry"),
                        new Subject(5L, "English"),
                        new Subject(6L, "Art"),
                        new Subject(7L, "Psychology"),
                        new Subject(8L, "History"),
                        new Subject(9L, "Law"),
                        new Subject(10L, "Economics")
                )));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void subjectsManager_shouldReturnViewSubjectsManagerAndStatus200() throws Exception {
        List<Subject> subjects = List.of(
                new Subject(1L, "Accounting and Finance"),
                new Subject(2L, "Computer Science"),
                new Subject(3L, "Architecture"),
                new Subject(4L, "Chemistry"),
                new Subject(5L, "English"),
                new Subject(6L, "Art"),
                new Subject(7L, "Psychology"),
                new Subject(8L, "History"),
                new Subject(9L, "Law"),
                new Subject(10L, "Economics")
        );
        List<Group> groups = List.of(
                new Group(1L, "HR-32"),
                new Group(2L, "YJ-56"));

        mockMvc.perform(get("/subjects/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("subjectsManager"))
                .andExpect(model().attribute("subjects", subjects))
                .andExpect(model().attribute("groups", groups));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void addSubject_shouldReturnStatus300() throws Exception {
        String subjectName = "NEW_SUBJECT";
        mockMvc.perform(post("/subjects/add")
                        .param("subjectName", subjectName).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successAdd=true"));
        Subject subject = entityManager.find(Subject.class, 11L);

        assertEquals(subjectName, subject.getSubjectName());


    }

    @Test
    @WithUserDetails("olivertaylor")
    void deleteSubject_shouldReturnStatus300() throws Exception {
        mockMvc.perform(delete("/subjects/delete")
                        .param("subjectId", "1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successDelete=true"));
        Subject subject = entityManager.find(Subject.class, 1L);
        assertNull(subject);
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateSubject_shouldReturnStatus300() throws Exception {
        String subjectName = "NEW_NAME";
        mockMvc.perform(patch("/subjects/update")
                        .param("subjectId", "1")
                        .param("subjectName", subjectName)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/subjects/manager?successUpdate=true"));
        Subject subject = entityManager.find(Subject.class, 1L);
        assertEquals(subjectName, subject.getSubjectName());
    }
}
