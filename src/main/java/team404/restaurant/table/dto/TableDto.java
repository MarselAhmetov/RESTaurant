package team404.restaurant.table.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.UuidIdDto;
import team404.restaurant.restaurant.dto.RestaurantDto;

@Getter
@Setter
public class TableDto extends UuidIdDto {
    private Integer seatCount;
    private RestaurantDto restaurant;
}
