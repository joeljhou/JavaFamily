package com.mayikt.list;


/**
 * @author 周宇
 * @create 2022-02-14 10:04
 */
public interface List_<E> {
    int size();
    boolean add(E e);
    void add(int index, E element);
    E get(int index);
    E set(int index, E element);
    E remove(int index);
}
