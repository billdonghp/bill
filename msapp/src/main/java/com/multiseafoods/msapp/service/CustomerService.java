package com.multiseafoods.msapp.service;

import com.multiseafoods.msapp.entity.Customer;
import com.multiseafoods.msapp.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    public Customer get(Long id){
        return customerMapper.selectByPrimaryKey(id);
    }
}
