package com.example.demo.service.impl;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cr;

    @Override
    public List<City> findAll() {
        return cr.findAll();
    }

    @Override
    public City create(City city) {
        return cr.insert(city);
    }
}