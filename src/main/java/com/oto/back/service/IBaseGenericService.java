package com.oto.back.service;

import com.oto.back.model.AEntity;
import com.oto.back.model.User;
import com.oto.back.model.exception.TechnicalException;

import java.util.List;

public interface IBaseGenericService<T extends AEntity> {
    T get(String id);
    List<T> getAll();
    List<T> getBy(String property, Object value);
    T getOneBy(String property, Object value);
    T add(T entity);
    T update(String id, T entity);
    void delete(String id);
}
