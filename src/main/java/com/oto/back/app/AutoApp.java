package com.oto.back.app;

import com.oto.back.model.Auto;
import com.oto.back.model.dto.AutoDto;
import com.oto.back.app.mapper.AutoMapperImpl;
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

    public AutoDto get(String id) {
        Auto auto = autoService.get(id);
        return autoMapper.toDto(auto);
    }

    public void delete(String id) {
        autoService.delete(id);
    }

    public List<AutoDto> getAll() {
        List<Auto> autos = autoService.getAll();
        return autoMapper.toDtos(autos);
    }

    public void add(AutoDto autoDto) {
        autoService.add(autoMapper.toEntity(autoDto));
    }

    public void update(AutoDto autoDto) {
        autoService.update(autoMapper.toEntity(autoDto));
    }
}
