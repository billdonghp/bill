package com.multiseafoods.msapp.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Oem {
    private Long id;

    private String no;

    private String material;

    private Integer weight;

    private Integer qty;

    private Integer finweight;

    private Integer finqty;

    private Integer status;

    private Float oemfee;

    private Integer resqty;

    private Date createdat;

    private String remark;

}