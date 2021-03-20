package team404.restaurant.dish.model;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;

// TODO: 20.03.2021 Заменить на разделы меню
public enum DishType {
    DRINK, SOUP, SALAD, FIRST, SECOND, DESERT;

    public static final Collection<DishType> DISH_TYPES = Collections.unmodifiableCollection(
            Lists.newArrayList(
                    DRINK,
                    SOUP,
                    SALAD,
                    FIRST,
                    SECOND,
                    DESERT)
    );
}
