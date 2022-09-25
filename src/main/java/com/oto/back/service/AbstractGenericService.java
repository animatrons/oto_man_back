package com.oto.back.service;

import com.oto.back.dao.IGenericDao;
import com.oto.back.model.AEntity;
import com.oto.back.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractGenericService<T extends AEntity, U extends IGenericDao<T>> implements IBaseGenericService<T> {

    protected U dao = null;

    @Override
    public T get(String id) {
        String table = dao.getTableName();
        return dao.find(id).orElseThrow(() -> new NotFoundException(String.format("%s with id %s not found", table, id)));
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public void add(T entity) {
        dao.insert(entity);
    }

    @Override
    public void update(String id, T entity) {
        dao.update(id, entity);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }
}
