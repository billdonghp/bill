/*
 * @(#)SparateDataProcessor.java 1.0 2009-7-7 下午06:22:23
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.task;

import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-7 下午06:22:23
 * @version 1.0
 * 
 */
public class SparateDataProcessor {

	private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private ArServices service = new ArServices();
	
	public void process() {
		
		SimpleMap map = new SimpleMap();
		
		try {
			
			// sparate detail data
			service.sparateDailyAttenance(map);
			// sparate card record
			service.sparateCardRecord(map);
			
			// sparate eatery card record
			service.sparateEateryCardRecord(map);
			
			// delete detail operation data
			service.deleteDetailOperation(map);
			
			// DICC,DISD hr_employee,sy_admin insert to DICI operation data 
			service.sparateEmpAttenance(map);
			
		} catch (Exception e) {
			
			logger.error("Sparate data Error: " + e);
			throw new GlRuntimeException("Sparate data error.", e);
		}
		
	}
}

