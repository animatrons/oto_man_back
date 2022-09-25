package com.oto.back.service.impl;

import com.oto.back.dao.IRideDao;
import com.oto.back.model.Ride;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.AbstractGenericService;
import com.oto.back.service.IRideService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService extends AbstractGenericService<Ride, IRideDao> implements IRideService {

    public RideService(IRideDao rideDao) {
        this.dao = rideDao;
    }

}
