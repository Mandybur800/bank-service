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
import ua.bank.bankservice.util.CurrencyConverter;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final CurrencyConverter converter;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        PageRequest pageable = PageRequest.of(page, size,
                Sort.by("date").descending().and(Sort.by("id")));
        return transactionRepository.getAllByAccount(account, pageable);
    }

    @Override
    @Transactional
    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        Transaction transactionFirst = new Transaction();
        transactionFirst.setAccountFrom(fromAccount);
        transactionFirst.setAccountTo(toAccount);
        transactionFirst.setDate(LocalDateTime.now());
        transactionFirst.setAmount(amount);
        transactionFirst.setType(Transaction.OperationType.OUTCOMING);
        transactionRepository.save(transactionFirst);
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        if (fromAccount.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException("Not enough money on account #" + fromAccount);
        }
        accountService.save(fromAccount);
        Transaction transactionSecond = new Transaction();
        transactionSecond.setAccountFrom(fromAccount);
        transactionSecond.setAccountTo(toAccount);
        transactionSecond.setDate(transactionFirst.getDate());
        transactionSecond.setAmount(converter.convert(fromAccount.getCurrency(),
                toAccount.getCurrency(), amount));
        transactionSecond.setType(Transaction.OperationType.INCOMING);
        transactionRepository.save(transactionSecond);
        toAccount.setBalance(toAccount.getBalance().add(transactionSecond.getAmount()));
        accountService.save(toAccount);
    }
}
