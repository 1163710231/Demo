package com.example.demo.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingSupply {
    private int id;
    private String city;
    private String station;
    private int buildingId;
    private Date time;
    private String timePrefix;
    private float heatSupply;
}
