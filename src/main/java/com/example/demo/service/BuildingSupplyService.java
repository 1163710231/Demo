package com.example.demo.service;

import com.example.demo.mapper.BuildingSupplyMapper;
import com.example.demo.pojo.BuildingSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BuildingSupplyService {
    @Autowired
    BuildingSupplyMapper buildingSupplyMapper;

    /**
     * @return 返回所有的 BuildingSupply 数据
     */
    public List<BuildingSupply> selectAll() {
        return buildingSupplyMapper.selectAll();
    }

    /**
     * @param id 要查找的 BuildingSupply 的 id
     * @return 返回要查找的 BuildingSupply 数据
     */
    public BuildingSupply selectById(int id) {
        return buildingSupplyMapper.selectById(id);
    }

    /**
     * @param random       用于产生数据的随机器
     * @param insertNumber 要插入的数据的数量
     * @return 成功插入的数据的数量
     */
    public int insertData(Random random, int insertNumber) {
        int insertCount = 0;
        for (int i = 0; i < insertNumber; i++) {
            BuildingSupply buildingSupply = new BuildingSupply();

            // 设置 city、station 和 building_id
            int buildingId = random.nextInt(1000);
            buildingSupply.setCity("哈尔滨");
            buildingSupply.setStation("清泉" + (buildingId / 100));
            buildingSupply.setBuildingId(buildingId);

            // 设置 Date 及相应的 Calendar 和 SimpleDateFormat
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            calendar.setTime(date);
            calendar.add(Calendar.DATE, random.nextInt(10));  // 把日期往后增加一天，整数是往后推，负数是往前移动
            date = calendar.getTime();  // 这个时间就是日期往后推一天的结果

            // 设置 time 和 time_prefix
            buildingSupply.setTime(date);
            buildingSupply.setTimePrefix(sdf.format(date));

            insertCount += buildingSupplyMapper.insertOneData(buildingSupply);
        }
        System.out.println("成功插入了 " + insertCount + " 条数据");
        return insertCount;
    }
}
