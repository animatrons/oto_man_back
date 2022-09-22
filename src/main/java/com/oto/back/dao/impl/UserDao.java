package com.oto.back.dao.impl;

import com.oto.back.dao.AbstractGenericDao;
import com.oto.back.dao.IUserDao;
import com.oto.back.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractGenericDao<User> implements IUserDao {

    public UserDao(JdbcTemplate jdbcTemplate) {
        super(User.class, jdbcTemplate);
    }
}
