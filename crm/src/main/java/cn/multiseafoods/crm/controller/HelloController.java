package cn.multiseafoods.crm.controller;

import cn.multiseafoods.crm.properties.LimitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {
   /* @Value("${minMoney}")
    private BigDecimal minMoney;

    @Value(("${description}"))
    private String description;*/

    @Autowired
    private LimitProperties limitProperties;
    @GetMapping(value="/say")
   // @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String  say(){
        return "说明：" + limitProperties.getDescription();
    }

    @PostMapping("/dopost")
    public String doPost(){
        return "dopost";
    }

    /**
     * @RequestMapping 如果未指定method=RequestMethod.GET 则都支持
     * @return
     */
    @RequestMapping("all")
    public String all(){
        return "Get And Post";
    }

    @GetMapping("/doPath/{id}")
    public String doPath(@PathVariable("id") Integer id){
        return "ID: " + id;
    }

    @RequestMapping("/doreq")
    public String doreq(@RequestParam("id") Integer id){
        return  "ID: " + id;
    }

    @RequestMapping("/doreqdef")
    public String doreqdef(@RequestParam(value="id",required = false, defaultValue = "0") Integer id){
        return  "ID: " + id;
    }

}
