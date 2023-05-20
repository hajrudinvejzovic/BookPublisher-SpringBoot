package com.SpringProject.SpringBootProject.repository;


import com.SpringProject.SpringBootProject.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository  extends JpaRepository<Orders,Long> {
}
