package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.City;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CityService {
    public List<City> getAllCities();
}
