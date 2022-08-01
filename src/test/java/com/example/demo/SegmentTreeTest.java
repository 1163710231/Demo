package com.example.demo;

import com.example.demo.segmentTree.SegmentTree;
import com.example.demo.segmentTree.SegmentTreeFactory;
import com.example.demo.segmentTree.TreeNode;
import com.example.demo.segmentTree.TreeNodeMergeTool;
import org.junit.jupiter.api.Test;

public class SegmentTreeTest {
    @Test
    public void testSegmentTreeWithInteger() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);
        System.out.println(segmentTree.queryInterval(2, 4));
        segmentTree.setNode(3, 5);
        System.out.println(segmentTree.queryInterval(2, 4));
    }

    @Test
    public void testSegmentTreeWithTreeNode() {
        Integer[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        TreeNode[] dd = SegmentTreeFactory.getTreeNodes(nums);
        TreeNodeMergeTool treeNodeMergeTool = new TreeNodeMergeTool();
        SegmentTree<TreeNode> segmentTree = new SegmentTree<TreeNode>(dd, treeNodeMergeTool);
        System.out.println(segmentTree);
        System.out.println(segmentTree.queryInterval(0, 8));
    }
}
