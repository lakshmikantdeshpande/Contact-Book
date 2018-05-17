package com.ldeshpande.contactbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
@NoArgsConstructor
class Name {

    @Max(15)
    private String firstName;

    @Max(15)
    private String middleName;

    @Max(15)
    private String lastName;

}
