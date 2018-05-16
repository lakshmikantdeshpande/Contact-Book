package com.ldeshpande.contactbook.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
class Name {

    @NotNull
    private Title title;

    @Max(15)
    private String firstName;

    @Max(15)
    private String middleName;

    @Max(15)
    private String lastName;

}
