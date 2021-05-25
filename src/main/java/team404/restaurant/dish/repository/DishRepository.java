package team404.restaurant.dish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.dish.model.Dish;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> getAllByMenu_Id(Long menuId);
}
