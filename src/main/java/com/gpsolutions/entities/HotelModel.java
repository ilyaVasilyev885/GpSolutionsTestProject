package com.gpsolutions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "description")
    private String description;

    @OneToOne(targetEntity = AddressModel.class, optional = false, cascade = CascadeType.ALL)
    private AddressModel address;

    @OneToOne(targetEntity = ContactModel.class, optional = false, cascade = CascadeType.ALL)
    private ContactModel contacts;

    @Column(name = "check_in", nullable = false)
    private LocalTime checkIn;

    @Column(name = "check_out")
    private LocalTime checkOut;

    @ManyToMany(targetEntity = AmenityModel.class)
    private List<AmenityModel> amenities;

}
