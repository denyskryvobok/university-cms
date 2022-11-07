package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(Mappings.HOME_PAGE)
public class HomePageController {

    @GetMapping
    public String showHomePage(Authentication authentication, Model model) {
        log.info("Home page start");
        model.addAttribute("user", authentication);
        return "home";
    }
}
