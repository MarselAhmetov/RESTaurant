package team404.restaurant.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.dish.dto.DishDto;
import team404.restaurant.employee.dto.EmployeeDto;
import team404.restaurant.general.dto.UuidIdDto;
import team404.restaurant.position.dto.PositionDto;
import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.table.dto.TableDto;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto extends UuidIdDto {
    private TableDto table;
    private RestaurantDto restaurant;
    private List<PositionDto> positions;
    private EmployeeDto employee;
    private String status;
    private Date createTime;
}
