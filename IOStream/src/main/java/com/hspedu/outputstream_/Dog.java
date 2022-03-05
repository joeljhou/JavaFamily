package com.hspedu.outputstream_;

import java.io.Serializable;

/**
 * 如果需要序列化某个类，必须实现Serializable
 */
public class Dog implements Serializable {
    private String name;
    private int age;

    //序列化对象时，默认将里面所有属性都进行序列化，但除了static或transient修饰的成员
    private static String nation;
    private transient String color;

    private Master master = new Master();
    //serialVersionUID 序列版本号，可以提供序列化兼容性
    private static final long serialVersionUID = 1L;

    public Dog(String name, int age, String nation,String color) {
        this.name = name;
        this.age = age;
        this.nation = nation;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nation=" + nation +
                ", color='" + color + '\'' +
                '}';
    }
}