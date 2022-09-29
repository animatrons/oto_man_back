package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.RiderDto;
import com.oto.back.model.dto.UserDto;
import com.oto.back.model.dto.UserOutputDto;
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
    public ResponseDto<List<UserOutputDto>> getAll() {
        return userApp.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<UserOutputDto> get(@PathVariable("id") String id) {
        return userApp.get(id);
    }

    @GetMapping("user")
    public ResponseDto<UserOutputDto> getBy(@RequestParam("email") String email) {
        return userApp.getByEmailAddress(email);
    }

    @PostMapping
    public ResponseDto<UserOutputDto> add(@RequestBody UserDto userDto) {
        return userApp.add(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<UserOutputDto> delete(@PathVariable("id") String id) {
        return userApp.delete(id);
    }

    @PutMapping
    public ResponseDto<UserOutputDto> update(@RequestParam("id") String id, @RequestBody UserDto userDto) {
        return userApp.update(id, userDto);
    }

}
