package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Orders;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;


    @GetMapping
    public Page<Orders> findAllOrders(Pageable pageable){
        return this.ordersRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Orders findOrderById(@PathVariable(value = "id") long orderId){
        return this.ordersRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order with this Id NOT FOUND" + orderId));
    }
    @PostMapping
    public Orders createOrder(@RequestBody Orders order){
        return this.ordersRepository.save(order);
    }
    @PutMapping("/{id}")
    public Orders updateOrder(@RequestBody Orders order, @PathVariable(value = "id") long orderId){
        Orders existingUser = this.ordersRepository.findById(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Order with this Id NOT FOUND" + orderId));
        existingUser.setUser(existingUser.getUser());
        existingUser.setOrder(existingUser.getOrder());
        existingUser.setPayment(existingUser.getPayment());
        existingUser.setTotal_price(existingUser.getTotal_price());
        existingUser.setTotal_quantity(existingUser.getTotal_quantity());
        return this.ordersRepository.save(existingUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrder(@PathVariable(value = "id") long orderId){
        Orders existingOrder = this.ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with this id NOT FOUND: " + orderId));
        this.ordersRepository.delete(existingOrder);
        return ResponseEntity.ok().build();
    }

}
