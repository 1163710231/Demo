package com.example.demo.mapper;

import com.example.demo.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 这个注解表示这是一个 mybatis 的 mapper 类
@Repository  // 被 spring 整合
public interface EmployeeMapper {
    List<Employee> selectAllEmployees();

    Employee selectEmployeeById(int id);

    int insertEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployeeById(int id);
}
