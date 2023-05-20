package com.SpringProject.SpringBootProject.repository;

import com.SpringProject.SpringBootProject.entity.Book_Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book_AuthorsRepository  extends JpaRepository<Book_Authors,Long> {
}
