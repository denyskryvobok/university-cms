package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(Mappings.TIMETABLE_PAGE)
public class TimetableController {
    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping(Mappings.GROUP_TIMETABLE_FOR_MONTH)
    public String getGroupTimetableForMonth(@RequestParam(name = "id") Long id,
                                            @RequestParam(name = "month") String yearMonth,
                                            Model model) {
        log.info("GetGroupScheduleForMonth start with id:{}, month:{}", id, yearMonth);
        Map<LocalDate, List<Timetable>> dateToTimetables = timetableService.getGroupTimetableForMonth(id, yearMonth);
        model.addAttribute("dateToTimetables", dateToTimetables);
        model.addAttribute("month", getMonthName(yearMonth));
        return "timetablesForMoth";
    }

    @GetMapping(Mappings.GROUP_TIMETABLE_FOR_DATE)
    public String getGroupTimetableForOneDay(@RequestParam Long id,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                             Model model) {
        log.info("GetGroupScheduleForOneDay start with id:{}, month:{}", id, date);
        List<Timetable> timetableForOneDay = timetableService.getGroupTimetableForOneDay(id, date);
        model.addAttribute("timetableForOneDay", timetableForOneDay);
        return "timetablesForDay";
    }

    @GetMapping(Mappings.TEACHER_TIMETABLE_FOR_MONTH)
    public String getTeacherTimetableForMonth(@RequestParam(name = "id") Long id,
                                              @RequestParam(name = "month") String yearMonth,
                                              Model model) {
        log.info("GetTimetablesForMonth start with id:{}, month:{}", id, yearMonth);
        Map<LocalDate, List<Timetable>> dateToTimetables = timetableService.getTeacherTimetableForMonth(id, yearMonth);
        model.addAttribute("dateToTimetables", dateToTimetables);
        model.addAttribute("month", getMonthName(yearMonth));
        return "timetablesForMoth";
    }

    @GetMapping(Mappings.TEACHER_TIMETABLE_FOR_DATE)
    public String getTeacherTimetableForOneDay(@RequestParam Long id,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                               Model model) {
        log.info("GetTimetablesForOneDay start with id:{}, month:{}", id, date);
        List<Timetable> timetableForOneDay = timetableService.getTeacherTimetableForOneDay(id, date);
        model.addAttribute("timetableForOneDay", timetableForOneDay);
        return "timetablesForDay";
    }

    private String getMonthName(String yearMonth) {
        int month = Integer.parseInt(yearMonth.substring(yearMonth.indexOf("-") + 1));
        return Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
    }
}
