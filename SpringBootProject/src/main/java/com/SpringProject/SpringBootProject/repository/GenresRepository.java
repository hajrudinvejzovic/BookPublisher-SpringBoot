package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository  extends JpaRepository<Genres,Long> {
}
