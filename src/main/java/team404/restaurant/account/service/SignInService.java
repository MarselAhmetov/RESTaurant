package team404.restaurant.account.service;

import team404.restaurant.account.dto.SignInDto;
import team404.restaurant.general.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInDto);
}
