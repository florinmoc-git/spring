package com.hospital.microservices.patient_service.entities.embedables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @Column(name = "street_number")
    private int number;
    private String street;
    private String city;
    private String county;
    private String postcode;
    private String country;
}
