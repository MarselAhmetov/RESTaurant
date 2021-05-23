package team404.restaurant.position.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.dish.model.Dish;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.general.model.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "POSITION")
@AttributeOverride(name = "id", column = @Column(name = "POSITION_ID"))
public class Position extends LongIdEntity {

    @ManyToOne
    @JoinColumn(name = "DISH_ID")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "COOK_ID")
    private Employee cook;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PositionStatus status;
}
