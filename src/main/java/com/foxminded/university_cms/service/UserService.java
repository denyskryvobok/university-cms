package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.dto.RolesDTO;
import com.foxminded.university_cms.dto.StudentRegistrationDTO;
import com.foxminded.university_cms.dto.TeacherRegistrationDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUserName(String username);

    void saveUserAsStudent(StudentRegistrationDTO studentRegistrationDTO);

    void saveUserAsTeacher(TeacherRegistrationDTO teacherRegisterForm);

    Map<User, List<String>> getAllUsersWithRoles();

    boolean updateRoles(String userName, RolesDTO rolesDTO);

    Person getPersonByUsername(String username);
}
