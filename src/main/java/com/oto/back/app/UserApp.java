package com.oto.back.app;

import com.oto.back.app.mapper.UserCredentialsMapperImpl;
import com.oto.back.app.mapper.UserOutputMapperImpl;
import com.oto.back.controller.security.domain.RegisterCredentials;
import com.oto.back.model.User;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.UserDto;
import com.oto.back.app.mapper.UserMapperImpl;
import com.oto.back.model.dto.UserOutputDto;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApp {

    private final IUserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    public UserApp(IUserService userService) {
        this.userService = userService;
    }
    UserMapperImpl userMapper = new UserMapperImpl();
    UserCredentialsMapperImpl userCredentialsMapper = new UserCredentialsMapperImpl();
    UserOutputMapperImpl outputMapper = new UserOutputMapperImpl();

    public ResponseDto<UserOutputDto> get(String id) {
        User user = userService.get(id);
        return new ResponseDto<>(outputMapper.toDto(user), 200, "OK");
    }

    public ResponseDto<UserOutputDto> getByEmailAddress(String email) {
        UserOutputDto dto = userService.getBy("email", email)
                .stream()
                .findFirst()
                .map(user -> outputMapper.toDto(user))
                .orElseThrow(() -> new NotFoundException(String.format("User with Email address '%s' NOT found, OK?", email)));
        return new ResponseDto<>(dto, 200, "OK");
    }

    public ResponseDto<UserOutputDto> getByUserName(String userName) {
        UserOutputDto dto = userService.getBy("userName", userName)
                .stream()
                .findFirst()
                .map(user -> outputMapper.toDto(user))
                .orElseThrow(() -> new NotFoundException(String.format("User with userName '%s' NOT found, OK?", userName)));
        return new ResponseDto<>(dto, 200, "OK");
    }

    public User getUserByUsername(String userName) {
        return userService.getBy("userName", userName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("User with userName '%s' NOT found, OK?", userName)));
    }

    public User getUserByEmail(String email) {
        return userService.getBy("email", email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("User with email '%s' NOT found, OK?", email)));
    }

    public ResponseDto<UserOutputDto> delete(String id) {
        userService.delete(id);
        return new ResponseDto<>(null, 200, "DELETED");
    }

    public ResponseDto<List<UserOutputDto>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseDto<>(outputMapper.toDtos(users), 200, "OK");
    }

    public ResponseDto<UserOutputDto> add(UserDto userDto) {
        User addedUser = userService.add(userMapper.toEntity(userDto));
        return new ResponseDto<>(outputMapper.toDto(addedUser), 200, "ADDED");
    }

    public ResponseDto<UserOutputDto> update(String id, UserDto userDto) {
        User updatedUser = userService.update(id, userMapper.toEntity(userDto));
        return new ResponseDto<>(outputMapper.toDto(updatedUser), 200, "UPDATED");
    }

    public boolean userExists(String email) {
        try {
            User usr = userService.getOneBy("email", email);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public ResponseDto<UserOutputDto> register(RegisterCredentials credentials) {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        User user = userCredentialsMapper.toEntity(credentials);
        user = userService.add(user);
        return new ResponseDto<>(outputMapper.toDto(user), 200, "REGISTERED");
    }
}
