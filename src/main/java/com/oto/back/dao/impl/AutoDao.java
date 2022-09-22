package com.oto.back.dao.impl;

import com.oto.back.dao.AbstractGenericDao;
import com.oto.back.dao.IAutoDao;
import com.oto.back.model.Auto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AutoDao extends AbstractGenericDao<Auto> implements IAutoDao {
    public AutoDao(JdbcTemplate jdbcTemplate) {
        super(Auto.class, jdbcTemplate);
    }
}
