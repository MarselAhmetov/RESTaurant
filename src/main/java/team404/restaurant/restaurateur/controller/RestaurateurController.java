package team404.restaurant.restaurateur.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.employee.dto.EmployeeWithPasswordDto;
import team404.restaurant.restaurateur.dto.RestaurateurDto;
import team404.restaurant.restaurateur.service.RestaurateurService;

@Tag(name = "Restaurateur")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class RestaurateurController {

    private final RestaurateurService restaurateurService;

    @Operation(summary = "Restaurateur submit")
    @PostMapping("/api/restaurateur")
    public void submitRestaurateur(@RequestBody RestaurateurDto restaurateurDto) {
        restaurateurService.submit(restaurateurDto);
    }

    @Operation(summary = "Get information about current restaurateur")
    @GetMapping("/api/restaurateur")
    @PreAuthorize("isAuthenticated()")
    public RestaurateurDto getCurrentRestaurateur() {
        return restaurateurService.getCurrentRestaurateur();
    }

    @Operation(summary = "Get information about current restaurateur")
    @PostMapping("/api/restaurateur/employee")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    public void createEmployeeAccount(@RequestBody EmployeeWithPasswordDto employee) {
        restaurateurService.createEmployeeAccount(employee);
    }
}
