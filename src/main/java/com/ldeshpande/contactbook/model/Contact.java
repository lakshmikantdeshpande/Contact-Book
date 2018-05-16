package com.ldeshpande.contactbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(of = {"email"})
public class Contact {

    @Id
    @Generated
    @NotNull
    @JsonIgnore
    private String id;

    @NotNull
    private Name name;

    @NotNull
    @Email
    private String email;

    @Valid
    private List<Address> addresses;

    private List<Phone> phoneNumbers;

}
