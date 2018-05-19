package com.ldeshpande.contactbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@NoArgsConstructor
public class Address {

    @Length(max = 75)
    @NotNull
    private String addressLine1;

    @Length(max = 50)
    @NotNull
    private String landmark;

    @Length(max = 50)
    @NotNull
    private String state;

    @Length(max = 20)
    @NotNull
    private String country;

    @Digits(integer = 6, fraction = 0)
    private Integer zipCode;

}
