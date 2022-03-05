package com.hspedu.outputstream_;

import com.hspedu.outputstream_.Dog;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author 周宇
 * @create 2022-02-06 23:41
 */
public class ObjectOutputStream_ {
    @Test
    public void serializable() {
        //序列化后，保存的文件格式，不是纯文本，而是按照他的格式来保存的
        String filePath = "src\\main\\java\\com\\xhgj\\outputstream_\\data.dat";

        ObjectOutputStream oos = null;
        try {
            //1.创建ObjectOutputStream对象
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            //2.对象保存
            oos.writeInt(100);          //int->Integer（实现了Serializable）
            oos.writeBoolean(true);  //boolean->Boolean（实现了Serializable）
            oos.writeChar('a');      //char->Character（实现了Serializable）
            oos.writeDouble(9.9);    //double->Double（实现了Serializable）
            oos.writeUTF("北京冬奥会开幕式");  //String（实现了Serializable）
            oos.writeObject(new Dog("小黄", 2, "日本", "白色"));  //保存对象
            System.out.println("数据保存完毕(序列号完成)");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deserializable() {
        String filePath = "src\\main\\java\\com\\xhgj\\outputstream_\\data.dat";
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            System.out.println(ois.readInt());
            System.out.println(ois.readBoolean());
            System.out.println(ois.readChar());
            System.out.println(ois.readDouble());
            System.out.println(ois.readUTF());
            Object dog = ois.readObject();
            System.out.println("运行类型=" + dog.getClass());
            System.out.println("Dog信息=" + dog);   //底层Object->Dog
            //这里是特别重要的细节
            //1.如果我们需要使用Dpg方法，需要向下转型
            //2.需要我们将Dog类的定义，拷贝到可以引用的位置
            Dog dog2 = (Dog) dog;
            System.out.println(dog2.getName());  //小黄
            System.out.println("数据读取完毕(反序列号完成)");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


