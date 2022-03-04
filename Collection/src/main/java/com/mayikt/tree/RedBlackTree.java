package com.mayikt.tree;

/**
 * @author 周宇
 * @create 2022-02-19 17:26
 * 红黑树：https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    /**
     * 定义: 用标准二叉树和一些额外信息(替代3-节点)表示的2-3树
     * 红链接：将两个2-节点连接起来构成一个3-节点
     * 黑链接：则是2-3树中的普通节点
     * (1) 红链接均为左连接
     * (2) 没有任何一个节点同时和两条红链接相连
     * (3) 该树是完美的黑色平衡树，即任意空连接到根节点的路径上的黑连接数量相同
     * 红黑树旋转
     * 左旋：当某个节点的左节点为黑色，右节点为红色，此时需要左旋
     * 前提：当前节点为 h,它的右子节点为 x
     * 1.让 h的左子节点变为 h的右子节点：h.right = h.left
     * 2.让 h成为 x的左子节点：x.left = h
     * 3.让 h的color属性变为 x 的color属性：x.color = h.color
     * 4.让 h的color属性变为RED：h.color = true
     * 右旋：当某个节点的左节点是红色，且左子节点也是红色，需要右旋
     * 前提：当前节点为 h,它的左子节点为 x
     * 1.让 x的右子节点变为 h的左子节点：h.left = x.right
     * 2.让 h成为 x的右子节点：x.right = h
     * 3.让 x的color属性等于 h 的color属性：x.color = h.color
     * 4.让 h的color属性变为RED：h.color = true
     * 颜色反转：当一个节点的左右子节点都为RED时，也就是出现临时4-节点，发生颜色反转
     * 1.此时需要把左右子节点颜色变为BLACK
     * 2.同时让当前节点颜色变为RED即可
     */

    //根节点
    private Node<K, V> root;
    //红链接标识
    private static final boolean RED = true;
    //黑链接标识
    private static final boolean BLACK = false;
    //记录树中元素个数
    private int size;

    //获取树中元素的个数
    public int size() {
        return size;
    }

    /**
     * 判断当前节点的父指向链接是否为红色
     */
    private boolean isRed(Node<K, V> x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * 左旋转
     */
    private Node<K, V> rotateLeft(Node<K, V> h) {
        //前提：当前节点为 h,它的右子节点为 x
        Node<K, V> x = h.right;
        //1.让 h的左子节点变为 h的右子节点
        h.right = h.left;
        //2.让 h成为 x的左子节点
        x.left = h;
        //3.让 h的color属性变为 x 的color属性
        x.color = h.color;
        //4.让 h的color属性变为RED
        h.color = RED;
        return x;
    }

    /**
     * 右旋转
     */
    private Node<K, V> rotateRight(Node<K, V> h) {
        //前提：当前节点为 h,它的左子节点为 x
        Node<K, V> x = h.left;
        //1.让 x的右子节点变为 h的左子节点
        h.left = x.right;
        //2.让 h成为 x的右子节点
        x.right = h;
        //3.让 x的color属性等于 h 的color属性
        x.color = h.color;
        //4.让 h的color属性变为RED
        h.color = RED;
        return x;
    }

    /**
     * 颜色反转，相当于完成拆分4-节点
     */
    private void flipColors(Node<K, V> h) {
        //左子节点变成黑色
        h.left.color = BLACK;
        //右子节点变成黑色
        h.right.color = BLACK;
        //当前节点变成红色
        h.color = RED;
    }

    /**
     * 在整棵树上完成插入操作
     */
    public void put(K key, V value) {
        root = put(root, key, value);
        //根节点颜色总是黑色
        root.color = BLACK;
    }

    /**
     * 在指定树中，完成插入操作，并返回添加元素后新的树
     */
    private Node<K, V> put(Node<K, V> h, K key, V value) {
        //如果h为空，直接返回一个红色的节点
        if (h == null) {
            //数量+1
            size++;
            return new Node<>(key, value, null, null, RED);
        }
        //比较h节点的键和key的大小
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);   //向左侧添加
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);  //向右侧添加
        } else {
            h.value = value;           //发生值替换
        }
        //左旋:当前节点的左子节点为黑色，右子节点为红色
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        //右旋：当前节点的左子节点和左子节点的左子节点都为红色
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        //颜色反转：当前节点的左子节点和右子节点都为红色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    /**
     * 根据key，从树中找到对应的值
     */
    public V get(K key) {
        return get(root, key);
    }

    /**
     * 从指定树x中，找出key对应的值
     */
    private V get(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        //比较x节点键和key的大小
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    /**
     * 红黑树的节点类
     */
    static class Node<K, V> {
        private K key;       //存储键
        private V value;     //存储值
        private Node<K, V> left;   //记录左子节点
        private Node<K, V> right;  //记录右子节点
        private boolean color;     //记录父节点指向链接的颜色

        public Node(K key, V value, Node<K, V> left, Node<K, V> right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}
