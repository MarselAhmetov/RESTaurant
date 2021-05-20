package team404.restaurant.table.service;

import team404.restaurant.table.dto.TableDto;

import java.util.List;
import java.util.UUID;

public interface TableService {
    void save(TableDto tableDto);
    void edit(TableDto tableDto);
    void createTableQR(UUID tableId);
    TableDto getTable(UUID tableId);
    List<TableDto> getTablesInRestaurant(UUID restaurantId);
}
