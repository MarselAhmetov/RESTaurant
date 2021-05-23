package team404.restaurant.employee.service;

import team404.restaurant.account.model.Account;
import team404.restaurant.employee.dto.EmployeeDto;
import team404.restaurant.employee.dto.EmployeeWithPasswordDto;
import team404.restaurant.employee.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeDto> getEmployeesByRestaurant(UUID restaurant);
    EmployeeWithPasswordDto getEmployeeWithAccount(UUID employeeId);
    EmployeeDto getEmployee(UUID employeeId);
    Employee getEmployeeByAccount(Account account);
}
