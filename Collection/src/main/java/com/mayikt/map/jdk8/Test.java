package com.mayikt.map.jdk8;

/**
 * @author 周宇
 * @create 2022-02-21 2:57
 */
public class Test {
    public static void main(String[] args) {
        HashMap8<Object, Object> hashMap8 = new HashMap8<>();
        hashMap8.put("a", "蚂蚁课堂A");
        hashMap8.put(97, "蚂蚁课堂B");
        hashMap8.put("a","蚂蚁课堂C");      //可以覆盖 size=2
        System.out.println(hashMap8.size());
        System.out.println(hashMap8.get("a"));
        System.out.println(hashMap8.get(97));
        System.out.println("========== hashMap7 ==========");
    }
}
