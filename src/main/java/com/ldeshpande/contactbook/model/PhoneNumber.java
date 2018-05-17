package com.ldeshpande.contactbook.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Embeddable
@NoArgsConstructor
class PhoneNumber {

    @NotNull
    @Size(min = 10, max = 10)
    private Integer phoneNumber;

}
