package team404.restaurant.restaurant.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.UuidIdDto;

@Getter
@Setter
public class RestaurantDto extends UuidIdDto {
    @ApiModelProperty(name = "Name", required = true, notes = "Name of restaurant")
    private String name;
    @ApiModelProperty(name = "Location", required = true, notes = "Location")
    private String location;
}
