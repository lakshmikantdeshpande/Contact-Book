package com.ldeshpande.contactbook.service;

import com.ldeshpande.contactbook.model.Contact;

import java.util.List;

public interface ContactService {

    Contact addContact(Contact contact);

    Contact findContactByEmail(String email);

    List<Contact> findContactsByName(String name);

    Contact updateContact(Contact contact);

    void deleteContact(String email);

    List<Contact> findAll(int page, int limit);

}
