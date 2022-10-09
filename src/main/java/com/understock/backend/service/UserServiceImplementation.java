package com.understock.backend.service;

import com.understock.backend.models.Address;
import com.understock.backend.models.Order;
import com.understock.backend.models.Role;
import com.understock.backend.models.User;
import com.understock.backend.repository.RoleRepository;
import com.understock.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementing defined methods within the UserService.
 *
 * This will define all the common methods that will be used
 * to operate CRUD operations within the User model.
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean userExists(String username) {
        log.info("Checking if user '{}' exists.", username);
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user:{}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        log.info("Saving new user - {} - to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void saveRole(Role role) {
        log.info("Saving new role - {} - to the database", role.getName());
        roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role: {} to the user: {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void addAddressToUser(String username, Address address) {
        log.info("Adding address: {} to the user: {}", address, username);
        User user = userRepository.findByUsername(username);
        user.getAddresses().add(address);
    }

    @Override
    public void addOrderToUser(String username, Order order) {
        log.info("Adding order: {} to the user: {}", order, username);
        User user = userRepository.findByUsername(username);
        user.getOrders().add(order);
    }

    @Override
    public List<User> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in the DB.");
            throw new UsernameNotFoundException("User not found in the DB.");
        } else {
            log.info("User - {} found in the DB.", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
