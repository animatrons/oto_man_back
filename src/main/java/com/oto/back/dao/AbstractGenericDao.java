package com.oto.back.dao;

import com.oto.back.dao.mapper.AbstractRowMapper;
import com.oto.back.dao.mapper.IRowMapper;
import com.oto.back.dao.mapper.RowMapperFactory;
import com.oto.back.model.AEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractGenericDao<T extends AEntity> implements IGenericDao<T> {

    protected final JdbcTemplate jdbcTemplate;
    protected final Class<T> clazz;

    public AbstractGenericDao(Class<T> clazz, JdbcTemplate jdbcTemplate) {
        this.clazz = clazz;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<T> find(String id) {
        try {
            var cTor = this.clazz.getConstructor();
            T instance = cTor.newInstance();
            var table = instance.getTableName();
            var sql = "SELECT * FROM " + table + " WHERE id = ?;";
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            return jdbcTemplate.query(sql, (RowMapper<T>) rowMapper, Integer.parseInt(id))
                    .stream()
                    .findFirst();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        try {
            var cTor = this.clazz.getConstructor();
            T instance = cTor.newInstance();
            var table = instance.getTableName();
            var sql = "SELECT * FROM " + table +";";
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            return jdbcTemplate.query(sql, (RowMapper<T>) rowMapper);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(T entity) {
        try {
            var cTor = this.clazz.getConstructor();
            T instance = cTor.newInstance();
            var table = instance.getTableName();
            AbstractRowMapper rowMapper = RowMapperFactory.getRowMapper(table);
            var sql = rowMapper.getParametrizedInsertQuery(entity);
            Object[] args = rowMapper.getNonNullValueProperties(entity);
            int[] types = rowMapper.getTypes(entity);
            return jdbcTemplate.update(sql, args, types);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String id) {
        try {
            var cTor = this.clazz.getConstructor();
            T instance = cTor.newInstance();
            var table = instance.getTableName();
            var sql = "DELETE FROM " + table + " WHERE id = ?;";
            return jdbcTemplate.update(sql, Integer.parseInt(id));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
