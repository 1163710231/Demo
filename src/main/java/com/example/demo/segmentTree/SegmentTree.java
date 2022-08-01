package com.example.demo.segmentTree;

public class SegmentTree<E> {
    private final E[] dataArray;  // 用于存储底层数据的数组
    private final E[] treeArray;  // 用于存储线段树的数组
    private final MergeTool<E> mergeTool;    // 用于融合线段树的工具

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] dataArray, MergeTool<E> mergeTool) {
        this.mergeTool = mergeTool;

        // 初始化 dataArray
        this.dataArray = (E[]) new Object[dataArray.length];
        System.arraycopy(dataArray, 0, this.dataArray, 0, dataArray.length);

        // 初始化 treeArray
        this.treeArray = (E[]) new Object[dataArray.length * 4];
        buildSegmentTree(0, 0, dataArray.length - 1);
    }

    @SuppressWarnings("unchecked")
    public SegmentTree(E[] dataArray, TreeNodeMergeTool treeNodeMergeTool) {
        this.mergeTool = (MergeTool<E>) treeNodeMergeTool;

        // 初始化 dataArray
        this.dataArray = (E[]) new Object[dataArray.length];
        System.arraycopy(dataArray, 0, this.dataArray, 0, dataArray.length);

        // 初始化 treeArray
        this.treeArray = (E[]) new Object[dataArray.length * 4];
        buildSegmentTree(0, 0, dataArray.length - 1);
    }

    /**
     * 递归地在 rootIndex 的位置构建表示区间 [left - right] 的线段树
     *
     * @param rootIndex 该树的根节点下标
     * @param left      该树在 dataArray 中最左节点的下标
     * @param right     该树在 dataArray 中最右节点的下标
     */
    private void buildSegmentTree(int rootIndex, int left, int right) {
        if (left == right) {  // 递归终止
            // 当线段树在 dataArray 中的范围为 1 时，说明该节点为叶节点，其值为 dataArray 中对应的值
            treeArray[rootIndex] = dataArray[left];
        } else {
            int leftRootIndex = getLeftChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int rightRootIndex = getRightChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int mid = (left + right) >> 1;

            buildSegmentTree(leftRootIndex, left, mid);  // 递归地构建左子树
            buildSegmentTree(rightRootIndex, mid + 1, right);  // 递归地构建右子树

            // tree[rootIndex] 的值为当前两个孩子的值融合得到的结果
            treeArray[rootIndex] = mergeTool.merge(treeArray[leftRootIndex], treeArray[rightRootIndex]);
        }
    }

    /**
     * 根据一个节点的下标获取该节点的左孩子的下标
     *
     * @param parentRootIndex 该节点的下标
     * @return 该节点的左孩子的下标
     */
    private int getLeftChildRootIndex(int parentRootIndex) {
        return 2 * parentRootIndex + 1;
    }

    /**
     * 根据一个节点的下标获取该节点的右孩子的下标
     *
     * @param parentRootIndex 该节点的下标
     * @return 该节点的右孩子的下标
     */
    private int getRightChildRootIndex(int parentRootIndex) {
        return 2 * parentRootIndex + 2;
    }

    /**
     * 查询一个指定区间中的信息（面向用户）
     *
     * @param left  要查询的区间的左边界
     * @param right 要查询的区间的右边界
     * @return 要查询的区间中的信息
     */
    public E queryInterval(int left, int right) {
        if (left < 0 || left >= dataArray.length || right < 0 || right >= dataArray.length || left > right) {
            try {
                throw new Exception("区间错误!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queryIntervalInTree(0, 0, dataArray.length - 1, left, right);
    }

    /**
     * 查询一个指定区间中的信息
     *
     * @param rootIndex 该树的根节点在 treeArray 中的下标
     * @param treeLeft  该树在 treeArray 中最左节点的下标
     * @param treeRight 该树在 treeArray 中最右节点的下标
     * @param left      要查询的区间的左边界
     * @param right     要查询的区间的右边界
     * @return 要查询的区间中的信息
     */
    private E queryIntervalInTree(int rootIndex, int treeLeft, int treeRight, int left, int right) {
        if (treeLeft == left && treeRight == right) {  // 递归终止
            return treeArray[rootIndex];  // 要查询的范围与该树的范围完全重合，则该树的根节点为叶节点，其值即为要查找的值
        } else {
            int leftRootIndex = getLeftChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int rightRootIndex = getRightChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int treeMid = (treeLeft + treeRight) >> 1;

            if (right <= treeMid) {  // 要查询的区间完全在左子树中
                return queryIntervalInTree(leftRootIndex, treeLeft, treeMid, left, right);
            } else if (left >= treeMid + 1) {  // 要查询的区间完全在右子树中
                return queryIntervalInTree(rightRootIndex, treeMid + 1, treeRight, left, right);
            } else {  // 要查询的区间在左右子树中都有
                E leftQueryResult = queryIntervalInTree(leftRootIndex, treeLeft, treeMid, left, treeMid);
                E rightQueryResult = queryIntervalInTree(rightRootIndex, treeMid + 1, treeRight, treeMid + 1, right);
                return mergeTool.merge(leftQueryResult, rightQueryResult);  // 融合左右子区间的查询结果并返回
            }
        }
    }

    /**
     * 修改一个指定节点的值（面向用户）
     *
     * @param nodeIndex 要修改的节点在 dataArray 中的下标
     * @param newNode   要修改的节点的新值
     */
    public void setNode(int nodeIndex, E newNode) {
        if (nodeIndex < 0 || nodeIndex >= dataArray.length) {
            try {
                throw new Exception("目标下标越界!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dataArray[nodeIndex] = newNode;
        setNodeInTree(0, 0, dataArray.length - 1, nodeIndex, newNode);
    }

    /**
     * 修改一个指定节点的值
     *
     * @param rootIndex 该树的根节点在 treeArray 中的下标
     * @param treeLeft  该树在 treeArray 中最左节点的下标
     * @param treeRight 该树在 treeArray 中最右节点的下标
     * @param nodeIndex 要修改的节点在 dataArray 中的下标
     * @param newNode   要修改的节点的新值
     */
    private void setNodeInTree(int rootIndex, int treeLeft, int treeRight, int nodeIndex, E newNode) {
        if (treeLeft == treeRight) {  // 递归终止
            treeArray[rootIndex] = newNode;  // 要修改的范围与该树的范围完全重合，则该树的根节点为叶节点，其值即为要修改的值
        } else {
            int leftRootIndex = getLeftChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int rightRootIndex = getRightChildRootIndex(rootIndex);  // 获取左子树的根节点的下标
            int treeMid = (treeLeft + treeRight) >> 1;

            if (nodeIndex <= treeMid) {  // 要修改的节点在左子树中
                setNodeInTree(leftRootIndex, treeLeft, treeMid, nodeIndex, newNode);
            } else {  // 要修改的节点在右子树中
                setNodeInTree(rightRootIndex, treeMid + 1, treeRight, nodeIndex, newNode);
            }
            // 由于该节点的孩子节点被修改了，所以该节点的值也要重新计算
            treeArray[rootIndex] = mergeTool.merge(treeArray[leftRootIndex], treeArray[rightRootIndex]);
        }
    }

    /**
     * 重写的 toString 函数
     *
     * @return treeArray 的字符串表达
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[\n");
        for (int i = 0; i < treeArray.length; i++) {
            if (treeArray[i] != null) stringBuilder.append(treeArray[i]);
            else stringBuilder.append("null");

            if (i != treeArray.length - 1) stringBuilder.append(", \n");
        }
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
