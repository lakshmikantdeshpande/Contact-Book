package com.ldeshpande.contactbook.controller;

import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "contacts", params = "email")
    public ResponseEntity<Contact> findContactByEmail(@RequestParam String email) {
        return ResponseEntity.ok(contactService.findContactByEmail(email));
    }

    @GetMapping(value = "contacts", params = "name")
    public ResponseEntity<Contact> findContactByName(@RequestParam String name) {
        return ResponseEntity.ok(contactService.findContactByName(name));
    }

    @PostMapping(value = "contacts")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.addContact(contact));
    }

}
