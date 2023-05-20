package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository  extends JpaRepository<Employees,Long> {
}
