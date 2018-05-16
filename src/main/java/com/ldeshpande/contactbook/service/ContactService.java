package com.ldeshpande.contactbook.service;

import com.ldeshpande.contactbook.model.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {

    boolean addContact(Contact contact);

    Contact findContactByEmail(String email);

}
