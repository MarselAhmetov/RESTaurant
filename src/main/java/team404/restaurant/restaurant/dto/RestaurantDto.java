package team404.restaurant.restaurant.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.UuidIdDto;

@Getter
@Setter
public class RestaurantDto extends UuidIdDto {
    private String name;
    private String location;
}
