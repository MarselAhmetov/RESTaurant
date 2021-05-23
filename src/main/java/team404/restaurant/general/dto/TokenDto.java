package team404.restaurant.general.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import team404.restaurant.account.dto.AccountSafeDto;

@Data
@AllArgsConstructor
public class TokenDto {
    private String token;
    private AccountSafeDto account;
}
