package ua.bank.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.bank.bankservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "FROM roles WHERE roleName = ?1")
    Role getByName(String name);
}
