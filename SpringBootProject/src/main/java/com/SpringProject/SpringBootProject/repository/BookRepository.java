package com.SpringProject.SpringBootProject.repository;

import com.SpringProject.SpringBootProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
