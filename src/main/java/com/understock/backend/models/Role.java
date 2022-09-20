package com.understock.backend.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Model to define data required for the Role to be
 * stored inside the database. The ID is generated using
 * @GeneratedValue from the JPA library.
 *
 * The Constructor is generated automatically using
 * the Lombok library that reduces boilerplate code.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
