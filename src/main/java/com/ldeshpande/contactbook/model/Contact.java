package com.ldeshpande.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @Column(unique = true)
    private String email;

    @Valid
    @Embedded
    private Address address;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long phoneNumber;

}
