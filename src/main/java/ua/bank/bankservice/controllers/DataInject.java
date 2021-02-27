package ua.bank.bankservice.controllers;

import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.bank.bankservice.model.Role;
import ua.bank.bankservice.model.User;
import ua.bank.bankservice.service.RoleService;
import ua.bank.bankservice.service.UserService;

@Component
@AllArgsConstructor
public class DataInject {
    private final RoleService roleService;
    private final UserService userService;

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
    }
}
