package ua.bank.bankservice.service;

import java.util.List;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.model.Transaction;

public interface TransactionService {
    void transfer(String fromAccount, String toAccount, int amount);

    List<Transaction> getAllByAccount(int page, int size, Account account);
}
