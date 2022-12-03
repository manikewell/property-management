package com.soad.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PROPERTY_TITLE", nullable = false, length = 500)
    private String title;
    private String description;
    private String ownerName;
    @Column(name = "PROPERTY_EMAIL", nullable = false, length = 500)
    private String ownerEmail;
    private double price;
    private String address;
}
