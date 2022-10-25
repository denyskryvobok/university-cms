package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/month")
    public String getTimetablesForMonth(@RequestParam(name = "id") Long id,
                                        @RequestParam(name = "month") Month month,
                                        Model model) {
        log.info("GetTimetablesForMonth start with id:{}, month:{}", id, month);
        Map<LocalDate, List<Timetable>> dateToTimetables = groupService.getTimetablesForMonth(id, month);
        model.addAttribute("dateToTimetables", dateToTimetables);
        return "timetablesForMoth";
    }

    @GetMapping("/date")
    public String getTimetablesForOneDay(@RequestParam Long id,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         Model model) {
        log.info("GetTimetablesForOneDay start with id:{}, month:{}", id, date);
        List<Timetable> timetableForOneDay = groupService.getTimetableForOneDay(id, date);
        model.addAttribute("timetableForOneDay", timetableForOneDay);
        return "timetablesForDay";
    }
}
