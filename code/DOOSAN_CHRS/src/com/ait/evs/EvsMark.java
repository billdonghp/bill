/*
 * @(#)EvsMarks.java 1.0 2007-1-17 下午12:03:23
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.evs;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author aqing (eqing@ait.net.cn)
 * @Date 2007-1-17 下午12:03:23
 * @version 1.0
 * 
 */
public class EvsMark {
    private String value;

    private String name;

    private boolean isDefaultValue;

    private int empCnt;

    public boolean isDefaultValue() {
	return isDefaultValue;
    }

    public void setDefaultValue(boolean isDefaultValue) {
	this.isDefaultValue = isDefaultValue;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public int getEmpCnt() {
	return empCnt;
    }

    public void setEmpCnt(int empCnt) {
	this.empCnt = empCnt;
    }

}
