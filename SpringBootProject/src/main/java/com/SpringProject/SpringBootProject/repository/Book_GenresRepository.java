package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Book_Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Book_GenresRepository  extends JpaRepository<Book_Genres,Long> {
}
