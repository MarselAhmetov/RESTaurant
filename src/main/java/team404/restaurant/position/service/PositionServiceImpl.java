package team404.restaurant.position.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.dish.model.Dish;
import team404.restaurant.dish.repository.DishRepository;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.employee.service.EmployeeService;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;
import team404.restaurant.order.model.Order;
import team404.restaurant.order.model.OrderStatus;
import team404.restaurant.order.repository.OrderRepository;
import team404.restaurant.position.dto.PositionDto;
import team404.restaurant.position.dto.PositionMinDto;
import team404.restaurant.position.model.Position;
import team404.restaurant.position.model.PositionStatus;
import team404.restaurant.position.repository.PositionRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final EmployeeService employeeService;
    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;

    @Override
    @Transactional
    public void addPosition(PositionMinDto positionDto) {
        Order order;
        if (positionDto.getOrderId() != null) {
            order = orderRepository.findById(positionDto.getOrderId()).orElseThrow();
        } else if (positionDto.getTableId() != null) {
            order = orderRepository.getByTable_IdAndStatusNot(positionDto.getTableId(), OrderStatus.CLOSED);
        } else {
            throw new IllegalArgumentException();
        }
        Position position = new Position();
        if (positionDto.getDish() != null) {
            Dish dish = dishRepository.findById(positionDto.getDish().getId()).orElseThrow();
            position.setDish(dish);
        }
        position.setStatus(PositionStatus.CREATED);
        positionRepository.save(position);
        order.getPositions().add(position);
        simpleDao.update(order);
    }

    @Override
    public void deletePosition() {

    }

    @Override
    @Transactional
    public void changePositionStatus(PositionDto positionDto) {
        Position position = positionRepository.findById(positionDto.getId()).orElseThrow();
        switch (positionDto.getStatus()) {
            case "CREATED":
                if (position.getStatus().equals(PositionStatus.COOKING)) {
                    position.setStatus(PositionStatus.CREATED);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case "COOKING":
                if (position.getStatus().equals(PositionStatus.CREATED) || position.getStatus().equals(PositionStatus.COOKED)) {
                    position.setStatus(PositionStatus.COOKING);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case "COOKED":
                if (position.getStatus().equals(PositionStatus.COOKING)) {
                    position.setStatus(PositionStatus.COOKED);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case "DELIVERED":
                if (position.getStatus().equals(PositionStatus.COOKED)) {
                    position.setStatus(PositionStatus.DELIVERED);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
        }
        simpleDao.update(position);
    }

    @Override
    @Transactional
    public List<PositionDto> getPositionsByStatus(PositionStatus status) {
        List<Position> positions = positionRepository.getPositionsByStatus(status);
        return mapper.mapAsList(positions, PositionDto.class);
    }

    @Override
    @Transactional
    public List<PositionDto> getMyPositions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Employee employee = employeeService.getEmployeeByAccount(userDetails.getAccount());
        List<Position> positions = positionRepository.getPositionsByStatusAndCook(PositionStatus.COOKING, employee);
        return mapper.mapAsList(positions, PositionDto.class);
    }
}
