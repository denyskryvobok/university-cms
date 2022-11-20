package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupServiceImpTest extends TestcontainersInitializer {
    @Autowired
    private GroupService groupService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void updateGroup_shouldReturnFalse_whenInputGroupNameIsTheSame() {
        boolean actual = groupService.updateGroup(1L, "HR-32");
        assertFalse(actual);
    }

    @Test
    void updateGroup_shouldReturnTrue_whenInputGroupNameIsDifferent() {
        boolean actual = groupService.updateGroup(1L, "group");
        assertTrue(actual);
    }

    @Test
    void deleteGroup_shouldRemoveGroupButNotRemoveStudentOfThisGroup() {
        groupService.deleteGroup(1L);
        Group group = entityManager.find(Group.class, 1L);
        Student s1 = entityManager.find(Student.class, 1L);
        Student s2 = entityManager.find(Student.class, 2L);

        assertNotNull(s1);
        assertNotNull(s2);
        assertNull(group);
    }
}