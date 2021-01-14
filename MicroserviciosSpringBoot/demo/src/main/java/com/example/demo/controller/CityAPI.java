package com.example.demo.controller;

import com.example.demo.model.City;

import java.util.List;

public interface CityAPI {
    List<City> findAll();

    City create(City city);
}