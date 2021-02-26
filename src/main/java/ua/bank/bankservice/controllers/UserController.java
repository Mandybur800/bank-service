package ua.bank.bankservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.bank.bankservice.model.User;
import ua.bank.bankservice.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId) {
        return userService.get(userId).orElseThrow(() ->
                new RuntimeException("Can't get user with id:" + userId));
    }
}
