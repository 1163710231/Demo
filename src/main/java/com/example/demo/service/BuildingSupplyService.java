package com.example.demo.service;

import com.example.demo.mapper.BuildingSupplyMapper;
import com.example.demo.pojo.BuildingSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingSupplyService {
    @Autowired
    BuildingSupplyMapper buildingSupplyMapper;

    /**
     *
     * @return 返回所有的 BuildingSupply 数据
     */
    public List<BuildingSupply> selectAll() {
        return buildingSupplyMapper.selectAll();
    }
}
