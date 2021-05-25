package team404.restaurant.menu.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.LongIdDto;
import team404.restaurant.restaurant.dto.RestaurantDto;

@Getter
@Setter
public class MenuDto extends LongIdDto {
    private String name;
    private RestaurantDto restaurant;
}
