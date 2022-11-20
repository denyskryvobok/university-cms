package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.dto.RolesDTO;
import com.foxminded.university_cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(Mappings.ADMIN_PAGE)
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(Mappings.ADMIN_PROFILE)
    public String getAdminProfile(Model model) {
        log.info("GetAdminAccount start");
        Map<User, List<String>> allUsersWithRoles = userService.getAllUsersWithRoles();
        model.addAttribute("allUsersWithRoles", allUsersWithRoles);
        model.addAttribute("rolesDTO", new RolesDTO());
        return "adminProfile";
    }

    @PutMapping(Mappings.ADMIN_UPDATE_ROLES)
    public String updateRoles(@RequestParam String username, RolesDTO rolesDTO, RedirectAttributes redirectAttributes) {
        log.info("UpdateRoles start with username:{}, input roles:{}", username, rolesDTO);
        if (!userService.updateRoles(username, rolesDTO)) {
            log.info("No changes has been made to the user");
            redirectAttributes.addAttribute("notChange", true);
        } else {
            log.info("User roles have been changed");
            redirectAttributes.addAttribute("change", true);
        }
        return "redirect:/admin/adminProfile";
    }
}
