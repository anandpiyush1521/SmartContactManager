package com.scm.services;

import com.scm.entities.Contact;
import com.scm.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ContactService {
    //save contacts
    Contact save(Contact contact);

    //update contacts
    Contact update(Contact contact);

    //get contacts
    List<Contact> getAll();

    //get contacts by id
    Contact getById(String id);

    //delete contacts
    void delete(String id);

    //search contacts
    List<Contact> search(String name, String email, String phoneNumber);

    //get contact by userid
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);
}
