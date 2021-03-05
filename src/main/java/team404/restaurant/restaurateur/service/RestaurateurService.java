package team404.restaurant.restaurateur.service;

import team404.restaurant.restaurateur.dto.RestaurateurDto;

public interface RestaurateurService {
    void submit(RestaurateurDto restaurateurDto);
    RestaurateurDto getCurrentRestaurateur();
}
