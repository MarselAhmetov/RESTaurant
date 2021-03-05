package team404.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.restaurateur.model.Restaurateur;
import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.general.repository.RestaurantRepository;
import team404.restaurant.general.repository.RestaurateurRepository;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurateurRepository restaurateurRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void addRestaurant(RestaurantDto restaurantDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Restaurateur restaurateur = restaurateurRepository.findRestaurateurByAccount_Id(userDetails.getAccount().getId());
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setLocation(restaurantDto.getLocation());
        restaurant.setOwner(restaurateur);
        restaurantRepository.save(restaurant);
    }
}
