package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(Mappings.TEACHER_PAGE)
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String showTeachers(Model model) {
        log.info("ShowTeachers start");
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers";
    }
}
