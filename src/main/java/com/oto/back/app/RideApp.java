package com.oto.back.app;

import com.oto.back.model.Ride;
import com.oto.back.model.dto.ResponseDto;
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

    public ResponseDto<RideDto> get(String id) {
        Ride ride = rideService.get(id);
        return new ResponseDto<>(rideMapper.toDto(ride), 200, "OK");
    }

    public ResponseDto<RideDto> delete(String id) {
        rideService.delete(id);
        return new ResponseDto<>(null, 200, "DELETED");
    }

    public ResponseDto<List<RideDto>> getAll() {
        List<Ride> rides = rideService.getAll();
        return new ResponseDto<>(rideMapper.toDtos(rides), 200, "OK");
    }

    public ResponseDto<RideDto> add(RideDto rideDto) {
        rideService.add(rideMapper.toEntity(rideDto));
        return new ResponseDto<>(rideDto, 200, "ADDED");
    }

    public ResponseDto<RideDto> update(String id, RideDto rideDto) {
        rideService.update(id, rideMapper.toEntity(rideDto));
        return new ResponseDto<>(rideDto, 200, "UPDATED");
    }
}
