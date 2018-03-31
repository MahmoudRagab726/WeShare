package com.menainnovations.weshare.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    private long id ;
    private String cityName;
    private List<Area> areas;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "City_Name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
