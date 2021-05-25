package team404.restaurant.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.employee.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> getAllByRestaurant_Id(UUID restaurantId);
    Employee getEmployeeByAccount_Id(Long accountId);
}
