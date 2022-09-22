package com.oto.back.dao.mapper;

import com.oto.back.dao.impl.AutoDao;
import com.oto.back.model.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AutoRowMapper extends AbstractRowMapper<Auto> implements RowMapper<Auto> {
    protected AutoRowMapper() {
        super(Auto.class);
    }

    @Override
    public Auto mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        try {
            return getMappedInstance(rs);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
