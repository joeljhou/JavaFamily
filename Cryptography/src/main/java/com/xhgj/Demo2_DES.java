package com.xhgj;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 周宇
 * @create 2022-03-06 5:38
 * DES加密是一种对称加密，明文，密码(只有自己知道)，密文
 * https://www.bilibili.com/video/BV1JV411J7GH?p=3
 */
public class Demo2_DES {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        BothDES bs = new BothDES();
        String data = "DES加密算法";
        boolean encrypt = bs.encrypt(data.getBytes());
        System.out.println(encrypt == true ? "加密成功并写入文件" : "加密失败");
        System.out.println("读取加密文件并解密：" + bs.decrypt());
    }
}

class BothDES {
    String algorithm = "DES";//表示DES算法
    //随机秘钥文件
    String secretFile = Demo2_DES.class.getResource("/").getPath() + "SecretKey.dat";
    //加密数据文件
    String dataEncryptFile = Demo2_DES.class.getResource("/").getPath() + "dataEncryptFile.dat";

    //构造函数 生成随机密码秘钥
    public BothDES() throws NoSuchAlgorithmException {
        System.out.println("===构造函数===");
        SecureRandom srand = new SecureRandom();                   //随机安全密码
        KeyGenerator gk = KeyGenerator.getInstance(algorithm);     //密钥生成器
        gk.init(srand);                                            //秘钥初始化
        SecretKey key = gk.generateKey();                          //生成密码密钥
        this.writeFile(key.getEncoded(), secretFile);              //将密码秘钥写入文件
        System.out.println("生成随机密码秘钥");
    }

    //写入文件
    public void writeFile(byte[] data, String filePath) {
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(filePath);
            fileout.write(data);
        } catch (Exception e) {
            Logger.getLogger(BothDES.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        } finally {
            if (fileout != null) {
                try {
                    fileout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //读取文件
    public byte[] readFile(String filePath) {
        FileInputStream filein = null;
        try {
            File file = new File(filePath);
            filein = new FileInputStream(filePath);
            byte[] data = new byte[(int) file.length()];
            filein.read(data);
            return data;
        } catch (Exception e) {
            Logger.getLogger(BothDES.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return null;
        } finally {
            if (filein != null) {
                try {
                    filein.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Key getKey() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] secret = this.readFile(secretFile);   //获取随机密码秘钥
        DESKeySpec ksp = new DESKeySpec(secret);     //生成DES Key
        SecretKeyFactory fac = SecretKeyFactory.getInstance(algorithm);   //算法接口
        SecretKey selfKey = fac.generateSecret(ksp);
        return selfKey;
    }

    /**
     * 加密
     */
    public boolean encrypt(byte[] data) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key dKey = this.getKey();
        Cipher cipher = Cipher.getInstance(algorithm);  //Cipher用于执行加密的类，根据算法
        cipher.init(Cipher.ENCRYPT_MODE, dKey);         //加密还是解密 传递key
        byte[] fdata = cipher.doFinal(data);            //执行加密
        this.writeFile(fdata, this.dataEncryptFile);           //写入文件
        return true;
    }

    /**
     * 解密
     */
    public String decrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key dKey = this.getKey();
        Cipher cipher = Cipher.getInstance(algorithm);  //Cipher用于执行加密的类，根据算法
        cipher.init(Cipher.DECRYPT_MODE, dKey);         //设定解密
        byte[] fData = cipher.doFinal(this.readFile(this.dataEncryptFile));       //执行解密
        return new String(fData);
    }
}