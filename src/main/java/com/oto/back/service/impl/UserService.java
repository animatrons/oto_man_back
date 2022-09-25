package com.oto.back.service.impl;

import com.oto.back.dao.IUserDao;
import com.oto.back.model.User;
import com.oto.back.service.AbstractGenericService;
import com.oto.back.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractGenericService<User, IUserDao> implements IUserService {

    public UserService(IUserDao userDao) {
        this.dao = userDao;
    }
}
