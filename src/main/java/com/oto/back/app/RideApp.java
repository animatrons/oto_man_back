package com.oto.back.app;

import com.oto.back.model.Ride;
import com.oto.back.model.mapper.RideMapperImpl;
import com.oto.back.service.IRideService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideApp {
    private final IRideService rideService;

    public RideApp(IRideService rideService) {
        this.rideService = rideService;
    }
    RideMapperImpl rideMapper = new RideMapperImpl();

    public Ride get(String id) {
        return rideService.get(id);
    }

    public void delete(String id) {
        rideService.delete(id);
    }

    public List<Ride> getAll() {
        return rideService.getAll();
    }

    public void add(Ride ride) {
        rideService.add(ride);
    }

    public void update(Ride ride) {
        rideService.update(ride);
    }
}
