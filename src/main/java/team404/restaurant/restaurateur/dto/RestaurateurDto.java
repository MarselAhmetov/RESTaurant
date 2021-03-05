package team404.restaurant.restaurateur.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.account.dto.AccountSafeDto;
import team404.restaurant.general.dto.UuidIdDto;

@Setter
@Getter
public class RestaurateurDto extends UuidIdDto {
    private AccountSafeDto account;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String phoneNumber;
}
