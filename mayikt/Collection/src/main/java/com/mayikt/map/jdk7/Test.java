package com.mayikt.map.jdk7;

/**
 * @author 周宇
 * @create 2022-02-17 0:04
 */
public class Test {
    public static void main(String[] args) {
        HashMap_ArrayList mapArrayList = new HashMap_ArrayList();
        mapArrayList.put("a",97);
        mapArrayList.put("97",97);
        mapArrayList.put("a",97);      //无法覆盖  size=3
        System.out.println(mapArrayList.size());
        System.out.println(mapArrayList.get("a"));
        System.out.println(mapArrayList.get("b"));
        System.out.println(mapArrayList.get("97"));
        System.out.println("========== HashMap_ArrayList ==========");

        HashMap_LinkedList mapLinkedList = new HashMap_LinkedList();
        mapLinkedList.put("a",97);
        mapLinkedList.put("97",97);
        mapLinkedList.put("a",97);      //可以覆盖 size=2
        System.out.println(mapLinkedList.size());
        System.out.println(mapLinkedList.get("a"));
        System.out.println(mapLinkedList.get("b"));
        System.out.println(mapLinkedList.get("97"));
        System.out.println("========== HashMap_LinkedList ==========");

        HashMap7<Object, Object> hashMap7 = new HashMap7<>();
        hashMap7.put("a", "蚂蚁课堂A");
        hashMap7.put(97, "蚂蚁课堂B");
        hashMap7.put("a","蚂蚁课堂C");      //可以覆盖 size=2
        System.out.println(hashMap7.size());
        System.out.println(hashMap7.get("a"));
        System.out.println(hashMap7.get(97));
        System.out.println("========== hashMap7 ==========");
    }
}
