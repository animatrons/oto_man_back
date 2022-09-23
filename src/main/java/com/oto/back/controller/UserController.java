package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.model.User;
import com.oto.back.model.dto.UserDto;
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
    public List<UserDto> getAll() {
        return userApp.getAll();
    }

    @GetMapping("{id}")
    public UserDto get(@PathVariable("id") String id) {
        return userApp.get(id);
    }

    @PostMapping
    public void add(@RequestBody UserDto userDto) {
        userApp.add(userDto);
    }
}
