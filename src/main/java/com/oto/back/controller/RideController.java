package com.oto.back.controller;

import com.oto.back.app.RideApp;
import com.oto.back.model.dto.AutoDto;
import com.oto.back.model.dto.ResponseDto;
import com.oto.back.model.dto.RideDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/auth/api/rides")
public class RideController {

    private final RideApp rideApp;
    public RideController(RideApp rideApp) {
        this.rideApp = rideApp;
    }

    @GetMapping
    public ResponseDto<List<RideDto>> getAll() {
        return rideApp.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<RideDto> get(@PathVariable("id") String id) {
        return rideApp.get(id);
    }

    @PostMapping
    public ResponseDto<RideDto> add(@RequestBody RideDto rideDto) {
        return rideApp.add(rideDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<RideDto> delete(@PathVariable("id") String id) {
        return rideApp.delete(id);
    }

    @PutMapping
    public ResponseDto<RideDto> update(@RequestParam("id") String id, @RequestBody RideDto rideDto) {
        return rideApp.update(id, rideDto);
    }
}
