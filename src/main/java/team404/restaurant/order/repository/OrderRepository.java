package team404.restaurant.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.order.model.Order;
import team404.restaurant.order.model.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> getAllByStatus(OrderStatus status);
    List<Order> getAllByStatusAndEmployee(OrderStatus status, Employee employee);
    Optional<Order> findByTable_IdAndStatusNot(UUID tableId, OrderStatus status);
}
