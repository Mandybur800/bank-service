package ua.bank.bankservice.service;

import java.util.Optional;
import ua.bank.bankservice.model.User;

public interface UserService {
    User create(User user);

    User update(User user);

    Optional<User> get(Long id);

    Optional<User> getByPhone(String phone);

    void remove(Long id);
}
