package com.example.demo.segmentTree;

public interface MergeTool<E> {
    // 通过一个merge操作把 a 和 b 两个元素合并成一个元素，然后将其返回
    E merge(E a, E b);
}
