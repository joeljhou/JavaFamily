package com.mayikt.threads.v903.day04;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 周宇
 * @create 2021-07-06 22:24
 * 对象大小分布分析
 * 启用指针压缩-XX:+UseCompressedOops(默认开启)，禁止指针压缩:-XX:-UseCompressedOops
 * int     32bit  4
 * short   16bit  2
 * long     64bit 8
 * byte     8bit
 * char     16bit
 * float   32bit
 * double   64bit
 * boolean 1bit
 */
public class Object01_SizeDistributionAnalysis {

    public static void main(String[] args) {
        /**
         * 没有关闭指针压缩
         * 开启了指针压缩
         * 正常new 对象 没有任何属性 12字节 int j = 4;=4
         * 12+4=16
         */
        MayiktLockObject mayiktLock = new MayiktLockObject();
        System.out.println(ClassLayout.parseInstance(mayiktLock).toPrintable());
    }

    static class MayiktLockObject{
        //offset=12+4=16  8 - offset % 8 = 0
        int j = 4;
        //offset+8=24     8 - offset % 8 = 0
        long i = 1;
        //offset+1=25     8 - offset % 8 = 7
        boolean m = false;
    }
}
