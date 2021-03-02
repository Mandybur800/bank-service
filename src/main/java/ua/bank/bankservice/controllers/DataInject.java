package ua.bank.bankservice.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.bank.bankservice.model.Account;
import ua.bank.bankservice.model.Currency;
import ua.bank.bankservice.model.Role;
import ua.bank.bankservice.model.User;
import ua.bank.bankservice.service.AccountService;
import ua.bank.bankservice.service.RoleService;
import ua.bank.bankservice.service.UserService;

@Component
@AllArgsConstructor
public class DataInject {
    private final RoleService roleService;
    private final UserService userService;
    private final AccountService accountService;

    @PostConstruct
    private void init() {
        Role user = new Role();
        user.setRoleName(Role.RoleType.USER);
        roleService.create(user);
        Role admin = new Role();
        admin.setRoleName(Role.RoleType.ADMIN);
        roleService.create(admin);
        User adminUser = new User();
        adminUser.setName("Odmen");
        adminUser.setDateOfBirth(LocalDate.of(2000,01,01));
        adminUser.setRoles(Set.of(admin, user));
        adminUser.setPassword("pass1word");
        adminUser.setPhoneNumber("0678534856");
        userService.create(adminUser);
        Account firstAccount = new Account();
        firstAccount.setAccountNumber("1234");
        firstAccount.setCurrency(Currency.UAH);
        firstAccount.setActive(true);
        firstAccount.setUser(adminUser);
        firstAccount.setBalance(new BigDecimal(20000));
        accountService.create(firstAccount);
        Account secondAccount = new Account();
        secondAccount.setAccountNumber("5678");
        secondAccount.setCurrency(Currency.USD);
        secondAccount.setActive(true);
        secondAccount.setUser(adminUser);
        secondAccount.setBalance(new BigDecimal(500));
        accountService.create(secondAccount);
    }
}
