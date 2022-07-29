package com.example.demo.config;

import com.example.demo.bootQuartz.InsertEmployee;
import com.example.demo.bootQuartz.QuartzJob1;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1(){
        return JobBuilder.newJob(QuartzJob1.class).storeDurably().build();
    }

    @Bean
    public JobDetail insertEmployeeJobDetail() {
        return JobBuilder.newJob(InsertEmployee.class).storeDurably().build();
    }

//    @Bean
    public Trigger trigger1(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5) // 每一秒执行一次
                .repeatForever(); // 永久重复，一直执行下去
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail1())
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger insertEmployeeTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(insertEmployeeJobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }
}

