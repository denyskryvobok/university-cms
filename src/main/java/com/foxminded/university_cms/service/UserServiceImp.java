package com.foxminded.university_cms.service;

import com.foxminded.university_cms.dao.RoleDAO;
import com.foxminded.university_cms.dao.StudentDAO;
import com.foxminded.university_cms.dao.TeacherDAO;
import com.foxminded.university_cms.dao.UserDAO;
import com.foxminded.university_cms.entity.Person;
import com.foxminded.university_cms.entity.Student;
import com.foxminded.university_cms.entity.Teacher;
import com.foxminded.university_cms.entity.security.Role;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.entity.security.UserRole;
import com.foxminded.university_cms.exception.RoleNotFoundException;
import com.foxminded.university_cms.dto.RolesDTO;
import com.foxminded.university_cms.dto.StudentRegistrationDTO;
import com.foxminded.university_cms.dto.TeacherRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByUserName(String username) {
        log.info("GetUserByUserName start with username:{}", username);
        return userDAO.findUserByUserName(username);
    }

    @Override
    public void saveUserAsStudent(StudentRegistrationDTO srf) {
        log.info("SaveUserAsStudent start with registration form values:{}", srf);
        User user = srf.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        saveRoles(user, "ROLE_STUDENT");

        Student student = srf.toStudent();
        student.setUser(user);
        user.setStudent(student);

        userDAO.save(user);
        studentDAO.save(student);
    }

    @Override
    public void saveUserAsTeacher(TeacherRegistrationDTO trf) {
        log.info("SaveUserAsTeacher start with registration form values:{}", trf);
        User user = trf.toUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        saveRoles(user, "ROLE_TEACHER");

        Teacher teacher = trf.toTeacher();
        teacher.setUser(user);
        user.setTeacher(teacher);

        userDAO.save(user);
        teacherDAO.save(teacher);
    }

    private void saveRoles(User user, String roleName) {
        log.info("SaveRoles start with user:{}, roleName:{}", user, roleName);
        roleDAO.findRoleByName(roleName).ifPresentOrElse(role -> {
            UserRole userRole = new UserRole(user, role);
            user.getUserRoles().add(userRole);
            role.getUserRoles().add(userRole);
        }, () -> {
            log.error("Role with name '{}' not found", roleName);
            throw new RoleNotFoundException(format("Role with name '%s' not found", roleName));
        });
    }

    @Override
    public Map<User, List<String>> getAllUsersWithRoles() {
        log.info("GetAllUsersWithRoles start");
        return userDAO.findAllWithUserRoles().stream()
                .sorted(comparing(User::getUserId))
                .collect(groupingBy(user -> user,
                                    LinkedHashMap::new,
                                    collectingAndThen(toList(),
                                                      users -> users.stream()
                                                                    .flatMap(user -> user.getUserRoles().stream())
                                                                    .map(UserRole::getRole)
                                                                    .sorted(comparing(Role::getRoleId))
                                                                    .map(Role::getRoleName)
                                                                    .collect(toList()))));
    }

    @Override
    public boolean updateRoles(String username, RolesDTO rolesDTO) {
        log.info("UpdateRoles start with username:{}, roleDTO:{}", username, rolesDTO);

        Optional<User> optionalUser = userDAO.findUserByUserName(username);

        if (optionalUser.isEmpty()) {
            log.error("User '{}' not found", username);
            throw new UsernameNotFoundException(format("User '%s' not found", username));
        }
        User user = optionalUser.get();

        Set<String> userRoles = user.getUserRoles().stream()
                                                    .map(userRole -> userRole.getRole().getRoleName())
                                                    .collect(toSet());
        return manageRoles(rolesDTO, user, userRoles);
    }

    private boolean manageRoles(RolesDTO rolesDTO, User user, Set<String> userRoles) {
        log.info("ManageRoles start with user:{}, users' roles:{}, input roles:{}", user, userRoles, rolesDTO);
        if (userRoles.equals(rolesDTO.getRoles())) {
            log.info("Input roles and user roles the same");
            return false;
        }

        roleDAO.findRolesByNames(userRoles);
        Iterator<UserRole> iterator = user.getUserRoles().iterator();
        while (iterator.hasNext()) {
            UserRole userRole = iterator.next();
            iterator.remove();
            userRole.getRole().getUserRoles().remove(userRole);
            userRole.setUser(null);
            userRole.setRole(null);
        }
        if (rolesDTO.getRoles().isEmpty()) {
            log.info("All roles was removed from User");
            return true;
        }

        Set<Role> newRoles = roleDAO.findRolesByNames(rolesDTO.getRoles());
        for (Role role : newRoles) {
            UserRole userRole = new UserRole(user, role);
            role.getUserRoles().add(userRole);
            user.getUserRoles().add(userRole);
        }
        return true;
    }

    @Override
    public Person getPersonByUsername(String username) {
        log.info("GetPersonByUsername start with username{}", username);
        Optional<User> optionalUser = userDAO.findUserByUserName(username);
        if (optionalUser.isEmpty()) {
            log.error("User '{}' not found", username);
            throw new UsernameNotFoundException(format("User '%s' not found", username));
        }
        User user = optionalUser.get();
        if (user.getTeacher() == null) {
            log.info("GetPersonByUsername return Student");
            return user.getStudent();
        } else {
            log.info("GetPersonByUsername return Teacher");
            return user.getTeacher();
        }
    }
}
