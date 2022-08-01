package com.example.demo.segmentTree;

public class TreeNode {
    private int allSum;  // 区间中所有数字的总和
    private int maxLeftSum;  // 左起最大子数组和
    private int maxRightSum;  // 右起最大子数组和
    private int maxSum;  // 区间中最大子数组和
    private int leftMaxRightBorder;  // 最大左起子区间的右边界下标
    private int rightMaxLeftBorder;  // 最大右起子区间的左边界下标
    private int leftBorder;  // 最大子区间的左边界下标
    private int rightBorder;  // 最大子区间的右边界下标

    public TreeNode(int allSum, int maxLeftSum, int maxRightSum, int maxSum, int leftBorder, int rightBorder, int leftMaxRightBorder, int rightMaxLeftBorder) {
        this.allSum = allSum;
        this.maxLeftSum = maxLeftSum;
        this.maxRightSum = maxRightSum;
        this.maxSum = maxSum;
        this.leftMaxRightBorder = leftMaxRightBorder;
        this.rightMaxLeftBorder = rightMaxLeftBorder;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public int getAllSum() {
        return allSum;
    }

    public void setAllSum(int allSum) {
        this.allSum = allSum;
    }

    public int getMaxLeftSum() {
        return maxLeftSum;
    }

    public void setMaxLeftSum(int maxLeftSum) {
        this.maxLeftSum = maxLeftSum;
    }

    public int getMaxRightSum() {
        return maxRightSum;
    }

    public void setMaxRightSum(int maxRightSum) {
        this.maxRightSum = maxRightSum;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getLeftMaxRightBorder() {
        return leftMaxRightBorder;
    }

    public void setLeftMaxRightBorder(int leftMaxRightBorder) {
        this.leftMaxRightBorder = leftMaxRightBorder;
    }

    public int getRightMaxLeftBorder() {
        return rightMaxLeftBorder;
    }

    public void setRightMaxLeftBorder(int rightMaxLeftBorder) {
        this.rightMaxLeftBorder = rightMaxLeftBorder;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(int leftBorder) {
        this.leftBorder = leftBorder;
    }

    public int getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(int rightBorder) {
        this.rightBorder = rightBorder;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "allSum=" + allSum +
                ", maxLeftSum=" + maxLeftSum +
                ", maxRightSum=" + maxRightSum +
                ", maxSum=" + maxSum +
                ", leftMaxRightBorder=" + leftMaxRightBorder +
                ", rightMaxLeftBorder=" + rightMaxLeftBorder +
                ", leftBorder=" + leftBorder +
                ", rightBorder=" + rightBorder +
                '}';
    }
}
