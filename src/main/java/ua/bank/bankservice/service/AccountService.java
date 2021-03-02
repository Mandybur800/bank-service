package ua.bank.bankservice.service;

import java.util.List;
import ua.bank.bankservice.model.Account;

public interface AccountService {
    Account save(Account account);

    List<Account> getAccountsByPhone(String phone);

    Account getByAccountNumber(String accountNumber);
}
