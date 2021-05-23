package team404.restaurant.employee.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import team404.restaurant.account.dto.AccountSafeDto;
import team404.restaurant.general.dto.UuidIdDto;
import team404.restaurant.restaurant.dto.RestaurantDto;

@Data
@Setter
@Getter
public class EmployeeDto extends UuidIdDto {
    private AccountSafeDto account;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String phoneNumber;

    private String role;

    private RestaurantDto restaurant;
}
