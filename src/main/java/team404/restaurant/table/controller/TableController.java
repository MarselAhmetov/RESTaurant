package team404.restaurant.table.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.UUID;

@Api(value = "Table controller")
@RestController
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @ApiOperation(value = "Add table")
    @PreAuthorize("hasAuthority('RESTAURATEUR')")
    @PostMapping("/api/table")
    public void addTable(@ApiParam(name = "Table information") @RequestBody TableDto tableDto) {
        tableService.save(tableDto);
    }

    @ApiOperation(value = "Get tables in Restaurant ")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/tables")
    public void getTablesInRestaurant(@ApiParam(name = "Restaurant id") @RequestParam UUID restaurantId) {
        tableService.getTablesInRestaurant(restaurantId);
    }

    @ApiOperation(value = "Get tables in Restaurant ")
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
