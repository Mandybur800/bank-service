package ua.bank.bankservice.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "FROM transactions t WHERE t.accountTo = ?1 OR t.accountFrom = ?1")
    List<Transaction> getAllByAccount(Account account, Pageable pageable);
}
