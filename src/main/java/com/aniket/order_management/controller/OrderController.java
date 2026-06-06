package com.aniket.order_management.controller;


import com.aniket.order_management.model.Order;
import com.aniket.order_management.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // GET order By Id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getAllOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // GET orders by customer name
    @GetMapping("/coustomer/{coustomeName}")
    public ResponseEntity<List<Order>> getOrderByCoustomer(@PathVariable String coustomerName){
        return ResponseEntity.ok(orderService.findOrderByCustomer(coustomerName));
    }

    //Post - Create New Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.status(201)
                .body(orderService.createOrder(order));
    }

    // PUT - update order status
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ){
        return ResponseEntity.ok(orderService.updateOrderStatus(id,status));
    }

    //Delete - Deleting Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order Deleted Succesfully");
    }

}


















