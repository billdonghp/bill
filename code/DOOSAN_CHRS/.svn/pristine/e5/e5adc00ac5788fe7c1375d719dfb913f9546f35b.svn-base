/*
 * @(#)SparateDataTask.java 1.0 2009-7-7 下午06:31:38
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-7 下午06:31:38
 * @version 1.0
 * 
 */
public class SparateDataTask extends TimerTask {

	private static final Logger logger = Logger.getLogger(SparateDataTask.class);

	private SparateDataProcessor processor = new SparateDataProcessor();

	public void run() {

		processor.process();
	}
}

