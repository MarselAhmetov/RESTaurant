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
    OrderDto getById(UUID orderId);
    Boolean takeOrder(OrderDto orderDto);
    void closeOrder(OrderDto orderDto);
    OrderDto getOrderByTable(UUID tableId);
    List<OrderDto> getMyOrders();
    List<OrderDto> getOrderByStatus(OrderStatus status);
}
