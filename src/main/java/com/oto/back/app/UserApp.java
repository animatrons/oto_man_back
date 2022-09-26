package com.oto.back.app;

import com.oto.back.model.User;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.UserDto;
import com.oto.back.app.mapper.UserMapperImpl;
import com.oto.back.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApp {

    private final IUserService userService;
    public UserApp(IUserService userService) {
        this.userService = userService;
    }
    UserMapperImpl userMapper = new UserMapperImpl();

    public ResponseDto<UserDto> get(String id) {
        User user = userService.get(id);
        return new ResponseDto<>(userMapper.toDto(user), 200, "OK");
    }

    public ResponseDto<UserDto> delete(String id) {
        userService.delete(id);
        return new ResponseDto<>(null, 200, "DELETED");
    }

    public ResponseDto<List<UserDto>> getAll() {
        List<User> users = userService.getAll();
        return new ResponseDto<>(userMapper.toDtos(users), 200, "OK");
    }

    public ResponseDto<UserDto> add(UserDto userDto) {
        userService.add(userMapper.toEntity(userDto));
        return new ResponseDto<>(userDto, 200, "ADDED");
    }

    public ResponseDto<UserDto> update(String id, UserDto userDto) {
        userService.update(id, userMapper.toEntity(userDto));
        return new ResponseDto<>(userDto, 200, "UPDATED");
    }
}
