package com.ldeshpande.contactbook.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class Name {

    @NotNull
    private Title title;

    @Max(15)
    private String firstName;

    @Max(15)
    private String middleName;

    @Max(15)
    private String lastName;

}
