package com.miraijr.karaoke.application.order;

import com.miraijr.karaoke.application.room.RoomEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderEntity initOrder() {
        OrderEntity order = new OrderEntity();

        return orderRepository.save(order);
    }

    public OrderEntity getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!"));
    }
}
