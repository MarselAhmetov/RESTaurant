package team404.restaurant.menu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.menu.dto.MenuDto;
import team404.restaurant.menu.model.Menu;
import team404.restaurant.restaurant.model.Restaurant;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final SimpleDao simpleDao;

    @Override
    @Transactional
    public void submit(MenuDto menuDto) {
        Menu menu;
        if (menuDto.getId() != null) {
            menu = simpleDao.findById(Menu.class, menuDto.getId());
        } else {
            menu = new Menu();
        }
        menu.setName(menuDto.getName());
        menu.setLastUpdateTime(new Date());
        if (menuDto.getRestaurantDto() != null) {
            Restaurant restaurant = simpleDao.findById(Restaurant.class, menuDto.getRestaurantDto().getId());
            if (restaurant != null) {
                menu.setRestaurant(restaurant);
            } else {
                throw new IllegalArgumentException("Restaurant with such id does not exists");
            }
        } else {
            throw new IllegalArgumentException("Restaurant information required");
        }

        simpleDao.saveOrUpdate(menu);
    }

    @Override
    public void delete(Long menuId) {

    }
}
