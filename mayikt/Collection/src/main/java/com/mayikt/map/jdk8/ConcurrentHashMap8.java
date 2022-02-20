package com.mayikt.map.jdk8;

/**
 * @author 周宇
 * @create 2022-02-21 4:44
 */
public class ConcurrentHashMap8<K, V> {
    //默认初始容量16
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    //默认加载因子0.75f
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    //默认并发级别
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    //最大初始容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    //最小分段表容量 必须是2的幂
    static final int MIN_SEGMENT_TABLE_CAPACITY = 2;
    //允许的最大段数
    static final int MAX_SEGMENTS = 1 << 16;
    //锁定前重试
    static final int RETRIES_BEFORE_LOCK = 2;
}
