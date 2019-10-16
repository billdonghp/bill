package cn.multiseafoods.crm.services;

import cn.multiseafoods.crm.entity.Salesorder;
import cn.multiseafoods.crm.repository.SalesorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
@Component
public class SalesorderServices {

    @Autowired
    private SalesorderRepository repository;

    @Transactional
    public void twoSales(){
        Salesorder s1 = new Salesorder();
        s1.setCustomerId(2);

        repository.save(s1);

        Salesorder s2 = new Salesorder();

        s2.setCustomerId(1);
        s2.setTaxPrice(new BigDecimal(1000));
        repository.save(s2);

    }
}
