package com.example.demo.controller;

import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/selectAllEmployees")
    @ResponseBody
    public List<Employee> selectAllEmployees() {
        return employeeMapper.selectAllEmployees();
    }

    @RequestMapping("/employees")
    public String list(Model model) {
        List<Employee> employees = employeeMapper.selectAllEmployees();
        Map<Integer, Department> employeeDepartmentMap = new HashMap<>();
        for (Employee employee : employees) {
            int departmentId = employee.getDepartmentId();
            Department department = departmentMapper.selectDepartmentById(departmentId);
            employeeDepartmentMap.put(employee.getId(), department);
        }
        model.addAttribute("employees", employees);
        model.addAttribute("employeeDepartmentMap", employeeDepartmentMap);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentMapper.selectAllDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee employee) {
        employee.setDepartmentId(employee.getDepartmentId());
        int insertNumber = employeeMapper.insertEmployee(employee);
        System.out.println("成功向 employee 插入了 " + insertNumber + " 条数据!");
        return "redirect:/employees";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeMapper.selectEmployeeById(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentMapper.selectAllDepartments();
        model.addAttribute("departments", departments);

        return "/emp/update";
    }

    @RequestMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        int updateNumber = employeeMapper.updateEmployee(employee);
        System.out.println("成功修改了 employee 中的 " + updateNumber + " 条数据!");
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        int deleteNumber = employeeMapper.deleteEmployeeById(id);
        System.out.println("成功删除了 employee 中的 " + deleteNumber + " 条数据!");
        return "redirect:/employees";
    }
}
