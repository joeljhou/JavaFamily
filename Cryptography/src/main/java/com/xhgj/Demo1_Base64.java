package com.xhgj;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-03-06 5:29
 * BASE64加密：明文，BASE64密文 相当于一种公钥加密
 * 弊端：一旦别人知道我们是什么加密算法就能破解
 */
public class Demo1_Base64 {
    public static void main(String[] args) throws IOException {
        String str = "一个简单的字符串/r/n";
        BASE64Encoder encoder = new BASE64Encoder();
        String dataEn = encoder.encodeBuffer(str.getBytes());
        System.out.println("加密数据\n" + dataEn);

        BASE64Decoder decoder = new BASE64Decoder();
        String dataDn = new String(decoder.decodeBuffer(dataEn));
        System.out.println("解密数据\n" + dataDn);
    }
}
