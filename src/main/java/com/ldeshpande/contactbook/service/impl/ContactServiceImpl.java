package com.ldeshpande.contactbook.service.impl;

import com.ldeshpande.contactbook.exception.ContactException;
import com.ldeshpande.contactbook.exception.ErrorCode;
import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.repository.ContactRepository;
import com.ldeshpande.contactbook.service.ContactService;
import com.ldeshpande.contactbook.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact addContact(Contact contact) {
        Contact result;
        try {
            result = contactRepository.save(contact);
        } catch (Exception exception) {
            throw new ContactException(ErrorCode.CONTACT_NOT_SAVED, HelperUtil.convertToStackTrace(exception));
        }
        return result;
    }

    @Override
    public Contact findContactByEmail(String email) {
        Contact result = contactRepository.findContactByEmail(email);
        if (result == null) {
            throw new ContactException(ErrorCode.CONTACT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public Contact findContactByName(String name) {
        Contact result = contactRepository.findContactByName(name);
        if (result == null) {
            throw new ContactException(ErrorCode.CONTACT_NOT_FOUND);
        }
        return result;
    }

}
