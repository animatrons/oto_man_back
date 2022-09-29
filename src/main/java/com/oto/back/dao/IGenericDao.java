package com.oto.back.dao;

import com.oto.back.model.AEntity;
import com.oto.back.model.exception.TechnicalException;

import java.util.List;
import java.util.Optional;

public interface IGenericDao<T extends AEntity> {

    Optional<T> find(Integer id) throws TechnicalException;
    List<T> findAll() throws TechnicalException;
    List<T> findBy(String column, Object value) throws TechnicalException;
    int insert(T entity) throws TechnicalException;
    int delete(Integer id) throws TechnicalException;
    int update(Integer id, T entity) throws TechnicalException;
    String getTableName() throws TechnicalException;
}
