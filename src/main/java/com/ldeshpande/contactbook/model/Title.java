package com.ldeshpande.contactbook.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@ToString
@Embeddable
enum Title {

    MR("Mr."),
    MRS("Mrs.");

    private final String nameTitle;

    Title(String nameTitle) {
        this.nameTitle = nameTitle;
    }

}
