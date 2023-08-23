package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.PaymentsController;
import com.SpringProject.SpringBootProject.entity.Payments;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.PaymentsRepository;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentsControllerTests {
    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    PaymentsController paymentsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllPayments(){
        List<Payments> paymentsList = new ArrayList<>();
        LocalDate paymentDate = LocalDate.of(2023, 8, 20);
        paymentsList.add(new Payments(null, null, 1350, paymentDate));
        Page<Payments> paymentsPage = new PageImpl<>(paymentsList);

        when(paymentsRepository.findAll(any(Pageable.class))).thenReturn(paymentsPage);
        Page<Payments> result = paymentsController.findAllPayments(Pageable.unpaged());
        assertEquals(paymentsList.size(), result.getContent().size());
    }
    @Test
    public void testFindPaymentById(){
        LocalDate paymentDate = LocalDate.of(2023, 8, 20);
        Payments payment = new Payments(null, null, 1350, paymentDate);
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.of(payment));

        Payments result = paymentsController.findPaymentById(1L);
        assertEquals(payment.getAmount(),result.getAmount());
        assertEquals(payment.getPayment_date(),result.getPayment_date());
    }
    @Test
    public void testFindPaymentByIdNotFound(){
        LocalDate paymentDate = LocalDate.of(2023, 8, 20);
        Payments payment = new Payments(null, null, 1350, paymentDate);
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> paymentsController.findPaymentById(1L) );

    }
    @Test
    public void testCreatePayment(){
        LocalDate paymentDate = LocalDate.of(2023, 8, 20);
        Payments payment = new Payments(null, null, 1350, paymentDate);
        when(paymentsRepository.save(any(Payments.class))).thenReturn(payment);

        Payments result = paymentsController.createPayment(payment);
        assertEquals(payment.getAmount(),result.getAmount());
    }
    @Test
    public void testUpdatePayment(){
        LocalDate paymentDate = LocalDate.of(2023, 8, 20);
        Payments existingPayment = new Payments(null, null, 1350, paymentDate);
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.of(existingPayment));
        when(paymentsRepository.save(any(Payments.class))).thenReturn(existingPayment);

        LocalDate updatedPaymentDate = LocalDate.of(2023, 4, 14);
        Payments updatedPayment = new Payments(null,null,500, updatedPaymentDate);
        Payments result = paymentsController.updatePayment(updatedPayment,1L);
        assertEquals(existingPayment.getAmount(),result.getAmount());
        assertEquals(existingPayment.getPayment_date(),result.getPayment_date());

    }
    @Test
    public void testUpdatePaymentNotFound(){
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.empty());

        LocalDate updatedPaymentDate = LocalDate.of(2023, 4, 14);
        Payments updatedPayment = new Payments(null,null,500, updatedPaymentDate);

        assertThrows(ResourceNotFoundException.class, ()-> paymentsController.updatePayment(updatedPayment,1L));
    }
    @Test
    public void testDeletePayment(){
        LocalDate updatedPaymentDate = LocalDate.of(2023, 4, 14);
        Payments existingPayment = new Payments(null,null,500, updatedPaymentDate);
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.of(existingPayment));
        ResponseEntity<Payments> result = paymentsController.deletePayment(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeletePaymentNotFound(){
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> paymentsController.deletePayment(1L));
    }



}
