package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CalendarDAO extends JpaRepository<Calendar, Long> {

    @Query(value = "SELECT c FROM Calendar c " +
                    "LEFT JOIN FETCH c.timetables t " +
                    "LEFT JOIN FETCH t.teacher tc " +
                    "LEFT JOIN FETCH t.group g " +
                    "LEFT JOIN FETCH t.subject s " +
                    "WHERE MONTH(c.dateOfDay) = :month AND YEAR(c.dateOfDay) = :year")
    Set<Calendar> findCalendarForMonthWithTimetables(Integer month, Integer year);
}
