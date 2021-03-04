package team404.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Restaurant restaurant;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "LAST_UPDATE_TIME", nullable = false)
    Timestamp lastUpdateTime;
}
