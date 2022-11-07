package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.registration.form.RolesForm;
import com.foxminded.university_cms.registration.form.StudentRegistrationForm;
import com.foxminded.university_cms.registration.form.TeacherRegistrationForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceImpTest extends TestcontainersInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Test
    void getUserByUserName_shouldReturnOptionalUser() {
        Optional<User> user = userService.getUserByUserName("jamessmith");
        User actual = user.orElseThrow();
        User expected = new User("jamessmith", "$2a$10$k82frkwHnma39AQH9WEOUuY1l102RFjI/CP8xHZdQZPv9BF0QAPfu");
        expected.setUserId(1L);

        assertEquals(expected, actual);
    }

    @Test
    void saveUserAsStudent_shouldSaveUser() {
        var studentRegistrationForm = new StudentRegistrationForm("maryjones",
                "password",
                "Mary",
                "Jones",
                "1349 Lords Way",
                "Memphis",
                "38110",
                "United States",
                "7641097");
        userService.saveUserAsStudent(studentRegistrationForm);
        User user = entityManager.find(User.class, 12L);

        String expected = "maryjones";
        String actual = user.getUserName();

        assertEquals(expected, actual);
    }

    @Test
    void saveUserAsTeacher_shouldSaveUser() {
        var teacherRegistrationForm = new TeacherRegistrationForm("maryjones123",
                "password",
                "Mary",
                "Jones",
                "1349 Lords Way",
                "Memphis",
                "38110",
                "United States",
                "teacher");
        userService.saveUserAsTeacher(teacherRegistrationForm);
        User user = entityManager.find(User.class, 12L);

        String expected = "maryjones123";
        String actual = user.getUserName();

        assertEquals(expected, actual);
    }

    @Test
    void getAllUsersWithRoles_shouldReturnMapUsernameToUserRoles() {

        Map<String, List<String>> expected = new LinkedHashMap<>();
        expected.put("jamessmith", List.of("ROLE_STUDENT"));
        expected.put("olivertaylor", List.of("ROLE_ADMIN", "ROLE_TEACHER"));
        expected.put("jackdavies", List.of("ROLE_TEACHER"));
        expected.put("harryevans", List.of("ROLE_TEACHER"));
        expected.put("thomasdavis", List.of("ROLE_TEACHER"));
        expected.put("georgeroberts", List.of("ROLE_TEACHER"));
        expected.put("jessicaroberts", List.of("ROLE_TEACHER"));
        expected.put("greenthomas", List.of("ROLE_TEACHER"));
        expected.put("sarahhall", List.of("ROLE_TEACHER"));
        expected.put("charlesthomas", List.of("ROLE_TEACHER"));
        expected.put("karenclarke", List.of("ROLE_TEACHER"));

        Map<String, List<String>> actual = userService.getAllUsersWithRoles();

        assertEquals(expected, actual);
    }

    @Test
    void updateRoles_shouldReturnFalse_whenInputRolesAreTheSameAsUserRoles() {
        String username = "jamessmith";
        RolesForm rolesForm = new RolesForm();
        rolesForm.setRoles(Set.of("ROLE_STUDENT"));

        boolean actual = userService.updateRoles(username, rolesForm);

        assertFalse(actual);
    }

    @Test
    void updateRoles_shouldReturnTrue_whenInputRolesAreEmptyAndUserHasSomeRoles() {
        String username = "jamessmith";
        RolesForm rolesForm = new RolesForm();

        boolean actual = userService.updateRoles(username, rolesForm);
        User user = entityManager.find(User.class, 1L);

        assertTrue(user.getUserRoles().isEmpty());
        assertTrue(actual);
    }

    @Test
    void updateRoles_shouldReturnTrue_whenInputRolesAreDifferentFromUserRoles() {
        Set<String> expectedRoles = Set.of("ROLE_ADMIN", "ROLE_TEACHER");

        RolesForm rolesForm = new RolesForm();
        rolesForm.setRoles(expectedRoles);

        String username = "jamessmith";
        boolean actual = userService.updateRoles(username, rolesForm);
        Set<String> actualRoles = entityManager.find(User.class, 1L).getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(toSet());

        assertTrue(actualRoles.containsAll(expectedRoles));
        assertTrue(actual);
    }

    @Test
    void getPersonByUsername_shouldReturnStudent_whenInputUsernameBelongsToStudent() {
        Person actual = userService.getPersonByUsername("jamessmith");
        assertTrue(actual instanceof Student);
    }

    @Test
    void getPersonByUsername_shouldReturnTeacher_whenInputUsernameBelongsToTeacher() {
        Person actual = userService.getPersonByUsername("olivertaylor");
        assertTrue(actual instanceof Teacher);
    }
}
