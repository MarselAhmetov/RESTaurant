package team404.restaurant.position.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team404.restaurant.order.dto.OrderDto;
import team404.restaurant.order.model.OrderStatus;
import team404.restaurant.order.service.OrderService;
import team404.restaurant.position.dto.PositionDto;
import team404.restaurant.position.dto.PositionMinDto;
import team404.restaurant.position.model.PositionStatus;
import team404.restaurant.position.service.PositionService;

import java.util.List;

@Tag(name = "Position")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PositionController {

    private final PositionService positionService;

    @Operation(summary = "Add position")
    @PostMapping("/api/position")
    @PreAuthorize("hasAuthority('WAITER')")
    public void addPosition(@RequestBody PositionMinDto positionDto) {
        positionService.addPosition(positionDto);
    }

    @Operation(summary = "Change position status")
    @PostMapping("/api/position/status")
    @PreAuthorize("hasAnyAuthority('WAITER', 'COOK')")
    public void changePositionStatus(@RequestBody PositionDto positionDto) {
        positionService.changePositionStatus(positionDto);
    }

    @Operation(summary = "Get positions by status")
    @GetMapping("/api/position/status")
    @PreAuthorize("hasAnyAuthority('WAITER', 'COOK')")
    public List<PositionDto> getPositionsByStatus(@RequestParam String status) {
        return positionService.getPositionsByStatus(PositionStatus.valueOf(status));
    }

    @Operation(summary = "Get cook cooking positions")
    @GetMapping("/api/position/my")
    @PreAuthorize("hasAuthority('COOK')")
    public List<PositionDto> getMyPositions() {
        return positionService.getMyPositions();
    }
}
