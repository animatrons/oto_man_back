package com.oto.back.app;

import com.oto.back.model.Rider;
import com.oto.back.model.dto.RiderDto;
import com.oto.back.app.mapper.RiderMapperImpl;
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

    public RiderDto get(String id) {
        Rider rider = riderService.get(id);
        return riderMapper.toDto(rider);
    }

    public void delete(String id) {
        riderService.delete(id);
    }

    public List<RiderDto> getAll() {
        List<Rider> riders = riderService.getAll();
        return riderMapper.toDtos(riders);
    }

    public void add(RiderDto riderDto) {
        riderService.add(riderMapper.toEntity(riderDto));
    }

    public void update(RiderDto riderDto) {
        riderService.update(riderMapper.toEntity(riderDto));
    }
}
