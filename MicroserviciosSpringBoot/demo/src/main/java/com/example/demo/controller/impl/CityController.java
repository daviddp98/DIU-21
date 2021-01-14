package com.example.demo.controller.impl;

import com.example.demo.controller.CityAPI;
import com.example.demo.model.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController implements CityAPI {
    @Autowired
    private CityService cs;

    @Override
    @GetMapping
    public List<City> findAll() {
        return cs.findAll();
    }

    @Override
    @PostMapping
    public City create(@RequestBody City city) {
        return cs.create(city);
    }
}