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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@javax.persistence.Table(name = "TABLE_")
@AttributeOverride(name = "id", column = @Column(name = "TABLE_ID"))
public class Table extends UuidIdEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TableStatus status;

    @Column(name = "SEAT_COUNT")
    private Integer seatCount;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;
}
