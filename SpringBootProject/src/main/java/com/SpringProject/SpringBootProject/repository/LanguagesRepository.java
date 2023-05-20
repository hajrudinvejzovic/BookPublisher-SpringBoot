package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguagesRepository  extends JpaRepository<Languages,Long> {
}
