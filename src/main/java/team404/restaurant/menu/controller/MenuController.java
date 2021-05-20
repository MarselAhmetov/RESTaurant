package team404.restaurant.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(value = "Menu controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class MenuController {

    private final MenuService menuService;

    @ApiOperation(value = "Add menu")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/menu")
    public void addMenu(@ApiParam(name = "Menu information") @RequestBody MenuDto menuDto) {
        menuService.submit(menuDto);
    }

    @ApiOperation(value = "Get menus by restaurant id")
    @GetMapping("/api/menus")
    public List<MenuDto> getMenusInRestaurant(@ApiParam(name = "Restaurant id") @RequestParam UUID restaurantId) {
        return menuService.getMenusByRestaurant(restaurantId);
    }
}
