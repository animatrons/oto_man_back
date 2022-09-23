package com.oto.back.service.impl;

import com.oto.back.dao.IUserDao;
import com.oto.back.model.User;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.IUserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(String id) {
        return userDao.find(id).orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(String id) {

    }
}
