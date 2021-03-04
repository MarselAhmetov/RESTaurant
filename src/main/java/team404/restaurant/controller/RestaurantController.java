package team404.restaurant.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.dto.RestaurantDto;
import team404.restaurant.service.RestaurantService;

@Api(value = "Restaurant Controller")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @ApiOperation(value = "Add restaurant endpoint")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/addRestaurant")
    public void addRestaurant(@ApiParam(name = "Information of restaurant") @RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }
}
