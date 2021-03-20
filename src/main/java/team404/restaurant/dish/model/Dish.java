package team404.restaurant.dish.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.general.model.LongIdEntity;
import team404.restaurant.menu.model.Menu;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DISH")
@AttributeOverride(name = "id", column = @Column(name = "DISH_ID"))
public class Dish extends LongIdEntity {
    @OneToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @Enumerated(EnumType.STRING)
    @Column(name = "DISH_TYPE")
    private DishType dishType;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COST")
    private Long cost;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COMPOSITION")
    private String composition;

    @Column(name = "WEIGHT")
    private Integer weight;
}
