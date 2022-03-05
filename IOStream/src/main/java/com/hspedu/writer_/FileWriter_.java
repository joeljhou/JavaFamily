package com.hspedu.writer_;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 周宇
 * @create 2022-02-06 19:20
 */
public class FileWriter_ {
    /**
     * 将“风雨之后，定见彩虹”写入到note.txt文件
     */
    @Test
    public void writerFile() {
        String filePath = "src\\main\\java\\com\\xhgj\\filewriter_\\note.txt";
        char[] chars = {'a', 'b', 'c'};
        FileWriter fileWriter = null;
        try {
            //1.创建FileWriter对象
            fileWriter = new FileWriter(filePath, true);   //默认是覆盖写入
            //2.写入缓冲区
            //(2.1)write(int):写入单个字符
            fileWriter.write('H');
            //(2.2)write(char[]):写入指定数组
            fileWriter.write(chars);
            //(2.3)write(char[],off,len)
            fileWriter.write("FileWrite".toCharArray(), 0, 4);
            //(2.4)write(string)
            fileWriter.write("你好，北京冬运会");
            //(2.5)write(string,off,len)
            fileWriter.write("2022北京冬运会举行了~~~", 9, 6);
            //3.冲洗流。将保存在缓冲区中的数据立即写入其预期目的地
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    //3.关闭文件流释放资源 关闭会执行flush操作
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
