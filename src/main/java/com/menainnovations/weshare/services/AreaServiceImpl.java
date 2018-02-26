package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Area;
import com.menainnovations.weshare.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository areaRepository;
    @Override
    public List<Area> getAllAreas() {
        return areaRepository.findAllByOrderByAreaName();
    }
}
