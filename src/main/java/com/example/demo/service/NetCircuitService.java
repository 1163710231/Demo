package com.example.demo.service;

import com.example.demo.mapper.NetCircuitMapper;
import com.example.demo.pojo.NetCircuitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetCircuitService {
    @Autowired(required = false)
    private NetCircuitMapper netCircuitMapper;

    /**
     * @param circuitId 环路的 id
     * @return 环路的信息
     * @author: 4444
     */
    public NetCircuitVo getNetCircuitById(Integer circuitId) {
        System.out.println("circuitId:" + circuitId);
        NetCircuitVo netCircuitVo = netCircuitMapper.getNetCircuitById(circuitId);
        System.out.println(netCircuitVo);
        return netCircuitVo;
    }
}
