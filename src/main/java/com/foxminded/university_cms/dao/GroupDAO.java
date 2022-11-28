package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupDAO extends JpaRepository<Group, Long> {

    @Query(value = "SELECT DISTINCT g " +
                   "FROM Group g JOIN FETCH g.students s " +
                   "ORDER BY g.groupId ")
    List<Group> findAllGroupsWithStudents();

    @Query(value = "SELECT g FROM Group g LEFT JOIN FETCH g.students s WHERE g.groupId = :groupId")
    Optional<Group> findGroupByIdWithStudents(Long groupId);

    @Query(value = "SELECT g FROM Group g WHERE g.groupName = :groupName")
    Optional<Group> findGroupByName(String groupName);
}
