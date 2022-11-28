package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.dto.RolesDTO;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = {"STUDENT", "TEACHER"})
    void adminUrl_shouldReturnStatusClientError_whenUserNotHaveAdminRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAdminProfile_shouldReturnStatusCode200_whenUserHasRoleAdmin() throws Exception {
        Map<User, List<String>> userListMap = getUserToRoleList();

        when(userService.getAllUsersWithRoles()).thenReturn(userListMap);

        mockMvc.perform(get("/admin/adminProfile"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminProfile"))
                .andExpect(model().attribute("allUsersWithRoles", userListMap));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateRoles_shouldReturnStatus300_whenInputRolesDifferentFromUserCurrentRoles() throws Exception {
        when(userService.updateRoles(anyString(), any(RolesDTO.class))).thenReturn(true);

        mockMvc.perform(put("/admin/updateRoles")
                        .param("username", "username")
                        .param("roles", "ROLE_TEACHER", "ROLE_STUDENT").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile?change=true"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateRoles_shouldReturnStatus300AndParamNotChange_whenInputRoleSameAdUserCurrentRoles() throws Exception {
        when(userService.updateRoles(anyString(), any(RolesDTO.class))).thenReturn(false);

        mockMvc.perform(put("/admin/updateRoles")
                        .param("username", "username")
                        .param("roles", "ROLE_STUDENT").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/adminProfile?notChange=true"));
    }

    private Map<User, List<String>> getUserToRoleList() {
        User u1 = new User();
        u1.setUserId(1L);

        User u2 = new User();
        u1.setUserId(2L);

        return Map.of(
                u1, List.of("ROLE_TEACHER"),
                u2, List.of("ROLE_STUDENT")
        );
    }
}
