package com.goit.gojavaonline.spring.mvc.dto;

import com.goit.gojavaonline.spring.mvc.model.Employee;
import com.goit.gojavaonline.spring.mvc.model.Position;
import com.goit.gojavaonline.spring.mvc.utils.DateFormatUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDto {
    private Long id;
    private String lastName;
    private String name;
    private String birthDate;
    private String phone;
    private Position position;
    private float salary;

    public static EmployeeDto convert(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setBirthDate(DateFormatUtils.fromDate( employee.getBirthDate() ));
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }

    public static Employee convert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setLastName(employeeDto.getLastName());
        employee.setBirthDate(DateFormatUtils.toDate(employeeDto.getBirthDate()));
        employee.setPhone(employeeDto.getPhone());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }

    public static List<EmployeeDto> convert(List<Employee> employees) {
        return employees.stream().map(EmployeeDto::convert).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
