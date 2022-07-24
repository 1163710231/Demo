package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    // "30 0/5 10,18 * * ?"  每天 10 点和 18 点，每隔 5 分钟执行一次
    // "0 15 10 ? * 1-6"  每隔月的周一到周六的 10 点 15 分执行一次
    @Scheduled(cron = "10 28 5 * * ?")  // 秒 分 时 日 月 周几
    public void hello() {
        System.out.println("hello 被执行了！");
    }
}
