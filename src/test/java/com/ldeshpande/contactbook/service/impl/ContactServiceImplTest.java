package com.ldeshpande.contactbook.service.impl;

import com.ldeshpande.contactbook.exception.ContactException;
import com.ldeshpande.contactbook.exception.ErrorCode;
import com.ldeshpande.contactbook.model.Address;
import com.ldeshpande.contactbook.model.Contact;
import com.ldeshpande.contactbook.repository.ContactRepository;
import com.ldeshpande.contactbook.service.ContactService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactService contactService = new ContactServiceImpl();

    @Mock
    private ContactRepository contactRepository;

    private Contact contact;

    @Before
    public void setup() {
        initContact();
    }

    private void initContact() {
        contact = new Contact();
        contact.setName("ABC");
        contact.setPhoneNumber(9999999999L);
        contact.setEmail("abc@gmail.com");
        Address address = new Address();
        address.setAddressLine1("ABCDEF");
        address.setCountry("India");
        address.setState("MH");
        address.setLandmark("NOWHERE");
        address.setZipCode(44444);
        contact.setAddress(null);
    }

    @Test
    public void addContactTest() {
        Mockito.when(contactRepository.save(contact)).thenAnswer(x -> {
            Contact toBeReturned = x.getArgument(0);
            String email = toBeReturned.getEmail();
            if (email != null && email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                toBeReturned.setId(1L);
                return toBeReturned;
            }
            return null;
        });
        Contact result = new Contact();
        BeanUtils.copyProperties(contact, result);
        result.setId(1L);
        Contact temp = contactService.addContact(contact);
        Assert.assertEquals(temp, result);
    }

    @Test(expected = ContactException.class)
    public void addContactExistingContactTest() {
        Mockito.when(contactRepository.save(contact)).thenAnswer(x -> {
            Contact toBeReturned = x.getArgument(0);
            String email = toBeReturned.getEmail();
            if (email == null || email.equals("abc@gmail.com")) {
                throw new ContactException(ErrorCode.CONTACT_NOT_SAVED);
            }
            toBeReturned.setId(1L);
            return toBeReturned;
        });

        contactService.addContact(contact);
    }


    @Test
    public void findContactByEmailTest() {
        Mockito.when(contactRepository.findContactByEmail("abc@gmail.com")).thenReturn(contact);
        Contact result = contactService.findContactByEmail("abc@gmail.com");
        Assert.assertEquals(result, contact);
    }

    @Test(expected = ContactException.class)
    public void findContactByEmailNegativeTest() {
        contactService.findContactByEmail("def@gmail.com");
    }

    @Test
    public void findContactByNameTest() {
        Mockito.when(contactRepository.findContactsByName("ABC")).thenReturn(Collections.singletonList(contact));
        List<Contact> result = contactService.findContactsByName("ABC");
        Assert.assertEquals(result.get(0), contact);
    }

    @Test(expected = ContactException.class)
    public void findContactByNameNegativeTest() {
        contactService.findContactsByName("DEF");
    }

    @Test
    public void deleteContactTest() {
        Mockito.when(contactRepository.deleteContactByEmail("abc@gmail.com")).thenReturn(1);
        contactService.deleteContact("abc@gmail.com");
    }

    @Test(expected = ContactException.class)
    public void deleteContactNegativeTest() {
        Mockito.when(contactRepository.deleteContactByEmail("abc@gmail.com")).thenReturn(0);
        contactService.deleteContact("abc@gmail.com");
    }

    @Test
    public void updateContactTest() {
        Mockito.when(contactRepository.findContactByEmail("abc@gmail.com")).thenReturn(contact);
        Mockito.when(contactRepository.save(contact)).thenAnswer(x -> {
            Contact toBeReturned = x.getArgument(0);
            toBeReturned.setId(1L);
            return toBeReturned;
        });

        contact.setName("aaa");
        Contact result = contactService.updateContact(contact);
        Assert.assertEquals(contact, result);
    }

    @Test(expected = ContactException.class)
    public void updateContactNegativeEmailTest() {
        Mockito.when(contactRepository.findContactByEmail("abc@gmail.com")).thenReturn(null);
        contactService.updateContact(contact);
    }

    @Test(expected = ContactException.class)
    public void updateContactNegativeSaveTest() {
        Mockito.when(contactRepository.findContactByEmail("abc@gmail.com")).thenReturn(contact);
        Mockito.when(contactRepository.save(contact)).thenAnswer(x -> {
            throw new ContactException(ErrorCode.CONTACT_NOT_UPDATED);
        });

        contact.setName("aaa");
        Contact result = contactService.updateContact(contact);
        Assert.assertEquals(contact, result);
    }

    @Test
    public void findAll() {
        Page<Contact> page = new PageImpl<>(Collections.singletonList(contact));
        Pageable pageable = PageRequest.of(1, 10);
        Mockito.when(contactRepository.findAll(pageable)).thenReturn(page);
        List<Contact> contacts = contactService.findAll(1, 10);
        Assert.assertFalse(contacts.isEmpty());
    }

}
