package com.oto.back.dao;

import com.oto.back.model.AEntity;

import java.util.List;
import java.util.Optional;

public interface IGenericDao<T extends AEntity> {

    Optional<T> find(String id);
    List<T> findAll();
    int insert(T entity);
    int delete(String id);
}
