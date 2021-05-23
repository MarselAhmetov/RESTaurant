package team404.restaurant.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.employee.dto.EmployeeDto;
import team404.restaurant.employee.dto.EmployeeWithPasswordDto;
import team404.restaurant.employee.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Employee")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Get employees information by restaurant id")
    @GetMapping("/api/employee/restaurant")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    public List<EmployeeDto> getEmployeesInRestaurant(@RequestParam UUID restaurantId) {
        return employeeService.getEmployeesByRestaurant(restaurantId);
    }

    @Operation(summary = "Get employee with password by employee id")
    @GetMapping("/api/employee/account/{employeeId}")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    public EmployeeWithPasswordDto getEmployeeWithPassword(@PathVariable UUID employeeId) {
        return employeeService.getEmployeeWithAccount(employeeId);
    }

    @Operation(summary = "Get current employee")
    @GetMapping("/api/employee/current")
    @PreAuthorize("hasAnyAuthority('WAITER', 'COOK')")
    public EmployeeDto getCurrentEmployee() {
        return employeeService.getCurrentEmployee();
    }
}
