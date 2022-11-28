package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {

    List<Subject> getAllSubjects();

    boolean addSubject(String subjectName);

    void deleteSubject(Long subjectId);

    boolean updateSubject(Long subjectId, String subjectName);

    Set<Subject> getTeacherSubjects(Long teacherId);
}
