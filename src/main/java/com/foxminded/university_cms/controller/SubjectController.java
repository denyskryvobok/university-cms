package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;


    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String showSubject(Model model) {
        log.info("ShowSubjects start");
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }
}
