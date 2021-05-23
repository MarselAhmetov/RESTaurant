package team404.restaurant.employee.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.general.dto.UuidIdDto;

@Data
@Setter
@Getter
public class EmployeeWithPasswordDto extends EmployeeDto {
    private String password;
}
