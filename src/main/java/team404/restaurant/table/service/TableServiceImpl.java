package team404.restaurant.table.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.RestaurantRepository;
import team404.restaurant.general.service.QRService;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.table.dto.TableDto;
import team404.restaurant.table.model.Table;
import team404.restaurant.table.model.TableStatus;
import team404.restaurant.table.repository.TableRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;
    private final GlobalMapper mapper;
    private final QRService qrService;

    @Override
    public void save(TableDto tableDto) {
        Table table = new Table();

        if (tableDto.getRestaurant() != null) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(tableDto.getRestaurant().getId());
            table.setRestaurant(restaurant.orElseThrow());
        }
        table.setSeatCount(tableDto.getSeatCount());
        table.setStatus(TableStatus.FREE);

        String barcodeText = "http://restaurant404.tilda.ws?tableId=" + table.getId();
        qrService.generateQRCode(barcodeText, table.getId().toString());
        tableRepository.save(table);
    }

    @Override
    public void edit(TableDto tableDto) {

    }

    @Override
    public void createTableQR(UUID tableId) {

    }

    @Override
    public TableDto getTable(UUID tableId) {
        return mapper.map(tableRepository.findById(tableId).orElseThrow(), TableDto.class);
    }

    @Override
    public List<TableDto> getTablesInRestaurant(UUID restaurantId) {
        List<Table> tables = tableRepository.findAllByRestaurant_Id(restaurantId);
        return mapper.mapAsList(tables, TableDto.class);
    }
}
