/*
 * @(#)SimpleMapProtocol.java 1.0 2006-12-4
 *
 */
package com.ait.sqlmap.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2006-12-5 下午03:46:51
 * @version 1.0
 * 
 */
public class SimpleMapProtocol extends LinkedHashMap {

	protected String name = null;

	protected boolean nullToInitialize = false;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SimpleMapProtocol(int arg0, float arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public SimpleMapProtocol(int arg0) {
		super(arg0);
	}

	/**
	 * 
	 */
	public SimpleMapProtocol() {
		super();
	}

	/**
	 * @param arg0
	 */
	public SimpleMapProtocol(Map arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public SimpleMapProtocol(int arg0, float arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	/**
	 * Returns the name.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isNullToInitialize() {
		return nullToInitialize;
	}

	/**
	 * @param nullToInitialize
	 *            The nullToInitialize to set
	 */
	public void setNullToInitialize(boolean nullToInitialize) {
		this.nullToInitialize = nullToInitialize;
	}

}
