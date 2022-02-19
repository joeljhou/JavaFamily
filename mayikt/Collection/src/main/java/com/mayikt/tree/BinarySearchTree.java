package com.mayikt.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author 周宇
 * @create 2022-02-18 17:43
 * 二叉搜索树：https://www.cs.usfca.edu/~galles/visualization/BST.html
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    //根节点
    private Node<K, V> root;
    //记录树中的元素个数
    private int size;

    //获取树中元素的个数
    public int size() {
        return size;
    }

    //向树中插入一个键值对
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    //给指定树x上，添加一个键值对，并返回添加后的新树
    public Node<K, V> put(Node<K, V> x, K key, V value) {
        //添加节点 节点数+1
        if (x == null) {
            size++;
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);   //key小于x节点 继续找x节点的左子树
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);  //key大于x节点 继续找x节点的右子树
        } else {
            x.value = value;                  //key等于x节点 替换x节点的值为value
        }
        return x;
    }

    //根据key ,从树中找出对应的值
    public V get(K key) {
        V value = get(root, key);
        return value;
    }

    //从指定的树x中,找出key对应的值
    private V get(Node<K, V> x, K key) {
        //x数为null
        if (x == null) {
            return null;
        }
        //找到了 返回value
        int cmp = key.compareTo((K) x.key);
        if (cmp == 0) {
            V value = (V) x.value;
            return value;
        }
        return cmp < 0 ? get(x.left, key) : get(x.right, key);
    }

    //根据key ,删除树中对应的键值对
    public void delete(K key) {
        delete(root, key);
    }

    //删除指定树x上的键为key的键值对，并返回删除后的新树
    private Node<K, V> delete(Node<K, V> x, K key) {
        //x数为null
        if (x == null) {
            return null;
        }
        //x树不为null
        int cmp = key.compareTo((K) x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            //让元素个数-1
            size--;
            //执行删除
            if (x.right == null) {
                return x.left;
            }
            //找到右子树中最小的节点
            if (x.left == null) {
                return x.right;
            }
            //左右子树都不为null,找到右子树的最小节点
            Node<K, V> minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            //删除右子树中最小节点
            Node<K, V> n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;   //移除右数的最小子节点
                }
            }
            //让x节点的左子树成为minNode的左子树
            minNode.left = x.left;
            //让x节点的右子树成为minNode的右子树
            minNode.right = x.right;
            //让x节点的父节点指向minNode
            x = minNode;
        }
        return x;
    }

    //找出整棵树中最小的键
    public K min() {
        return min(root).key;
    }

    //找出指定数x中最小建所在的节点
    public Node<K, V> min(Node<K, V> x) {
        if (x.left != null) {
            return min(x.left);
        }
        return x;
    }

    //找出整棵树中最大的键
    public K max() {
        return max(root).key;
    }

    //找出指定数x中最大建所在的节点
    private Node<K, V> max(Node<K, V> x) {
        if (x.right != null) {
            return min(x.right);
        }
        return x;
    }

    /**
     * 前序遍历：先访问 根节点，再访问 左子树，最后访问 右子树
     * 中序遍历：先访问 左子树，再访问 根节点，最后访问 右子树
     * 后序遍历：先访问 左子树，再访问 右子树，最后访问 根节点
     */
    //使用前序遍历，获取整个树中的所有键
    public Queue<K> preErgodic() {
        Queue<K> keys = new LinkedBlockingQueue<>();
        preErgodic(root, keys);
        return keys;
    }

    //获取指定数x的所有键，并放到keys队列中
    private void preErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        //把x结点的key放入keys中
        keys.offer(x.key);
        //递归遍历x节点左子树
        if (x.left != null) {
            preErgodic(x.left, keys);
        }
        //递归遍历x结点右子树
        if (x.right != null) {
            preErgodic(x.right, keys);
        }
    }

    //使用中序遍历，获取整个树中的所有键
    public Queue<K> midErgodic() {
        Queue<K> keys = new LinkedBlockingQueue<>();
        midErgodic(root, keys);
        return keys;
    }

    //使用中序遍历，把指定数x的所有键放入到keys队列中
    private void midErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        //先递归，把左子树中的键放到keys中
        if (x.left != null) {
            midErgodic(x.left, keys);
        }
        //把当前节点x的键放入keys
        keys.offer(x.key);
        //再递归，把右子树中的键放到keys中
        if (x.right != null) {
            midErgodic(x.right, keys);
        }
    }

    //使用后续遍历，获取整个树中的所有键
    public Queue<K> afterErgodic() {
        Queue<K> keys = new LinkedBlockingQueue<>();
        afterErgodic(root, keys);
        return keys;
    }

    //使用后续遍历，把指定数x的所有键放入到keys队列中
    private void afterErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        //先递归，把左子树中的键放到keys中
        if (x.left != null) {
            afterErgodic(x.left, keys);
        }
        //再递归，把右子树中的键放到keys中
        if (x.right != null) {
            afterErgodic(x.right, keys);
        }
        //把当前节点x的键放入keys
        keys.offer(x.key);
    }

    //层序遍历
    public Queue<K> layerErgodic() {
        //定义两个队列，分别存放树中的键和树中的节点
        Queue<K> keys = new LinkedBlockingQueue<>();
        Queue<Node<K, V>> nodes = new LinkedBlockingQueue<>();

        //默认，往队列中放入根机电
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            //从队列中弹出一个节点，把key放入到keys中
            Node<K, V> n = nodes.poll();
            keys.offer(n.key);
            //判断当前节点还有没有左子节点，如果有 则放入node中
            if (n.left != null) {
                nodes.offer(n.left);
            }
            //判断当前节点还有没有右子节点，如果有 则放入node中
            if (n.right != null) {
                nodes.offer(n.right);
            }
        }
        return keys;
    }

    //计算整棵树的最大深度
    public int maxDepth() {
        return maxDepth(root);
    }

    //计算指定树x的最大深度
    private int maxDepth(Node<K, V> x) {
        //如果根节点为空，则最大深度为0
        if (x == null) {
            return 0;
        }
        int maxL = 0;
        int maxR = 0;

        //计算左子树的最大深度
        if (x.left != null) {
            maxL = maxDepth(x.left);
        }
        //计算右子树的最大深度
        if (x.right != null) {
            maxR = maxDepth(x.right);
        }
        //当前数的最大深度=左子树的最大深度或右子树的最大深度中的较大者+1
        return maxL > maxR ? maxL + 1 : maxR + 1;
    }

    /**
     * 二叉树的节点类
     */
    static class Node<K, V> {
        private K key;       //存储键
        private V value;     //存储值
        private Node<K, V> left;   //记录左子节点
        private Node<K, V> right;  //记录右子节点

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
