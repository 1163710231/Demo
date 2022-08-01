package com.example.demo.service;

import com.example.demo.mapper.BuildingSupplyMapper;
import com.example.demo.pojo.BuildingSupply;
import com.example.demo.segmentTree.SegmentTree;
import com.example.demo.segmentTree.SegmentTreeFactory;
import com.example.demo.segmentTree.TreeNode;
import com.example.demo.segmentTree.TreeNodeMergeTool;
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
            buildingSupply.setBuildingId(String.valueOf(buildingId));

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
        for (int buildingId = 0; buildingId < buildingNumber; buildingId++) {
            // 对于每个建筑物的每天
            for (int day = 0; day < dayNumber; day++) {
                // 每天生成指定数量的数据
                for (int i = 0; i < dataNumberPerDay; i++) {
                    BuildingSupply buildingSupply = new BuildingSupply();

                    // 设置 city、station 和 building_id
                    buildingSupply.setCity("哈尔滨");
                    buildingSupply.setStation("清泉" + (buildingId / 100));
                    buildingSupply.setBuildingId(String.valueOf(buildingId));

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

    /**
     * @param buildingId 要查询的建筑物的 id
     * @param day        要查询的日期（天）
     * @return 所有查询到的 BuildingSupply 数据
     */
    public List<BuildingSupply> selectByBuildingIdAndDate(String buildingId, Date day) {
        long startTime = System.currentTimeMillis();  // 获取当前系统时间
        List<BuildingSupply> buildingSupplies = buildingSupplyMapper.selectByBuildingIdAndDate(buildingId, day);
        // 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数
        long endTime = System.currentTimeMillis();
        double usedSecond = (endTime - startTime) / 1000.0;
        System.out.println("成功查询到 " + buildingSupplies.size() + " 条数据，共用时 " + usedSecond + " 秒");
        return buildingSupplies;
    }

    /**
     * @param buildingId 要查询的建筑物的 id
     * @param day        要查询的日期（天）
     * @return 所有查询到的 BuildingSupply 数据
     */
    public List<BuildingSupply> selectByBuildingIdAndDay(String buildingId, Date day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long startTime = System.currentTimeMillis();  // 获取当前系统时间
        List<BuildingSupply> buildingSupplies = buildingSupplyMapper.selectByBuildingIdAndDay(buildingId, sdf.format(day));
        // 获取当前的系统时间，与初始时间相减就是程序运行的毫秒数
        long endTime = System.currentTimeMillis();
        double usedSecond = (endTime - startTime) / 1000.0;
        System.out.println("成功查询到 " + buildingSupplies.size() + " 条数据，共用时 " + usedSecond + " 秒");
        return buildingSupplies;
    }

    /**
     *
     * @param buildingSupplies BuildingSupply 数据组成的 List
     * @return 用 buildingSupplies 生成的线段树
     */
    public SegmentTree<TreeNode> useBuildingSupplyListOnSegmentTree(List<BuildingSupply> buildingSupplies) {
        // 把 buildingSupplies 中的 heatSupply 值存储在数组中，同时求 heatSupplies 的平均值
        double sum = 0;
        Integer[] heatSupplies = new Integer[buildingSupplies.size()];
        for (int i = 0; i < heatSupplies.length; i++) {
            heatSupplies[i] = (int) buildingSupplies.get(i).getHeatSupply();
            sum += heatSupplies[i];
        }
        int averageHeatSupply = (int) (sum / heatSupplies.length);
        // 用 averageHeatSupply 与 heatSupplies 中的每一项相减，使得 heatSupplies 中的元素有正有负
        for (int i = 0; i < heatSupplies.length; i++) {
            heatSupplies[i] = heatSupplies[i] - averageHeatSupply;
        }
        // 用 heatSupplies 构建线段树并查找供热量最大的区间的左右边界下标
        TreeNode[] dd = SegmentTreeFactory.getTreeNodes(heatSupplies);
        TreeNodeMergeTool treeNodeMergeTool = new TreeNodeMergeTool();

        return new SegmentTree<TreeNode>(dd, treeNodeMergeTool);
    }
}
