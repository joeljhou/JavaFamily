package com.mayikt.map.jdk7;

import com.mayikt.map.Map_;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author 周宇
 * @create 2022-02-14 23:47
 * HashMap7 数组+链表
 * 存在问题1：并发环境下get 循环死锁
 * 存在问题2：黑客可以精心设计恶意引起Dos，链表性能推荐
 */
public class HashMap7<K, V> implements Map_<K, V> {
    /**
     * 默认初始容量16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 最大初始容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 加载因子0.75f
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 空表实例
     */
    static final Entry_<?, ?>[] EMPTY_TABLE = {};
    /**
     * 表容器(存放k-v的容器) 数组+链表
     */
    private Entry_<K, V>[] table = (Entry_<K, V>[]) EMPTY_TABLE;
    /**
     * 映射的数量
     */
    private int size;
    /**
     * 扩容阈值 要调整大小的下一个大小值（初始容量 * 加载因子0.75）
     */
    private int threshold;
    /**
     * 加载因子
     */
    private float loadFactor;

    /**
     * 保存在 VM 启动之前无法初始化的值。
     */
    private static class Holder {
        /**
         * 切换到使用替代散列的表容量
         */
        static final int ALTERNATIVE_HASHING_THRESHOLD;

        static {
            String altThreshold = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction("jdk.map.althashing.threshold"));

            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : Integer.MAX_VALUE;

