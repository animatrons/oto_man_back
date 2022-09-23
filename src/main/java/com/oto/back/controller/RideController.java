package com.oto.back.controller;

import com.oto.back.app.RideApp;
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
    public List<RideDto> getAll() {
        return rideApp.getAll();
    }

    @GetMapping("{id}")
    public RideDto get(@PathVariable("id") String id) {
        return rideApp.get(id);
    }

    @PostMapping
    public void add(@RequestBody RideDto rideDto) {
        rideApp.add(rideDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        rideApp.delete(id);
    }
}
