package team404.restaurant.table.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team404.restaurant.general.repository.RestaurantRepository;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.table.dto.TableDto;
import team404.restaurant.table.model.Table;
import team404.restaurant.table.repository.TableRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void submit(TableDto tableDto) {
        Table table;
        if (tableDto.getId() != null) {
            table = tableRepository.getOne(tableDto.getId());
        } else {
            table = new Table();
        }

        table.setNumber(tableDto.getNumber());
        if (tableDto.getRestaurant() != null) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(tableDto.getRestaurant().getId());
            table.setRestaurant(restaurant.orElseThrow());
        }
        table.setSeatCount(tableDto.getSeatCount());
        table.setToken(UUID.randomUUID());
    }

    @Override
    public void createTableQR(UUID tableId) {

    }

    @Override
    public TableDto getTable(UUID tableId) {
        return null;
    }

    @Override
    public List<TableDto> getTablesInRestaurant(UUID restaurantId) {
        return null;
    }
}
