package com.example.demo.controller;

import com.example.demo.pojo.BuildingSupply;
import com.example.demo.segmentTree.SegmentTree;
import com.example.demo.segmentTree.TreeNode;
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

    @GetMapping("/selectMaxIntervalByBuildingIdAndDate/{buildingId}/{dayOffset}")
    @ResponseBody
    public String selectMaxIntervalByBuildingIdAndDay(@PathVariable("buildingId") String buildingId, @PathVariable("dayOffset") int dayOffset) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayOffset);
        date = calendar.getTime();
        // 先用 selectByBuildingIdAndDate 进行查询
        List<BuildingSupply> buildingSupplies1 = buildingSupplyService.selectByBuildingIdAndDate(buildingId, date);
        SegmentTree<TreeNode> segmentTree1 = buildingSupplyService.useBuildingSupplyListOnSegmentTree(buildingSupplies1);
        TreeNode rootNode1 = segmentTree1.queryInterval(0, buildingSupplies1.size() - 1);
        int leftBorder1 = rootNode1.getLeftBorder();
        int rightBorder1 = rootNode1.getRightBorder();
        System.out.println("selectByBuildingIdAndDate:" + leftBorder1 + " || " + rightBorder1);
        System.out.println(rootNode1);

        // 再用 selectByBuildingIdAndDay 进行查询
        List<BuildingSupply> buildingSupplies2 = buildingSupplyService.selectByBuildingIdAndDay(buildingId, date);
        SegmentTree<TreeNode> segmentTree2 = buildingSupplyService.useBuildingSupplyListOnSegmentTree(buildingSupplies2);
        TreeNode rootNode2 = segmentTree2.queryInterval(0, buildingSupplies2.size() - 1);
        int leftBorder2 = rootNode2.getLeftBorder();
        int rightBorder2 = rootNode2.getRightBorder();
        System.out.println("selectByBuildingIdAndDay:" + leftBorder2 + " || " + rightBorder2);
        System.out.println(rootNode2);

        System.out.println();
        return buildingSupplies1.toString();
    }
}
