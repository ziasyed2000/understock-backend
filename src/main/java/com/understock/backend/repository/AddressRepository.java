package com.understock.backend.repository;

import com.understock.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The repository for a data model, allows CRUD functionality.
 */
public interface AddressRepository extends JpaRepository<Role, Long> {
    AddressRepository findDistinctById(Long id);
}
