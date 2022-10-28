package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDAO extends JpaRepository<Subject, Long> {
}
