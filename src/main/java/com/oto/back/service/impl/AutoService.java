package com.oto.back.service.impl;

import com.oto.back.dao.IAutoDao;
import com.oto.back.dao.impl.AutoDao;
import com.oto.back.model.Auto;
import com.oto.back.model.exception.NotFoundException;
import com.oto.back.service.AbstractGenericService;
import com.oto.back.service.IAutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService extends AbstractGenericService<Auto, IAutoDao> implements IAutoService {

    public AutoService(IAutoDao autoDao) {
        this.dao = autoDao;
    }

}
