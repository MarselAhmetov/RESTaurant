package team404.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.restaurant.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    List<Restaurant> getAllByOwner_Id(UUID owner_id);
}
