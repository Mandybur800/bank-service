package ua.bank.bankservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bank.bankservice.exception.NotEnoughMoneyException;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.model.Transaction;
import ua.bank.bankservice.repository.TransactionRepository;
import ua.bank.bankservice.service.AccountService;
import ua.bank.bankservice.service.TransactionService;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        PageRequest date = PageRequest.of(page, size,
                Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.getAllByAccountNumber(account.getAccountNumber(), date);
    }

    @Override
    @Transactional
    public void transfer(String fromAccount, String toAccount, int amount) {
        Transaction transactionFirst = new Transaction();
        Account accountFrom = accountService.getByAccountNumber(fromAccount);
        Account accountTo = accountService.getByAccountNumber(toAccount);
        transactionFirst.setAccountFrom(accountFrom);
        transactionFirst.setAccountTo(accountTo);
        transactionFirst.setDate(LocalDateTime.now());
        transactionFirst.setAmount(new BigDecimal(amount));
        transactionFirst.setType(Transaction.OperationType.OUTCOMING);
        transactionRepository.save(transactionFirst);
        accountFrom.setBalance(accountFrom.getBalance().subtract(new BigDecimal(amount)));
        if (accountFrom.getBalance().compareTo(new BigDecimal(0)) < 0) {
            throw new NotEnoughMoneyException("Not enough money on account #" + fromAccount);
        }
        accountService.create(accountFrom);
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAccountFrom(accountFrom);
        transactionSecond.setAccountTo(accountTo);
        transactionSecond.setDate(transactionFirst.getDate());
        transactionSecond.setAmount(new BigDecimal(amount));
        transactionSecond.setType(Transaction.OperationType.INCOMING);
        transactionRepository.save(transactionSecond);
        accountTo.setBalance(accountTo.getBalance().add(transactionSecond.getAmount()));
        accountService.create(accountTo);
    }

}
