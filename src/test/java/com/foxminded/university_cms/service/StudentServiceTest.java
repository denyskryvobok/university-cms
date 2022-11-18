package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest extends TestcontainersInitializer{
    @Autowired
    private StudentService studentService;

    @Test
    void getStudentsWhoAreNotInGroupByGroupId_shouldReturnStudentsWhoNotInSelectedGroup() {
        Set<Student> actual = studentService.getStudentsWhoAreNotInGroupByGroupId(1L);

        Set<Student> expected = new LinkedHashSet<>();
        Student s1 = new Student();
        s1.setStudentId(3L);
        expected.add(s1);
        Student s2 = new Student();
        s2.setStudentId(4L);
        expected.add(s2);

        assertEquals(expected, actual);
    }
}