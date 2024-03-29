package team404.restaurant.account.model;

import lombok.*;
import team404.restaurant.general.model.LongIdEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends LongIdEntity {
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
}
