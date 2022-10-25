package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public interface TeacherDAO extends JpaRepository<Teacher, Long> {

    @Query(value = "SELECT t " +
                   "FROM Teacher tc JOIN tc.timetables t JOIN FETCH t.subject " +
                   "WHERE tc.teacherId = :teacherId AND t.dateOfDay = :date")
    Set<Timetable> findTimetableForOneDay(Long teacherId, LocalDate date);

    @Query(value = "SELECT t " +
                   "FROM Teacher tc JOIN tc.timetables t JOIN FETCH t.subject " +
                   "WHERE tc.teacherId = :teacherId AND t.month = :month")
    Set<Timetable> findTimetableForMonth(Long teacherId, Month month);
}
