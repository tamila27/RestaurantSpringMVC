package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.dto.EmployeeDto;
import com.goit.gojavaonline.spring.mvc.service.EmployeeService;
import com.goit.gojavaonline.spring.mvc.utils.UserIsNotAuthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeController extends SecureController {

    private EmployeeService employeeService;

    /*@RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView employee(@RequestParam("employeeName") String employeeName) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", employeeService.getEmployeeName(employeeName));
        modelAndView.setViewName("employee");
        return modelAndView;
    }*/

//    @RequestMapping(value = "/employees", method = RequestMethod.GET)
//    public String employees(Map<String, Object> model) {
//        model.put("employees", employeeService.getEmployees());
//        return "employees";
//    }

//    @RequestMapping(value = "/employee/{employeeName}", method = RequestMethod.GET)
//    public ModelAndView employee(@PathVariable String employeeName) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("employee", employeeService.getEmployeeName(employeeName));
//        modelAndView.setViewName("employee");
//        return modelAndView;
//    }

 /*   @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public Employee employee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }*/

    @RequestMapping(value = "/employee", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public EmployeeDto createEmployee( @RequestBody EmployeeDto employee) throws UserIsNotAuthenticatedException {
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

    @RequestMapping(value = "/waiters", method = RequestMethod.GET)
    public ModelAndView employee() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("waiters", employeeService.getAllWaiters());
        modelAndView.addObject("active", "waiters");
        modelAndView.setViewName("waiters");
        return modelAndView;
    }

    /*@RequestMapping(value = "/waiter", method = RequestMethod.GET)
    public List<EmployeeDto> waiters() {
        return employeeService.getAllWaiters();
    }*/

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
