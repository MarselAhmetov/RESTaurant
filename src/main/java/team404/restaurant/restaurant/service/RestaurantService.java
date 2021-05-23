package team404.restaurant.restaurant.service;

import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.restaurant.dto.RestaurantFilter;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    void addRestaurant(RestaurantDto restaurantDto);
    RestaurantDto getRestaurantById(UUID restaurantId);
    List<RestaurantDto> getRestaurants(RestaurantFilter filter);
    List<RestaurantDto> getRestaurants();
    List<RestaurantDto> getRestaurantsByRestaurateur(UUID restaurateurId);

}
