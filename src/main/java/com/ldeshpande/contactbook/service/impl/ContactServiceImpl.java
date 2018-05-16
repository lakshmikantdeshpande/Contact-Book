package com.ldeshpande.contactbook.service.impl;

import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.repository.ContactRepository;
import com.ldeshpande.contactbook.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public boolean addContact(Contact contact) {
        Contact result = contactRepository.save(contact);
        if (result == null) {
            log.error("Failed to save information to database");
        } else {
            log.info("Contact with email id: {} got added to database...", contact.getEmail());
        }
        return contact == null;
    }

    @Override
    public Contact findContactByEmail(String email) {
        Contact result = contactRepository.findContactByEmail(email);
        if (result == null) {
            log.error("Failed to find contact with email id: {}", email);
        }
        return contactRepository.findContactByEmail(email);
    }

}
