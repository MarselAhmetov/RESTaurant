package team404.restaurant.position.service;

import team404.restaurant.position.dto.PositionDto;
import team404.restaurant.position.dto.PositionMinDto;
import team404.restaurant.position.model.PositionStatus;

import java.util.List;

public interface PositionService {
    void addPosition(PositionMinDto positionDto);
    void deletePosition();
    void changePositionStatus(PositionDto positionDto);
    List<PositionDto> getPositionsByStatus(PositionStatus status);
    List<PositionDto> getMyPositions();
}
