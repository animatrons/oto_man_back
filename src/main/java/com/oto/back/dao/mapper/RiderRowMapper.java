package com.oto.back.dao.mapper;

import com.oto.back.model.Rider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class RiderRowMapper extends AbstractRowMapper<Rider> implements RowMapper<Rider> {
    protected RiderRowMapper() {
        super(Rider.class);
    }

    @Override
    public Rider mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        try {
            return getMappedInstance(rs);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
