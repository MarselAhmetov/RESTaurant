package team404.restaurant.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import team404.restaurant.dish.model.Dish;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.general.model.UuidIdEntity;
import team404.restaurant.position.model.Position;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.table.model.Table;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "ORDER_")
@AttributeOverride(name = "id", column = @Column(name = "ORDER_ID"))
public class Order extends UuidIdEntity {

    @ManyToOne
    @JoinColumn(name = "TABLE_ID")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Table table;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POSITION_ORDER_REL",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "POSITION_ID"))
    private List<Position> positions;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus status;

}
