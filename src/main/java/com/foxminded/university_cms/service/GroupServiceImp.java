package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.entity.Group;
import com.foxminded.university_cms.exception.GroupNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class GroupServiceImp implements GroupService {
    private final GroupDAO groupDAO;

    public GroupServiceImp(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public List<Group> getAllGroupsWithStudents() {
        log.info("GetAllGroupsWithStudents start");
        return groupDAO.findAllGroupsWithStudents();
    }

    @Override
    public List<Group> getAllGroups() {
        log.info("GetAllGroups start");
        return groupDAO.findAll();
    }

    @Override
    public Group getGroupById(Long groupId) {
        log.info("GetGroupById start");
        return groupDAO.findById(groupId).orElseThrow(GroupNotFoundException::new);
    }
}
