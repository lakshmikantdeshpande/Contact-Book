package com.ldeshpande.contactbook.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;

@Data
public class Address {

    @Length(max = 75)
    private String addressLine1;

    @Length(max = 50)
    private String landmark;

    @Length(max = 50)
    private String state;

    @Length(max = 20)
    private String country;

    @Digits(integer = 6, fraction = 0)
    private Integer zipCode;

}
