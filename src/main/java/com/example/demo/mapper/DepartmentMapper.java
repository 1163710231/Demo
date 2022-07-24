package com.example.demo.mapper;

import com.example.demo.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 这个注解表示这是一个 mybatis 的 mapper 类
@Repository  // 被 spring 整合
public interface DepartmentMapper {
    List<Department> selectAllDepartments();

    Department selectDepartmentById(int id);
}
