package com.SpringProject.SpringBootProject.repository;

import com.SpringProject.SpringBootProject.entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors,Long> {
}
