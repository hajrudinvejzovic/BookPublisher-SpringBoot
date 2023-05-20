package com.SpringProject.SpringBootProject.repository;
import com.SpringProject.SpringBootProject.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BooksRepository  extends JpaRepository<Books,Long> {
}
