package team404.restaurant.dish.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.dish.dto.DishDto;
import team404.restaurant.dish.service.DishService;

import java.util.List;

@Tag(name = "Dish")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class DishController {

    private final DishService dishService;

    @Operation(summary = "Add dish")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/dish")
    public void addDish(@RequestBody DishDto dishDto) {
        dishService.submit(dishDto);
    }

    @Operation(summary = "Get dishes in menu")
    @GetMapping("/api/dish")
    public List<DishDto> getAllByMenuId(@RequestParam Long menuId) {
        return dishService.getAllByMenuId(menuId);
    }
}
