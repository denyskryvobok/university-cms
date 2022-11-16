package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public interface TimetableDAO extends JpaRepository<Timetable, Long> {

    @Query(value = "SELECT t " +
            "FROM Group g JOIN g.timetables t JOIN FETCH t.subject s JOIN FETCH t.calendar c " +
            "WHERE g.groupId = :groupId AND c.dateOfDay = :date")
    Set<Timetable> findGroupTimetableForOneDay(Long groupId, LocalDate date);

    @Query(value = "SELECT t " +
            "FROM Group g JOIN g.timetables t JOIN FETCH t.subject s JOIN FETCH t.calendar c " +
            "WHERE g.groupId = :groupId AND MONTH(c.dateOfDay) = :month AND YEAR(c.dateOfDay) = :year ")
    Set<Timetable> findGroupTimetableForMonth(Long groupId, Integer month, Integer year);

    @Query(value = "SELECT t " +
            "FROM Teacher tc JOIN tc.timetables t JOIN FETCH t.subject s JOIN FETCH t.calendar c " +
            "WHERE tc.teacherId = :teacherId AND c.dateOfDay = :date")
    Set<Timetable> findTeacherTimetableForOneDay(Long teacherId, LocalDate date);

    @Query(value = "SELECT t " +
            "FROM Teacher tc JOIN tc.timetables t JOIN FETCH t.subject s JOIN FETCH t.calendar c " +
            "WHERE tc.teacherId = :teacherId AND MONTH(c.dateOfDay) = :month AND YEAR(c.dateOfDay) = :year ")
    Set<Timetable> findTeacherTimetableForMonth(Long teacherId, Integer month, Integer year);

    @Query(value = "SELECT t " +
            "FROM Timetable t " +
            "JOIN FETCH t.calendar c " +
            "JOIN FETCH t.subject s " +
            "WHERE t.timetableId = :timetableId")
    Optional<Timetable> findTimetable(Long timetableId);
}
