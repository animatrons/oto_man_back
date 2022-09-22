package com.oto.back.controller;

import com.oto.back.model.User;
import com.oto.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable("id") String id) {
        return userService.get(id);
    }

    @PostMapping
    public void add(@RequestBody User user) {

    }
}
