package com.mayikt.map.jdk7;

import com.mayikt.map.Map_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周宇
 * @create 2022-02-14 23:47
 * 第 1 版本：基于ArrayList简单实现
 * 目的：是理解Entry
 * 缺点：没有实现扩容 put方法无法实现覆盖，get方法遍历耗时，查询效率低
 */
public class HashMap_ArrayList<K, V> implements Map_<K, V> {
    //表(存放k-v的容器)
    //private Entry_<K,V>[] table = (Entry_<K,V>[]) EMPTY_TABLE;
    //1.使用数组太复杂了(需要考虑扩容)，我们先使用ArrayList集合存储 重要的是理解思想
    private List<Entry_<K,V>> listEntry = new ArrayList<Entry_<K,V>>();

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

    @Override
    public V put(K key, V value) {
        //1.封装一个Entry
        Entry_<K,V> entry = new Entry_<K,V>(key,value);
        //2.加入Entry容器
        listEntry.add(entry);
        size++;
        return value;
    }

    @Override
    public V get(Object key) {
        //遍历匹配Entry容器中的key 效率低
        for (Entry_<K, V> entry : listEntry) {
            if (entry.key.equals(key)){
                return (V) entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        V value = get(key);
        Entry_<K,V> entry = new Entry_<K,V>((K) key,value);
        listEntry.remove(entry);
        size--;
        return value;
    }

    @Override
    public void clear() {
        listEntry.clear();
        size=0;
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
