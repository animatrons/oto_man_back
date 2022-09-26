package com.oto.back.controller;

import com.oto.back.app.RiderApp;
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
    public List<RiderDto> getAll() {
        return riderApp.getAll();
    }

    @GetMapping("{id}")
    public RiderDto get(@PathVariable("id") String id) {
        return riderApp.get(id);
    }

    @PostMapping
    public void add(@RequestBody RiderDto riderDto) {
        riderApp.add(riderDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        riderApp.delete(id);
    }

    @PutMapping
    public void update(@RequestParam("id") String id, @RequestBody RiderDto riderDto) {
        riderApp.update(id, riderDto);
    }

}
