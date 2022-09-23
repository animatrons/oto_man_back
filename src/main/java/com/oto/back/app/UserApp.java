package com.oto.back.app;

import com.oto.back.model.User;
import com.oto.back.model.dto.UserDto;
import com.oto.back.model.mapper.UserMapperImpl;
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

    public UserDto get(String id) {
        User user = userService.get(id);
        return userMapper.toDto(user);
    }

    public void delete(String id) {
        userService.delete(id);
    }

    public List<UserDto> getAll() {
        List<User> users = userService.getAll();
        return userMapper.toDtos(users);
    }

    public void add(UserDto userDto) {
        userService.add(userMapper.toEntity(userDto));
    }

    public void update(UserDto userDto) {
        userService.update(userMapper.toEntity(userDto));
    }
}
