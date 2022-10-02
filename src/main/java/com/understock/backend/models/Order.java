package com.understock.backend.models;

import lombok.*;
import javax.persistence.*;
import java.net.URL;
import java.util.UUID;

/*
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String orderDate;
    private UUID orderNumber;
    private String totalPrice;
    private URL productPage;
    private String deliveryStatus;
}
