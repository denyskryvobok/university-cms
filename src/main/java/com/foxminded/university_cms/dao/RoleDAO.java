package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface RoleDAO extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r " +
                    "JOIN FETCH r.userRoles ur " +
                    "JOIN FETCH ur.user u " +
                    "LEFT JOIN FETCH u.student s " +
                    "LEFT JOIN FETCH u.teacher t " +
                    "WHERE r.roleName = :roleName")
    Optional<Role> findRoleByName(String roleName);

    @Query(value = "SELECT r " +
                    "FROM Role r " +
                    "LEFT JOIN FETCH r.userRoles ur " +
                    "LEFT JOIN FETCH ur.user u " +
                    "LEFT JOIN FETCH u.student " +
                    "LEFT JOIN FETCH u.teacher " +
                    "WHERE r.roleName IN (:roles) ")
    Set<Role> findRolesByNames(Set<String> roles);
}
