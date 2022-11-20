package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    List<Student> getAllStudents();

    Set<Student> getStudentsWhoAreNotInGroupByGroupId(Long groupId);
}
