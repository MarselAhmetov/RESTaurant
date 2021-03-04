package team404.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
