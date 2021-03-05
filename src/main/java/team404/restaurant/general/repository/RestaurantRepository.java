package team404.restaurant.general.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.restaurant.model.Restaurant;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

}
