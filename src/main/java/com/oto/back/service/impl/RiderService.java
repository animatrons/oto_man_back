package com.oto.back.service.impl;

import com.oto.back.dao.IRiderDao;
import com.oto.back.model.Rider;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.AbstractGenericService;
import com.oto.back.service.IRiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderService extends AbstractGenericService<Rider, IRiderDao> implements IRiderService {

    public RiderService(IRiderDao riderDao) {
        this.dao = riderDao;
    }

}
