package com.ait.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.dao.RegularlySentMail;
import com.ait.sqlmap.util.SimpleMap;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         skulls(yangfan9336@gmail.com) 
 *   @create-time   2009-11-30   上午11:27:58   
 *   @revision      1.0
 ***********************************************************************/
public class RegularlySentMailProcessor {
	
private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private RegularlySentMail regularlySentMail = new RegularlySentMail();
	
	public void process() {	
	    	
		
		try {
			SimpleDateFormat pOutformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String nowDate=pOutformatter.format(new Date());	
			logger.info("定时发送邮件启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
		    if(nowDate.substring(11, 13).equals("07"))
		    {	//每天早晨7点执行	
		    	logger.info("定时发送邮件发送时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
			  regularlySentMail.regularlySentMail();
		    }
			
		} catch (Exception e) {
			
			logger.error("regularlySentMail Error: " + e);
			throw new GlRuntimeException("regularlySentMail error.", e);
		}
	
	}
	

}
