package com.mayikt.stack;

import java.util.EmptyStackException;

/**
 * @author 周宇
 * @create 2022-02-19 1:36
 * 堆栈（Stack）：先进后出原则
 */
public class Stack_<E> {

    private Object[] elementData;
    private int elementCount;
    //容量增量
    private int capacityIncrement;


    public int size() {
        return elementCount;
    }

    //构造函数 创建一个空堆栈。
    public Stack_() {
    }

    //入栈
    public E push(E item) {
        //addElement(item);
        return item;
    }

    //出栈,返回栈顶元素,同时从栈中移除该元素
    public synchronized E pop() {
        E obj;
        int len = size();
        obj = peek();
        //removeElementAt(len - 1);
        return obj;
    }

    //返回栈顶元素,未出栈
    public synchronized E peek() {
        int len = size();
        if (len == 0)
            throw new EmptyStackException();
        //return elementAt(len - 1);
        return null;
    }

    //栈是否为空
    public boolean empty() {
        return size() == 0;
    }

    public synchronized int search(Object o) {
        //int i = lastIndexOf(o);
        //if (i >= 0) {
        //    return size() - i;
        //}
        return -1;
    }

}
