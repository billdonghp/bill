package cn.multiseafoods.crm.controller;

import cn.multiseafoods.crm.entity.Employee;
import cn.multiseafoods.crm.entity.Result;
import cn.multiseafoods.crm.repository.EmployeeRepository;
import cn.multiseafoods.crm.services.EmployeeService;
import cn.multiseafoods.crm.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Api(tags ="雇员信息")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/insert")
    public Result<Employee> insertEmployee(@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        employee.setName(employee.getName());
        employee.setAge(employee.getAge());
        employee.setSex(employee.getSex());
        return ResultUtils.success(service.save(employee));
    }

    @PostMapping("/get/{id}")
    public void get(@PathVariable("id") Integer id) throws Exception{
        service.get(id);
    }

}
