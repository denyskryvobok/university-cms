package com.foxminded.university_cms.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AuthenticationIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void formLogin_shouldNotAuthenticateUser_whenCredentialsAreWrong() throws Exception {
        mockMvc.perform(formLogin().user("wrongUser").password("wrongUser"))
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    void formLogin_shouldAuthenticateUser_whenCredentialsAreCorrect() throws Exception {
        mockMvc.perform(formLogin().user("olivertaylor").password("password"))
                .andExpect(authenticated())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/profile"));
    }
}