                // disable alternative hashing if -1
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }
                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch (IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
            }
            ALTERNATIVE_HASHING_THRESHOLD = threshold;
        }
    }

    /**
     * 哈希种子 避免潜在的hash攻击
     */
    private int hashSeed = 0;

    /**
     * 构造一个具有指定初始容量和负载因子的空
     *
     * @param initialCapacity 初始容量
     * @param loadFactor      加载因子
     */
    public HashMap7(int initialCapacity, float loadFactor) {
        //非法初始容量异常
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        //保证最大初始容量为 1<<30
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        //非法负载率
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        //设置加载因子 和 实际键值映射的数量
        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();
    }

    public HashMap7(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    //构造一个具有默认初始容量 (16) 和默认加载因子 (0.75) 的空 HashMap
    public HashMap7() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    //向上取整到2的幂
    private static int roundUpToPowerOf2(int number) {
        return number >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    //默认的数组初始化
    private void inflateTable(int toSize) {
        //初始容量16  向上取整到2的幂
        int capacity = roundUpToPowerOf2(toSize);
        //计算初始容量 16 * 0.75 = 12
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);
        //第一次初始化
        table = new Entry_[capacity];
        //根据需要初始化哈希种子
        initHashSeedAsNeeded(capacity);
    }

    //子类的初始化挂钩
    void init() {
    }

    /**
     * 根据需要初始化哈希种子 绝大多数情况下返回false
     */
    final boolean initHashSeedAsNeeded(int capacity) {
        //当前的替代哈希
        boolean currentAltHashing = hashSeed != 0;
        boolean useAltHashing = sun.misc.VM.isBooted() &&
                (capacity >= HashMap7.Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean switching = currentAltHashing ^ useAltHashing;
        //if (switching) {
        //    //随机哈希种子生成
        //    hashSeed = useAltHashing
        //            ? sun.misc.Hashing.randomHashSeed(this)
        //            : 0;
        //}
        return switching;
    }

    /**
     * 检索对象散列码并将补充散列函数应用于结果散列，以防止质量差的散列函数
     * 这很关键，因为 HashMap 使用长度为二的幂的哈希表，否则会遇到低位没有差异的 hashCode 的冲突
     * 注意：空键总是映射到哈希 0，因此索引为 0
     */
    final int hash(Object k) {
        int h = 0;
        //为string类型的key，单独提供 murmur-hash 算法，但是在多核CPU上性能不太好，JDK1.8就废弃了
        //if (0 != h && k instanceof String) {
        //    return sun.misc.Hashing.stringHash32((String) k);
        //}
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 返回哈希码 hash 的索引
     */
    static int indexFor(int hash, int length) {
        //length(2的幂)-1的值 转化为二进制每一位都是1
        //保证按&运算 快速拿到下标 且 分布均匀
        return hash & (length - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回与 HashMap 中的指定键关联的条目。
     * 如果 HashMap 不包含键的映射，则返回 null。
     */
    final Entry_<K, V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        for (Entry_<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);           //容器初始化
        }
        if (key == null) {
            return putForNullKey(value);       //key为空键的数据单独处理
        }
        int hash = hash(key);                  //计算hash值
        int i = indexFor(hash, table.length);  //根据hash值计算桶索引下标
        for (Entry_<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            //key相同 替换数据
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        addEntry(hash, key, value, i);         //添加数据
        return null;
    }

    //存储放空键数据
    private V putForNullKey(V value) {
        for (Entry_<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        addEntry(0, null, value, 0);
        return null;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            if (size == 0) {
                return null;
            }
            for (Entry_<K, V> e = table[0]; e != null; e = e.next) {
                if (e.key == null)
                    return e.value;
            }
            return null;
        }
        Entry_<K, V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    //数组扩容 调整大小
    void resize(int newCapacity) {
        Entry_[] oldTable = table;
        int oldCapacity = oldTable.length;
        //保证最大容量为小于扩等于 1<<30
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry_[] newTable = new Entry_[newCapacity];
        //转移数据到newTable
        transfer(newTable, initHashSeedAsNeeded(newCapacity));
        //改变引用为新表
        table = newTable;
        threshold = (int) Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    }

    /**
     * 将所有条目从当前表转移到新表
     */
    void transfer(Entry_[] newTable, boolean rehash) {
        int newCapacity = newTable.length;              //新表容器大小
        //遍历之前表容器 桶
        for (Entry_<K, V> e : table) {
            //遍历桶下的链表
            while (null != e) {
                Entry_<K, V> next = e.next;             //保存下一个节点
                if (rehash) {                           //是否重新散列 计算hash
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);  //重新计算桶下标
                /**
                 * 情况1：没有发生hash碰撞 当前newTable[i]无值
                 *      e 表示当前节点 的 next 下一个节点指向为 null，桶的首节点设置为e
                 * 情况2，发生hash碰撞情况 当前newTable[i]有值 头插法形成链
                 *      e 表示当前节点 的 next 指向首节点newTable[i]，桶的首节点设置为e
                 */
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    @Override
    public V remove(Object key) {
        Entry_<K, V> e = removeEntryForKey(key);
        return (e == null ? null : e.value);
    }

    /**
     * 移除并返回与 HashMap 中指定键关联的条目
     * 不存在返回null
     */
    final Entry_<K, V> removeEntryForKey(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        //下一个节点 第一次保存首节点
        Entry_<K, V> prev = table[i];
        //当前节点 第一次保存首节点
        Entry_<K, V> e = prev;
        while (e != null) {
            //下一个节点
            Entry_<K, V> next = e.next;
            Object k;
            //当前节点key相等 移除操作
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (prev == e) {
                    //首节点key相同的情况 首节点设置为next
                    table[i] = next;
                } else {
                    //存在hash冲突的情况
                    //上一个节点的next指向下一个节点 跳过当前节点
                    prev.next = next;
                }
                e.recordRemoval(this);
                return e;
            }
            prev = e;
            e = next;
        }
        return e;
    }

    @Override
    public void clear() {
        //将指定的 Object 引用分配给指定的 Objects 数组的每个元素。
        Arrays.fill(table, null);
        size = 0;
    }

    static class Entry_<K, V> implements Map_.Entry_<K, V> {
        private K key;
        private V value;
        Entry_<K, V> next;
        int hash;

        //使用构造函数赋值
        Entry_(int h, K k, V v, Entry_<K, V> n) {
            hash = h;
            key = k;
            value = v;
            next = n;
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

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }

        //记录访问  调用put(k,v)覆盖时，都会调用此方法
        void recordAccess(HashMap7<K, V> m) {
        }

        //记录删除 每当从表中删除条目时都会调用此方法
        void recordRemoval(HashMap7<K, V> m) {
        }

    }

    void addEntry(int hash, K key, V value, int bucketIndex) {
        //扩容resize
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);   //扩容2倍
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        //创建Entry
        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, K key, V value, int bucketIndex) {
        //获取原来的Entry对象
        Entry_<K, V> next = table[bucketIndex];
        //e为空没有hash冲突  e有值说明发生hash冲突 使用头插法
        table[bucketIndex] = new Entry_<>(hash, key, value, next);
        size++;
    }

}
