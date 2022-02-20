package com.mayikt.map.jdk7;

import java.util.Hashtable;

/**
 * @author 周宇
 * @create 2022-02-21 4:19
 * ConcurrentHashMap7：
 * 将一个大集合才分成16个小的Hashtable集合(即Segment,Entry<K,V>[] table)
 */
public class ConcurrentHashMap7<K, V> {

    //默认并发级别
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;

    /**
     * 这些段，每个段都是一个专门的哈希表。
     */
    private Hashtable<K, V>[] segments;

    public ConcurrentHashMap7() {
        this.segments = new Hashtable[DEFAULT_CONCURRENCY_LEVEL];
        //注意懒加载形式
    }

    public void put(K key, V value) {
        //先计算key存放Segments数组下的索引
        int segmentsIndex = key.hashCode() & (segments.length - 1);
        Hashtable<K, V> segment = segments[segmentsIndex];
        if (segment == null) {
            segment = new Hashtable<>();
        }
        segment.put(key, value);
        segments[segmentsIndex] = segment;
    }

    public V get(K key) {
        //先计算key存放Segments数组下的索引
        int segmentsIndex = key.hashCode() & (segments.length - 1);
        Hashtable<K, V> segment = segments[segmentsIndex];
        if (segment != null) {
            return segment.get(key);
        }
        return null;
    }

    public static void main(String[] args) {
        ConcurrentHashMap7<Integer, Integer> concurrentHashMap7 = new ConcurrentHashMap7<>();
        for (int i = 0; i < 10; i++) {
            concurrentHashMap7.put(i, i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(concurrentHashMap7.get(i));
        }
    }
}
