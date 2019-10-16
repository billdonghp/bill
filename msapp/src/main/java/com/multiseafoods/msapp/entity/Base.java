package com.multiseafoods.msapp.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Base {
    private Integer id;

    private String name;

    private String objecttype;

    private Date createdat;

    private Date updatedat;


}