package team404.restaurant.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.LongIdDto;

@Getter
@Setter
public class AccountSafeDto extends LongIdDto {
    @ApiModelProperty(name = "Email", required = true)
    private String email;
    @ApiModelProperty(name = "Role", required = true)
    private String role;
}
