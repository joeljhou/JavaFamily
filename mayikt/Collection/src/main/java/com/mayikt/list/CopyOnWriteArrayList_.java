package com.mayikt.list;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 周宇
 * @create 2022-02-14 10:11
 */
public class CopyOnWriteArrayList_<E> implements List_<E> {

    //保证线程安全
    final ReentrantLock lock = new ReentrantLock();
    //所有线程可见
    private volatile Object[] array;

    final Object[] getArray() {
        return array;
    }

    final void setArray(Object[] a) {
        array = a;
    }

    public CopyOnWriteArrayList_() {
        setArray(new Object[0]);
    }

    @Override
    public int size() {
        return getArray().length;
    }

    //每次扩容的时候产生新数组，效率非常低
    @Override
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();  //获取原数组
            int len = elements.length;       //获取原数组长度
            Object[] newElements = Arrays.copyOf(elements, len + 1);  //复制扩容+1
            newElements[len] = e;
            setArray(newElements);
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public void add(int index, E element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();  //获取原数组
            int len = elements.length;       //获取原数组长度
            if (index > len || index < 0) {
                //缩印越界跑抛出异常
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + len);
            }
            int numMoved = len - index;
            Object[] newElements = null;
            if (numMoved == 0) {
                newElements = Arrays.copyOf(elements, len + 1);  //最后插入
            } else {
                newElements = new Object[len + 1];
                //数组复制 原数组 从元数据的起始位置开始 目标数组 目标数组的开始起始位置 要copy的数组的长度
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index, newElements, index + 1, numMoved);
            }
            newElements[index] = element;
            setArray(newElements);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E get(int index) {
        return (E) getArray()[index];
    }

    @Override
    public E set(int index, E element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();    //获取原数组
            E oldValue = (E) elements[index];  //要修改的数组
            if (oldValue != element) {
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len);
                newElements[index] = element;
                setArray(newElements);
            } else {
                setArray(elements);     //不是完全没有操作；确保易失的写语义
            }
            return oldValue;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E remove(int index) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            E oldValue = (E) elements[index];
            int numMoved = len - index - 1;
            if (numMoved == 0)
                setArray(Arrays.copyOf(elements, len - 1));      //移除最后一个
            else {
                Object[] newElements = new Object[len - 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index + 1, newElements, index, numMoved);
                setArray(newElements);
            }
            return oldValue;
        } finally {
            lock.unlock();
        }
    }

}
