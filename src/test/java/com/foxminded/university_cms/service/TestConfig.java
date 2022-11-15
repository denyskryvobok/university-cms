package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.CalendarDAO;
import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.dao.RoleDAO;
import com.foxminded.university_cms.dao.StudentDAO;
import com.foxminded.university_cms.dao.SubjectDAO;
import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.dao.TimetableDAO;
import com.foxminded.university_cms.dao.UserDAO;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class TestConfig {

    @Bean
    public TimetableService timetableService(TimetableDAO timetableDAO,
                                             GroupDAO groupDAO,
                                             CalendarDAO calendarDAO,
                                             SubjectDAO subjectDAO,
                                             TeacherDAO teacherDAO,
                                             DateParser dateParser) {
        return new TimetableServiceImp(timetableDAO, dateParser, groupDAO, calendarDAO, subjectDAO, teacherDAO);
    }

    @Bean
    public SubjectService subjectService(SubjectDAO subjectDAO) {
        return new SubjectServiceImp(subjectDAO);
    }

    @Bean
    public CalendarService calendarService(CalendarDAO calendarDAO,
                                    DateParser dateParser) {
        return new CalendarServiceImp(calendarDAO, dateParser);
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
    public DateParser dateParser() {
        return new DateParser();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
