package com.mayikt.map.jdk8;

import com.mayikt.map.Map_;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author 周宇
 * @create 2022-02-15 18:34
 * 数据结构：数组+链表+红黑树
 * Java 7/8 HashMap源码详解与面试题分析：https://www.bilibili.com/video/BV18E411C7kD
 */
public class HashMap8<K, V> implements Map_<K, V> {
    /**
     * 默认初始容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    /**
     * 最大容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 加载因子0.75f
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 树形阈值
     */
    static final int TREEIFY_THRESHOLD = 8;
    /**
     * 取消阈值
     */
    static final int UNTREEIFY_THRESHOLD = 6;
    /**
     * 最小树形容量
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /* ---------------- 静态实用程序 -------------- */

    /**
     * 计算key的hash值
     * 如果key为空时，存在第一个链表
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                            ((p = (ParameterizedType) t).getRawType() ==
                                    Comparable.class) &&
                            (as = p.getActualTypeArguments()) != null &&
                            as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable) k).compareTo(x));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /* ---------------- Fields -------------- */
    /**
     * 表容器(存放k-v的容器) 数组+链表+红黑树
     */
    private HashMap8.Node_<K, V>[] table;
    /**
     * 保存缓存的 entrySet()
     */
    private Set<Map.Entry<K, V>> entrySet;
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

    /* ---------------- Public 方法 -------------- */

    /**
     * 构造一个具有指定初始容量和负载因子的空
     *
     * @param initialCapacity 初始容量
     * @param loadFactor      加载因子
     */
    public HashMap8(int initialCapacity, float loadFactor) {
        //非法初始容量异常
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
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
        this.threshold = tableSizeFor(initialCapacity);
    }

