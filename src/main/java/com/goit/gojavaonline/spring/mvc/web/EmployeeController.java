package com.goit.gojavaonline.spring.mvc.web;

import com.goit.gojavaonline.spring.mvc.model.Position;
import com.goit.gojavaonline.spring.mvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
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



    @RequestMapping( value = "/adminemployee", method = RequestMethod.GET)
    public ModelAndView adminDish() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", employeeService.getAllEmployee());
        modelAndView.addObject("positions", Position.values());
        modelAndView.addObject("active", "employee");
        modelAndView.setViewName("adminEmployee");
        return modelAndView;
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
