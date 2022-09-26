package com.oto.back.dao;

import com.oto.back.dao.mapper.AbstractRowMapper;
import com.oto.back.dao.mapper.RowMapperFactory;
import com.oto.back.model.AEntity;
import com.oto.back.model.exception.BusinessException;
import com.oto.back.model.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public abstract class AbstractGenericDao<T extends AEntity> implements IGenericDao<T> {

    protected final JdbcTemplate jdbcTemplate;
    protected final Class<T> clazz;

    protected final String table;
    public AbstractGenericDao(Class<T> clazz, JdbcTemplate jdbcTemplate) {
        this.clazz = clazz;
        this.jdbcTemplate = jdbcTemplate;
        try {
            table = getTableName();
        } catch (TechnicalException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<T> find(String id) throws TechnicalException {
        try {
            if (id == null) {
                throw new BusinessException("REQUIRED PARAMS ARE MISSING IN THIS QUERY");
            }
            var sql = "SELECT * FROM " + table + " WHERE id = ?;";
            AbstractRowMapper<? extends AEntity> rowMapper = RowMapperFactory.getRowMapper(table);
            return jdbcTemplate.query(sql, (RowMapper<T>) rowMapper, Integer.parseInt(id))
                    .stream()
                    .findFirst();
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }

    @Override
    public List<T> findAll() throws TechnicalException {
        try {
            var sql = "SELECT * FROM " + table +";";
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            return jdbcTemplate.query(sql, (RowMapper<T>) rowMapper);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }

    @Override
    public int insert(T entity) throws TechnicalException {
        try {
            if (entity == null) {
                throw new BusinessException("REQUIRED PARAMS ARE MISSING IN THIS QUERY");
            }
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            var sql = rowMapper.getParametrizedInsertQuery(entity);
            Object[] args = rowMapper.getNonNullValueProperties(entity);
            int[] types = rowMapper.getTypes(entity);
            return jdbcTemplate.update(sql, args, types);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ClassNotFoundException | NoSuchFieldException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }

    @Override
    public int delete(String id) throws TechnicalException {
        try {
            if (id == null) {
                throw new BusinessException("REQUIRED PARAMS ARE MISSING IN THIS QUERY");
            }
            var sql = "DELETE FROM " + table + " WHERE id = ?;";
            return jdbcTemplate.update(sql, Integer.parseInt(id));
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }

    @Override
    public int update(String id, T entity) throws TechnicalException {
        try {
            if (id == null || entity == null) {
                throw new BusinessException("REQUIRED PARAMS ARE MISSING IN THIS QUERY");
            }
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            var sql = rowMapper.getUpdateQuery(id, entity);
            return jdbcTemplate.update(sql);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }

    @Override
    public String getTableName() throws TechnicalException {
        try {
            Constructor<T> cTor = this.clazz.getConstructor();
            T instance = cTor.newInstance();
            return instance.getTableName();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new TechnicalException(e.getMessage());
        }
    }
}
