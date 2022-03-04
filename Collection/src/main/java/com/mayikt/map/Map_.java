package com.mayikt.map;

/**
 * @author 周宇
 * @create 2022-02-15 22:25
 */
public interface Map_<K, V> {
    /**
     * Map集合映射数
     */
    int size();

    /**
     * 判断是否为空
     */
    boolean isEmpty();

    /**
     * 添加k-v映射关系
     */
    V put(K key, V value);

    /**
     * 根据key中查询value
     */
    V get(Object key);

    /**
     * 根据key删除映射
     */
    V remove(Object key);

    /**
     * 清除所有映射
     */
    void clear();

    /**
     * HashMap中的存储key,value的对象
     */
    interface Entry_<K, V> {
        /**
         * 获取key
         */
        K getKey();

        /**
         * 获取value
         */
        V getValue();

        /**
         * 设置value
         */
        V setValue(V value);
    }

}
