package team404.restaurant.dish.controller;

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
import team404.restaurant.dish.dto.DishDto;
import team404.restaurant.dish.service.DishService;

import java.util.List;

@Api(value = "Dish controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class DishController {

    private final DishService dishService;

    @ApiOperation(value = "Add dish")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/dish")
    public void addDish(@ApiParam(name = "Dish information") @RequestBody DishDto dishDto) {
        dishService.submit(dishDto);
    }

    @ApiOperation(value = "Get dishes in menu")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @GetMapping("/api/dish")
    public List<DishDto> getAllByMenuId(@RequestParam Long menuId) {
        return dishService.getAllByMenuId(menuId);
    }
}
