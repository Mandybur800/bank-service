package ua.bank.bankservice.service;

import ua.bank.bankservice.model.Role;

public interface RoleService {
    Role create(Role role);

    Role getByName(String name);
}
