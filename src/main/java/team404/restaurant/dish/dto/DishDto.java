package team404.restaurant.dish.dto;

import lombok.Getter;
import lombok.Setter;
import team404.restaurant.general.dto.LongIdDto;
import team404.restaurant.menu.dto.MenuDto;

@Setter
@Getter
public class DishDto extends LongIdDto {
    private MenuDto menu;

    private String dishType;

    private String name;

    private Long cost;

    private String description;

    private String composition;

    private Integer weight;
}
