package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.SubjectDAO;
import com.foxminded.university_cms.entity.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SubjectServiceImp implements SubjectService {
    private final SubjectDAO subjectDAO;

    public SubjectServiceImp(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDAO.findAll().stream()
                                   .sorted(Comparator.comparing(Subject::getSubjectId))
                                   .collect(Collectors.toList());
    }
}
