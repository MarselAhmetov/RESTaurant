package team404.restaurant.order.service;

import team404.restaurant.order.dto.OrderDto;
import team404.restaurant.order.model.Order;
import team404.restaurant.order.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    void save(Order order);
    void changeStatus(UUID orderId, OrderStatus status);
    void edit(OrderDto orderDto);
    Boolean takeOrder(OrderDto orderDto);
    List<OrderDto> getMyOrders();
    List<OrderDto> getOrderByStatus(OrderStatus status);
    void closeOrder(OrderDto orderDto);
}
