package com.example.demo;

import com.example.demo.segmentTree.SegmentTree;
import org.junit.jupiter.api.Test;

public class SegmentTreeTest {
    @Test
    public void testSegmentTree() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);
        System.out.println(segmentTree.queryInterval(2, 4));
        segmentTree.setNode(3, 5);
        System.out.println(segmentTree.queryInterval(2, 4));
    }
}
