package com.aniket.order_management.service;


import com.aniket.order_management.kafka.OrderProducer;
import com.aniket.order_management.model.Order;
import com.aniket.order_management.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProducer orderProducer;

    // Finding All Orders
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    // Find Order By Id
    public Order getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));
    }

    // Find order By Coustomer Name
    public List<Order> findOrderByCustomer(String customerName){
        return orderRepository.findOrderByCustomerName(customerName);
    }

    //Cretae Order
    public Order createOrder(Order order){
        order.setStatus("Pending");
        Order saveOrder= orderRepository.save(order);


        // Send Kafka event
        orderProducer.sendOrderEvent(
                "New order placed by: " + order.getCustomerName() +
                        " for product: " + order.getProduct()
        );

        return saveOrder;
    }

    // Update order status
    public Order updateOrderStatus(Long id,String status){
        Order order=getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    //Deleting Order
    public void deleteOrderById(Long id){
        orderRepository.deleteById(id);
    }
}



