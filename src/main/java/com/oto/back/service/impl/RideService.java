package com.oto.back.service.impl;

import com.oto.back.dao.IRideDao;
import com.oto.back.model.Ride;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.IRideService;

import java.util.List;

public class RideService implements IRideService {
    private final IRideDao rideDao;

    public RideService(IRideDao rideDao) {
        this.rideDao = rideDao;
    }


    @Override
    public Ride get(String id) {
        return rideDao.find(id).orElseThrow(() -> new NotFoundException(String.format("Ride with id %s not found", id)));
    }

    @Override
    public List<Ride> getAll() {
        return rideDao.findAll();
    }

    @Override
    public void add(Ride entity) {
        rideDao.insert(entity);
    }

    @Override
    public void update(Ride entity) {

    }

    @Override
    public void delete(String id) {
        rideDao.delete(id);
    }
}
