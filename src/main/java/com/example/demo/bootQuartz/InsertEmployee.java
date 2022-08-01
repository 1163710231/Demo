package com.example.demo.bootQuartz;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class InsertEmployee extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(InsertEmployee.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("============InsertEmployee===============");
        Employee employee = new Employee("name", "email", 1, 101, new Date());
        int insertNumber = employeeMapper.insertEmployee(employee);
        System.out.println("成功向 employee 插入了 " + insertNumber + " 条数据!");

        System.out.println(new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("醒了! 成功向 employee 插入了 " + insertNumber + " 条数据!");
    }
}
