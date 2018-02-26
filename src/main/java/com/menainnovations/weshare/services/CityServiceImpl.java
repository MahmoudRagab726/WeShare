package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.City;
import com.menainnovations.weshare.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAllByOrderByCityName();
    }
}
