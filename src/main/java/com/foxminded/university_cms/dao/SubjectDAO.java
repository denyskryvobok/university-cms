package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface SubjectDAO extends JpaRepository<Subject, Long> {
    @Query(value = "SELECT s FROM Subject s WHERE s.subjectName = :subjectName")
    Optional<Subject> findByName(String subjectName);

    @Query(value = "SELECT s FROM Subject s " +
                    "JOIN FETCH s.timetables t " +
                    "JOIN FETCH t.teacher tc " +
                    "WHERE t.teacher.teacherId = :teacherId")
    Set<Subject> findTeacherSubjects(Long teacherId);
}
