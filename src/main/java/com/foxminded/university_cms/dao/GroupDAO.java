package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDAO extends JpaRepository<Group, Long> {
    @Override
    @Query(value = "SELECT DISTINCT g " +
                   "FROM Group g JOIN FETCH g.students s " +
                   "ORDER BY g.groupId ")
    List<Group> findAll();
}