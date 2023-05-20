package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Employees;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping
    public Page<Employees> findAllEmployees(Pageable pageable){
        return this.employeesRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Employees findEmployeeById(@PathVariable(value = "id") long employeeId){
        return this.employeesRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with this Id NOT FOUND!" + employeeId));
    }
    @PostMapping
    public Employees createEmployee(@RequestBody Employees employee){
        return this.employeesRepository.save(employee);
    }
    @PutMapping("/{id")
    public Employees updateEmployee(@RequestBody Employees employee, @PathVariable (value = "id") long employeeId){
        Employees existingEmployee = this.employeesRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with this Id NOT FOUND!" + employeeId));
        existingEmployee.setName(employee.getName());
        existingEmployee.setSurname(existingEmployee.getSurname());
        existingEmployee.setCity(existingEmployee.getCity());
        existingEmployee.setContact(existingEmployee.getContact());
        return this.employeesRepository.save(existingEmployee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Employees> deleteEmployee(@PathVariable(value = "id") long employeeId){
        Employees existingEmployee = this.employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with this id NOT FOUND: " + employeeId));
        this.employeesRepository.delete(existingEmployee);
        return ResponseEntity.ok().build();
    }
}
