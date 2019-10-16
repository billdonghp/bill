package com.multiseafoods.msapp.entity;

import lombok.Data;

@Data
public class Purchase {
    private Long id;

    private String no;

    private String material;

    private Integer weight;

    private Integer totalweight;

    private Integer qty;

    private Float price;

    private String remark;

}