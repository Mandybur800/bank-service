package ua.bank.bankservice.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_from", nullable = false)
    private Account accountFrom;
    @ManyToOne
    @JoinColumn(name = "account_to", nullable = false)
    private Account accountTo;
    private double amount;
    @Column(nullable = false)
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Override
    public String toString() {
        return "Transaction{"
                + "id=" + id
                + ", accountFrom=" + accountFrom.getAccountNumber()
                + ", accountTo=" + accountTo.getAccountNumber()
                + ", amount=" + amount
                + ", date=" + date
                + ", type=" + type
                + '}';
    }

    public enum OperationType {
        INCOMING, OUTCOMING
    }
}
