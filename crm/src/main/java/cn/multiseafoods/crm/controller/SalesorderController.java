package cn.multiseafoods.crm.controller;

import cn.multiseafoods.crm.services.SalesorderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesorderController {

    @Autowired
    private SalesorderServices ss;

    @GetMapping("ctwo")
    public void twoSaleorder(){
        ss.twoSales();

    }

}
