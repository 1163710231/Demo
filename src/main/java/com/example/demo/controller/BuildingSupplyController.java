package com.example.demo.controller;

import com.example.demo.pojo.BuildingSupply;
import com.example.demo.segmentTree.SegmentTree;
import com.example.demo.segmentTree.SegmentTreeFactory;
import com.example.demo.segmentTree.TreeNode;
import com.example.demo.segmentTree.TreeNodeMergeTool;
import com.example.demo.service.BuildingSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @GetMapping("/insertRandomData/{insertNumber}")
    @ResponseBody
    public String insertRandomData(@PathVariable("insertNumber") int insertNumber) {
        Random random = new Random();
        int insertSucceedNumber = buildingSupplyService.insertRandomData(random, insertNumber);
        return "成功插入了 " + insertSucceedNumber + " 条数据";
    }

    @GetMapping("/insertBuildingSupplyData/{buildingNumber}/{dayNumber}/{dataNumberPerDay}")
    @ResponseBody
    public String insertBuildingSupplyData(@PathVariable("buildingNumber") int buildingNumber, @PathVariable("dayNumber") int dayNumber, @PathVariable("dataNumberPerDay") int dataNumberPerDay) {
        int insertSucceedNumber = buildingSupplyService.insertBuildingSupplyData(buildingNumber, dayNumber, dataNumberPerDay);
        return "成功插入了 " + insertSucceedNumber + " 条数据";
    }

    @GetMapping("/selectMaxIntervalByBuildingIdAndDay/{buildingId}/{dayOffset}")
    @ResponseBody
    public String selectMaxIntervalByBuildingIdAndDay(@PathVariable("buildingId") String buildingId, @PathVariable("dayOffset") int dayOffset) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayOffset);
        date = calendar.getTime();
        List<BuildingSupply> buildingSupplies = buildingSupplyService.selectByBuildingIdAndDay(buildingId, date);
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
        SegmentTree<TreeNode> segmentTree = new SegmentTree<TreeNode>(dd, treeNodeMergeTool);
        TreeNode rootNode = segmentTree.queryInterval(0, heatSupplies.length - 1);
        int leftBorder = rootNode.getLeftBorder();
        int rightBorder = rootNode.getRightBorder();
        System.out.println(leftBorder + " || " + rightBorder);
        System.out.println(rootNode);
        return buildingSupplies.toString();
    }
}
