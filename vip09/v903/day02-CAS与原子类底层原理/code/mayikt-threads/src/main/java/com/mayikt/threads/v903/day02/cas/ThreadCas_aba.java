package com.mayikt.threads.v903.day02.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 周宇
 * @create 2021-06-24 20:51
 * 接近Cas aba的问题
 */
public class ThreadCas_aba {
    // 注意：如果引用类型是Long、Integer、Short、Byte、Character一定一定要注意值的缓存区间！
    // 比如Long、Integer、Short、Byte缓存区间是在-128~127，会直接存在常量池中，而不在这个区间内对象的值则会每次都new一个对象，那么即使两个对象的值相同，CAS方法都会返回false
    // 先声明初始值，修改后的值和临时的值是为了保证使用CAS方法不会因为对象不一样而返回false
    private static final Integer INIT_NUM = 1000;
    private static final Integer UPDATE_NUM = 100;
    private static final Integer TEM_NUM = 200;
    /**
     * initialRef 初始值
     * initialStamp 初始印章
     */
    private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(INIT_NUM, 1);

    public static void main(String[] args) {
        /**
         * INIT_NUM==V==1000  第一次版本号码 === 1
         */
        new Thread(() -> {
            Integer value = (Integer) atomicStampedReference.getReference();
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t: 当前值为：\t" + value + "\t版本号为：\t" + stamp);

            try {
                //阻塞1s
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // value 旧值 内存中的值   UPDATE_NUM 修改的值
            if (atomicStampedReference.compareAndSet(value, UPDATE_NUM, 1, stamp + 1)) {
                System.out.println(Thread.currentThread().getName() + "\t: 当前值为：\t" + atomicStampedReference.getReference() + "\t\t版本号为：\t" + atomicStampedReference.getStamp());
            } else {
                System.out.println("版本号不同，更新失败！");
            }
        }, "线程A").start();
    }
}

