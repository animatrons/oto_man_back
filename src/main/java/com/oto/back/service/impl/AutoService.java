package com.oto.back.service.impl;

import com.oto.back.dao.IAutoDao;
import com.oto.back.model.Auto;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.IAutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService implements IAutoService {
    private final IAutoDao autoDao;

    public AutoService(IAutoDao autoDao) {
        this.autoDao = autoDao;
    }

    @Override
    public Auto get(String id) {
        return autoDao.find(id).orElseThrow(() -> new NotFoundException(String.format("Auto with id %s not found", id)));
    }

    @Override
    public List<Auto> getAll() {
        return autoDao.findAll();
    }

    @Override
    public void add(Auto entity) {
        autoDao.insert(entity);
    }

    @Override
    public void update(Auto entity) {

    }

    @Override
    public void delete(String id) {
        autoDao.delete(id);
    }
}
