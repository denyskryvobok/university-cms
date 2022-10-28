package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class TeacherServiceImp implements TeacherService {
    private final TeacherDAO teacherDAO;

    public TeacherServiceImp(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDAO.findAll();
    }
}
