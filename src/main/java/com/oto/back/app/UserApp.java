package com.oto.back.app;

import com.oto.back.model.User;
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

    public User get(String id) {
        return userService.get(id);
    }

    public void delete(String id) {
        userService.delete(id);
    }

    public List<User> getAll() {
        return userService.getAll();
    }

    public void add(User user) {
        userService.add(user);
    }

    public void update(User user) {
        userService.update(user);
    }
}
