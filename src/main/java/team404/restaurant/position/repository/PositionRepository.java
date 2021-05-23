package team404.restaurant.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.order.model.Order;
import team404.restaurant.order.model.OrderStatus;
import team404.restaurant.position.model.Position;
import team404.restaurant.position.model.PositionStatus;

import java.util.List;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> getPositionsByStatus(PositionStatus positionStatus);
    List<Position> getPositionsByStatusAndCook(PositionStatus status, Employee employee);
}
