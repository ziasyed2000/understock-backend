package com.understock.backend.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/*
* Model to define data required for the User to be
* stored inside the database. The ID is generated using
* @GeneratedValue from the JPA library.
*
* The Constructor is generated automatically using
* the Lombok library that reduces boilerplate code.
*/
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
