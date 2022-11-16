package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    void addSubject(String subjectName);

    void deleteSubject(Long subjectId);

    boolean updateSubject(Long subjectId, String subjectName);
}
