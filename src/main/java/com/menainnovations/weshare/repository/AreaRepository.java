package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends CrudRepository<Area,Long>{
    public List<Area> findAllByOrderByAreaName();
}
