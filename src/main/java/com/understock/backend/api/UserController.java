package com.understock.backend.api;

import com.understock.backend.models.Role;
import com.understock.backend.models.User;
import com.understock.backend.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Login route is handled by Spring Web Security automatically.

    /**
     * This POST route registers a using, by getting the User Data object
     * from the body and passes that into our custom userService to add that
     * user into the DB. We then assign the basic permissions to that user.
     */
    @PostMapping("/register")
    public ResponseEntity<?>saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        if (userService.userExists(user.getUsername())) {
            return ResponseEntity.status(403).build();
        } else {
            userService.saveUser(user);
            userService.addRoleToUser(user.getUsername(), "ROLE_USER");

            return ResponseEntity.created(uri).body(userService.getUser(user.getUsername()));
        }
    }

    @GetMapping("/getUserList")
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

//    @PostMapping("/role/save")
//    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveRole(role));
//    }
//
//    @PostMapping("/role/addToUser")
//    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
//        userService.addRoleToUser(form.getUsername(), form.getRoleName());
//        return ResponseEntity.ok().build();
//    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
