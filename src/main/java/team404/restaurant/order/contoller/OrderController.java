package team404.restaurant.order.contoller;

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

import java.util.List;

@Tag(name = "Order")
@RestController
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Take order")
    @PostMapping("/api/order/take")
    @PreAuthorize("hasAuthority('WAITER')")
    public Boolean takeOrder(@RequestBody OrderDto order) {
        return orderService.takeOrder(order);
    }

    @Operation(summary = "Close order")
    @PostMapping("/api/order/close")
    @PreAuthorize("hasAuthority('WAITER')")
    public void closeOrder(@RequestBody OrderDto order) {
        orderService.closeOrder(order);
    }

    @Operation(summary = "Get waiter active orders")
    @GetMapping("/api/order/my")
    @PreAuthorize("hasAuthority('WAITER')")
    public List<OrderDto> getMyOrders() {
        return orderService.getMyOrders();
    }

    @Operation(summary = "Get orders by status")
    @GetMapping("/api/order")
    @PreAuthorize("hasAuthority('WAITER')")
    public List<OrderDto> getOrdersByStatus(@RequestParam String status) {
        return orderService.getOrderByStatus(OrderStatus.valueOf(status));
    }
}
