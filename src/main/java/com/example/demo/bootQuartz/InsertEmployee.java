package com.example.demo.bootQuartz;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Employee;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class InsertEmployee extends QuartzJobBean {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Employee employee = new Employee("name", "email", 1, 1, new Date());
        int insertNumber = employeeMapper.insertEmployee(employee);
        System.out.println("成功向 employee 插入了 " + insertNumber + " 条数据!");
    }
}
