package com.oto.back.dao.mapper;

import com.oto.back.model.AEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;

@Scope("singleton")
public class RowMapperFactory {

    public static AbstractRowMapper<? extends AEntity> getRowMapper(String table) throws ClassNotFoundException {
        switch (table) {
            case "user" -> {
                return new UserRowMapper();
            }
            case "rider" -> {
                return new RiderRowMapper();
            }
            case "ride" -> {
                return new RideRowMapper();
            }
            case "auto" ->  {
                return new AutoRowMapper();
            }
            default -> {
                throw new ClassNotFoundException();
            }
        }
    }
}
