package com.foxminded.university_cms.dao;

import com.foxminded.university_cms.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserDAO extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u " +
            "LEFT JOIN FETCH u.userRoles ur " +
            "LEFT JOIN FETCH ur.role r " +
            "LEFT JOIN FETCH u.teacher t " +
            "LEFT JOIN FETCH u.student s " +
            "LEFT JOIN FETCH s.group g " +
            "WHERE u.userName = :userName")
    Optional<User> findUserByUserName(String userName);

    @Query(value = "SELECT u " +
                    "FROM User u " +
                    "LEFT JOIN FETCH u.userRoles ur " +
                    "LEFT JOIN FETCH ur.role " +
                    "LEFT JOIN FETCH u.student " +
                    "LEFT JOIN FETCH u.teacher ")
    Set<User> findAllWithUserRoles();
}
