package com.foxminded.university_cms.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@ActiveProfiles("test")
public abstract class SpringSecurityConfig {
    @Autowired
    private WebApplicationContext webApplicationContext;

    protected static MockMvc mockMvc;

    @BeforeEach
    public void applySecurity() {
        mockMvc = webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }
}
