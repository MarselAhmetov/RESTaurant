package team404.restaurant.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.menu.model.Menu;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> getAllByRestaurant_Id(UUID restaurantId);
}
