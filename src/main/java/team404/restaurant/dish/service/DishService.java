package team404.restaurant.dish.service;

import team404.restaurant.dish.dto.DishDto;

import java.util.List;

public interface DishService {
    void submit(DishDto dishDto);
    void delete();
    List<DishDto> getAllByMenuId(Long menuId);
}
