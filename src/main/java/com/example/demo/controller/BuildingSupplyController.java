package com.example.demo.controller;

import com.example.demo.pojo.BuildingSupply;
import com.example.demo.service.BuildingSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BuildingSupplyController {
    @Autowired
    BuildingSupplyService buildingSupplyService;

    @GetMapping("/selectAllBuildingSupplies")
    @ResponseBody
    public List<BuildingSupply> selectAll() {
        List<BuildingSupply> buildingSupplies = buildingSupplyService.selectAll();
        System.out.println(buildingSupplies);
        return buildingSupplies;
    }

    @GetMapping("/selectBuildingSupplyById/{id}")
    @ResponseBody
    public BuildingSupply selectById(@PathVariable("id") int id) {
        BuildingSupply buildingSupply = buildingSupplyService.selectById(id);
        System.out.println("成功查找到了目标 BuildingSupply :" + buildingSupply);
        return buildingSupply;
    }
}
