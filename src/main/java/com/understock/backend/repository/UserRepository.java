package com.understock.backend.repository;

import com.understock.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repository for a data model, allows CRUD functionality.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
