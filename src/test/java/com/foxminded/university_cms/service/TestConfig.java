package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class TestConfig {

    @Bean
    public TimetableService timetableService(TimetableDAO timetableDAO) {
        return new TimetableServiceImp(timetableDAO);
    }

    @Bean
    public UserService userService(UserDAO userDAO,
                                   RoleDAO roleDAO,
                                   StudentDAO studentDAO,
                                   TeacherDAO teacherDAO,
                                   PasswordEncoder passwordEncoder) {
        return new UserServiceImp(userDAO, roleDAO, studentDAO, teacherDAO, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
