package com.ldeshpande.contactbook.controller;

import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController(value = "/api/v1")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contacts")
    public Contact findContactByEmail(@RequestParam String email) {
        log.info("Getting contact by email id: {}", email);
        return contactService.findContactByEmail(email);
    }

}
