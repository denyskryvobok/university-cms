package com.foxminded.university_cms.integration;

import com.foxminded.university_cms.entity.security.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@Transactional
class AdminControllerIntegrationTest extends IntegrationTestcontainersInitializer {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("jamessmith")
    void adminUrl_shouldReturnStatusClientError_whenUserNotHaveADMINRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("olivertaylor")
    void getAdminProfile_shouldReturnStatusCode200_whenUserHasRoleAdmin() throws Exception {
        Map<User, List<String>> expected = new LinkedHashMap<>();
        User u1 = new User();
        u1.setUserId(1L);
        User u2 = new User();
        u2.setUserId(2L);
        User u3 = new User();
        u3.setUserId(3L);
        User u4 = new User();
        u4.setUserId(4L);
        User u5 = new User();
        u5.setUserId(5L);
        User u6 = new User();
        u6.setUserId(6L);
        User u7 = new User();
        u7.setUserId(7L);
        User u8 = new User();
        u8.setUserId(8L);
        User u9 = new User();
        u9.setUserId(9L);
        User u10 = new User();
        u10.setUserId(10L);
        User u11 = new User();
        u11.setUserId(11L);
        User u12 = new User();
        u12.setUserId(12L);
        User u13 = new User();
        u13.setUserId(13L);
        User u14 = new User();
        u14.setUserId(14L);

        expected.put(u1, List.of("ROLE_STUDENT"));
        expected.put(u2, List.of("ROLE_ADMIN", "ROLE_TEACHER"));
        expected.put(u3, List.of("ROLE_TEACHER"));
        expected.put(u4, List.of("ROLE_TEACHER"));
        expected.put(u5, List.of("ROLE_TEACHER"));
        expected.put(u6, List.of("ROLE_TEACHER"));
        expected.put(u7, List.of("ROLE_TEACHER"));
        expected.put(u8, List.of("ROLE_TEACHER"));
        expected.put(u9, List.of("ROLE_TEACHER"));
        expected.put(u10, List.of("ROLE_TEACHER"));
        expected.put(u11, List.of("ROLE_TEACHER"));
        expected.put(u12, List.of("ROLE_STUDENT"));
        expected.put(u13, List.of("ROLE_STUDENT"));
        expected.put(u14, List.of("ROLE_STUDENT"));
        mockMvc.perform(get("/admin/adminProfile"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminProfile"))
                .andExpect(model().attribute("allUsersWithRoles", expected));
    }

    @Test
    @WithUserDetails("olivertaylor")
    void updateRoles_shouldReturnStatus300() throws Exception {
        mockMvc.perform(put("/admin/updateRoles")
                        .param("username", "jamessmith")
                        .param("roles", "ROLE_TEACHER", "ROLE_STUDENT").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile?change=true"));
    }
}
