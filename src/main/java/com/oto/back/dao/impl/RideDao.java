package com.oto.back.dao.impl;

import com.oto.back.dao.AbstractGenericDao;
import com.oto.back.dao.IRideDao;
import com.oto.back.model.Ride;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RideDao extends AbstractGenericDao<Ride> implements IRideDao {
    public RideDao(JdbcTemplate jdbcTemplate) {
        super(Ride.class, jdbcTemplate);
    }
}
