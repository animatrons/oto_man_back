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
    public List<T> getBy(String property, Object value) {
        try {
            return dao.findBy(property, value);
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public T get(String id) {
        try {
            String table = dao.getTableName();
            return dao.find(Integer.parseInt(id)).orElseThrow(() -> new NotFoundException(String.format("%s with id %s not found", table, id)));
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public T getOneBy(String property, Object value) throws NotFoundException {
        try {
            String table = dao.getTableName();
            return dao.findOneBy(property, value)
                    .orElseThrow(() -> new NotFoundException(String.format("%s not found", table)));
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
    public T add(T entity) {
        try {
            var id = dao.insert(entity);
            entity.setId(id);
            return entity;
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public T update(String id, T entity) {
        try {
            dao.update(Integer.parseInt(id), entity);
            entity.setId(Integer.parseInt(id));
            return entity;
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            dao.delete(Integer.parseInt(id));
        } catch (TechnicalException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
