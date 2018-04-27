package com.menainnovations.weshare.controller;


import com.menainnovations.weshare.model.Area;
import com.menainnovations.weshare.model.City;
import com.menainnovations.weshare.services.AreaServiceImpl;
import com.menainnovations.weshare.services.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CityAreaController {
    @Autowired
    CityServiceImpl cityService;
    @Autowired
    AreaServiceImpl areaService;

    @RequestMapping(value = "/cities" ,method = RequestMethod.GET)
    public List<City> getAllCities(){
        return cityService.getAllCities();
    }

    @RequestMapping(value = "/city" ,method = RequestMethod.POST)
    public String addCity(@RequestBody City city){
        return cityService.addCity(city);
    }
    @RequestMapping(value = "/city/{id}" ,method = RequestMethod.POST)
    public String deleteCity(@PathVariable long id){
        return cityService.deleteCity(id);
    }
    @RequestMapping(value = "/city/{id}/area" ,method = RequestMethod.POST)
    public String addArea(@PathVariable long id ,@RequestBody Area area){
        return areaService.addArea(id,area);
    }
    @RequestMapping(value = "/area/{id}" ,method = RequestMethod.POST)
    public String deleteArea(@PathVariable long id){
        return areaService.deleteArea(id);
    }


}
