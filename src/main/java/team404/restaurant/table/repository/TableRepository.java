package team404.restaurant.table.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.table.model.Table;

import java.util.UUID;

public interface TableRepository extends JpaRepository<Table, UUID> {
}
