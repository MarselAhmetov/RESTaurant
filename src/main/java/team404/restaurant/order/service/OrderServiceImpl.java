package team404.restaurant.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.dish.repository.DishRepository;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.employee.service.EmployeeService;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;
import team404.restaurant.order.dto.OrderDto;
import team404.restaurant.order.model.Order;
import team404.restaurant.order.model.OrderStatus;
import team404.restaurant.order.repository.OrderRepository;
import team404.restaurant.table.model.TableStatus;
import team404.restaurant.table.repository.TableRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final EmployeeService employeeService;
    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void changeStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public void edit(OrderDto orderDto) {

    }

    @Override
    public OrderDto getById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return mapper.map(order, OrderDto.class);
    }

    @Override
    @Transactional
    public Boolean takeOrder(OrderDto orderDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Employee employee = employeeService.getEmployeeByAccount(userDetails.getAccount());
        if (orderDto.getId() != null) {
            Order order = orderRepository.findById(orderDto.getId()).orElseThrow();
            order.setEmployee(employee);
            order.setStatus(OrderStatus.ACTIVE);
            simpleDao.update(order);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<OrderDto> getMyOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Employee employee = employeeService.getEmployeeByAccount(userDetails.getAccount());
        List<Order> orders = orderRepository.getAllByStatusAndEmployee(OrderStatus.ACTIVE, employee);
        return mapper.mapAsList(orders, OrderDto.class);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrderByStatus(OrderStatus status) {
        List<Order> orders = orderRepository.getAllByStatus(status);
        return mapper.mapAsList(orders, OrderDto.class);
    }

    @Override
    @Transactional
    public void closeOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow();
        order.setStatus(OrderStatus.CLOSED);
        order.getTable().setStatus(TableStatus.FREE);
        simpleDao.update(order);
    }

    @Override
    @Transactional
    public OrderDto getOrderByTable(UUID tableId) {
        Order order = orderRepository.getByTable_IdAndStatus(tableId, OrderStatus.ACTIVE);
        return mapper.map(order, OrderDto.class);
    }
}
