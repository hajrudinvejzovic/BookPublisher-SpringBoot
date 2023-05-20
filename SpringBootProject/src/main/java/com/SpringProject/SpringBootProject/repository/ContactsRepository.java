package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository  extends JpaRepository<Contacts,Long> {
}
