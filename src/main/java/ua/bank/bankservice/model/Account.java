package ua.bank.bankservice.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;

@Entity(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;
    @Min(0)
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(name = "active")
    private boolean isActive;
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User user;
}
