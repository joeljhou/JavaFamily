package com.mayikt.threads;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author zhouyu
 * @create 2021-05-30 2:04
 * 桌面应用程序的开发 C#
 */
public class CsMain {
    public static void main(String[] args) {
        //确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        //创建及设置窗口
        JFrame jFrame = new JFrame("每特教育");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加“Hello World”标签
        JLabel jLabel = new JLabel("666");
        jFrame.getContentPane().add(jLabel);

        //显示窗口
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
