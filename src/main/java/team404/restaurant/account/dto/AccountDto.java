package team404.restaurant.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends AccountSafeDto {
    private String password;
}
