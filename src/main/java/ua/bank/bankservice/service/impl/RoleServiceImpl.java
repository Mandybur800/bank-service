package ua.bank.bankservice.service.impl;

import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.bank.bankservice.model.Role;
import ua.bank.bankservice.repository.RoleRepository;
import ua.bank.bankservice.service.RoleService;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByName(String name) {
        return roleRepository.getByName(Role.RoleType.valueOf(name)).orElseThrow(
                () -> new EntityNotFoundException("Can't get role '" + name + "' from db"));
    }
}
