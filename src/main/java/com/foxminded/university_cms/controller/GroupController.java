package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping(Mappings.GROUPS_PAGE)
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showGroups(Model model) {
        log.info("ShowGroups start");
        model.addAttribute("groups", groupService.getAllGroupsWithStudents());
        return "groups";
    }

    @GetMapping(Mappings.GROUP_MANAGER)
    public String groupManager(Model model) {
        log.info("GroupManager start");
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groupManager";
    }

    @PostMapping(Mappings.GROUP_ADD)
    public String addGroup(@RequestParam String groupName,
                           RedirectAttributes redirectAttributes) {
        log.info("AddGroup start");
        groupService.addGroup(groupName);
        redirectAttributes.addAttribute("successAdd", true);
        return "redirect:/groups/manager";
    }

    @PatchMapping(Mappings.GROUP_UPDATE)
    public String updateGroup(@RequestParam String groupName,
                              @RequestParam Long groupId,
                              RedirectAttributes redirectAttributes) {
        log.info("UpdateGroup start");
        boolean updateStatus = groupService.updateGroup(groupId, groupName);
        if (updateStatus) {
            log.info("Success Update");
            redirectAttributes.addAttribute("successUpdate", true);
        } else {
            log.info("Fail Update");
            redirectAttributes.addAttribute("failUpdate", true);
        }
        return "redirect:/groups/manager";
    }

    @DeleteMapping(Mappings.GROUP_DELETE)
    public String deleteGroup(@RequestParam Long groupId,
                              RedirectAttributes redirectAttributes) {
        log.info("DeleteGroup start");
        groupService.deleteGroup(groupId);
        redirectAttributes.addAttribute("successDelete", true);
        return "redirect:/groups/manager";
    }

    @GetMapping(Mappings.GROUP_STUDENTS)
    public String showStudentsOfGroup(@RequestParam Long groupId, Model model) {
        log.info("ShowStudentsOfGroup start");
        Group group = groupService.getGroupWithStudents(groupId);
        Set<Student> students = studentService.getStudentsWhoAreNotInGroupByGroupId(groupId);
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "selectStudentsForGroup";
    }

    @PatchMapping(Mappings.GROUP_STUDENTS_ADD)
    public String addStudentToGroup(@RequestParam Long studentId,
                                    @RequestParam Long groupId,
                                    RedirectAttributes redirectAttributes) {
        log.info("AddStudentToGroup start");
        groupService.addStudentToGroup(studentId, groupId);
        redirectAttributes.addAttribute("successAdd", true);
        redirectAttributes.addAttribute("groupId", groupId);
        return "redirect:/groups/students";
    }

    @DeleteMapping(Mappings.GROUP_STUDENTS_DELETE)
    public String deleteStudentFromGroup(@RequestParam Long studentId,
                                         @RequestParam Long groupId,
                                         RedirectAttributes redirectAttributes) {
        log.info("DeleteStudentFromGroup start");
        groupService.deleteStudentFromGroup(studentId, groupId);
        redirectAttributes.addAttribute("successDelete", true);
        redirectAttributes.addAttribute("groupId", groupId);
        return "redirect:/groups/students";
    }
}
