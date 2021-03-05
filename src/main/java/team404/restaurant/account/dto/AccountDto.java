package team404.restaurant.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends AccountSafeDto {
    @ApiModelProperty(name = "Password", required = true)
    private String password;
}
