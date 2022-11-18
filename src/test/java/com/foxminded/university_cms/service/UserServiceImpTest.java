package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dto.RolesDTO;
import com.foxminded.university_cms.dto.StudentRegistrationDTO;
import com.foxminded.university_cms.dto.TeacherRegistrationDTO;
import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.security.User;
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
        var studentRegistrationForm = new StudentRegistrationDTO("maryjones",
                "password",
                "Mary",
                "Jones",
                "1349 Lords Way",
                "Memphis",
                "38110",
                "United States",
                "7641097");
        userService.saveUserAsStudent(studentRegistrationForm);
        User user = entityManager.find(User.class, 15L);

        String expected = "maryjones";
        String actual = user.getUserName();

        assertEquals(expected, actual);
    }

    @Test
    void saveUserAsTeacher_shouldSaveUser() {
        var teacherRegistrationForm = new TeacherRegistrationDTO("maryjones123",
                "password",
                "Mary",
                "Jones",
                "1349 Lords Way",
                "Memphis",
                "38110",
                "United States",
                "teacher");
        userService.saveUserAsTeacher(teacherRegistrationForm);
        User user = entityManager.find(User.class, 15L);

        String expected = "maryjones123";
        String actual = user.getUserName();

        assertEquals(expected, actual);
    }

    @Test
    void getAllUsersWithRoles_shouldReturnMapUsernameToUserRoles() {

        Map<User, List<String>> expected = new LinkedHashMap<>();

        User u1 = new User();
        u1.setUserId(1L);
        User u2 = new User();
        u2.setUserId(2L);
        User u3 = new User();
        u3.setUserId(3L);
        User u4 = new User();
        u4.setUserId(4L);
        User u5 = new User();
        u5.setUserId(5L);
        User u6 = new User();
        u6.setUserId(6L);
        User u7 = new User();
        u7.setUserId(7L);
        User u8 = new User();
        u8.setUserId(8L);
        User u9 = new User();
        u9.setUserId(9L);
        User u10 = new User();
        u10.setUserId(10L);
        User u11 = new User();
        u11.setUserId(11L);
        User u12 = new User();
        u12.setUserId(12L);
        User u13 = new User();
        u13.setUserId(13L);
        User u14 = new User();
        u14.setUserId(14L);

        expected.put(u1, List.of("ROLE_STUDENT"));
        expected.put(u2, List.of("ROLE_ADMIN", "ROLE_TEACHER"));
        expected.put(u3, List.of("ROLE_TEACHER"));
        expected.put(u4, List.of("ROLE_TEACHER"));
        expected.put(u5, List.of("ROLE_TEACHER"));
        expected.put(u6, List.of("ROLE_TEACHER"));
        expected.put(u7, List.of("ROLE_TEACHER"));
        expected.put(u8, List.of("ROLE_TEACHER"));
        expected.put(u9, List.of("ROLE_TEACHER"));
        expected.put(u10, List.of("ROLE_TEACHER"));
        expected.put(u11, List.of("ROLE_TEACHER"));
        expected.put(u12, List.of("ROLE_STUDENT"));
        expected.put(u13, List.of("ROLE_STUDENT"));
        expected.put(u14, List.of("ROLE_STUDENT"));

        Map<User, List<String>> actual = userService.getAllUsersWithRoles();

        assertEquals(expected, actual);
    }

    @Test
    void updateRoles_shouldReturnFalse_whenInputRolesAreTheSameAsUserRoles() {
        String username = "jamessmith";
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setRoles(Set.of("ROLE_STUDENT"));

        boolean actual = userService.updateRoles(username, rolesDTO);

        assertFalse(actual);
    }

    @Test
    void updateRoles_shouldReturnTrue_whenInputRolesAreEmptyAndUserHasSomeRoles() {
        String username = "jamessmith";
        RolesDTO rolesDTO = new RolesDTO();

        boolean actual = userService.updateRoles(username, rolesDTO);
        User user = entityManager.find(User.class, 1L);

        assertTrue(user.getUserRoles().isEmpty());
        assertTrue(actual);
    }

    @Test
    void updateRoles_shouldReturnTrue_whenInputRolesAreDifferentFromUserRoles() {
        Set<String> expectedRoles = Set.of("ROLE_ADMIN", "ROLE_TEACHER");

        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setRoles(expectedRoles);

        String username = "jamessmith";
        boolean actual = userService.updateRoles(username, rolesDTO);
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
