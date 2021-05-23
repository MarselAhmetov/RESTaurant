package team404.restaurant.menu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.menu.dto.MenuDto;
import team404.restaurant.menu.service.MenuService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Menu")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "Add menu")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/menu")
    public void addMenu(@RequestBody MenuDto menuDto) {
        menuService.submit(menuDto);
    }

    @Operation(summary = "Get menus by restaurant id")
    @GetMapping("/api/menus")
    public List<MenuDto> getMenusInRestaurant(@RequestParam UUID restaurantId) {
        return menuService.getMenusByRestaurant(restaurantId);
    }
}
