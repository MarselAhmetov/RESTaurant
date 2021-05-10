package team404.restaurant.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.general.model.UuidIdEntity;
import team404.restaurant.restaurant.model.Restaurant;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "TABLE")
@AttributeOverride(name = "id", column = @Column(name = "TABLE_ID"))
public class Table extends UuidIdEntity {

    private Integer seatCount;
    private Long number;
    private UUID token;
    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Restaurant restaurant;
}
