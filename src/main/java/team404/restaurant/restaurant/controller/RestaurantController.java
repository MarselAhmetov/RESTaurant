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
import team404.restaurant.restaurant.dto.RestaurantFilter;
import team404.restaurant.restaurant.service.RestaurantService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Restaurant")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Add restaurant endpoint")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/restaurant")
    public void addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.addRestaurant(restaurantDto);
    }

    @GetMapping("/api/restaurant/{restaurantId}")
    public RestaurantDto getRestaurant(@PathVariable UUID restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @GetMapping("/api/restaurant")
        public List<RestaurantDto> getRestaurants(@RequestParam(name = "restaurateurId") UUID restaurateurId) {
        RestaurantFilter filter = RestaurantFilter.builder()
                .restaurateurId(restaurateurId)
                .build();
        return restaurantService.getRestaurants(filter);
    }

}
