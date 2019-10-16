package cn.multiseafoods.crm.services;

import cn.multiseafoods.crm.entity.Employee;
import cn.multiseafoods.crm.enums.ResultEnum;
import cn.multiseafoods.crm.exception.EmployeeException;
import cn.multiseafoods.crm.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public void get(Integer id) throws Exception{
        Employee em =  repository.findById(id).orElse(null);
        Integer age = em.getAge();
        if(age <= 10){
            throw new EmployeeException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 18) {
            throw new EmployeeException(ResultEnum.MIDDLE_SCHOOL);
        }
    }
    public Employee save(Employee employee){
        return repository.save(employee);
    }
}
