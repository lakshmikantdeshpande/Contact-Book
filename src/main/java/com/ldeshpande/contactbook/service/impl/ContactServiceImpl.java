package com.ldeshpande.contactbook.service.impl;

import com.ldeshpande.contactbook.exception.ContactException;
import com.ldeshpande.contactbook.exception.ErrorCode;
import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.repository.ContactRepository;
import com.ldeshpande.contactbook.service.ContactService;
import com.ldeshpande.contactbook.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Contact> findContactsByName(String name) {
        List<Contact> contacts = contactRepository.findContactsByName(name);
        if (contacts == null || contacts.isEmpty()) {
            throw new ContactException(ErrorCode.CONTACT_NOT_FOUND);
        }
        return contacts;
    }

    @Override
    public void deleteContact(String email) {
        int deleteCount = contactRepository.deleteContactByEmail(email);
        if (deleteCount == 0) {
            throw new ContactException(ErrorCode.CONTACT_NOT_DELETED);
        }
    }

    @Override
    public List<Contact> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Contact> pageResult = contactRepository.findAll(pageable);
        return pageResult.getContent();
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact originalContact = contactRepository.findContactByEmail(contact.getEmail());
        if (originalContact == null) {
            throw new ContactException(ErrorCode.CONTACT_NOT_UPDATED);
        } else {
            originalContact.setAddress(contact.getAddress());
            originalContact.setName(contact.getName());
            originalContact.setPhoneNumber(contact.getPhoneNumber());
            try {
                originalContact = contactRepository.save(originalContact);
            } catch (Exception exception) {
                throw new ContactException(ErrorCode.CONTACT_NOT_UPDATED, HelperUtil.convertToStackTrace(exception));
            }
        }
        return originalContact;
    }

}
