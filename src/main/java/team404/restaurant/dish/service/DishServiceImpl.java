package team404.restaurant.dish.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.dish.dto.DishDto;
import team404.restaurant.dish.model.Dish;
import team404.restaurant.dish.model.DishType;
import team404.restaurant.dish.repository.DishRepository;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.menu.model.Menu;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final SimpleDao simpleDao;
    private final DishRepository dishRepository;
    private final GlobalMapper mapper;

    @Override
    @Transactional
    public void submit(DishDto dishDto) {
        Dish dish;
        if (dishDto.getId() != null) {
            dish = simpleDao.findById(Dish.class, dishDto.getId());
            if (dish == null) {
                throw new IllegalArgumentException("Dish with such id does not exists");
            }
        } else {
            dish = new Dish();
        }

        dish.setName(dishDto.getName());
        dish.setWeight(dishDto.getWeight());
        dish.setComposition(dishDto.getComposition());
        dish.setDescription(dishDto.getDescription());
        dish.setCost(dishDto.getCost());
        dish.setDishType(DishType.valueOf(dishDto.getDishType()));
        if (dishDto.getMenu() != null) {
            Menu menu = simpleDao.findById(Menu.class, dishDto.getMenu().getId());
            if (menu == null) {
                throw new IllegalArgumentException("Menu with such id does not exists");
            }
            dish.setMenu(menu);
        }

        simpleDao.saveOrUpdate(dish);

    }

    @Override
    public void delete() {

    }

    @Override
    public List<DishDto> getAllByMenuId(Long menuId) {
        List<Dish> dishes = dishRepository.getAllByMenu_Id(menuId);
        List<DishDto> result = new ArrayList<>();
        if (!dishes.isEmpty()) {
            result = mapper.mapAsList(dishes, DishDto.class);
        }
        return result;
    }
}
