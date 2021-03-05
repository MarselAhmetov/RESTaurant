package team404.restaurant.account.service;

import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.account.dto.AccountSafeDto;

public interface AccountService {
    void submit(AccountDto accountDto);
    AccountSafeDto getCurrentAccountInformation();
}
