package com.oto.back.app;

import com.oto.back.model.Rider;
import com.oto.back.model.mapper.RiderMapperImpl;
import com.oto.back.service.IRiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderApp {
    private final IRiderService riderService;

    public RiderApp(IRiderService riderService) {
        this.riderService = riderService;
    }
    RiderMapperImpl riderMapper = new RiderMapperImpl();

    public Rider get(String id) {
        return riderService.get(id);
    }

    public void delete(String id) {
        riderService.delete(id);
    }

    public List<Rider> getAll() {
        return riderService.getAll();
    }

    public void add(Rider rider) {
        riderService.add(rider);
    }

    public void update(Rider rider) {
        riderService.update(rider);
    }
}
