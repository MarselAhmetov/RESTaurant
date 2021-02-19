package team404.restaurant.model.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String login;
    private String password;
    private String role;
}
