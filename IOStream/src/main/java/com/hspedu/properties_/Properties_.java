package com.hspedu.properties_;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

/**
 * @author 周宇
 * @create 2022-02-07 15:27
 */
public class Properties_ {

    /**
     * 传统方法解决：比较麻烦
     */
    @Test
    public void properties01() {
        String filePath = "src/main/java/com/hspedu/properties_/mysql.properties";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {  //循环读取
                String[] split = line.split("=");  //拆分
                //如果我们要求得到指定ip值
                if ("ip".equals(split[0])) {
                    System.out.println(split[0] + "值是:" + split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Properties类读取配置文件
     */
    @Test
    public void properties02(){
        String filePath = "src/main/java/com/hspedu/properties_/mysql.properties";
        try {
            //使用Properties类读取mysql.properties文件
            //1.创建Properties对象
            Properties properties = new Properties();
            //2.加载指定的配置文件
            properties.load(new FileReader(filePath));
            //3.把k-v显示到控制台
            properties.list(System.out);
            //4.根据key获取对应值
            String user = properties.getProperty("user");
            String pwd = properties.getProperty("pwd");
            System.out.println("用户名="+user);
            System.out.println("密码是="+pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Properties类创建/修改配置文件
     */
    @Test
    public void properties03(){
        String filePath = "src/main/java/com/hspedu/properties_/mysql.properties2";
        Properties properties = new Properties();
        //setProperty
        //1.如果该文件没有key 就是创建
        //2.如果该文件有key 就是修改
        properties.setProperty("charset","utf8");
        properties.setProperty("user","汤姆");   //注意保存是保存的是中文的unicode码
        properties.setProperty("pwd","abc111");
        try {
            //将key-value 存储到文件中
            properties.store(new FileWriter(filePath),null);
            System.out.println("保存配置文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
