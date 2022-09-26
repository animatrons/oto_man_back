package com.oto.back.app;

import com.oto.back.model.Rider;
import com.oto.back.model.dto.ResponseDto;
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

    public ResponseDto<RiderDto> get(String id) {
        Rider rider = riderService.get(id);
        return new ResponseDto<>(riderMapper.toDto(rider), 200, "OK");
    }

    public ResponseDto<RiderDto> delete(String id) {
        riderService.delete(id);
        return new ResponseDto<>(null, 200, "DELETED");
    }

    public ResponseDto<List<RiderDto>> getAll() {
        List<Rider> riders = riderService.getAll();
        return new ResponseDto<>(riderMapper.toDtos(riders), 200, "OK");
    }

    public ResponseDto<RiderDto> add(RiderDto riderDto) {
        riderService.add(riderMapper.toEntity(riderDto));
        return new ResponseDto<>(riderDto, 200, "ADDED");
    }

    public ResponseDto<RiderDto> update(String id, RiderDto riderDto) {
        riderService.update(id, riderMapper.toEntity(riderDto));
        return new ResponseDto<>(riderDto, 200, "UPDATED");
    }

    /**
     * Used only in mapper, provided in expression of the mapping to convert id in entity to it's corresponding DTO in mapped dto.
     * DO NOT CHANGE OR TOUCH, MAPPER WILL BREAK
     *
     * @param id id
     * @return dto
     */
    public RiderDto getDtoForMapper(String id) {
        Rider rider = riderService.get(id);
        return riderMapper.toDto(rider);
    }
}
