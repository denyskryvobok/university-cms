package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Long> {
}
