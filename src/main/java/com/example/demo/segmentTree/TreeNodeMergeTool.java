package com.example.demo.segmentTree;

public class TreeNodeMergeTool implements MergeTool<TreeNode> {
    @Override
    public TreeNode merge(TreeNode leftNode, TreeNode rightNode) {
        int allSum = leftNode.getAllSum() + rightNode.getAllSum();  // 区间中所有数字的总和
        int maxLeftSum = Math.max(leftNode.getMaxLeftSum(), leftNode.getAllSum() + rightNode.getMaxLeftSum());  // 左起最大子数组和
        int maxRightSum = Math.max(rightNode.getMaxRightSum(), rightNode.getAllSum() + leftNode.getMaxRightSum());  // 右起最大子数组和
        int maxSum = Math.max(leftNode.getMaxRightSum() + rightNode.getMaxLeftSum(), Math.max(leftNode.getMaxSum(), rightNode.getMaxSum()));  // 区间中最大子数组和

        int leftMaxRightBorder;  // 最大左起子区间的右边界下标
        int rightMaxLeftBorder;  // 最大右起子区间的左边界下标
        int leftBorder;  // 最大子区间的左边界下标
        int rightBorder;  // 最大子区间的右边界下标

        // 给 leftMaxRightBorder 赋值
        if (leftNode.getMaxLeftSum() > leftNode.getAllSum() + rightNode.getMaxLeftSum()) {  // 最大左起子区间为左子区间的左起最大子区间
            leftMaxRightBorder = leftNode.getLeftMaxRightBorder();
        } else {  // 最大左起子区间为整个左子区间加右子区间的左起最大子区间
            leftMaxRightBorder = rightNode.getLeftMaxRightBorder();
        }
        // 给 rightMaxLeftBorder 赋值
        if (rightNode.getMaxRightSum() > rightNode.getAllSum() + leftNode.getMaxRightSum()) {  // 最大右起子区间为右子区间的右起最大子区间
            rightMaxLeftBorder = rightNode.getRightMaxLeftBorder();
        } else {  // 最大右起子区间为整个右子区间加左子区间的右起最大子区间
            rightMaxLeftBorder = leftNode.getRightMaxLeftBorder();
        }
        // 给 leftBorder 和 rightBorder 赋值
        if (leftNode.getMaxRightSum() + rightNode.getMaxLeftSum() > Math.max(leftNode.getMaxSum(), rightNode.getMaxSum())) {  // 最大子区间横跨左右两个子区间
            leftBorder = leftNode.getRightMaxLeftBorder();
            rightBorder = rightNode.getLeftMaxRightBorder();
        } else {
            if (leftNode.getMaxSum() > rightNode.getMaxSum()) {  // 最大子区间为左子区间的最大子区间
                leftBorder = leftNode.getLeftBorder();
                rightBorder = leftNode.getRightBorder();
            } else {  // 最大子区间为右子区间的最大子区间
                leftBorder = rightNode.getLeftBorder();
                rightBorder = rightNode.getRightBorder();
            }
        }
        return new TreeNode(allSum, maxLeftSum, maxRightSum, maxSum, leftBorder, rightBorder, leftMaxRightBorder, rightMaxLeftBorder);
    }
}