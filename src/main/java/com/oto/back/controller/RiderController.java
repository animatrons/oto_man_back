package com.oto.back.controller;

import com.oto.back.app.RiderApp;
import com.oto.back.model.dto.RiderDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
}
