package team404.restaurant.service;

import team404.restaurant.dto.SignInDto;
import team404.restaurant.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
