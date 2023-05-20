package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository  extends JpaRepository<Cities,Long> {
}
