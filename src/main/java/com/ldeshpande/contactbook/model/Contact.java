package com.ldeshpande.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "contact")
@Data
@EqualsAndHashCode(of = {"email"})
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @JsonIgnore
    private Long id;

    @NotNull
    private Name name;

    @NotNull
    @Email
    private String email;

    @Valid
    @Embedded
    private List<Address> addresses;

    @Embedded
    private List<Phonenumber.PhoneNumber> phoneNumbers;

}
