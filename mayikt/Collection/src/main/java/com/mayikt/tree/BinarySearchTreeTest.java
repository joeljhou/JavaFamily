package com.mayikt.tree;

import java.util.Queue;

/**
 * @author 周宇
 * @create 2022-02-19 0:46
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        //BinarySearchTree<Integer, String> binarySearchTree = new BinarySearchTree();
        //binarySearchTree.put(1, "冰墩墩");
        //binarySearchTree.put(2, "雪容融");
        //binarySearchTree.put(3, "奥运五福");
        //System.out.println("插入完毕后元素个数：" + binarySearchTree.size());
        //System.out.println("键2对应的元素是：" + binarySearchTree.get(2));
        //binarySearchTree.delete(3);
        //System.out.println("删除后的元素个数：" + binarySearchTree.size());
        //System.out.println("删除后键3对应的元素是：" + binarySearchTree.get(3));
    }

    BinarySearchTree<String, String> tree = new BinarySearchTree<>();
    void init(){
        tree.put("E", "5");
        tree.put("B", "2");
        tree.put("G", "7");
        tree.put("A", "1");
        tree.put("D", "4");
        tree.put("F", "6");
        tree.put("H", "8");
        tree.put("C", "3");
        /**
         * 树节点插入后示意图
         * ---------E---------
         * ------/------\-----
         * ----B---------G----
         * --/---\-----/---\--
         * -A-----D---F-----H-
         * -----/--------------
         * ----C---------------
         */
    }

    //前序遍历 结果：EBADCGFH
    @org.junit.jupiter.api.Test
    public void preErgodic(){
        init();
        Queue<String> keys = tree.preErgodic();
        for (String key : keys) {
            System.out.print(key);
        }
    }

    //前序遍历 结果：ABCDEFGH
    @org.junit.jupiter.api.Test
    public void midErgodic(){
        init();
        Queue<String> keys = tree.midErgodic();
        for (String key : keys) {
            System.out.print(key);
        }
    }

    //后序遍历 结果：ACDBFHGE
    @org.junit.jupiter.api.Test
    public void afterErgodic(){
        init();
        Queue<String> keys = tree.afterErgodic();
        for (String key : keys) {
            System.out.print(key);
        }
    }

    //层序遍历 结果：EBGADFHC
    @org.junit.jupiter.api.Test
    public void layerErgodic(){
        init();
        Queue<String> keys = tree.layerErgodic();
        for (String key : keys) {
            System.out.print(key);
        }
    }

    //最大深度
    @org.junit.jupiter.api.Test
    public void maxDepth(){
        init();
        System.out.println(tree.maxDepth());
    }
}
