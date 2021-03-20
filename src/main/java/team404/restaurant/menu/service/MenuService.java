package team404.restaurant.menu.service;

import team404.restaurant.menu.dto.MenuDto;

public interface MenuService {
    void submit(MenuDto menuDto);
    void delete(Long menuId);
}
