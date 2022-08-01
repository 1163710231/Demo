package com.example.demo.segmentTree;

public class SegmentTreeFactory {
    public static TreeNode[] getTreeNodes(Integer[] nums) {
        TreeNode[] treeNodes = new TreeNode[nums.length];
        for (int i = 0; i < treeNodes.length; i++) {
            treeNodes[i] = new TreeNode(nums[i], nums[i], nums[i], nums[i], i, i, i, i);
        }
        return treeNodes;
    }
}
