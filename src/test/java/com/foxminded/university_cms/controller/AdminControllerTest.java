package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.registration.form.RolesForm;
import com.foxminded.university_cms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AdminControllerTest extends SpringSecurityConfig {
    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = {"STUDENT", "TEACHER"})
    void adminUrl_shouldReturnStatusClientError_whenUserNotHaveADMINRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAdminProfile_shouldReturnStatusCode200_whenUserHasRoleAdmin() throws Exception {
        when(userService.getAllUsersWithRoles()).thenReturn(
                Map.of("jamessmith", List.of("ROLE_TEACHER"),
                        "olivertaylor", List.of("ROLE_STUDENT"))
        );
        mockMvc.perform(get("/admin/adminProfile"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminProfile"))
                .andExpect(model().attribute("allUsersWithRoles",
                        Map.of("jamessmith", List.of("ROLE_TEACHER"),
                                "olivertaylor", List.of("ROLE_STUDENT"))));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateRoles_shouldReturnStatus300() throws Exception {
        when(userService.updateRoles("username", new RolesForm())).thenReturn(true);

        mockMvc.perform(get("/admin/updateRoles")
                        .param("username", "username"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile?change=true"));
    }
}
