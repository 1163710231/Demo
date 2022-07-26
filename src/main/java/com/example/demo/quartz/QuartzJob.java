package com.example.demo.quartz;

import org.quartz.*;

import java.util.Date;

@DisallowConcurrentExecution  // 禁止并发执行同一个 JobDetail 定义的多个实例，必须一个接一个的执行
@PersistJobDataAfterExecution  // 将 JobDetail 中的 JobDataMap 持久化（对 Trigger 中的 DataMap 无效），让从而使得多个实例共用一个 JobDataMap
public class QuartzJob implements Job {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartzJob execute at " + new Date());
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        JobDataMap mergedDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("jobDataMap: " + jobDataMap.getString("job"));
        System.out.println("triggerDataMap: " + triggerDataMap.getString("triggerKey"));
        System.out.println("mergedDataMap: " + mergedDataMap.getString("triggerKey"));

        System.out.println("name: " + name);

        // Scheduler 每次执行都会根据 JobDetail 创建一个新的 Job 实例，从而解决并发访问的问题
        System.out.println("jobDetail: " + System.identityHashCode(jobExecutionContext.getJobDetail()));
        System.out.println("job: " + System.identityHashCode(jobExecutionContext.getJobInstance()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        triggerDataMap.put("count", triggerDataMap.getInt("count") + 1);
        jobDataMap.put("countJob", jobDataMap.getInt("countJob") + 1);
        System.out.println("triggerDataMap count: " + triggerDataMap.getInt("count"));
        System.out.println("jobDataMap count: " + jobDataMap.getInt("countJob"));
    }
}
