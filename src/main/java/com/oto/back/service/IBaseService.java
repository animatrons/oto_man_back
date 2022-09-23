package com.oto.back.service;

import com.oto.back.model.AEntity;
import com.oto.back.model.User;

import java.util.List;

public interface IBaseService<T extends AEntity> {
    T get(String id);
    List<T> getAll();
    void add(T entity);
    void update(T entity);
    void delete(String id);
}
