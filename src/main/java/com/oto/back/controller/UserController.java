package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.model.User;
import com.oto.back.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/api/users")
public class UserController {

    private final UserApp userApp;
    public UserController(UserApp userApp) {
        this.userApp = userApp;
    }

    @GetMapping
    public List<User> getAll() {
        return userApp.getAll();
    }

    @GetMapping("{id}")
    public User get(@PathVariable("id") String id) {
        return userApp.get(id);
    }

    @PostMapping
    public void add(@RequestBody User user) {
        userApp.add(user);
    }
}
