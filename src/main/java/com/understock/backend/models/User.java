package com.understock.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/*
* Model to define data required for the User to be
* stored inside the database. The ID is generated using
* @GeneratedValue from the JPA library.
*
* The Constructor is generated automatically using
* the Lombok library that reduces boilerplate code.
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*
    * -- Work In Progress --
    * Additional fields will be added later when needed.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;

    /*
    * Adding the relationship to the Role model, where many
    * Users can have many Roles.
    *
    * The collection will contain all the privileges a User holds,
    * this will be used to lock routes for specific Users.
    */
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
