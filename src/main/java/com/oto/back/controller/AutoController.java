package com.oto.back.controller;

import com.oto.back.app.AutoApp;
import com.oto.back.model.dto.AutoDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/api/autos")
public class AutoController {
    private final AutoApp autoApp;
    public AutoController(AutoApp autoApp) {
        this.autoApp = autoApp;
    }

    @GetMapping
    public List<AutoDto> getAll() {
        return autoApp.getAll();
    }

    @GetMapping("{id}")
    public AutoDto get(@PathVariable("id") String id) {
        return autoApp.get(id);
    }

    @PostMapping
    public void add(@RequestBody AutoDto autoDto) {
        autoApp.add(autoDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        autoApp.delete(id);
    }
}
