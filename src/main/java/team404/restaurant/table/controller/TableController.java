package team404.restaurant.table.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.table.dto.TableDto;
import team404.restaurant.table.service.TableService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Tag(name = "Table", description = "Table controller")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
public class TableController {

    private final TableService tableService;

    @Operation(summary = "Add table")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/table")
    public void addTable(@RequestBody TableDto tableDto) {
        tableService.save(tableDto);
    }

    @Operation(summary = "Get tables in Restaurant")
    @GetMapping("/api/tables")
    public List<TableDto> getTablesInRestaurant(@RequestParam UUID restaurantId) {
        return tableService.getTablesInRestaurant(restaurantId);
    }

    @Operation(summary = "Reserve table")
    @PostMapping("/api/table/reserve")
    public void reserveTable(@RequestBody TableDto table) {
        tableService.reserveTable(table.getId());
    }

    @Operation(summary = "Get QR image for table")
    @GetMapping("/api/table/qr")
    public ResponseEntity index(@RequestParam String tableId) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("qr_" + tableId + ".png"));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", stream);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(stream.toByteArray());
    }
}
