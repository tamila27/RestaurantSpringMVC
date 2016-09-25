package com.goit.gojavaonline.spring.mvc.web.endpoint;

import com.goit.gojavaonline.spring.mvc.dto.OrderDto;
import com.goit.gojavaonline.spring.mvc.service.EmployeeService;
import com.goit.gojavaonline.spring.mvc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tamila on 9/25/16.
 */
@RestController
public class OrderEndPoint {
    private OrdersService ordersService;
    private EmployeeService employeeService;

    @RequestMapping(value = "/order/table/{tableNum}", method = RequestMethod.GET)
    public List<OrderDto> getByTableNum(@PathVariable(value = "tableNum") String tableNum) {
           return ordersService.getByTable(Integer.valueOf(tableNum));
    }

    @RequestMapping(value = "/order/waiter/{waiterName}", method = RequestMethod.GET)
    public List<OrderDto> getByWaiter(@PathVariable(value = "waiterName") String waiterName) {
        return ordersService.getByWaiter(employeeService.getEmployeeName(waiterName).getId());
    }

    @RequestMapping(value = "/order/date/{date}", method = RequestMethod.GET)
    public List<OrderDto> getByDate(@PathVariable(value = "date") String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        return ordersService.getByDate(date);
    }

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
