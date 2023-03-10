package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
public class TeacherControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showTeachers_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/teachers"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("jamessmith")
    void showTeachers_shouldReturnViewTeachersAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Teacher> teachers = getAllTeachers();

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(view().name("teachers"))
                .andExpect(model().attribute("teachers", teachers));
    }

    @Test
    @WithUserDetails("jackdavies")
    void getSubjects_shouldReturnStatus200WithSubjects_whenUserHasTeacherRole() throws Exception {
        Subject subject = new Subject("Computer Science");
        subject.setSubjectId(2L);

        Set<Subject> subjects = Set.of(subject);

        mockMvc.perform(get("/teachers/subjects")
                        .param("teacherId", "2"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subjects", subjects))
                .andExpect(view().name("teacherSubjects"));
    }

    private List<Teacher> getAllTeachers() {
        return List.of(
                new Teacher("Oliver", "Taylor", "367 Pritchard Cour", "Owatonna", "55060", "United States", 1L, "Lecturer in Accounting"),
                new Teacher("Jack", "Davies", "2830 Elliot Avenue", "Seattle", "98119", "United States", 2L, "Associate Professorship of Computer Science"),
                new Teacher("Harry", "Evans", "2767 Barrington Court", "Carryville", "72454", "United States", 3L, "Senior Lecturer in Architecture"),
                new Teacher("Thomas", "Davis", "3952 Shinn Street", "New York", "10004", "United States", 4L, "Chemistry Teacher"),
                new Teacher("George", "Roberts", "10 Tree Top Lane", "Conshohocken", "19428", "United States", 5L, "Teaching Fellow in English"),
                new Teacher("Jessica", "Roberts", "2701 Fidler Drive", "San Antonio", "78217", "United States", 6L, "Art & Design Teacher"),
                new Teacher("Green", "Thomas", "1484 Armbrester Drive", "Irvine", "92614", "United States", 7L, "Lecturer in Psychology"),
                new Teacher("Sarah", "Hall", "552 Parrish Avenue", "San Antonio", "78205", "United States", 8L, "Lecturer in Roman History"),
                new Teacher("Charles", "Thomas", "4632 Tanglewood Road", "Tupelo", "38801", "United States", 9L, "Lecturer - Foundation Law"),
                new Teacher("Karen", "Clarke", "4487 Nickel Road", "Alhambra", "91801", "United States", 10L, "Research Associate in the Economics"));
    }
}
