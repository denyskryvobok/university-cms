package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.GroupDAO;
import com.foxminded.university_cms.entity.Group;
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
        return groupDAO.findAll();
    }
}
