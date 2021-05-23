package team404.restaurant.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.restaurant.dto.RestaurantFilter;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.restaurateur.model.Restaurateur;
import team404.restaurant.restaurant.dto.RestaurantDto;
import team404.restaurant.restaurant.repository.RestaurantRepository;
import team404.restaurant.general.repository.RestaurateurRepository;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurateurRepository restaurateurRepository;
    private final RestaurantRepository restaurantRepository;
    private final GlobalMapper mapper;

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

    @Override
    public RestaurantDto getRestaurantById(UUID restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        RestaurantDto restaurantDto;
        if (restaurant.isPresent()) {
            restaurantDto = mapper.map(restaurant, RestaurantDto.class);
        } else {
            throw new IllegalArgumentException("Restaurant with such id does not exist");
        }
        return restaurantDto;
    }

    @Override
    public List<RestaurantDto> getRestaurants(RestaurantFilter filter) {
        // TODO: 28.04.2021 Переписать на критерии (не срочно)
        List<Restaurant> restaurants = new ArrayList<>();
        System.out.println(filter.getRestaurateurId());
        if (filter.getRestaurateurId() != null) {
            restaurants = restaurantRepository.getAllByOwner_Id(filter.getRestaurateurId());
        } else {
            restaurants = restaurantRepository.findAll();
        }
        return mapper.mapAsList(restaurants, RestaurantDto.class);
    }
}
