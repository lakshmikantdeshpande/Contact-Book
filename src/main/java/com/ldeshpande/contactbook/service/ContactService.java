package com.ldeshpande.contactbook.service;

import com.ldeshpande.contactbook.model.Contact;

public interface ContactService {

    boolean addContact(Contact contact);

    Contact findContactByEmail(String email);

}
