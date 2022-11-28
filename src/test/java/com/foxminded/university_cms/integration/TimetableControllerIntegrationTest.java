package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.dto.TimetableDTO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class TimetableControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getGroupTimetableForMonth_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/timetable/groupMonth"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getGroupTimetableForOneDay_shouldReturnStatus302RedirectionAndRedirectToLoginPage_whenUserUnauthorized() throws Exception {
        mockMvc.perform(get("/timetable/groupDate"))
                .andExpect(redirectedUrl("http://localhost/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("jamessmith")
    void getGroupTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200_whenUserIsAuthenticated() throws Exception {
        Map<LocalDate, List<Timetable>> expected = getGroupDateToTimetableMapForMonth();

        mockMvc.perform(get("/timetable/groupMonth")
                        .param("id", "1")
                        .param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", expected));
    }

    @Test
    @WithUserDetails("jamessmith")
    void getGroupTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200_whenUserIsAuthenticated() throws Exception {
        List<Timetable> expected = getGroupTimetablesForDay();

        mockMvc.perform(get("/timetable/groupDate")
                        .param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", expected));
    }

    @Test
    @WithUserDetails("jackdavies")
    void getTeacherTimetableForMonth_shouldReturnViewTimetablesForMothWithModelAttributeAndStatus200_whenUserHasRoleTeacher() throws Exception {
        Map<LocalDate, List<Timetable>> expected = getTeacherDateToTimetableMapForMonth();

        mockMvc.perform(get("/timetable/teacherMonth")
                        .param("id", "1").param("month", "2022-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForMoth"))
                .andExpect(model().attribute("dateToTimetables", expected));
    }

    @Test
    @WithUserDetails("jackdavies")
    void getTeacherTimetableForOneDay_shouldReturnViewTimetablesForDayWithModelAttributeAndStatus200_whenUserHasRoleTeacher() throws Exception {
        List<Timetable> list = getTeacherTimetablesForOneDay();

        mockMvc.perform(get("/timetable/teacherDate")
                        .param("id", "1")
                        .param("date", "2022-10-03"))
                .andExpect(status().isOk())
                .andExpect(view().name("timetablesForDay"))
                .andExpect(model().attribute("timetableForOneDay", list));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void showTimetableManager_shouldReturnStatus200_whenUserHasRoleAdminAndInputHasGroupIdAndYearMonthParam() throws Exception {
        List<Subject> subjects = getAllSubjects();
        List<Teacher> teachers = getAllTeachers();

        Map<Calendar, List<Timetable>> calendarToTimetables = getCalendarToTimetableMap();
        mockMvc.perform(get("/timetable/manager")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10"))
                .andExpect(view().name("timetableManager"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("calendarToTimetables", calendarToTimetables))
                .andExpect(model().attribute("month", "October"))
                .andExpect(model().attribute("group", new Group(1L, "group")))
                .andExpect(model().attribute("subjects", subjects))
                .andExpect(model().attribute("teachers", teachers))
                .andExpect(model().attribute("yearMonth", "2022-10"))
                .andExpect(model().attribute("timetableDTO", new TimetableDTO()));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void deleteTimetable_shouldReturnStatus300_whenUserHasRoleAdminAndInputHasRequiredParams() throws Exception {
        mockMvc.perform(delete("/timetable/delete")
                        .param("timetableId", "1")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?groupId=1&yearMonth=2022-10&date=2022-10-03&deleteSuccess=true"));
        Timetable timetable = entityManager.find(Timetable.class, 1L);
        assertNull(timetable);
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateTimetable_shouldReturnStatus300AndSetAttributeUpdateSuccess_whenUpdateTimetableReturnTrue() throws Exception {
        Long subjectId = 2L;

        mockMvc.perform(patch("/timetable/update").param("timetableId", "1")
                        .param("teacherId", "1").param("subjectId", "2")
                        .param("subjectOrder", "1")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?updateSuccess=true&groupId=1&yearMonth=2022-10&date=2022-10-03"));
        Timetable timetable = entityManager.find(Timetable.class, 1L);
        assertEquals(subjectId, timetable.getSubject().getSubjectId());
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateTimetable_shouldReturnStatus300AndSetAttributeUpdateFail_whenUpdateTimetableReturnFalse() throws Exception {
        mockMvc.perform(patch("/timetable/update")
                .param("timetableId", "1")
                .param("teacherId", "1")
                .param("subjectId", "1")
                .param("subjectOrder", "1")
                .param("groupId", "1")
                .param("yearMonth", "2022-10")
                .param("date", "2022-10-03").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?updateFail=true&groupId=1&yearMonth=2022-10&date=2022-10-03"));
        Timetable timetable = entityManager.find(Timetable.class, 1L);
        assertAll(() -> assertEquals(1, timetable.getSubjectOrder()),
                () -> assertEquals(1L, timetable.getTeacher().getTeacherId()),
                () -> assertEquals(1L, timetable.getGroup().getGroupId()),
                () -> assertEquals(1L, timetable.getSubject().getSubjectId()));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void addTimetable_shouldReturnStatus300_whenUserGasRoleAdminAllRequiredParametersExist() throws Exception {
        mockMvc.perform(post("/timetable/add")
                        .param("teacherId", "1")
                        .param("subjectId", "1")
                        .param("subjectOrder", "7")
                        .param("calendarId", "1")
                        .param("groupId", "1")
                        .param("yearMonth", "2022-10")
                        .param("date", "2022-10-03")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/timetable/manager?groupId=1&yearMonth=2022-10&date=2022-10-03&addSuccess=true"));
        Timetable timetable = entityManager.find(Timetable.class, 12L);
        assertNotNull(timetable);
    }

    private Map<LocalDate, List<Timetable>> getGroupDateToTimetableMapForMonth() {
        Timetable t1 = new Timetable(1L, 1);

        Timetable t2 = new Timetable(2L, 2);

        Timetable t3 = new Timetable(3L, 3);

        Timetable t4 = new Timetable(4L, 3);

        Timetable t5 = new Timetable(5L, 4);

        Timetable t6 = new Timetable(6L, 1);

        Timetable t7 = new Timetable(7L, 2);

        Timetable t8 = new Timetable(8L, 3);

        Timetable t9 = new Timetable(9L, 4);

        Timetable t10 = new Timetable(10L, 5);

        Timetable t11 = new Timetable(11L, 6);

        Map<LocalDate, List<Timetable>> expected = new LinkedHashMap<>();

        expected.put(LocalDate.parse("2022-10-03"), List.of(t1, t2, t3, t4, t5));
        expected.put(LocalDate.parse("2022-10-04"), List.of(t6, t7, t8, t9, t10, t11));

        return expected;
    }

    private List<Timetable> getGroupTimetablesForDay() {
        Timetable t1 = new Timetable(1L, 1);

        Timetable t2 = new Timetable(2L, 2);

        Timetable t3 = new Timetable(3L, 3);

        Timetable t4 = new Timetable(4L, 3);

        Timetable t5 = new Timetable(5L, 4);

        return List.of(t1, t2, t3, t4, t5);
    }

    private Map<LocalDate, List<Timetable>> getTeacherDateToTimetableMapForMonth() {
        Timetable first = new Timetable(1L, 1);
        Timetable second = new Timetable(3L, 2);
        Timetable third = new Timetable(10L, 3);
        return Map.of(LocalDate.parse("2022-10-03"), List.of(first, second),
                      LocalDate.parse("2022-10-04"), List.of(third));
    }

    private List<Timetable> getTeacherTimetablesForOneDay() {
        Timetable first = new Timetable(1L, 1);
        Timetable second = new Timetable(3L, 2);
        return List.of(first, second);
    }

    private Map<Calendar, List<Timetable>> getCalendarToTimetableMap() {
        Timetable t1 = new Timetable(1L, 1);

        Timetable t2 = new Timetable(2L, 2);

        Timetable t3 = new Timetable(3L, 3);

        Timetable t4 = new Timetable(4L, 3);

        Timetable t5 = new Timetable(5L, 4);

        Timetable t6 = new Timetable(6L, 1);

        Timetable t7 = new Timetable(7L, 2);

        Timetable t8 = new Timetable(8L, 3);

        Timetable t9 = new Timetable(9L, 4);

        Timetable t10 = new Timetable(10L, 5);

        Timetable t11 = new Timetable(11L, 6);

        Map<Calendar, List<Timetable>> calendarToTimetables = new LinkedHashMap<>();

        calendarToTimetables.put(new Calendar(1L, LocalDate.parse("2022-10-03")),
                                 List.of(t1, t2, t3, t4, t5));
        calendarToTimetables.put(new Calendar(2L, LocalDate.parse("2022-10-04")),
                                 List.of(t6, t7, t8, t9, t10, t11));

        return calendarToTimetables;
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

    private List<Subject> getAllSubjects() {
        return List.of(
                new Subject(1L, "Accounting and Finance"),
                new Subject(2L, "Computer Science"),
                new Subject(3L, "Architecture"),
                new Subject(4L, "Chemistry"),
                new Subject(5L, "English"),
                new Subject(6L, "Art"),
                new Subject(7L, "Psychology"),
                new Subject(8L, "History"),
                new Subject(9L, "Law"),
                new Subject(10L, "Economics"));
    }
}
