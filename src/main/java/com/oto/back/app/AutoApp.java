package com.oto.back.app;

import com.oto.back.model.Auto;
import com.oto.back.model.mapper.AutoMapperImpl;
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

    public Auto get(String id) {
        return autoService.get(id);
    }

    public void delete(String id) {
        autoService.delete(id);
    }

    public List<Auto> getAll() {
        return autoService.getAll();
    }

    public void add(Auto auto) {
        autoService.add(auto);
    }

    public void update(Auto auto) {
        autoService.update(auto);
    }
}
