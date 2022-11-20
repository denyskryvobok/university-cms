package com.foxminded.university_cms.service;

import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.entity.security.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;
@Slf4j
@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("LoadUserByUsername start with username:{}", username);
        User user = userService.getUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("User '%s' not found", username)));
        Set<GrantedAuthority> userAuthorities = getUserAuthority(user.getUserRoles());
        return buildUserForAuthentication(user, userAuthorities);
    }

    private Set<GrantedAuthority> getUserAuthority(Set<UserRole> userRoles) {
        log.info("GetUserAuthority start with userRoles:{}", userRoles);
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                .collect(toSet());
    }

    private UserDetails buildUserForAuthentication(User user, Set<GrantedAuthority> userAuthorities) {
        log.info("BuildUserForAuthentication start with user:{}, user' authorities:{}", user, userAuthorities);
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                                                                      user.getPassword(),
                                                                      user.getActive(),
                                                                      true,
                                                                      true,
                                                                      true,
                                                                      userAuthorities);
    }
}
