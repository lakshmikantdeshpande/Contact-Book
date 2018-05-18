package com.ldeshpande.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class Contact {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Email
    private String email;

    @Valid
    @Embedded
    private List<Address> addresses;

    @Valid
    @Embedded
    private List<PhoneNumber> phoneNumbers;

}
