package team404.restaurant.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    @ApiModelProperty(name = "Email", required = true, notes = "User email")
    private String email;
    @ApiModelProperty(name = "Password", required = true, notes = "User password")
    private String password;
}