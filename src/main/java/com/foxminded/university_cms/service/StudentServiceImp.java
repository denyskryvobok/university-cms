package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.StudentDAO;
import com.foxminded.university_cms.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Student> getAllStudents() {
        log.info("GetAllStudents start");
        return studentDAO.findAll().stream()
                                   .sorted(Comparator.comparing(Student::getStudentId))
                                   .collect(Collectors.toList());
    }

    @Override
    public Set<Student> getStudentsWhoAreNotInGroupByGroupId(Long groupId) {
        log.info("GetStudentsWhoAreNotInGroupByGroupId start");
        return studentDAO.findStudentsWhoAreNotInGroupByGroupId(groupId);
    }
}
