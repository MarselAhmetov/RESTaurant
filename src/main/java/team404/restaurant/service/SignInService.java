package team404.restaurant.service;

import org.springframework.stereotype.Service;
import team404.restaurant.model.dto.SignInDto;
import team404.restaurant.model.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
