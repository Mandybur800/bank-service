package ua.bank.bankservice.service.impl;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.repository.AccountRepository;
import ua.bank.bankservice.service.AccountService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountsByPhone(String phone) {
        return accountRepository.findAllByPhoneNumber(phone);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Can't found account by number"
                        + accountNumber));
    }

    @Override
    public void blockAccount(String accountNumber) {
        Account account = getByAccountNumber(accountNumber);
        account.setActive(false);
        accountRepository.save(account);
    }
}
