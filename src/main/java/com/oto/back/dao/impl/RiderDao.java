package com.oto.back.dao.impl;

import com.oto.back.dao.AbstractGenericDao;
import com.oto.back.dao.IRiderDao;
import com.oto.back.model.Rider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RiderDao extends AbstractGenericDao<Rider> implements IRiderDao {
    public RiderDao(JdbcTemplate jdbcTemplate) {
        super(Rider.class, jdbcTemplate);
    }
}
