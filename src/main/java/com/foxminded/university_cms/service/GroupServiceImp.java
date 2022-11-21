package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.dao.StudentDAO;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.exception.GroupNotFoundException;
import com.foxminded.university_cms.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class GroupServiceImp implements GroupService {
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<Group> getAllGroupsWithStudents() {
        log.info("GetAllGroupsWithStudents start");
        return groupDAO.findAllGroupsWithStudents();
    }

    @Override
    public List<Group> getAllGroups() {
        log.info("GetAllGroups start");
        return groupDAO.findAll(Sort.by(Sort.Direction.ASC, "groupId"));
    }

    @Override
    public Group getGroupById(Long groupId) {
        log.info("GetGroupById start");
        return groupDAO.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }

    @Override
    public void addGroup(String groupName) {
        log.info("AddGroup start with groupName:{}", groupName);
        groupDAO.save(new Group(groupName));
    }

    @Override
    public boolean updateGroup(Long groupId, String groupName) {
        log.info("UpdateGroup start with groupId:{}, groupName:{}", groupId, groupName);
        Group group = groupDAO.findById(groupId).orElseThrow(() -> {
            log.error("Group not found with id:{}", groupId);
            return new GroupNotFoundException();
        });
        if (group.getGroupName().equals(groupName)) {
            return false;
        }
        group.setGroupName(groupName);
        return true;
    }

    @Override
    public void deleteGroup(Long groupId) {
        log.info("DeleteGroup start with groupId:{}", groupId);
        Group group = groupDAO.findGroupByIdWithStudents(groupId).orElseThrow(() -> {
            log.error("Group not found with groupId:{}", groupId);
            return new GroupNotFoundException();
        });
        group.getStudents().forEach((student) -> student.setGroup(null));
        groupDAO.delete(group);
    }

    @Override
    public Group getGroupWithStudents(Long groupId) {
        log.info("GetGroupWithStudents start with groupId:{}", groupId);
        return groupDAO.findGroupByIdWithStudents(groupId).orElseThrow(() -> {
            log.info("Group with groupId:{} not found", groupId);
            return new GroupNotFoundException();
        });
    }

    @Override
    public void deleteStudentFromGroup(Long studentId, Long groupId) {
        log.info("DeleteStudentFromGroup start with groupId:{}, studentId:{}", groupId, studentId);
        Group group = groupDAO.findGroupByIdWithStudents(groupId).orElseThrow(() -> {
            log.error("Group not found with groupId:{}", groupId);
            return new GroupNotFoundException();
        });

        Iterator<Student> iterator = group.getStudents().iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId().equals(studentId)) {
                student.setGroup(null);
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void addStudentToGroup(Long studentId, Long groupId) {
        log.info("AddStudentToGroup start with groupId:{}, studentId:{}", groupId, studentId);
        Group group = groupDAO.findGroupByIdWithStudents(groupId).orElseThrow(() -> {
            log.error("Group not found with groupId:{}", groupId);
            return new GroupNotFoundException();
        });
        Student student = studentDAO.findStudentWithGroup(studentId).orElseThrow(() -> {
            log.error("Student not found with studentId:{}", studentId);
            return new StudentNotFoundException();
        });
        student.setGroup(group);
        group.getStudents().add(student);
    }
}
