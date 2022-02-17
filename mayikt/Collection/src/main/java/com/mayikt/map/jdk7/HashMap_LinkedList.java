package com.mayikt.map.jdk7;

import com.mayikt.map.Map_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-14 23:47
 * 第 2 版本：基于LinkedList简单实现 非常接近JDK1.7
 * 缺点：没有实现扩容
 */
public class HashMap_LinkedList<K, V> implements Map_<K, V> {
    //表(存放k-v的容器)
    //private Entry_<K,V>[] table = (Entry_<K,V>[]) EMPTY_TABLE;
    //1.使用数组太复杂了(需要考虑扩容)，我们先使用LinkedList集合存储 重要的是理解思想
    private LinkedList<Entry_>[] objects = new LinkedList[100];

    //键值个数
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        //hashCode与equals 根据key hashCode % objects.length 获取数组中存放的下标位置
        int hash = hashCode % objects.length;
        return hash;
    }

    @Override
    public V put(K key, V value) {
        //计算数组存放的下标
        int hash = hash(key);
        //查询出相同hashCode linkedList集合是否存在
        LinkedList<Entry_> linkedList = objects[hash];
        Entry_<K, V> entry = new Entry_<K,V>(key, value);  //对象

        //hashCode不同 直接添加
        if (linkedList == null) {
            linkedList = new LinkedList();
            linkedList.add(entry);
            objects[hash] = linkedList;
            size++;
            return value;
        }
        //hashCode相同 值也相同 修改
        for (Entry_ ey : linkedList) {
            if (ey.getKey().equals(key)) {
                ey.setValue(value);  //覆盖
                return value;
            }
        }
        //hashCode相同，值不同 添加到链表后
        linkedList.add(entry);
        objects[hash] = linkedList;
        size++;
        return value;
    }

    @Override
    public V get(Object key) {
        int hash = hash((K) key);
        LinkedList<Entry_> linkedList = objects[hash];
        //没有数据
        if (linkedList == null) {
            return null;
        }
        //有数据
        for (Entry_ entry : linkedList) {
            if (entry.getKey().equals(key)) {
                return (V) entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int hash = hash((K) key);
        LinkedList<Entry_> linkedList = objects[hash];
        //没有数据
        if (linkedList == null) {
            return null;
        }
        //有数据
        for (Entry_ entry : linkedList) {
            if (entry.getKey().equals(key)) {
                linkedList.remove(entry);     //移除这条数据
                if (linkedList.size()==0){
                    objects[hash] = null;
                }
                size--;
                return (V) entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        //循环清空
        for (LinkedList<Entry_> object : objects) {
            object = null;
        }
    }

    static class Entry_<K, V> implements Map_.Entry_<K, V> {
        private K key;
        private V value;

        //使用构造函数赋值
        public Entry_(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

}
