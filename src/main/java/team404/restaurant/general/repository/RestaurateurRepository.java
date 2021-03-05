package team404.restaurant.general.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team404.restaurant.restaurateur.model.Restaurateur;

import java.util.UUID;

public interface RestaurateurRepository extends JpaRepository<Restaurateur, UUID> {
    Restaurateur findRestaurateurByAccount_Id(Long accountId);
}
