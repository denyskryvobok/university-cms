package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String showTeachers(Model model) {
        log.info("ShowTeachers start");
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers";
    }
}
