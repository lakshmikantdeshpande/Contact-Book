package com.ldeshpande.contactbook.repository;

import com.ldeshpande.contactbook.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    Contact save(Contact contact);

    Contact findContactByEmail(String email);

    Contact findContactByName(String name);

    int deleteContactByEmail(String email);

    List<Contact> findAll();

}
