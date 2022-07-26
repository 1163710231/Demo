package com.example.demo;

import com.example.demo.quartz.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Random;

public class JobTest {
    public static void main(String[] args) {
        int testNumber = new Random().nextInt(3 - 1 + 1) + 1;
        JobTest jobTest = new JobTest();
        try {
            switch (testNumber) {
                case 1:
                    jobTest.jobTest1();
                    break;
                case 2:
                    jobTest.jobTest2();
                    break;
                default:
                    jobTest.jobTest3();
                    break;
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private void jobTest1() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job1", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    private void jobTest2() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job2", "group1")
                .usingJobData("job", "jobDetail")
                .usingJobData("name", "jobDetail-Name")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "triggerGroup1")
                .usingJobData("triggerKey", "triggerValue")
                .usingJobData("name", "triggerValue-Name")  // Trigger 会覆盖 JobDetail 中定义的 name 值
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    private void jobTest3() throws SchedulerException {
        int count = 0;
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("job2", "group1")
                .usingJobData("job", "jobDetail")
                .usingJobData("name", "jobDetail-Name")
                .usingJobData("countJob", 0)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "triggerGroup1")
                .usingJobData("triggerKey", "triggerValue")
                .usingJobData("name", "triggerValue-Name")  // Trigger 会覆盖 JobDetail 中定义的 name 值
                .usingJobData("count", count)
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
