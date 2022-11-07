package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(Mappings.PROFILE_PAGE)
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profileManager(Authentication authentication) {
        log.info("ProfileManager start");
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            log.info("User redirected to admin profile");
            return "redirect:/admin/adminProfile";
        } else {
            log.info("User redirected to user profile");
            return "redirect:/profile/userProfile";
        }
    }

    @GetMapping(Mappings.USER_PROFILE)
    public String getUserProfile(Authentication authentication, Model model) {
        log.info("GetStudentProfile started");
        User currentUser = (User) authentication.getPrincipal();
        Person user = userService.getPersonByUsername(currentUser.getUsername());
        if (user instanceof Student) {
            log.info("User get a student profile");
            model.addAttribute("student", user);
            return "studentProfile";
        } else {
            log.info("User get a teacher profile");
            model.addAttribute("teacher", user);
            return "teacherProfile";
        }
    }
}
