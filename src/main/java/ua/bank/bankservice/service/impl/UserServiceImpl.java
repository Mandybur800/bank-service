package ua.bank.bankservice.service.impl;

import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.bank.bankservice.model.User;
import ua.bank.bankservice.repository.UserRepository;
import ua.bank.bankservice.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get user with id: '" + id + "'"));
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.getFirstByPhoneNumber(phone).orElseThrow(
                () -> new EntityNotFoundException("Can't get user with phone: '" + phone + "'"));
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
