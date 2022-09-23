package com.oto.back.service.impl;

import com.oto.back.dao.IRiderDao;
import com.oto.back.model.Rider;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.IRiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService implements IRiderService {
    private final IRiderDao riderDao;

    public RiderService(IRiderDao riderDao) {
        this.riderDao = riderDao;
    }

    @Override
    public Rider get(String id) {
        return riderDao.find(id).orElseThrow(() -> new NotFoundException(String.format("Rider with id %s not found", id)));
    }

    @Override
    public List<Rider> getAll() {
        return riderDao.findAll();
    }

    @Override
    public void add(Rider entity) {
        riderDao.insert(entity);
    }

    @Override
    public void update(Rider entity) {

    }

    @Override
    public void delete(String id) {
        riderDao.delete(id);
    }
}
