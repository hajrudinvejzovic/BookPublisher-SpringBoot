package com.SpringProject.SpringBootProject.controller;

import com.SpringProject.SpringBootProject.entity.Payments;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentsController {
    @Autowired
    private PaymentsRepository paymentsRepository;

    @GetMapping
    public Page<Payments> findAllPayments(Pageable pageable){
        return this.paymentsRepository.findAll(pageable);
    }


    @GetMapping("/{id}")
    public Payments findPaymentById(@PathVariable(value = "id") long paymentId){
        return this.paymentsRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with this Id NOT FOUND!" + paymentId));
    }
    @PostMapping
    public Payments createPayment(@RequestBody Payments payment){
        return this.paymentsRepository.save(payment);
    }

    @PutMapping("/{id}")
    public Payments updatePayment(@RequestBody Payments payment, @PathVariable(value = "id") long paymentId){
        Payments existingPayment = this.paymentsRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment with this Id NOT FOUND" + paymentId));
        existingPayment.setPayment_date(existingPayment.getPayment_date());
        existingPayment.setAmount(existingPayment.getAmount());
        existingPayment.setOrder(existingPayment.getOrder());
        existingPayment.setUser(existingPayment.getUser());
        existingPayment.setUsers_id(existingPayment.getUsers_id());
        return this.paymentsRepository.save(existingPayment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Payments> deletePayment(@PathVariable(value = "id") long paymentId){
        Payments existingPayment = this.paymentsRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with this id NOT FOUND: " + paymentId));
        this.paymentsRepository.delete(existingPayment);
        return ResponseEntity.ok().build();
    }
}
