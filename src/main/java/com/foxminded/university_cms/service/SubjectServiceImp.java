package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.SubjectDAO;
import com.foxminded.university_cms.entity.Subject;
import com.foxminded.university_cms.exception.SubjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Service
@Transactional
public class SubjectServiceImp implements SubjectService {
    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    public List<Subject> getAllSubjects() {
        log.info("GetAllSubjects start");
        return subjectDAO.findAll().stream()
                .sorted(Comparator.comparing(Subject::getSubjectId))
                .collect(Collectors.toList());
    }

    @Override
    public void addSubject(String subjectName) {
        log.info("AddSubject start with subjectName:{}", subjectName);
        subjectDAO.save(new Subject(subjectName));
    }

    @Override
    public void deleteSubject(Long subjectId) {
        log.info("DeleteSubject start with subjectId:{}", subjectId);
        subjectDAO.deleteById(subjectId);
    }

    @Override
    public boolean updateSubject(Long subjectId, String subjectName) {
        log.info("UpdateSubject start with subjectId:{}, subjectName:{}", subjectId, subjectName);
        Optional<Subject> subjectOptional = subjectDAO.findById(subjectId);
        if (subjectOptional.isEmpty()) {
            log.error("Subject with id '{}' not found", subjectId);
            throw new SubjectNotFoundException(format("Subject with id '%d' not found", subjectId));
        }
        Subject subject = subjectOptional.get();
        if (subject.getSubjectName().equals(subjectName)) {
            return false;
        } else {
            subject.setSubjectName(subjectName);
            return true;
        }
    }
}
