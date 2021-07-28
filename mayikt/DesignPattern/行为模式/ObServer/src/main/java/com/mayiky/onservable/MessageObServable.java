package com.mayiky.onservable;

import java.util.Observable;

/**
 * @author 周宇
 * @create 2021-07-28 18:14
 */
public class MessageObServable extends Observable {

    @Override
    public void notifyObservers(Object arg) {
        //1.修改状态为可以群发
        setChanged();
        //2.调用父类的notifyObservers方法
        super.notifyObservers(arg);
    }

}
