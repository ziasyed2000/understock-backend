package com.understock.backend.service;

import com.understock.backend.models.Role;
import com.understock.backend.models.User;

import java.util.List;

/**
 * Interface of the UserService, prototyping custom methods
 * to be used when client makes a request, once authenticated.
 */
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User>getUsers();
}
