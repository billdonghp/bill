package com.multiseafoods.msapp.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Salesorder {
    private Long id;

    private String material;

    private Integer qty;

    private Date createdat;

    private String weight;

    private Float carryfee;

    private Float total;

    private Float price;

    private Float amount;

    private Integer status;

    private String remark;

}