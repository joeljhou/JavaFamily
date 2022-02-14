package com.mayikt.list;

import java.util.NoSuchElementException;

/**
 * @author 周宇
 * @create 2022-02-15 0:57
 * Java单向链表实现（参考JDK中的LinkedList双向链表）
 */
public class Linked<E> {

    //头结点 指向第一个节点的指针
    private Node<E> first;
    //单链表节点个数
    private int size;

    //构造函数
    public Linked() {
    }

    //返回此列表中的第一个元素
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    //返回此列表中的最后一个元素
    public E getLast() {
        return node(size - 1).item;
    }

    //从此列表中删除并返回第一个元素
    public E removeFirst() {
        Node<E> fdelNode = first;
        if (fdelNode == null) {
            throw new NoSuchElementException();
        }
        E element = fdelNode.item;      //第一个元素
        Node<E> next = fdelNode.next;   //第二个节点
        //清空第一个节点 帮助GC
        fdelNode.item = null;
        fdelNode.next = null;
        //第二个节点设置为头节点
        first = next;
        size--;
        return element;
    }

    //移除并返回此列表中的最后一个元素。
    public E removeLast() {
        Node<E> f = first;     //第一个头节点
        if (f == null) {
            throw new NoSuchElementException();
        }
        //获取倒数第二个节点
        //索引从0开始 (size-1)——>最后一个元素   (size-2)——>倒数第二个节点
        Node<E> secondLast = node(size - 2);
        Node<E> ldelNode = secondLast.next;              //最后一个节点
        E element = ldelNode.item;
        //清空最后一个节点 帮助GC
        ldelNode.item = null;
        ldelNode.next = null;
        //取消倒数第二个节点链接到最后一个节点
        secondLast.next = null;
        size--;
        return element;
    }

    //向链表头部添加元素
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);   //要插入的节点对象
        newNode.next = first;              //下一节点指向之前的头节点
        first = newNode;                   //改变头结点
        //first = new Node<E>(e,first);    等价上述代码
        size++;                            //元素个数+1
    }

    //向链表尾部插入元素
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);   //要插入的节点对象
        Node<E> temp = this.first;         //临时节点——>循环找到最后一个节点
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    //包含
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    //返回此列表中的元素数
    public int size() {
        return size;
    }

    //将指定元素附加到此列表的末尾
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    //删除第一次出现的指定元素
    public boolean remove(Object o) {
        boolean flag = false;  //标记链表中是否存在元素o
        Node<E> prev = null;   //被删除的节点的上一个节点

        if (o == null) {
            for (Node<E> x = first; x != null; prev = x, x = x.next) {
                if (x.item == null) {
                    flag = true;
                    break;
                }
            }
        } else {
            for (Node<E> x = first; x != null; prev = x, x = x.next) {
                if (o.equals(x.item)) {
                    flag = true;
                    break;
                }
            }
        }
        //存在
        if (flag) {
            Node<E> delNode = prev.next;  //要删除的节点
            Node<E> next = delNode.next;  //要删除的下一个节点
            prev.next = next;          //被删除节点的前一个节点——>指向被删除节点的后一个节点
            //清空第一个节点 帮助GC
            delNode.item = null;
            delNode.next = null;
            size--;
        }
        ;
        return flag;
    }

    //从此列表中删除所有元素。此调用返回后，列表将为空。
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }
        first = null;
        size = 0;
    }

    //返回此列表中指定位置的元素
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    //将此列表中指定位置的元素替换为指定元素
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    //向链表中间插入元素
    public void add(int index, E element) {
        checkElementIndex(index);
        if (index == size) {
            addLast(element);     //向链表尾部插入元素
        } else {
            //在非空节点 succ 之后插入元素 e
            Node<E> newNode = new Node<>(element);   //要插入的节点对象
            Node<E> succ = node(index - 1);      //获取指定下标的前一个元素
            succ.next = newNode;                     //前一个元素指向要插入的新节点
            newNode.next = succ.next;                //新节点链接下一个节点，形成链
        }
        size++;
    }

    //移除此列表中指定位置的元素
    public E remove(int index) {
        Node<E> prev = node(index - 1);
        Node<E> delNode = prev.next;
        Node<E> next = delNode.next;
        prev.next = next;
        E element = delNode.item;
        //清空第一个节点 帮助GC
        delNode.item = null;
        delNode.next = null;
        size--;
        return element;
    }

    //指定元素第一次出现的索引 不包含返回-1
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    //指定元素最后一次出现的索引 不包含返回-1
    public int lastIndexOf(Object o) {
        int lastindex = -1;
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    lastindex = index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    lastindex = index;
                index++;
            }
        }
        return lastindex;
    }

    //检索但不删除此列表的头部（第一个元素）。
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    //检索但不删除此列表的头部（第一个元素）
    public E element() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    //检索并删除此列表的头部（第一个元素）。
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : removeFirst();
    }

    //检索并删除此列表的头部（第一个元素）
    public E remove() {
        return removeFirst();
    }

    //添加指定元素作为此列表的尾部（最后一个元素）。
    public boolean offer(E e) {
        return add(e);
    }

    //在此列表的前面插入指定的元素
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    //在此列表的末尾插入指定的元素
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    //检索但不删除此列表的第一个元素，如果此列表为空，则返回 {@code null}。
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    //检索但不删除此列表的最后一个元素，如果此列表为空，则返回 {@code null}。
    public E peekLast() {
        final Node<E> l = node(size - 1);
        return (l == null) ? null : l.item;
    }

    //检索并删除此列表的第一个元素，如果此列表为空，则返回 {@code null}。
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : removeFirst();
    }

    //检索并删除此列表的最后一个元素，如果此列表为空，则返回 {@code null}。
    public E pollLast() {
        final Node<E> l = node(size - 1);
        return (l == null) ? null : removeLast();
    }

    //将元素推送到此列表表示的堆栈上。换句话说，在这个列表的前面插入元素。
    public void push(E e) {
        addFirst(e);
    }

    //从此列表表示的堆栈中弹出一个元素。换句话说，删除并返回此列表的第一个元素。
    public E pop() {
        return removeFirst();
    }

    //删除此列表中第一次出现的指定元素（从头到尾遍历列表时）。如果列表不包含该元素，则它不变。
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    //删除此列表中指定元素的最后一次出现（从头到尾遍历列表时）。如果列表不包含该元素，则它不变。
    public boolean removeLastOccurrence(Object o) {
        int index = lastIndexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    //以正确的顺序（从第一个元素到最后一个元素）返回包含此列表中所有元素的数组。
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    //以正确的顺序（从第一个元素到最后一个元素）返回一个包含此列表中所有元素的数组；
    // 返回数组的运行时类型是指定数组的运行时类型。
    // 如果列表适合指定的数组，则在其中返回。
    // 否则，将使用指定数组的运行时类型和此列表的大小分配一个新数组。
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    //检查元素索引
    private void checkElementIndex(int index) {
        //下标越界异常
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index: " + index + ", Size: " + size);
        }
    }

    //返回指定下标的节点
    Node<E> node(int index) {
        Node<E> x = first;                 //索引0 直接返回
        for (int i = 0; i < index; i++) {  //索引1-n 循环获取next
            x = x.next;
        }
        return x;
    }

    //链表存储的结点
    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public Node(E element) {
            this(element, null);
        }
    }

}
