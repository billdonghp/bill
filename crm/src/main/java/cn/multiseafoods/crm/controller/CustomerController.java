package cn.multiseafoods.crm.controller;

import cn.multiseafoods.crm.entity.Customer;
import cn.multiseafoods.crm.repository.CustomerRepository;
import cn.multiseafoods.crm.properties.LimitProperties;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Api(tags ="客户信息")
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private LimitProperties limitProperties;

    @Autowired
    private CustomerRepository repository;

   /* @Autowired
    private Customer customer;*/

    /**
     * @Controller + @ResponseBody  == @RestController
     */
    @GetMapping("/login")
    @ResponseBody
    public String login(){
        return "Description:" + limitProperties.getDescription();
    }

    /**
     * 使用模版渲染
     */
    @GetMapping(value="/logout")
    public String logout(){
        return "index";
    }

    @GetMapping("/findall")
    @ResponseBody
    public List<Customer> findAll(){
        logger.info("findall");

        return repository.findAll();
    }
    @GetMapping("/add")
    @ResponseBody
    public Customer addCustomer(Customer customer){
        customer.setMinMoney(customer.getMinMoney());
        customer.setMaxMoney(customer.getMaxMoney());
        customer.setProducer(customer.getProducer());
        return repository.save(customer);
    }

    @GetMapping("/insert")
    @ResponseBody
    public Customer insertCustomer(@RequestParam("minMoney") BigDecimal minMoney,
                                   @RequestParam("maxMoney") BigDecimal maxMoney,
                                   @RequestParam("producer") String producer){
        Customer customer = new Customer();
        customer.setMinMoney(minMoney);
        customer.setMaxMoney(maxMoney);
        customer.setProducer(producer);

        return repository.save(customer);
    }
    /**
     * 获取Customer
     * @param id
     * @return
     */

    @GetMapping("/findById/{id}")
    @ResponseBody
    public Customer findById(@PathVariable("id") Integer id){
        return repository.findById(id).orElse(null);
    }

    /**
     * 更新Customer
     * @param id
     * @param producer
     * @return
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public Customer update(@PathVariable("id") Integer id,
                           @RequestParam("producer") String producer){
        Optional<Customer> optional = repository.findById(id);
        if(optional.isPresent()){
            Customer customer = optional.get();
            customer.setProducer(producer);
            return repository.save(customer);
        }
        return null;
    }
}
