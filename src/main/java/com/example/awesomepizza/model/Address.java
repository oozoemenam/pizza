package com.example.awesomepizza.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Embeddable
@Data
public class Address {
    @NotBlank
    private String street1;

    private String street2;

    @NotBlank
    private String city;

    private String state;

    private String postalCode;

    private String country;
}
