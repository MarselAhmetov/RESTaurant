package team404.restaurant.service;

import team404.restaurant.model.dto.AccountDto;
import team404.restaurant.model.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(AccountDto accountDto);
}
