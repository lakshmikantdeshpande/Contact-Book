package com.ldeshpande.contactbook.controller;

import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "contacts/search")
    public ResponseEntity findContact(@RequestParam String by, @RequestParam String query) {
        switch (by.toUpperCase()) {
            case "EMAIL":
                return ResponseEntity.ok(contactService.findContactByEmail(query));
            case "NAME":
                return ResponseEntity.ok(contactService.findContactsByName(query));
            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "contacts")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @DeleteMapping(value = "contacts")
    public ResponseEntity<Void> deleteContact(@RequestParam String email) {
        contactService.deleteContact(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "contacts")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.updateContact(contact));
    }

    @GetMapping(value = "contacts/all")
    public ResponseEntity<List<Contact>> getAllContacts(@RequestParam Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(contactService.findAll(page, limit));
    }

}
