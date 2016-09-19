package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.EmployeeDao;
import com.goit.gojavaonline.spring.mvc.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @Transactional
    public Employee getEmployeeName(String employeeName) {
        return employeeDao.findByName(employeeName);
    }

    @Transactional
    public List<Employee> getAllWaiters() {
        return employeeDao.getAllWaiters();
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
