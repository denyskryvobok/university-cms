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
                .mvcMatchers("/admin/**",
                        "/timetable/add", "/timetable/delete", "/timetable/update", "/timetable/manager",
                        "/subjects/manager", "/subjects/add", "/subjects/delete", "/subjects/update",
                        "groups/manager", "groups/add", "/groups/delete", "/groups/update", "/groups/students",
                        "/groups/students/delete", "groups/students/add").hasRole("ADMIN")
                .mvcMatchers("/timetable/teacherMonth",
                                      "/timetable/teacherDate",
                                      "/teachers/subjects").hasRole("TEACHER")
                .mvcMatchers("/students/studentProfile").hasRole("STUDENT")
                .mvcMatchers("/", "/login", "/webjars/**", "/register/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/profile")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .build();
    }
}
