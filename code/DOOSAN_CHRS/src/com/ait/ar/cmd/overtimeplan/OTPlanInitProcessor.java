/*
 * @(#)SendMailProcessor.java 1.0 2007-9-29 
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ar.cmd.overtimeplan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.sqlmap.util.SimpleMap;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (wangliwei@ait.net.cn)
 * @Date 2007-9-29                  
 * @version 1.0  
 *                    
 */
public class OTPlanInitProcessor {

	private static final Logger logger = Logger.getLogger(OTPlanInitProcessor.class);

	ArServices aRservices = new ArServices() ;
	
	public OTPlanInitProcessor() {
		
	}

	public void process() {
	
		SimpleMap parameterObject = new SimpleMap() ;
		
		GregorianCalendar now = new GregorianCalendar();
		
		int day = now.get(Calendar.DATE) ;
		int hour = now.get(Calendar.HOUR_OF_DAY) ;
		logger.debug("加班上限启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime())) ;
	    if(day == 25 && hour < 7 && hour > 0){
	    	
	    	int result = aRservices.validateInitOTPlan(parameterObject) ;
	    	
	    	if (result == 0)
	    	{
	    		logger.debug("加班上限初始化时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime())) ;
		    	aRservices.initOTPlan(parameterObject) ;
	    	}
	   }  
            
   }
}