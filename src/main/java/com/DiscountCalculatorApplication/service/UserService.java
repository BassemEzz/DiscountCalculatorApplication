package com.DiscountCalculatorApplication.service;

import com.DiscountCalculatorApplication.model.Role;
import com.DiscountCalculatorApplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDetailsService userDetailsService;

    public Users findByUsername(String username) {
        try {
            org.springframework.security.core.userdetails.User userDetails =
                    (org.springframework.security.core.userdetails.User) userDetailsService.loadUserByUsername(username);

            Users users = new Users();
            users.setUsername(userDetails.getUsername());
            users.setPassword(userDetails.getPassword());

            Set<Role> roles = new HashSet<>();
            userDetails.getAuthorities().forEach(authority -> {
                if (authority.getAuthority().equals("ROLE_EMPLOYEE")) {
                    roles.add(new Role(Role.RoleType.EMPLOYEE));
                } else if (authority.getAuthority().equals("ROLE_AFFILIATE")) {
                    roles.add(new Role(Role.RoleType.AFFILIATE));
                } else if (authority.getAuthority().equals("ROLE_LOYAL_CUSTOMER")) {
                    roles.add(new Role(Role.RoleType.LOYAL_CUSTOMER));
                } else if (authority.getAuthority().equals("ROLE_CUSTOMER")) {
                    roles.add(new Role(Role.RoleType.CUSTOMER));
                }
            });

            users.setRoles(roles);
            return users;

        } catch (UsernameNotFoundException e) {
            return null;
        }
    }
}


