package com.mayikt.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 周宇
 * @create 2021-07-14 22:27
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron = "0/2 * * * * *") 写法：
    //https://www.bejson.com/othertools/cron/

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("<<定时任务执行>> 现在时间：" + dateFormat.format(new Date()));
    }

    //Spring自带的定时任务不支持集群
    //大型项目通常使用分布式定时任务框架，例如：Xxl-job，Elastic-job（基于quartz 二次开发之后的分布式调度解决方案）
}
