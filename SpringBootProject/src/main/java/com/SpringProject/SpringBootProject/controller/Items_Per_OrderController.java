package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Items_Per_Order;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.Items_Per_OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/items-per-order")
public class Items_Per_OrderController {
    @Autowired
    private Items_Per_OrderRepository items_per_orderRepository;
    @GetMapping
    public Page<Items_Per_Order> findAllItemsPerOrder(Pageable pageable){
        return this.items_per_orderRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public Items_Per_Order findItemsPerOrderById(@PathVariable(value = "id") long itemsPerOrderId){
        return this.items_per_orderRepository.findById(itemsPerOrderId)
                .orElseThrow(()-> new ResourceNotFoundException("Items Per Order with this Id NOT FOUND!" + itemsPerOrderId));
    }
    @PostMapping
    public Items_Per_Order createItemsPerOrder(@RequestBody Items_Per_Order itemsPerOrder){
        return this.items_per_orderRepository.save(itemsPerOrder);
    }
    @PutMapping("/{id}")
    public Items_Per_Order updateItemsPerOrder(@RequestBody Items_Per_Order itemsPerOrder, @PathVariable(value = "id") long itemsPerOrderId){
        Items_Per_Order existingItemsPerOrder = this.items_per_orderRepository.findById(itemsPerOrderId)
                .orElseThrow(()-> new ResourceNotFoundException("Items Per Order with this Id NOT FOUND"  + itemsPerOrderId));
        existingItemsPerOrder.setOrder(existingItemsPerOrder.getOrder());
        existingItemsPerOrder.setBook(existingItemsPerOrder.getBook());
        existingItemsPerOrder.setQuantity(existingItemsPerOrder.getQuantity());
        return this.items_per_orderRepository.save(existingItemsPerOrder);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Items_Per_Order> deleteItemPerOrder(@PathVariable(value = "id") long itemPerOrderId){
        Items_Per_Order existingItemPerOrder = this.items_per_orderRepository.findById(itemPerOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemPerOrder with this id NOT FOUND: " + itemPerOrderId));
        this.items_per_orderRepository.delete(existingItemPerOrder);
        return ResponseEntity.ok().build();
    }
}
