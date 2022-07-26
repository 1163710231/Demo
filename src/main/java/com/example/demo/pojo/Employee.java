package com.example.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;

//    private Department department;
    private Integer departmentId;
    private Date birth;

    public Employee(Integer id, String lastName, String email, Integer gender, Integer departmentId, Date birth) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentId = departmentId;
        this.birth = birth;
    }

    public Employee(String lastName, String email, Integer gender, Integer departmentId, Date birth) {
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.departmentId = departmentId;
        this.birth = birth;
    }
}
