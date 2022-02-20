package com.mayikt.tree;

import org.junit.jupiter.api.Test;

/**
 * @author 周宇
 * @create 2022-02-20 19:02
 */
public class RedBlackTreeTest {

    @Test
    public void redblacktree() {
        RedBlackTree<String, String> tree = new RedBlackTree<>();

        tree.put("1", "张三");
        tree.put("2", "李四");
        tree.put("3", "王五");
        tree.put("2", "李四1");

        System.out.println("元素个数：" + tree.size());

        System.out.println(tree.get("1"));
        System.out.println(tree.get("2"));
        System.out.println(tree.get("3"));
    }

}
