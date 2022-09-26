package com.oto.back.controller;

import com.oto.back.app.AutoApp;
import com.oto.back.model.dto.AutoDto;
import com.oto.back.model.dto.ResponseDto;
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
    public ResponseDto<List<AutoDto>> getAll() {
        return autoApp.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<AutoDto> get(@PathVariable("id") String id) {
        return autoApp.get(id);
    }

    @PostMapping
    public ResponseDto<AutoDto> add(@RequestBody AutoDto autoDto) {
        return autoApp.add(autoDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<AutoDto> delete(@PathVariable("id") String id) {
        return autoApp.delete(id);
    }

    @PutMapping
    public ResponseDto<AutoDto> update(@RequestParam("id") String id, @RequestBody AutoDto autoDto) {
        return autoApp.update(id, autoDto);
    }

}
