package team404.restaurant.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.restaurant.service.RestaurantService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Restaurant")
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Add restaurant")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/restaurant")
    public void addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }

    @Operation(summary = "Get restaurant by id")
    @GetMapping("/api/restaurant/{restaurantId}")
    public RestaurantDto getRestaurant(@PathVariable UUID restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @Operation(summary = "Get restaurants")
    @GetMapping("/api/restaurant")
    public List<RestaurantDto> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @Operation(summary = "Get restaurants by restaurantId")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @GetMapping("/api/restaurant/restaurateur")
    public List<RestaurantDto> getRestaurantsByRestaurantId(@RequestParam UUID restaurateurId) {
        return restaurantService.getRestaurantsByRestaurateur(restaurateurId);
    }

}
