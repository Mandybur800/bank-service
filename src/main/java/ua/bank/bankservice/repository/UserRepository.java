package ua.bank.bankservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.bank.bankservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM users u LEFT JOIN FETCH u.roles WHERE u.phoneNumber = ?1")
    Optional<User> getFirstByPhoneNumber(String phoneNumber);

    @Override
    @Query(value = "SELECT u FROM users u LEFT JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> findById(Long id);
}
