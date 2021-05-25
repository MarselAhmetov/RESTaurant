package team404.restaurant.account.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.LongIdDto;

@Getter
@Setter
public class AccountSafeDto extends LongIdDto {
    private String email;
    private String role;
}
