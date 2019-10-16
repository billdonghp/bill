/*
 * @(#)SendMailProcessor.java 1.0 2007-9-29 
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.hrm.department;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.hrm.business.HrmServices;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author kellywang (liumeng@ait.net.cn)
 * @Date 2007-9-29                  
 * @version 1.0  
 *                    
 */
public class DeptHistoryProcessor {

	private static final Logger logger = Logger.getLogger(DeptHistoryProcessor.class);

	HrmServices services = HrmServices.getInstance();
	ArServices aRservices = new ArServices() ;
	
	public DeptHistoryProcessor() {
		
	}

	public void process() {
	
		GregorianCalendar now = new GregorianCalendar();

		
		GregorianCalendar g=new GregorianCalendar(); 
		
		int day=g.get(Calendar.DATE);
	    Integer yearstr=new Integer(g.get(Calendar.YEAR));
	    Integer monthstr=new Integer(g.get(Calendar.MONTH)+1);
	    String str="";
	    if(monthstr<10&&monthstr>=1)
	    	str=yearstr.toString()+"0"+monthstr.toString();
	    else
	    	str=yearstr.toString()+monthstr.toString();  
	    
	    if(day==1){	    
	    	if(!services.isExistHistoryData(str)){
	    		services.changeDepartmentByMonth();
	    	}
	    	 
	    	//暂时不启动年假生成逻辑
	    	//aRservices.initVacationEmp() ;
	   }  
            
   }
}