package com.example.demo.mapper;

import com.example.demo.pojo.BuildingSupply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 这个注解表示这是一个 mybatis 的 mapper 类
@Repository  // 被 spring 整合
public interface BuildingSupplyMapper {
    List<BuildingSupply> selectAll();

    BuildingSupply selectById(int id);

    int insertOneData(BuildingSupply buildingSupply);
}
