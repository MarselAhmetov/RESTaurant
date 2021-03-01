package team404.restaurant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends LongIdDto {
    @ApiModelProperty(name = "Email", required = true, notes = "User email")
    private String email;
    @ApiModelProperty(name = "Password", required = true, notes = "Password")
    private String password;
    @ApiModelProperty(name = "Role", required = true, notes = "Role")
    private String role;
}
