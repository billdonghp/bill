/*
 * @(#)SendMailTask.java 1.0 2007-7-5 下午08:38:49
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.department;

import java.util.TimerTask;

import org.apache.log4j.Logger;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-7-5 下午08:38:49
 * @version 1.0
 * 
 */
public class DeptHistoryTask extends TimerTask {

	private static final Logger logger = Logger.getLogger(DeptHistoryTask.class);

	private DeptHistoryProcessor processor = new DeptHistoryProcessor();

	public void run() {

		processor.process();
	}

}
