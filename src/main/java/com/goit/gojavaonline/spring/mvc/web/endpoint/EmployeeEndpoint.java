package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.EmployeeDto;
import com.goit.gojavaonline.spring.mvc.model.Employee;
import com.goit.gojavaonline.spring.mvc.model.Position;
import com.goit.gojavaonline.spring.mvc.service.EmployeeService;
import com.goit.gojavaonline.spring.mvc.utils.UserIsNotAuthenticatedException;
import com.goit.gojavaonline.spring.mvc.web.SecureController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tamila on 9/23/16.
 */
@RestController
public class EmployeeEndpoint extends SecureController {

    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employee) throws UserIsNotAuthenticatedException {
        return employeeService.create(employee);
    }

    @RequestMapping( value = "/employee", method = RequestMethod.GET)
    public List<EmployeeDto> employees() {
        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/employee/{employeeName}", method = RequestMethod.GET)
    public EmployeeDto employee(@RequestBody EmployeeDto employee) {
        return employeeService.getEmployeeName(employee.getName());
    }

    @RequestMapping(value = "/employee/update/{employeeId}/{employeename}/{lastname}/{birthdate}/{phone}/{employeeposition}/{salary}", method = RequestMethod.PUT)
    public EmployeeDto employee(@PathVariable(value = "employeeId") String employeeId,
                                @PathVariable(value = "employeename") String employeename,
                                @PathVariable(value = "lastname") String lastname,
                                @PathVariable(value = "birthdate") String birthdate,
                                @PathVariable(value = "phone") String phone,
                                @PathVariable(value = "employeeposition") String employeeposition,
                                @PathVariable(value = "salary") String salary) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        Employee employee = new Employee();
        employee.setId(Long.valueOf(employeeId));
        employee.setName(employeename);
        employee.setLastName(lastname);
        try {
            employee.setBirthDate(formatter.parse(birthdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setPhone(phone);
        employee.setPosition(Position.valueOf(employeeposition));
        employee.setSalary(Float.valueOf(salary));

        return employeeService.updateEmployee(employee);
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
