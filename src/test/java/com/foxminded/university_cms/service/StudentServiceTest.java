package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest extends TestcontainersInitializer {
    @Autowired
    private StudentService studentService;

    @Test
    void getStudentsWhoAreNotInGroupByGroupId_shouldReturnStudentsWhoNotInSelectedGroup_whenStudentsNotFromInputGroupExist() {
        Set<Student> actual = studentService.getStudentsWhoAreNotInGroupByGroupId(1L);

        Set<Student> expected = getStudents();

        assertEquals(expected, actual);
    }

    private Set<Student> getStudents() {
        Set<Student> students = new LinkedHashSet<>();

        Student s1 = new Student();
        s1.setStudentId(3L);
        students.add(s1);

        Student s2 = new Student();
        s2.setStudentId(4L);
        students.add(s2);

        return students;
    }
}
