package com.foxminded.university_cms.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubjectServiceTest extends TestcontainersInitializer{

    @Autowired
    private SubjectService subjectService;

    @Test
    void updateSubject_shouldReturnTrue_whenInputSubjectNameIsDifferentFromSubjectNameInDB() {
        boolean actual = subjectService.updateSubject(1L, "Subject");
        assertTrue(actual);
    }

    @Test
    void updateSubject_shouldReturnFalse_whenInputSubjectNameSameAsSubjectNameInDB() {
        boolean actual = subjectService.updateSubject(1L, "Accounting and Finance");
        assertFalse(actual);
    }
}
