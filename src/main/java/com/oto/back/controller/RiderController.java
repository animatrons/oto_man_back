package com.oto.back.controller;

import com.oto.back.app.RiderApp;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.RideDto;
import com.oto.back.model.dto.RiderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/api/riders")
public class RiderController {
    private final RiderApp riderApp;
    public RiderController(RiderApp riderApp) {
        this.riderApp = riderApp;
    }

    @GetMapping
    public ResponseDto<List<RiderDto>> getAll() {
        return riderApp.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<RiderDto> get(@PathVariable("id") String id) {
        return riderApp.get(id);
    }

    @PostMapping
    public ResponseDto<RiderDto> add(@RequestBody RiderDto riderDto) {
        return riderApp.add(riderDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<RiderDto> delete(@PathVariable("id") String id) {
        return riderApp.delete(id);
    }

    @PutMapping
    public ResponseDto<RiderDto> update(@RequestParam("id") String id, @RequestBody RiderDto riderDto) {
        return riderApp.update(id, riderDto);
    }

}
