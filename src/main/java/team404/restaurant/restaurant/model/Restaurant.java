package team404.restaurant.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.restaurateur.model.Restaurateur;
import team404.restaurant.general.model.UuidIdEntity;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESTAURANT")
@AttributeOverride(name = "id", column = @Column(name = "RESTAURANT_ID"))
public class Restaurant extends UuidIdEntity {
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "OWNER")
    private Restaurateur owner;

    @Column(name = "LOCATION", nullable = false)
    private String location;
}
