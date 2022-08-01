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
    public int insertRandomData(Random random, int insertNumber) {
        long startTime = System.currentTimeMillis();  // 获取当前系统时间
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

            // 设置 time、 time_prefix 和 heat_supply
            buildingSupply.setTime(date);
            buildingSupply.setTimePrefix(sdf.format(date));
            buildingSupply.setHeatSupply(random.nextFloat() * 100);

            insertCount += buildingSupplyMapper.insertOneData(buildingSupply);
        }
        // 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数
        long endTime = System.currentTimeMillis();
        double usedMinute = (endTime - startTime) / 1000.0 / 60.0;
        System.out.println("成功插入了 " + insertCount + " 条数据，共用时 " + usedMinute + " 分钟");
        return insertCount;
    }

    /**
     * @param buildingNumber   建筑物数量
     * @param dayNumber        天数
     * @param dataNumberPerDay 每天要生成的数据量
     * @return 成功插入的数据的数量
     */
    public int insertBuildingSupplyData(int buildingNumber, int dayNumber, int dataNumberPerDay) {
        long startTime = System.currentTimeMillis();  // 获取当前系统时间
        int insertCount = 0;
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Random random = new Random();
        // 对于每个建筑物
        for (int building_id = 0; building_id < buildingNumber; building_id++) {
            // 对于每个建筑物的每天
            for (int day = 0; day < dayNumber; day++) {
                // 每天生成指定数量的数据
                for (int i = 0; i < dataNumberPerDay; i++) {
                    BuildingSupply buildingSupply = new BuildingSupply();

                    // 设置 city、station 和 building_id
                    buildingSupply.setCity("哈尔滨");
                    buildingSupply.setStation("清泉" + (building_id / 100));
                    buildingSupply.setBuildingId(building_id);

                    // 设置 Date 及相应的 Calendar 和 SimpleDateFormat
                    Date date = new Date();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, day);  // 把日期往后增加一天，整数是往后推，负数是往前移动
                    date = calendar.getTime();  // 这个时间就是日期往后推一天的结果

                    // 设置 time、 time_prefix 和 heat_supply
                    buildingSupply.setTime(date);
                    buildingSupply.setTimePrefix(sdf.format(date));
                    buildingSupply.setHeatSupply(random.nextFloat() * 100.0f);

                    insertCount += buildingSupplyMapper.insertOneData(buildingSupply);
                }
            }
        }
        // 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数
        long endTime = System.currentTimeMillis();
        double usedMinute = (endTime - startTime) / 1000.0 / 60.0;
        System.out.println("成功插入了 " + insertCount + " 条数据，共用时 " + usedMinute + " 分钟");
        return insertCount;
    }
}
