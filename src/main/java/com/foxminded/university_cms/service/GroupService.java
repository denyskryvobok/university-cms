package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroupsWithStudents();

    List<Group> getAllGroups();

    Group getGroupById(Long groupId);

    boolean addGroup(String groupName);

    boolean updateGroup(Long groupId, String groupName);

    void deleteGroup(Long groupId);

    Group getGroupWithStudents(Long groupId);

    void deleteStudentFromGroup(Long studentId, Long groupId);

    void addStudentToGroup(Long studentId, Long groupId);
}
