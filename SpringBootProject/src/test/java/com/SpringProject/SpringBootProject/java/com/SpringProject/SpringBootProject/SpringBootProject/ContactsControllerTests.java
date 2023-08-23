package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.ContactsController;
import com.SpringProject.SpringBootProject.entity.Contacts;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.ContactsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ContactsControllerTests {
    @Mock
    ContactsRepository contactsRepository;
    @InjectMocks
    ContactsController contactsController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllContacts(){
        List<Contacts> contactsList = new ArrayList<>();
        contactsList.add(new Contacts(null, null, "060888888", "email@gmail.com"));

        Page<Contacts> contactsPage = new PageImpl<>(contactsList);

        when(contactsRepository.findAll(any(Pageable.class))).thenReturn(contactsPage);
        Page<Contacts> result = contactsController.findAllContacts(Pageable.unpaged());
        assertEquals(contactsList.size(),result.getContent().size());


    }
    @Test
    public void testFindContactById(){
        Contacts contact = new Contacts(null, null, "060888888", "email@gmail.com");
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.of(contact));

        Contacts result = contactsController.findContactsById(1L);
        assertEquals(contact.getTelephone(),result.getTelephone());
        assertEquals(contact.getEmail(),result.getEmail());
    }
    @Test
    public void testFindContactByIdNotFound(){
        Contacts contact  = new Contacts(null, null, "060888888", "email@gmail.com");
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> contactsController.findContactsById(1L));
    }
    @Test
    public void testCreateContact(){
        Contacts contact = new Contacts(null, null, "060888888", "email@gmail.com");
        when(contactsRepository.save(any(Contacts.class))).thenReturn(contact);

        Contacts result = contactsController.createContact(contact);
        assertEquals(result.getEmail(),contact.getEmail());
        assertEquals(result.getTelephone(),contact.getTelephone());

    }
    @Test
    public void testUpdateContact(){
        Contacts existingContact = new Contacts(null, null, "060888888", "email@gmail.com");
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.of(existingContact));
        when(contactsRepository.save(any(Contacts.class))).thenReturn(existingContact);

        Contacts updatedContact = new Contacts(null, null, "061111111", "email.email@gmail.com");
        Contacts result = contactsController.findContactsById(2L);
        assertEquals(updatedContact.getTelephone(),result.getTelephone());
        assertEquals(updatedContact.getEmail(),result.getEmail());

    }
    @Test
    public void testUpdateContactNotFound(){
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.empty());

        Contacts updatedContact = new Contacts(null, null, "061111111", "email.email@gmail.com");

        assertThrows(ResourceNotFoundException.class,() -> contactsController.updateContact(updatedContact,1L));
    }
    @Test
    public void testDeleteContact(){
        Contacts existingContact = new Contacts(null, null, "061111111", "email.email@gmail.com");
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.of(existingContact));

        ResponseEntity<Contacts> result = contactsController.deleteContact(2L);
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }
    @Test
    public void testDeleteContactNotFound(){
        when(contactsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> contactsController.deleteContact(1L));

    }



}
