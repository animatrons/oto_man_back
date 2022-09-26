package com.oto.back.service;

import com.oto.back.dao.IGenericDao;
import com.oto.back.model.AEntity;
import com.oto.back.model.exception.InternalServerException;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.model.exception.TechnicalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractGenericService<T extends AEntity, U extends IGenericDao<T>> implements IBaseGenericService<T> {

    protected U dao = null;

    @Override
    public T get(String id) {
        try {
            String table = dao.getTableName();
            return dao.find(id).orElseThrow(() -> new NotFoundException(String.format("%s with id %s not found", table, id)));
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        try {
            return dao.findAll();
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public void add(T entity) {
        try {
            dao.insert(entity);
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public void update(String id, T entity) {
        try {
            dao.update(id, entity);
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            dao.delete(id);
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
