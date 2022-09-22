package com.oto.back.service;

import com.oto.back.dao.IUserDao;
import com.oto.back.model.User;
import com.oto.back.model.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserDao userDao;
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User get(String id) {
        return userDao.find(id).orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public void add(User user) {
        userDao.insert(user);
    }
}
