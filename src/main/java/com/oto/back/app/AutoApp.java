package com.oto.back.app;

import com.oto.back.model.Auto;
import com.oto.back.model.Rider;
import com.oto.back.model.dto.AutoDto;
import com.oto.back.app.mapper.AutoMapperImpl;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.RiderDto;
import com.oto.back.service.IAutoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoApp {
    private final IAutoService autoService;

    public AutoApp(IAutoService AutoService) {
        this.autoService = AutoService;
    }
    AutoMapperImpl autoMapper = new AutoMapperImpl();

    public ResponseDto<AutoDto> get(String id) {
        Auto auto = autoService.get(id);
        var autoDto = autoMapper.toDto(auto);
        return new ResponseDto<>(autoDto, 200, "OK");
    }

    public ResponseDto<AutoDto> delete(String id) {
        autoService.delete(id);
        return new ResponseDto<>(null, 200, "DELETED");
    }

    public ResponseDto<List<AutoDto>> getAll() {
        List<Auto> autos = autoService.getAll();
        return new ResponseDto<>(autoMapper.toDtos(autos), 200, "OK");
    }

    public ResponseDto<AutoDto> add(AutoDto autoDto) {
        var entity = autoMapper.toEntity(autoDto);
        return new ResponseDto<>(autoMapper.toDto(entity), 200, "ADDED");
    }

    public ResponseDto<AutoDto> update(String id, AutoDto autoDto) {
        var entity = autoService.update(id, autoMapper.toEntity(autoDto));
        return new ResponseDto<>(autoMapper.toDto(entity), 200, "DELETED");
    }

    /**
     * Used only in mapper, provided in expression of the mapping to convert id in entity to it's corresponding DTO in mapped dto.
     * DO NOT CHANGE OR TOUCH, MAPPER WILL BREAK
     *
     * @param id id
     * @return dto
     */
    public AutoDto getDtoForMapper(Integer id) {
        Auto auto = autoService.get(id.toString());
        return autoMapper.toDto(auto);
    }
}
