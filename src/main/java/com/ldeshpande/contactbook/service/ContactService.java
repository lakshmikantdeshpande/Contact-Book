package com.ldeshpande.contactbook.service;

import com.ldeshpande.contactbook.model.Contact;

public interface ContactService {

    Contact addContact(Contact contact);

    Contact findContactByEmail(String email);

    Contact findContactByName(String name);

}
