package com.ldeshpande.contactbook.repository;

import com.ldeshpande.contactbook.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    Contact save(Contact contact);

    Contact findContactByEmail(String email);

    List<Contact> findContactsByName(String name);

    int deleteContactByEmail(String email);

    Page<Contact> findAll(Pageable pageable);

}
