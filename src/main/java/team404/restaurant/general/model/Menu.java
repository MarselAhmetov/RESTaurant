package team404.restaurant.general.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.restaurant.model.Restaurant;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MENU")
@AttributeOverride(name = "id", column = @Column(name = "MENU_ID"))
public class Menu extends LongIdEntity {
    @OneToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LAST_UPDATE_TIME", nullable = false)
    private Timestamp lastUpdateTime;
}
