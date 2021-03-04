package team404.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.domain.Restaurant;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

}
