package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.EmployeeDao;
import com.goit.gojavaonline.spring.mvc.dto.EmployeeDto;
import com.goit.gojavaonline.spring.mvc.model.Employee;
import com.goit.gojavaonline.spring.mvc.web.AuthenticationRequired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private EmployeeDao employeeDao;

    @AuthenticationRequired
    public EmployeeDto create(EmployeeDto employee) {
        Employee employeeToCreate = EmployeeDto.convert( employee );
        employeeToCreate.setId( null );
        return saveAndLoad(employeeToCreate);
    }

    @Transactional
    private EmployeeDto saveAndLoad(Employee newEmployee) {
        employeeDao.save( newEmployee );
        Employee loadedEmployee = employeeDao.load( newEmployee.getId() );
        return EmployeeDto.convert( loadedEmployee );
    }

    @Transactional
    public List<EmployeeDto> getEmployees() {
        return convert(employeeDao.findAll());
    }

    @Transactional
    public EmployeeDto getEmployeeName(String employeeName) {
        return EmployeeDto.convert(employeeDao.findByName(employeeName));
    }

    @Transactional
    public List<EmployeeDto> getAllWaiters() {
        return convert(employeeDao.getAllWaiters());
    }

    private List<EmployeeDto> convert(List<Employee> employees) {
        return employees.stream().map(EmployeeDto::convert).collect(Collectors.toList());
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
