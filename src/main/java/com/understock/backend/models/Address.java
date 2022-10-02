package com.understock.backend.models;

import lombok.*;
import javax.persistence.*;

/*
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String apartmentNumber;
    private String streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String postalCode;
    private String country;
}
