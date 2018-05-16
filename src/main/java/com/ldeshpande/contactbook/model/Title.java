package com.ldeshpande.contactbook.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Title {

    MR("Mr."),
    MRS("Mrs.");

    private final String nameTitle;

    Title(String nameTitle) {
        this.nameTitle = nameTitle;
    }

}
