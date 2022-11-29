package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.dto.TimetableDTO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Timetable;
import com.foxminded.university_cms.service.CalendarService;
import com.foxminded.university_cms.service.GroupService;
import com.foxminded.university_cms.service.SubjectService;
import com.foxminded.university_cms.service.TeacherService;
import com.foxminded.university_cms.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

@Slf4j
@Controller
@RequestMapping(Mappings.TIMETABLE_PAGE)
public class TimetableController {
    @Autowired
    private TimetableService timetableService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping(Mappings.GROUP_TIMETABLE_FOR_MONTH)
    public String getGroupTimetableForMonth(@RequestParam Long id,
                                            @RequestParam @DateTimeFormat(iso = ISO.DATE) YearMonth yearMonth,
                                            Model model) {
        log.info("GetGroupScheduleForMonth start with id:{}, month:{}", id, yearMonth);
        Map<LocalDate, List<Timetable>> dateToTimetables = timetableService.getGroupTimetableForMonth(id, yearMonth);
        model.addAttribute("dateToTimetables", dateToTimetables);
        model.addAttribute("month", getNameOfMonth(yearMonth));
        return "timetablesForMoth";
    }

    @GetMapping(Mappings.GROUP_TIMETABLE_FOR_DATE)
    public String getGroupTimetableForOneDay(@RequestParam Long id,
                                             @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                             Model model) {
        log.info("GetGroupScheduleForOneDay start with id:{}, month:{}", id, date);
        List<Timetable> timetableForOneDay = timetableService.getGroupTimetableForOneDay(id, date);
        model.addAttribute("timetableForOneDay", timetableForOneDay);
        return "timetablesForDay";
    }

    @GetMapping(Mappings.TEACHER_TIMETABLE_FOR_MONTH)
    public String getTeacherTimetableForMonth(@RequestParam Long id,
                                              @RequestParam @DateTimeFormat(iso = ISO.DATE) YearMonth yearMonth,
                                              Model model) {
        log.info("GetTimetablesForMonth start with id:{}, month:{}", id, yearMonth);
        Map<LocalDate, List<Timetable>> dateToTimetables = timetableService.getTeacherTimetableForMonth(id, yearMonth);
        model.addAttribute("dateToTimetables", dateToTimetables);
        model.addAttribute("month", getNameOfMonth(yearMonth));
        return "timetablesForMoth";
    }

    @GetMapping(Mappings.TEACHER_TIMETABLE_FOR_DATE)
    public String getTeacherTimetableForOneDay(@RequestParam Long id,
                                               @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                               Model model) {
        log.info("GetTimetablesForOneDay start with id:{}, month:{}", id, date);
        List<Timetable> timetableForOneDay = timetableService.getTeacherTimetableForOneDay(id, date);
        model.addAttribute("timetableForOneDay", timetableForOneDay);
        return "timetablesForDay";
    }

    @GetMapping(Mappings.TIMETABLE_MANAGER)
    public String showTimetableManager(@RequestParam Long groupId,
                                       @RequestParam @DateTimeFormat(iso = ISO.DATE) YearMonth yearMonth,
                                       Model model) {
        log.info("ShowTimetableManager start with groupId:{}, date:{}", groupId, yearMonth);
        Map<Calendar, List<Timetable>> calendarToTimetables =
                calendarService.getTimetablesForEachDayOfMonth(groupId, yearMonth);

        model.addAttribute("calendarToTimetables", calendarToTimetables);
        model.addAttribute("month", getNameOfMonth(yearMonth));
        model.addAttribute("group", groupService.getGroupById(groupId));
        model.addAttribute("subjects", subjectService.getAllSubjects());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("timetableDTO", new TimetableDTO());
        return "timetableManager";
    }

    @DeleteMapping(Mappings.TIMETABLE_DELETE)
    public String deleteTimetable(@RequestParam Long timetableId,
                                  @RequestParam Long groupId,
                                  @RequestParam String yearMonth,
                                  @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                  RedirectAttributes redirectAttributes) {
        log.info("DeleteTimetable start with timetableId:{} groupId:{}, date:{}", timetableId, groupId, yearMonth);
        timetableService.deleteTimetable(timetableId);
        redirectAttributes.addAttribute("groupId", groupId);
        redirectAttributes.addAttribute("yearMonth", yearMonth);
        redirectAttributes.addAttribute("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        redirectAttributes.addAttribute("deleteSuccess", true);
        return "redirect:/timetable/manager";
    }

    @PatchMapping(Mappings.TIMETABLE_UPDATE)
    public String updateTimetable(TimetableDTO timetableDTO,
                                  @RequestParam Long groupId,
                                  @RequestParam String yearMonth,
                                  @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                                  RedirectAttributes redirectAttributes) {
        log.info("UpdateTimetable start with timetableDTO:{} groupId:{}, date:{}", timetableDTO, groupId, yearMonth);
        if (timetableService.updateTimetable(timetableDTO)) {
            redirectAttributes.addAttribute("updateSuccess", true);
        } else {
            redirectAttributes.addAttribute("updateFail", true);
        }
        redirectAttributes.addAttribute("groupId", groupId);
        redirectAttributes.addAttribute("yearMonth", yearMonth);
        redirectAttributes.addAttribute("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return "redirect:/timetable/manager";
    }

    @PostMapping(Mappings.TIMETABLE_ADD)
    public String addTimetable(TimetableDTO timetableDTO,
                               @RequestParam String yearMonth,
                               @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
                               RedirectAttributes redirectAttributes) {
        log.info("AddTimetable start with timetableDTO:{}, yearMonth:{}, date:{} ", timetableDTO, yearMonth, date);
        timetableService.addTimetable(timetableDTO);
        redirectAttributes.addAttribute("groupId", timetableDTO.getGroupId());
        redirectAttributes.addAttribute("yearMonth", yearMonth);
        redirectAttributes.addAttribute("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        redirectAttributes.addAttribute("addSuccess", true);
        return "redirect:/timetable/manager";
    }

    private String getNameOfMonth(YearMonth yearMonth) {
        return yearMonth.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
    }
}
