package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Cities;
import com.SpringProject.SpringBootProject.entity.Contacts;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {
    @Autowired
    private ContactsRepository contactsRepository;

    @GetMapping
    public Page<Contacts> findAllContacts(Pageable pageable){
        return this.contactsRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Contacts findContactsById(@PathVariable(value ="id") long contactId){
        return this.contactsRepository.findById(contactId)
                .orElseThrow(()-> new ResourceNotFoundException("Contact with this Id NOT FOUND!"+ contactId));
    }
    @PostMapping
    public Contacts createContact(@RequestBody Contacts contact){
        return this.contactsRepository.save(contact);
    }
    @PutMapping("/{id}")
    public Contacts updateContact(@RequestBody Contacts contact, @PathVariable(value  ="id") long contactId){
        Contacts existingContact = this.contactsRepository.findById(contactId)
                .orElseThrow(()-> new ResourceNotFoundException("Contact with this Id NOT FOUND!"));
        existingContact.setUser(existingContact.getUser());
        existingContact.setEmployee(existingContact.getEmployee());
        existingContact.setEmail(existingContact.getEmail());
        existingContact.setTelephone(existingContact.getTelephone());
        return this.contactsRepository.save(existingContact);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Contacts> deleteContact(@PathVariable(value = "id") long contactId){
        Contacts existingContact = this.contactsRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact with this id NOT FOUND: " + contactId));
        this.contactsRepository.delete(existingContact);
        return ResponseEntity.ok().build();
    }
}
