package com.SpringProject.SpringBootProject;
import com.SpringProject.SpringBootProject.controller.OrdersController;
import com.SpringProject.SpringBootProject.entity.Orders;
import com.SpringProject.SpringBootProject.exception.ResourceNotFoundException;
import com.SpringProject.SpringBootProject.repository.OrdersRepository;
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
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdersControllerTests {
    @Mock
    OrdersRepository ordersRepository;
    @InjectMocks
    OrdersController ordersController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testFindAllOrders(){
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(new Orders(null, 70,750,null, null));
        Page<Orders> ordersPage = new PageImpl<>(ordersList);

        when(ordersRepository.findAll(any(Pageable.class))).thenReturn(ordersPage);
        Page<Orders> result = ordersController.findAllOrders(Pageable.unpaged());
        assertEquals(ordersList.size(), result.getContent().size());
    }
    @Test
    public void testFindOrderById(){
        Orders order = new Orders(null, 70,750,null, null);
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(order));

        Orders result = ordersController.findOrderById(1L);
        assertEquals(order.getTotal_price(),result.getTotal_price());
    }
    @Test
    public void testFindOrderByIdNotFound(){
        Orders orders = new Orders(null, 70,750,null, null);
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()-> ordersController.findOrderById(1L) );

    }
    @Test
    public void testCreateOrder(){
        Orders order = new Orders(null, 55,700,null, null);
        when(ordersRepository.save(any(Orders.class))).thenReturn(order);

        Orders result = ordersController.createOrder(order);
        assertEquals(order.getTotal_quantity(),result.getTotal_quantity());
        assertEquals(order.getTotal_price(), result.getTotal_price());
    }
    @Test
    public void testUpdateOrder(){
        Orders existingOrder = new Orders(null, 55,700,null, null);

        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(existingOrder));
        when(ordersRepository.save(any(Orders.class))).thenReturn(existingOrder);

        Orders updatedOrder = new Orders(null, 30,420,null, null);
        Orders result = ordersController.updateOrder(updatedOrder,1L);
        assertEquals(existingOrder.getTotal_price(),result.getTotal_price());

    }
    @Test
    public void testUpdateOrderNotFound(){
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        Orders updatedOrder = new Orders(null, 30,420,null, null);

        assertThrows(ResourceNotFoundException.class, ()-> ordersController.updateOrder(updatedOrder,1L));
    }
    @Test
    public void testDeleteOrder(){
        Orders exisitingOrder = new Orders(null, 30,420,null, null);
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(exisitingOrder));
        ResponseEntity<Orders> result = ordersController.deleteOrder(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void testDeleteOrderNotFound(){
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> ordersController.deleteOrder(1L));
    }





}
