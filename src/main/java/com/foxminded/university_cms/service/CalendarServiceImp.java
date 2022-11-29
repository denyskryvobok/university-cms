package com.foxminded.university_cms.service;


import com.foxminded.university_cms.dao.CalendarDAO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Timetable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class CalendarServiceImp implements CalendarService {
    @Autowired
    private CalendarDAO calendarDAO;

    @Override
    public Map<Calendar, List<Timetable>> getTimetablesForEachDayOfMonth(Long groupId, YearMonth yearMonth) {
        log.info("GetTimetablesForEachDayOfMonth start with groupId:{}, yearMonth:{}", groupId, yearMonth);
        checkDates(yearMonth);
        Set<Calendar> calendarForMonth = calendarDAO.findCalendarForMonthWithTimetables(yearMonth.getMonthValue(), yearMonth.getYear());
        return calendarForMonth.stream()
                                .sorted(Comparator.comparing(Calendar::getDateOfDay))
                                .collect(groupingBy(calendar -> calendar, LinkedHashMap::new,
                                        collectingAndThen(toList(),
                                        groupTimetables -> groupTimetables.stream()
                                                    .flatMap(calendar -> calendar.getTimetables().stream())
                                                    .filter(timetable -> timetable.getGroup().getGroupId().equals(groupId))
                                                    .sorted(comparing(Timetable::getSubjectOrder))
                                                    .collect(toList()))));
    }

    private void checkDates(YearMonth yearMonth) {
        log.info("CheckDates start with yearMonth:{}", yearMonth);
        if (calendarDAO.findCalendarForMonth(yearMonth.getMonthValue(), yearMonth.getYear()).isEmpty()) {
            LocalDate firstDay = yearMonth.atDay(1);
            LocalDate lastDay = yearMonth.atEndOfMonth();
            firstDay.datesUntil(lastDay.plusDays(1)).forEach((date) -> calendarDAO.save(new Calendar(date)));
            log.info("Dates for yearMonth:{} have been added", yearMonth);
        }
    }
}
