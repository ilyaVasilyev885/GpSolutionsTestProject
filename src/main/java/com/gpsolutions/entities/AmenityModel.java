package com.gpsolutions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "amenities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmenityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

}
