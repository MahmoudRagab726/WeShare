package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Area;
import com.menainnovations.weshare.model.City;
import com.menainnovations.weshare.repository.AreaRepository;
import com.menainnovations.weshare.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    CityRepository cityRepository;
    @Override
    public List<Area> getAllAreas() {
        return areaRepository.findAllByOrderByAreaName();
    }

    public String addArea(long id, Area area) {
        City city=cityRepository.findOne(id);
        if(city!=null){
            area.setCity(city);
            areaRepository.save(area);
            return "{\"status\": 1}";
        }else {
            return "{\"status\": 0}";
        }

    }

    public String deleteArea(long id) {
        try {
            areaRepository.delete(id);
        }catch (Exception e){
            return "{\"status\": 0}";
        }
        return "{\"status\": 1}";
    }
}
