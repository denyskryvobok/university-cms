package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface StudentDAO extends JpaRepository<Student, Long> {

    @Query(value = "SELECT t " +
                   "FROM Student s JOIN s.group g JOIN g.timetables t " +
                   "WHERE s.studentId = :studentId AND t.dateOfDay = :date")
    List<Timetable> findTimetableForOneDay(Long studentId, LocalDate date);

    @Query(value = "SELECT t " +
                   "FROM Student s " +
                   "JOIN s.group g JOIN g.timetables t " +
                   "WHERE s.studentId = :studentId AND t.month = :month")
    List<Timetable> findTimetableForMonth(Long studentId, Month month);
}
