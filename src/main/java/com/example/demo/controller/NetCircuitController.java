package com.example.demo.controller;

import com.example.demo.pojo.NetCircuitVo;
import com.example.demo.service.NetCircuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NetCircuitController {
    @Autowired
    private NetCircuitService netCircuitService;

    @GetMapping("/getNetCircuitById/{circuitId}")
    @ResponseBody
    public String getNetCircuitById(@PathVariable("circuitId") Integer circuitId) {
        NetCircuitVo netCircuitVo = netCircuitService.getNetCircuitById(circuitId);
        System.out.println(netCircuitVo.toString());
        return netCircuitVo.toString();
    }
}
