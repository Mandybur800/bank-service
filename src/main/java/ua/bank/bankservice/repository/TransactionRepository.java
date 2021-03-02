package ua.bank.bankservice.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "FROM transactions t JOIN FETCH t.accountFrom af JOIN FETCH t.accountTo at "
            + "WHERE af = ?1 OR at = ?1")
    List<Transaction> getAllByAccount(Account account, Pageable pageable);
}
