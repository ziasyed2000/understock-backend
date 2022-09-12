package com.understock.backend;

import com.understock.backend.models.Role;
import com.understock.backend.models.User;
import com.understock.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class UnderstockApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnderstockApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "John Trov", "johntrov123", "test123", new ArrayList<>()));
            userService.saveUser(new User(null, "Jim Trov", "jimtrov123", "test123", new ArrayList<>()));
            userService.saveUser(new User(null, "Lala Trov", "lalatrov123", "test123", new ArrayList<>()));
            userService.saveUser(new User(null, "Mop Trov", "moptrov123", "test123", new ArrayList<>()));

            userService.addRoleToUser("johntrov123", "ROLE_USER");
            userService.addRoleToUser("jimtrov123", "ROLE_USER");
            userService.addRoleToUser("jimtrov123", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("lalatrov123", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("moptrov123", "ROLE_MANAGER");
        };
    }

}
