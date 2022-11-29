package com.foxminded.university_cms.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class TestConfig {

    @Bean
    public TimetableService timetableService() {
        return new TimetableServiceImp();
    }

    @Bean
    public SubjectService subjectService() {
        return new SubjectServiceImp();
    }

    @Bean
    public CalendarService calendarService() {
        return new CalendarServiceImp();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImp();
    }

    @Bean
    public GroupService groupService() {
        return new GroupServiceImp();
    }

    @Bean
    public StudentService studentService() {
        return new StudentServiceImp();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
