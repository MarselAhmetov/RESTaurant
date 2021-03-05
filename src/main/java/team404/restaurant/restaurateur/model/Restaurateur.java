package team404.restaurant.restaurateur.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.restaurant.account.model.Account;
import team404.restaurant.general.model.UuidIdEntity;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RESTAURATEUR")
@AttributeOverride(name = "id", column = @Column(name = "RESTAURATEUR_ID"))
public class Restaurateur extends UuidIdEntity {
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
