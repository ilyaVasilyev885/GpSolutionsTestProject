package com.gpsolutions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses", uniqueConstraints = {
        @UniqueConstraint(name = "unique_building", columnNames = {"house_number", "street", "city"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

}
