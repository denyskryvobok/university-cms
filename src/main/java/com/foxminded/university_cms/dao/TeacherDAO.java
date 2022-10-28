package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDAO extends JpaRepository<Teacher, Long> {
}
