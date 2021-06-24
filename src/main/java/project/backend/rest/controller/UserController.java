package project.backend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.backend.rest.dto.User;
import project.backend.rest.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{login}")
    public User getUser(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }
}
