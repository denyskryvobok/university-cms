package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface StudentDAO extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s " +
                    "FROM Student s LEFT JOIN FETCH s.group g " +
                    "WHERE g.groupId <> :groupId OR g.groupId IS NULL " +
                    "ORDER BY g.groupId ")
    Set<Student> findStudentsWhoAreNotInGroupByGroupId(Long groupId);

    @Query(value = "SELECT s FROM Student s JOIN FETCH s.group WHERE s.studentId = :studentId")
    Optional<Student> findStudentWithGroup(Long studentId);
}
