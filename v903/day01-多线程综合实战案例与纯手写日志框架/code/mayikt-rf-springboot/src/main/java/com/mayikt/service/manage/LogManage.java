package com.mayikt.service.manage;

import com.mayikt.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 周宇
 * @create 2021-06-21 13:25
 */
@Component
public class LogManage {
    /**
     * 缓存日志内容
     */
    private static BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<String>();
    private static String filePath = "D:\\Documents\\log\\mayikt.log";

    public LogManage() {
        new LogThread().start();
    }

    public static  void addLog(String msg) {
        //将日志缓存起来
        blockingQueue.offer(msg);
    }

    //单独的线程写入日志
    class LogThread extends Thread {
        @Override
        public void run() {
            while (true) {
                String log = blockingQueue.poll();
                if (!StringUtils.isEmpty(log)) {
                    // 将该log写入到磁盘中
                    FileUtils.writeText(filePath, log, true);
                }
            }
        }
    }

}
