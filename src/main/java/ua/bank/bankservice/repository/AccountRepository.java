package ua.bank.bankservice.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.bank.bankservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM accounts a WHERE a.user.phoneNumber = :phone")
    List<Account> findAllByPhoneNumber(String phone);

    Optional<Account> getAccountByAccountNumber(String number);
}
