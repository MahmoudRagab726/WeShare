package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City,Long>{
    public List<City> findAllByOrderByCityName();
}
