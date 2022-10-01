package com.oto.back.controller;

import com.oto.back.app.UserApp;
import com.oto.back.app.mapper.UserCredentialsMapperImpl;
import com.oto.back.controller.security.domain.RegisterCredentials;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.UserDto;
import com.oto.back.model.dto.UserOutputDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    private final UserApp userApp;

    public AuthController(UserApp userApp) {
        this.userApp = userApp;
    }

    @PostMapping("/register")
    public ResponseDto<UserOutputDto> register(@RequestBody RegisterCredentials credentials, HttpServletRequest request) {
        if (userApp.userExists(credentials.getEmail())) {
            throw new DataIntegrityViolationException("User with creds exists");
        }
        return userApp.register(credentials);
    }

    @PostMapping("/auth/api/user/edit")
    public ResponseDto<UserOutputDto> edit(@RequestParam("id") String id, @RequestBody UserDto user) {
        if (!userApp.userExists(user.getEmail())) {
            throw new DataIntegrityViolationException("User with creds exists");
        }
        return userApp.update(id, user);
    }

}
