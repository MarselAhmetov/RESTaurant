package team404.restaurant.restaurateur.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team404.restaurant.restaurateur.dto.RestaurateurDto;
import team404.restaurant.restaurateur.service.RestaurateurService;

@Tag(name = "Restaurateur")
@RestController
@RequiredArgsConstructor
public class RestaurateurController {

    private final RestaurateurService restaurateurService;

    @Operation(summary = "Restaurateur submit")
    @PostMapping("/api/restaurateur")
    @ResponseBody
    public void submitRestaurateur(@RequestBody RestaurateurDto restaurateurDto) {
        restaurateurService.submit(restaurateurDto);
    }

    @Operation(summary = "Get information about current restaurateur")
    @GetMapping("/api/restaurateur")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public RestaurateurDto getCurrentRestaurateur() {
        return restaurateurService.getCurrentRestaurateur();
    }
}
