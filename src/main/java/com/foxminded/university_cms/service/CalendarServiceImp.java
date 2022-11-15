package com.foxminded.university_cms.service;


import com.foxminded.university_cms.dao.CalendarDAO;
import com.foxminded.university_cms.entity.Calendar;
import com.foxminded.university_cms.entity.Timetable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class CalendarServiceImp implements CalendarService {
    private final CalendarDAO calendarDAO;
    private final DateParser dateParser;

    public CalendarServiceImp(CalendarDAO calendarDAO, DateParser dateParser) {
        this.calendarDAO = calendarDAO;
        this.dateParser = dateParser;
    }

    @Override
    public Map<Calendar, List<Timetable>> getTimetablesForEachDayOfMonth(Long groupId, String yearMonth) {
        List<Integer> dates = dateParser.parseYearMonth(yearMonth);
        Set<Calendar> calendarForMonth = calendarDAO.findCalendarForMonthWithTimetables(dates.get(0), dates.get(1));
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
}
