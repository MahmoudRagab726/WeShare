package com.menainnovations.weshare.controller;


import com.menainnovations.weshare.model.Area;
import com.menainnovations.weshare.model.City;
import com.menainnovations.weshare.services.AreaServiceImpl;
import com.menainnovations.weshare.services.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CityAreaController {
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    AreaServiceImpl areaService;

    @RequestMapping(value = "/city" ,method = RequestMethod.GET)
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }
    @RequestMapping(value = "/area" , method = RequestMethod.GET)
    public List<Area> getAllAreas(){
        return areaService.getAllAreas();
    }
}
