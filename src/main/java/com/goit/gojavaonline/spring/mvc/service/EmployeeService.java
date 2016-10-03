package com.goit.gojavaonline.spring.mvc.service;

import com.goit.gojavaonline.spring.mvc.dao.EmployeeDao;
import com.goit.gojavaonline.spring.mvc.dto.EmployeeDto;
import com.goit.gojavaonline.spring.mvc.model.Employee;
import com.goit.gojavaonline.spring.mvc.web.AuthenticationRequired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao;

    @AuthenticationRequired
    public EmployeeDto create(EmployeeDto employee) {
        Employee employeeToCreate = EmployeeDto.convert( employee );
        employeeToCreate.setId( null );
        return saveAndLoad(employeeToCreate);
    }

    @AuthenticationRequired
    @Transactional
    private EmployeeDto saveAndLoad(Employee newEmployee) {
        employeeDao.save( newEmployee );
        Employee loadedEmployee = employeeDao.load( newEmployee.getId() );
        return EmployeeDto.convert( loadedEmployee );
    }

    @Transactional
    public List<EmployeeDto> getEmployees() {
        return EmployeeDto.convert(employeeDao.findAll());
    }

    @Transactional
    public EmployeeDto getEmployeeName(String employeeName) {
        return EmployeeDto.convert(employeeDao.findByName(employeeName));
    }

    @Transactional
    public List<EmployeeDto> getAllWaiters() {
        return EmployeeDto.convert(employeeDao.getAllWaiters());
    }

    @Transactional
    public List<EmployeeDto> getAllEmployee() {
        return EmployeeDto.convert(employeeDao.findAll());
    }

    @AuthenticationRequired
    @Transactional
    public EmployeeDto updateEmployee(Employee employee) {

        employeeDao.update(employee);

        return EmployeeDto.convert(employee);

    }


    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
