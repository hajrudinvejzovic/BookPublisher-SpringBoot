package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.EmployeeController;
import com.SpringProject.SpringBootProject.entity.Employees;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.EmployeesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class EmployeeControllerTests {
    @Mock
    EmployeesRepository employeesRepository;
    @InjectMocks
    EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllEmployees(){
        List<Employees> employeesList = new ArrayList<>();
        employeesList.add(new Employees("Hajrudin", "Vejzović", null, null));

        Page<Employees> employeesPage = new PageImpl<>(employeesList);

        when(employeesRepository.findAll(any(Pageable.class))).thenReturn(employeesPage);
        Page<Employees> result = employeeController.findAllEmployees(Pageable.unpaged());
        assertEquals(employeesList.size(), result.getContent().size());

    }
    @Test
    public void testFindEmployeeById(){
        Employees employee = new Employees("Hajrudin", "Vejzović", null, null);
        when(employeesRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        Employees result = employeeController.findEmployeeById(1L);
        assertEquals(employee.getName(),result.getName());
        assertEquals(employee.getSurname(),result.getSurname());

    }
    @Test
    public void testFindEmployeeByIdNotFound(){
        Employees employee = new Employees("Hajrudin", "Vejzović", null, null);
        when(employeesRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> employeeController.findEmployeeById(1L) );

    }
    @Test
    public void testCreateEmployee(){
        Employees employee = new Employees("Hajrudin", "Vejzović", null, null);
        when(employeesRepository.save(any(Employees.class))).thenReturn(employee);

        Employees result = employeeController.createEmployee(employee);
        assertEquals(employee.getName(),result.getName());
        assertEquals(employee.getSurname(),result.getSurname());

    }
    @Test
    public void testUpdateEmployee(){
        Employees existingEmployee =  new Employees("Hajrudin", "Vejzović", null, null);

        when(employeesRepository.findById(anyLong())).thenReturn(Optional.of(existingEmployee));
        when(employeesRepository.save(any(Employees.class))).thenReturn(existingEmployee);

        Employees updatedEmployee = new Employees("UpdatedName", "UpdatedSurname", null, null);
        Employees result = employeeController.updateEmployee(updatedEmployee,1L);
        assertEquals(existingEmployee.getName(),result.getName());
        assertEquals(existingEmployee.getSurname(),result.getSurname());

    }
    @Test
    public void testUpdateEmployeeNotFound(){
        when(employeesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Employees updatedEmployee = new Employees("UpdatedName", "UpdatedSurname", null, null);

        assertThrows(ResourceNotFoundException.class, ()-> employeeController.updateEmployee(updatedEmployee,1L));
    }
    @Test
    public void testDeleteEmployee(){
        Employees existingEmployee =  new Employees("Hajrudin", "Vejzović", null, null);
        when(employeesRepository.findById(anyLong())).thenReturn(Optional.of(existingEmployee));
        ResponseEntity<Employees> result = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeleteEmployeeNotFound(){
        when(employeesRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> employeeController.deleteEmployee(1L));
    }

}
