package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String showGroups(Model model) {
        log.info("ShowGroups start");
        model.addAttribute("groups", groupService.getAllGroupsWithStudents());
        return "groups";
    }
}
