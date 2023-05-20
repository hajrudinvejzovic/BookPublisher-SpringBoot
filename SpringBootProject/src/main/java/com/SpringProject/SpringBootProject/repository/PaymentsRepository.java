package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository  extends JpaRepository<Payments,Long> {
}
