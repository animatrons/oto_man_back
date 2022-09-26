package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.model.dto.RiderDto;
import com.oto.back.model.dto.UserDto;
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

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        userApp.delete(id);
    }

    @PutMapping
    public void update(@RequestParam("id") String id, @RequestBody UserDto userDto) {
        userApp.update(id, userDto);
    }

}
