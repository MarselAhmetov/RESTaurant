package team404.restaurant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto extends LongIdDto {
    @ApiModelProperty(name = "Email", required = true, notes = "User email")
    private String email;
    @ApiModelProperty(name = "Password", required = true, notes = "User password")
    private String password;
}
