package com.understock.backend.service;

import com.understock.backend.models.Address;
import com.understock.backend.models.Order;
import com.understock.backend.models.Role;
import com.understock.backend.models.User;

import java.util.List;

/**
 * Interface of the UserService, prototyping custom methods
 * to be used when client makes a request, once authenticated.
 */
public interface UserService {
    boolean userExists(String username);
    User getUser(String username);
    void saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    void addAddressToUser(String username, Address address);
    void addOrderToUser(String username, Order order);
    List<User>getUsers();
}
