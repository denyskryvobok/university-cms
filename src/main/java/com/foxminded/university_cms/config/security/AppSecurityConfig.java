package com.foxminded.university_cms.config.security;

import com.foxminded.university_cms.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.foxminded.university_cms.config.security.roles.AdminRole.ADD_TIMETABLE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.ADMIN_RESOURCES;
import static com.foxminded.university_cms.config.security.roles.AdminRole.DELETE_TIMETABLE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_ADD;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_DELETE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_MANAGER;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_STUDENTS_ADD;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_STUDENTS_DELETE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUPS_UPDATE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.GROUP_STUDENTS;
import static com.foxminded.university_cms.config.security.roles.AdminRole.SUBJECTS_ADD;
import static com.foxminded.university_cms.config.security.roles.AdminRole.SUBJECTS_DELETE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.SUBJECTS_MANAGER;
import static com.foxminded.university_cms.config.security.roles.AdminRole.SUBJECTS_UPDATE;
import static com.foxminded.university_cms.config.security.roles.AdminRole.TIMETABLE_MANAGER;
import static com.foxminded.university_cms.config.security.roles.AdminRole.UPDATE_TIMETABLE;
import static com.foxminded.university_cms.config.security.roles.StudentRole.STUDENT_PROFILE;
import static com.foxminded.university_cms.config.security.roles.TeacherRole.TEACHER_SUBJECTS;
import static com.foxminded.university_cms.config.security.roles.TeacherRole.TEACHER_TIMETABLE_FOR_DAY;
import static com.foxminded.university_cms.config.security.roles.TeacherRole.TEACHER_TIMETABLE_FOR_MONTH;

@Configuration
public class AppSecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager configure(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                    .mvcMatchers(
                            ADMIN_RESOURCES,
                            ADD_TIMETABLE, DELETE_TIMETABLE, UPDATE_TIMETABLE, TIMETABLE_MANAGER,
                            SUBJECTS_MANAGER, SUBJECTS_ADD, SUBJECTS_DELETE, SUBJECTS_UPDATE,
                            GROUPS_MANAGER, GROUPS_ADD, GROUPS_DELETE, GROUPS_UPDATE, GROUP_STUDENTS,
                            GROUPS_STUDENTS_DELETE, GROUPS_STUDENTS_ADD).hasRole("ADMIN")
                    .mvcMatchers(
                            TEACHER_TIMETABLE_FOR_MONTH,
                            TEACHER_TIMETABLE_FOR_DAY,
                            TEACHER_SUBJECTS).hasRole("TEACHER")
                    .mvcMatchers(STUDENT_PROFILE).hasRole("STUDENT")
                    .mvcMatchers("/", "/login", "/webjars/**", "/register/**", "/subjects").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/profile")
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                .and()
                    .exceptionHandling()
                        .accessDeniedPage("/accessDenied")
                .and()
                    .build();
    }
}