    public HashMap8(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    //构造一个具有默认初始容量 (16) 和默认加载因子 (0.75) 的空 HashMap
    public HashMap8() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * 实现 Map.put 和相关方法。
     *
     * @param hash         键的哈希
     * @param key          键
     * @param value        值
     * @param onlyIfAbsent 如果为真，则不要更改现有值
     * @param evict        如果为 false，则表处于创建模式。
     * @return 前一个值，如果没有，则为 null
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        HashMap8.Node_<K, V>[] tab;    //tab    表容器
        HashMap8.Node_<K, V> p;        //p      当前key存放数组下的链表的首个节点
        int length;                    //length 表容器的数组长度
        int index;                     //index  当前key存放链表的数组索引
        //第一次初始化，进行数组扩容
        if ((tab = table) == null || (length = tab.length) == 0) {
            length = (tab = resize()).length;
        }
        //没有发生hash冲突 添加到数组下链表的第一个元素
        if ((p = tab[index = (length - 1) & hash]) == null) {  //计算索引 (length - 1) & hash
            tab[index] = newNode(hash, key, value, null);
        } else {   //可能发生了hash冲突
            HashMap8.Node_<K, V> e = null;   //e    当前节点e
            K k;                             //k    当前节点key
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) {
                //第一个节点相同的key 替换元素↓
                e = p;
            } else if (p instanceof HashMap8.TreeNode_) {
                //红黑树中添加元素  e=null ???
                e = ((HashMap8.TreeNode_<K, V>) p).putTreeVal(this, tab, hash, key, value);
            } else {
                //binCount 标记链表的第几个元素，从0开始表示第一个元素
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        //链表中添加元素 尾插法
                        p.next = newNode(hash, key, value, null);
                        //链表转红黑树/扩容
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    //链表中出现key内容相同的节点 需要替换元素↓
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    p = e;
                }
            }
            //替换元素情况↑
            if (e != null) {
                V oldValue = e.value;
                //更改现有值 或者 旧值为空
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                return oldValue;
            }
        }
        if (++size > threshold) {
            resize();
        }
        return null;
    }

    //数组扩容 调整大小
    final HashMap8.Node_<K, V>[] resize() {
        HashMap8.Node_<K, V>[] oldTab = table;                   //旧表容器
        int oldCap = (oldTab == null) ? 0 : oldTab.length;       //旧表容器大小
        int oldThr = threshold;                                  //旧表容器阈值
        //新的表容器大小  新的扩容阈值
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;                   //保证阈值小于扩等于 1<<30
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                //oldCap >= DEFAULT_INITIAL_CAPACITY   表示非第一次扩容
                //newCap = oldCap << 1                 表容量扩大到2倍
                newThr = oldThr << 1;                //表阈值也扩大到2倍
            }
        } else if (oldThr > 0) {
            //初始容量被置于阈值
            newCap = oldThr;
        } else {
            //零初始阈值表示使用默认值
            newCap = DEFAULT_INITIAL_CAPACITY;   //默认初始容量16
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);  //默认扩容阈值 0.75f*16=12
        }
        //如果新阈值为0的情况下
        if (newThr == 0) {
            //ft = 新的容量 * 0.75f
            float ft = (float) newCap * loadFactor;
            //新的阈值 = ft
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        //创建一个新容器  复制替换数据
        threshold = newThr;
        HashMap8.Node_<K, V>[] newTab = (HashMap8.Node_<K, V>[]) new HashMap8.Node_[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                HashMap8.Node_<K, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof HashMap8.TreeNode_)
                        ((HashMap8.TreeNode_<K, V>) e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        HashMap8.Node_<K, V> loHead = null, loTail = null;
                        HashMap8.Node_<K, V> hiHead = null, hiTail = null;
                        HashMap8.Node_<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            } else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    //链表转红黑树 表太小时扩容
    final void treeifyBin(HashMap8.Node_<K, V>[] tab, int hash) {
        int n, index;
        HashMap8.Node_<K, V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) {
            //数组长度小于64时扩容
            resize();
        } else if ((e = tab[index = (n - 1) & hash]) != null) {
            HashMap8.TreeNode_<K, V> hd = null, tl = null;
            do {
                HashMap8.TreeNode_<K, V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }

    @Override
    public V get(Object key) {
        HashMap8.Node_<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    /**
     * 实现 Map.get 和相关方法。
     *
     * @param hash 键的哈希
     * @param key  键
     * @return 节点，如果没有则为 null
     */
    final HashMap8.Node_<K, V> getNode(int hash, Object key) {
        HashMap8.Node_<K, V>[] tab;
        HashMap8.Node_<K, V> first, e;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                    ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof HashMap8.TreeNode_)
                    return ((HashMap8.TreeNode_<K, V>) first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        HashMap8.Node_<K, V> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    /**
     * 实现 Map.remove 和相关方法。
     *
     * @param hash       键的哈希
     * @param key        键
     * @param value      如果匹配值，则要匹配的值，否则忽略
     * @param matchValue 如果为真，则仅在值相等时删除
     * @param movable    如果为假，则在删除时不要移动其他节点
     * @return 节点，如果没有则为 null
     */
    final HashMap8.Node_<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
        return null;
    }

    @Override
    public void clear() {
        HashMap8.Node_<K, V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i) {
                tab[i] = null;
            }
        }
    }


    /* ---------------- 一条单向链表 -------------- */
    static class Node_<K, V> implements Map_.Entry_<K, V> {
        final int hash;
        final K key;
        V value;
        HashMap8.Node_<K, V> next;

        Node_(int hash, K key, V value, HashMap8.Node_<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    /* ---------------- 一颗红黑树 -------------- */
    static class Entry_<K, V> extends HashMap8.Node_<K, V> {
        Entry_<K, V> before, after;

        Entry_(int hash, K key, V value, HashMap8.Node_<K, V> next) {
            super(hash, key, value, next);
        }
    }

    //TreeNode_--继承自-->Entry_--继承自-->Node_
    static final class TreeNode_<K, V> extends Entry_<K, V> {
        HashMap8.TreeNode_<K, V> parent;  //红黑树链接
        HashMap8.TreeNode_<K, V> left;
        HashMap8.TreeNode_<K, V> right;
        HashMap8.TreeNode_<K, V> prev;    //需要在删除时取消链接
        boolean red;

        TreeNode_(int hash, K key, V val, HashMap8.Node_<K, V> next) {
            super(hash, key, val, next);
        }

        /**
         * 返回包含此节点的树的根
         */
        final HashMap8.TreeNode_<K, V> root() {
            for (HashMap8.TreeNode_<K, V> r = this, p; ; ) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }

        /**
         * 确保给定的根是其 bin 的第一个节点。
         */
        static <K, V> void moveRootToFront(HashMap8.Node_<K, V>[] tab, HashMap8.TreeNode_<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                HashMap8.TreeNode_<K, V> first = (HashMap8.TreeNode_<K, V>) tab[index];
                if (root != first) {
                    HashMap8.Node_<K, V> rn;
                    tab[index] = root;
                    HashMap8.TreeNode_<K, V> rp = root.prev;
                    if ((rn = root.next) != null)
                        ((HashMap8.TreeNode_<K, V>) rn).prev = rp;
                    if (rp != null)
                        rp.next = rn;
                    if (first != null)
                        first.prev = root;
                    root.next = first;
                    root.prev = null;
                }
                assert checkInvariants(root);
            }
        }

        /**
         * 当 hashCodes 相等且不可比较时，用于排序插入的打破平局实用程序。我们不需要总订单，只需要一致的插入规则来保持重新平衡之间的等价性。超出必要的平局进一步简化了测试。
         */
        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a == null || b == null ||
                    (d = a.getClass().getName().
                            compareTo(b.getClass().getName())) == 0)
                d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                        -1 : 1);
            return d;
        }

        /**
         * 从此节点链接的节点的表单树。
         */
        final void treeify(HashMap8.Node_<K, V>[] tab) {
            HashMap8.TreeNode_<K, V> root = null;
            for (HashMap8.TreeNode_<K, V> x = this, next; x != null; x = next) {
                next = (HashMap8.TreeNode_<K, V>) x.next;
                x.left = x.right = null;
                if (root == null) {
                    x.parent = null;
                    x.red = false;
                    root = x;
                } else {
                    K k = x.key;
                    int h = x.hash;
                    Class<?> kc = null;
                    for (HashMap8.TreeNode_<K, V> p = root; ; ) {
                        int dir, ph;
                        K pk = p.key;
                        if ((ph = p.hash) > h)
                            dir = -1;
                        else if (ph < h)
                            dir = 1;
                        else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                                (dir = compareComparables(kc, k, pk)) == 0)
                            dir = tieBreakOrder(k, pk);

                        HashMap8.TreeNode_<K, V> xp = p;
                        if ((p = (dir <= 0) ? p.left : p.right) == null) {
                            x.parent = xp;
                            if (dir <= 0)
                                xp.left = x;
                            else
                                xp.right = x;
                            root = balanceInsertion(root, x);
                            break;
                        }
                    }
                }
            }
            moveRootToFront(tab, root);
        }

        /**
         * 返回替换从该节点链接的非 TreeNode_ 的列表。
         */
        final HashMap8.Node_<K, V> untreeify(HashMap8<K, V> map) {
            HashMap8.Node_<K, V> hd = null, tl = null;
            for (HashMap8.Node_<K, V> q = this; q != null; q = q.next) {
                HashMap8.Node_<K, V> p = map.replacementNode(q, null);
                if (tl == null)
                    hd = p;
                else
                    tl.next = p;
                tl = p;
            }
            return hd;
        }

        /**
         * 使用给定的哈希和键查找从根 p 开始的节点。 kc 参数在第一次使用比较键时缓存可比较的ClassFor(key)。
         */
        final HashMap8.TreeNode_<K, V> find(int h, Object k, Class<?> kc) {
            HashMap8.TreeNode_<K, V> p = this;
            do {
                int ph, dir;
                K pk;
                HashMap8.TreeNode_<K, V> pl = p.left, pr = p.right, q;
                if ((ph = p.hash) > h)
                    p = pl;
                else if (ph < h)
                    p = pr;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    return p;
                else if (pl == null)
                    p = pr;
                else if (pr == null)
                    p = pl;
                else if ((kc != null ||
                        (kc = comparableClassFor(k)) != null) &&
                        (dir = compareComparables(kc, k, pk)) != 0)
                    p = (dir < 0) ? pl : pr;
                else if ((q = pr.find(h, k, kc)) != null)
                    return q;
                else
                    p = pl;
            } while (p != null);
            return null;
        }

        /**
         * 为根节点调用 find。
         */
        final HashMap8.TreeNode_<K, V> getTreeNode(int h, Object k) {
            return ((parent != null) ? root() : this).find(h, k, null);
        }

        /**
         * putVal 的树形版本。
         */
        final HashMap8.TreeNode_<K, V> putTreeVal(HashMap8<K, V> map, HashMap8.Node_<K, V>[] tab, int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            HashMap8.TreeNode_<K, V> root = (parent != null) ? root() : this;
            for (HashMap8.TreeNode_<K, V> p = root; ; ) {
                int dir, ph;
                K pk;
                if ((ph = p.hash) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    return p;
                else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                        (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        HashMap8.TreeNode_<K, V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null &&
                                (q = ch.find(h, k, kc)) != null) ||
                                ((ch = p.right) != null &&
                                        (q = ch.find(h, k, kc)) != null))
                            return q;
                    }
                    dir = tieBreakOrder(k, pk);
                }

                HashMap8.TreeNode_<K, V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    HashMap8.Node_<K, V> xpn = xp.next;
                    HashMap8.TreeNode_<K, V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null)
                        ((HashMap8.TreeNode_<K, V>) xpn).prev = x;
                    moveRootToFront(tab, balanceInsertion(root, x));
                    return null;
                }
            }
        }

        /**
         * 删除在此调用之前必须存在的给定节点。这比典型的红黑删除代码更混乱，
         * 因为我们不能将内部节点的内容与叶后继节点交换，叶后继节点由在遍历期间可独立访问的“下一个”指针固定。
         * 因此，我们交换了树链接。如果当前树的节点似乎太少，则将 bin 转换回普通 bin。
         * （测试在 2 到 6 个节点之间触发，具体取决于树结构）。
         */
        final void removeTreeNode_(HashMap8<K, V> map, HashMap8.Node_<K, V>[] tab,
                                   boolean movable) {
            int n;
            if (tab == null || (n = tab.length) == 0)
                return;
            int index = (n - 1) & hash;
            HashMap8.TreeNode_<K, V> first = (HashMap8.TreeNode_<K, V>) tab[index], root = first, rl;
            HashMap8.TreeNode_<K, V> succ = (HashMap8.TreeNode_<K, V>) next, pred = prev;
            if (pred == null)
                tab[index] = first = succ;
            else
                pred.next = succ;
            if (succ != null)
                succ.prev = pred;
            if (first == null)
                return;
            if (root.parent != null)
                root = root.root();
            if (root == null
                    || (movable
                    && (root.right == null
                    || (rl = root.left) == null
                    || rl.left == null))) {
                tab[index] = first.untreeify(map);  // too small
                return;
            }
            HashMap8.TreeNode_<K, V> p = this, pl = left, pr = right, replacement;
            if (pl != null && pr != null) {
                HashMap8.TreeNode_<K, V> s = pr, sl;
                while ((sl = s.left) != null) // find successor
                    s = sl;
                boolean c = s.red;
                s.red = p.red;
                p.red = c; // swap colors
                HashMap8.TreeNode_<K, V> sr = s.right;
                HashMap8.TreeNode_<K, V> pp = p.parent;
                if (s == pr) { // p was s's direct parent
                    p.parent = s;
                    s.right = p;
                } else {
                    HashMap8.TreeNode_<K, V> sp = s.parent;
                    if ((p.parent = sp) != null) {
                        if (s == sp.left)
                            sp.left = p;
                        else
                            sp.right = p;
                    }
                    if ((s.right = pr) != null)
                        pr.parent = s;
                }
                p.left = null;
                if ((p.right = sr) != null)
                    sr.parent = p;
                if ((s.left = pl) != null)
                    pl.parent = s;
                if ((s.parent = pp) == null)
                    root = s;
                else if (p == pp.left)
                    pp.left = s;
                else
                    pp.right = s;
                if (sr != null)
                    replacement = sr;
                else
                    replacement = p;
            } else if (pl != null)
                replacement = pl;
            else if (pr != null)
                replacement = pr;
            else
                replacement = p;
            if (replacement != p) {
                HashMap8.TreeNode_<K, V> pp = replacement.parent = p.parent;
                if (pp == null)
                    root = replacement;
                else if (p == pp.left)
                    pp.left = replacement;
                else
                    pp.right = replacement;
                p.left = p.right = p.parent = null;
            }

            HashMap8.TreeNode_<K, V> r = p.red ? root : balanceDeletion(root, replacement);

            if (replacement == p) {  // detach
                HashMap8.TreeNode_<K, V> pp = p.parent;
                p.parent = null;
                if (pp != null) {
                    if (p == pp.left)
                        pp.left = null;
                    else if (p == pp.right)
                        pp.right = null;
                }
            }
            if (movable)
                moveRootToFront(tab, r);
        }

        /**
         * 将树箱中的节点拆分为较低和较高的树箱，如果现在太小，则取消树化。仅从调整大小调用；请参阅上面关于拆分位和索引的讨论。
         *
         * @param map   the map
         * @param tab   the table for recording bin heads
         * @param index the index of the table being split
         * @param bit   the bit of hash to split on
         */
        final void split(HashMap8<K, V> map, HashMap8.Node_<K, V>[] tab, int index, int bit) {
            HashMap8.TreeNode_<K, V> b = this;
            // Relink into lo and hi lists, preserving order
            HashMap8.TreeNode_<K, V> loHead = null, loTail = null;
            HashMap8.TreeNode_<K, V> hiHead = null, hiTail = null;
            int lc = 0, hc = 0;
            for (HashMap8.TreeNode_<K, V> e = b, next; e != null; e = next) {
                next = (HashMap8.TreeNode_<K, V>) e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    if ((e.prev = loTail) == null)
                        loHead = e;
                    else
                        loTail.next = e;
                    loTail = e;
                    ++lc;
                } else {
                    if ((e.prev = hiTail) == null)
                        hiHead = e;
                    else
                        hiTail.next = e;
                    hiTail = e;
                    ++hc;
                }
            }

            if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD)
                    tab[index] = loHead.untreeify(map);
                else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                        loHead.treeify(tab);
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD)
                    tab[index + bit] = hiHead.untreeify(map);
                else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }

        /* ------------------------------------------------------------ */
        // 红黑树方法，全部改编自CLR
        //向左旋转
        static <K, V> HashMap8.TreeNode_<K, V> rotateLeft(HashMap8.TreeNode_<K, V> root,
                                                          HashMap8.TreeNode_<K, V> p) {
            HashMap8.TreeNode_<K, V> r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null)
                    rl.parent = p;
                if ((pp = r.parent = p.parent) == null)
                    (root = r).red = false;
                else if (pp.left == p)
                    pp.left = r;
                else
                    pp.right = r;
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        //向右旋转
        static <K, V> HashMap8.TreeNode_<K, V> rotateRight(HashMap8.TreeNode_<K, V> root,
                                                           HashMap8.TreeNode_<K, V> p) {
            HashMap8.TreeNode_<K, V> l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null)
                    lr.parent = p;
                if ((pp = l.parent = p.parent) == null)
                    (root = l).red = false;
                else if (pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        //平衡插入
        static <K, V> HashMap8.TreeNode_<K, V> balanceInsertion(HashMap8.TreeNode_<K, V> root,
                                                                HashMap8.TreeNode_<K, V> x) {
            x.red = true;
            for (HashMap8.TreeNode_<K, V> xp, xpp, xppl, xppr; ; ) {
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (!xp.red || (xpp = xp.parent) == null)
                    return root;
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xppr.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.right) {
                            root = rotateLeft(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                } else {
                    if (xppl != null && xppl.red) {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.left) {
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

        //平衡删除
        static <K, V> HashMap8.TreeNode_<K, V> balanceDeletion(HashMap8.TreeNode_<K, V> root,
                                                               HashMap8.TreeNode_<K, V> x) {
            for (HashMap8.TreeNode_<K, V> xp, xpl, xpr; ; ) {
                if (x == null || x == root)
                    return root;
                else if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (x.red) {
                    x.red = false;
                    return root;
                } else if ((xpl = xp.left) == x) {
                    if ((xpr = xp.right) != null && xpr.red) {
                        xpr.red = false;
                        xp.red = true;
                        root = rotateLeft(root, xp);
                        xpr = (xp = x.parent) == null ? null : xp.right;
                    }
                    if (xpr == null)
                        x = xp;
                    else {
                        HashMap8.TreeNode_<K, V> sl = xpr.left, sr = xpr.right;
                        if ((sr == null || !sr.red) &&
                                (sl == null || !sl.red)) {
                            xpr.red = true;
                            x = xp;
                        } else {
                            if (sr == null || !sr.red) {
                                if (sl != null)
                                    sl.red = false;
                                xpr.red = true;
                                root = rotateRight(root, xpr);
                                xpr = (xp = x.parent) == null ?
                                        null : xp.right;
                            }
                            if (xpr != null) {
                                xpr.red = (xp == null) ? false : xp.red;
                                if ((sr = xpr.right) != null)
                                    sr.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateLeft(root, xp);
                            }
                            x = root;
                        }
                    }
                } else { // symmetric
                    if (xpl != null && xpl.red) {
                        xpl.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        xpl = (xp = x.parent) == null ? null : xp.left;
                    }
                    if (xpl == null)
                        x = xp;
                    else {
                        HashMap8.TreeNode_<K, V> sl = xpl.left, sr = xpl.right;
                        if ((sl == null || !sl.red) &&
                                (sr == null || !sr.red)) {
                            xpl.red = true;
                            x = xp;
                        } else {
                            if (sl == null || !sl.red) {
                                if (sr != null)
                                    sr.red = false;
                                xpl.red = true;
                                root = rotateLeft(root, xpl);
                                xpl = (xp = x.parent) == null ?
                                        null : xp.left;
                            }
                            if (xpl != null) {
                                xpl.red = (xp == null) ? false : xp.red;
                                if ((sl = xpl.left) != null)
                                    sl.red = false;
                            }
                            if (xp != null) {
                                xp.red = false;
                                root = rotateRight(root, xp);
                            }
                            x = root;
                        }
                    }
                }
            }
        }

        /**
         * 递归不变检查
         */
        static <K, V> boolean checkInvariants(HashMap8.TreeNode_<K, V> t) {
            HashMap8.TreeNode_<K, V> tp = t.parent, tl = t.left, tr = t.right,
                    tb = t.prev, tn = (HashMap8.TreeNode_<K, V>) t.next;
            if (tb != null && tb.next != t)
                return false;
            if (tn != null && tn.prev != t)
                return false;
            if (tp != null && t != tp.left && t != tp.right)
                return false;
            if (tl != null && (tl.parent != t || tl.hash > t.hash))
                return false;
            if (tr != null && (tr.parent != t || tr.hash < t.hash))
                return false;
            if (t.red && tl != null && tl.red && tr != null && tr.red)
                return false;
            if (tl != null && !checkInvariants(tl))
                return false;
            if (tr != null && !checkInvariants(tr))
                return false;
            return true;
        }
    }

    //创建一个常规（非树）节点
    HashMap8.Node_<K, V> newNode(int hash, K key, V value, HashMap8.Node_<K, V> next) {
        return new HashMap8.Node_<>(hash, key, value, next);
    }

    //替换节点
    HashMap8.Node_<K, V> replacementNode(HashMap8.Node_<K, V> p, HashMap8.Node_<K, V> next) {
        return new HashMap8.Node_<>(p.hash, p.key, p.value, next);
    }

    //创建树节点
    HashMap8.TreeNode_<K, V> newTreeNode(int hash, K key, V value, HashMap8.Node_<K, V> next) {
        return new HashMap8.TreeNode_<>(hash, key, value, next);
    }

    //替换树节点
    HashMap8.TreeNode_<K, V> replacementTreeNode(HashMap8.Node_<K, V> p, HashMap8.Node_<K, V> next) {
        return new HashMap8.TreeNode_<>(p.hash, p.key, p.value, next);
    }

}
