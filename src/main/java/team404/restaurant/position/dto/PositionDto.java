package team404.restaurant.position.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.dish.dto.DishDto;
import team404.restaurant.employee.dto.EmployeeDto;
import team404.restaurant.general.dto.LongIdDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PositionDto extends LongIdDto {
    private DishDto dish;
    private EmployeeDto cook;
    private String status;
}
