package team404.restaurant.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import team404.restaurant.account.model.Account;
import team404.restaurant.general.model.UuidIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
@AttributeOverride(name = "id", column = @Column(name = "EMPLOYEE_ID"))
public class Employee extends UuidIdEntity {
    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "FATHER_NAME", nullable = false)
    private String fatherName;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
}
