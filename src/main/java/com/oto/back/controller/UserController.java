package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.model.dto.ResponseDto;
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
    public ResponseDto<List<UserDto>> getAll() {
        return userApp.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<UserDto> get(@PathVariable("id") String id) {
        return userApp.get(id);
    }

    @GetMapping("user")
    public ResponseDto<UserDto> getBy(@RequestParam("email") String email) {
        return userApp.getByEmailAddress(email);
    }

    @PostMapping
    public ResponseDto<UserDto> add(@RequestBody UserDto userDto) {
        return userApp.add(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<UserDto> delete(@PathVariable("id") String id) {
        return userApp.delete(id);
    }

    @PutMapping
    public ResponseDto<UserDto> update(@RequestParam("id") String id, @RequestBody UserDto userDto) {
        return userApp.update(id, userDto);
    }

}
