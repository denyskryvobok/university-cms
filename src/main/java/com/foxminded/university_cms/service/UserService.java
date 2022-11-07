package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.registration.form.RolesForm;
import com.foxminded.university_cms.registration.form.StudentRegistrationForm;
import com.foxminded.university_cms.registration.form.TeacherRegistrationForm;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUserName(String username);

    void saveUserAsStudent(StudentRegistrationForm studentRegistrationForm);

    void saveUserAsTeacher(TeacherRegistrationForm teacherRegisterForm);

    Map<String, List<String>> getAllUsersWithRoles();

    boolean updateRoles(String userName, RolesForm rolesForm);

    Person getPersonByUsername(String username);
}
