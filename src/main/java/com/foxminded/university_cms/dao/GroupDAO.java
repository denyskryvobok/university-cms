package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

public interface GroupDAO extends JpaRepository<Group, Long> {
    @Query(value = "SELECT t " +
                   "FROM Group g JOIN g.timetables t JOIN FETCH t.subject " +
                   "WHERE g.groupId = :groupId AND t.dateOfDay = :date")
    Set<Timetable> findTimetableForOneDay(Long groupId, LocalDate date);

    @Query(value = "SELECT t " +
                   "FROM Group g JOIN g.timetables t JOIN FETCH t.subject " +
                   "WHERE g.groupId = :groupId AND t.month = :month")
    Set<Timetable> findTimetableForMonth(Long groupId, Month month);

    @Override
    @Query(value = "SELECT DISTINCT g " +
                   "FROM Group g JOIN FETCH g.students s " +
                   "ORDER BY g.groupId ")
    List<Group> findAll();
}
