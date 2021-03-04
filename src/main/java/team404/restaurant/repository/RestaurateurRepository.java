package team404.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.domain.Restaurateur;

import java.util.UUID;

public interface RestaurateurRepository extends JpaRepository<Restaurateur, UUID> {
    Restaurateur findRestaurateurByAccount_Id(Long accountId);
}
