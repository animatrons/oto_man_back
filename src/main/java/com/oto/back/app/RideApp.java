package com.oto.back.app;

import com.oto.back.model.Ride;
import com.oto.back.model.dto.RideDto;
import com.oto.back.app.mapper.RideMapperImpl;
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

    public RideDto get(String id) {
        Ride ride = rideService.get(id);
        return rideMapper.toDto(ride);
    }

    public void delete(String id) {
        rideService.delete(id);
    }

    public List<RideDto> getAll() {
        List<Ride> rides = rideService.getAll();
        return rideMapper.toDtos(rides);
    }

    public void add(RideDto rideDto) {
        rideService.add(rideMapper.toEntity(rideDto));
    }

    public void update(RideDto rideDto) {
        rideService.update(rideMapper.toEntity(rideDto));
    }
}
