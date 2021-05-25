package team404.restaurant.menu.service;

import team404.restaurant.menu.dto.MenuDto;

import java.util.List;
import java.util.UUID;

public interface MenuService {
    void submit(MenuDto menuDto);
    List<MenuDto> getMenusByRestaurant(UUID restaurantId);
    void delete(Long menuId);
}
