package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping(Mappings.SUBJECTS_PAGE)
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GroupService groupService;

    @GetMapping
    public String showSubject(Model model) {
        log.info("ShowSubjects start");
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @GetMapping(Mappings.SUBJECTS_MANAGER)
    public String subjectsManager(Model model) {
        log.info("SubjectManager start");
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("groups", groupService.getAllGroups());
        return "subjectsManager";
    }

    @PostMapping(Mappings.SUBJECTS_ADD)
    public String addSubject(@RequestParam String subjectName, RedirectAttributes redirectAttributes) {
        log.info("AddSubject start with subject name:{}", subjectName);
        boolean subjectIsAdd = subjectService.addSubject(subjectName);
        if (subjectIsAdd) {
            log.info("Subject was added");
            redirectAttributes.addAttribute("successAdd", subjectName);
        } else {
            log.info("Subject was not added");
            redirectAttributes.addAttribute("failAdd", subjectName);
        }
        return "redirect:/subjects/manager";
    }

    @DeleteMapping(Mappings.SUBJECTS_DELETE)
    public String deleteSubject(@RequestParam Long subjectId, RedirectAttributes redirectAttributes) {
        log.info("DeleteSubject start with subjectId:{}", subjectId);
        subjectService.deleteSubject(subjectId);
        redirectAttributes.addAttribute("successDelete", true);
        return "redirect:/subjects/manager";
    }

    @PatchMapping(Mappings.SUBJECTS_UPDATE)
    public String updateSubject(@RequestParam Long subjectId,
                                @RequestParam String subjectName,
                                RedirectAttributes redirectAttributes) {
        log.info("UpdateSubject start with subjectId:{}, subject name:{}", subjectId, subjectName);
        boolean updateStatus = subjectService.updateSubject(subjectId, subjectName);
        if (updateStatus) {
            redirectAttributes.addAttribute("successUpdate", true);
        } else {
            redirectAttributes.addAttribute("failUpdate", true);
        }
        return "redirect:/subjects/manager";
    }
}
