package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroupsWithStudents();

    List<Group> getAllGroups();

    Group getGroupById(Long groupId);
}
