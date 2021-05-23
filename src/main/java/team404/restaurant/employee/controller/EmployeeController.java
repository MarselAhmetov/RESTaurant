package team404.restaurant.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Get information about current restaurateur")
    @GetMapping("/api/employee/restaurant")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    public List<EmployeeDto> getEmployeesInRestaurant(@RequestParam UUID restaurantId) {
        return employeeService.getEmployeesByRestaurant(restaurantId);
    }

    @Operation(summary = "Get information about current restaurateur")
    @GetMapping("/api/employee/account/{employeeId}")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    public EmployeeWithPasswordDto getEmployeeWithPassword(@PathVariable UUID employeeId) {
        return employeeService.getEmployeeWithAccount(employeeId);
    }
}
