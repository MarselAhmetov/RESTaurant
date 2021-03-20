package team404.restaurant.restaurant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.restaurant.dto.RestaurantFilter;
import team404.restaurant.restaurant.service.RestaurantService;

import java.util.List;
import java.util.UUID;

@Api(value = "Restaurant Controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ApiOperation(value = "Add restaurant endpoint")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/restaurant")
    public void addRestaurant(@ApiParam(name = "Information of restaurant") @RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }

    @GetMapping("/api/restaurant/{restaurantId}")
    public RestaurantDto getRestaurant(@PathVariable UUID restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }
}
