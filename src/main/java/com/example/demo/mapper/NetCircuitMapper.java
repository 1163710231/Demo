package com.example.demo.mapper;

import com.example.demo.pojo.NetCircuitVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NetCircuitMapper {
//    int deleteNetCircuitById(Integer circuitId);
//
//    int insert(NetCircuitVo record);
//
//    int insertNetCircuit(NetCircuitVo record);

    NetCircuitVo getNetCircuitById(Integer circuitId);

//    int updateNetCircuit(NetCircuitVo record);
//
//    int updateByPrimaryKey(NetCircuitVo record);
//
//    Integer getNetCircuitNoIsOnly(@Param("circuitNo")String circuitNo, @Param("circuitId")Integer circuitId);
//
//    Integer getNetCircuitNameIsOnly(@Param("circuitName")String circuitName, @Param("circuitId")Integer circuitId);
//
//    List<NetCircuitVo> getNetCircuitList(@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);
//
//    int getNetCircuitNum();
}
