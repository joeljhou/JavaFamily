package com.mayikt.list;

import java.util.Arrays;

/**
 * @author 周宇
 * @create 2022-02-14 7:20
 * 存手写ArrayList集合
 * 不考虑判断并发
 */
public class ArrayList_<E> implements List_<E> {

    private Object[] elementData;        //底层数组
    private int size;                    //0

    public ArrayList_() {
        //第一版>无参构造函数,默认初始化容量10
        //第二版>初始容量为0,之后懒加载进行容量初始化
        elementData = new Object[10];
    }

    public int size() {
        return this.size;
    }

    //添加元素
    public boolean add(E e) {
        if (elementData.length - (size + 1) < 0) { //扩容条件
            grow();
        }
        //添加元祖
        elementData[size++] = e;
        return true;
    }

    //添加元素 根据指定下标
    public void add(int index, E element) {
        //检查是否下标越界
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        //数组复制 原数组 从元数据的起始位置开始 目标数组 目标数组的开始起始位置 要copy的数组的长度
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    //修改元素
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    //获取元素
    public E get(int index) {
        rangeCheck(index);
        return (E) elementData[index];
    }

    //移除元素 根据下标
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = (E) elementData[index];  //获取要移除的元素
        elementData[--size] = null;           //移除
        return oldValue;
    }

    //移除元素 根据对象
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    elementData[--size] = null;           //移除
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    elementData[--size] = null;           //移除
                    return true;
                }
            }
        }
        return false;
    }

    //清空元素
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    //是空的?
    public boolean isEmpty() {
        return size == 0;
    }

    //包含
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //扩容
    private void grow() {
        int oldCapacity = elementData.length;    //原来容量
        int newCapacity = oldCapacity + (oldCapacity >> 1);   //扩容1.5倍
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    //检查下标是否越界
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    //得到第一个此对象返回下标 不包含返回-1
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    //得到最后一个此对象返回下标 不包含返回-1
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

}
