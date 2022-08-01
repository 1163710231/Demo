package com.example.demo.mapper;

import com.example.demo.pojo.BuildingSupply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper  // 这个注解表示这是一个 mybatis 的 mapper 类
@Repository  // 被 spring 整合
public interface BuildingSupplyMapper {
    List<BuildingSupply> selectAll();

    BuildingSupply selectById(int id);

    List<BuildingSupply> selectByBuildingIdAndDate(@Param("buildingId") String buildingId, @Param("day") Date day);

    List<BuildingSupply> selectByBuildingIdAndDay(@Param("buildingId") String buildingId, @Param("timePrefix") String timePrefix);

    int insertOneData(BuildingSupply buildingSupply);
}
