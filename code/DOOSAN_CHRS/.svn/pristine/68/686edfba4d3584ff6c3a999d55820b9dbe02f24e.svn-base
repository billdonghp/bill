package com.ait.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.dao.RegularlyArAbnormalSentMail;
import com.ait.ess.dao.RegularlyArModifySentMail;
import com.ait.ess.dao.RegularlySentMail;
import com.ait.ess.dao.RegularlyOtSentMail;
import com.ait.sqlmap.util.SimpleMap;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (zhangji@ait.net.cn) 
 *   @create-time   2013-5-15   上午11:27:58   
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyArAbnormalSentMailTaskProcessor {
	
private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private RegularlyArAbnormalSentMail regularlyArAbnormalSentMail= new RegularlyArAbnormalSentMail();
	
	public void process() {	
	    	
		
		try {
			SimpleDateFormat pOutformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String nowDate=pOutformatter.format(new Date());	
			logger.info("定时发送考勤异常情况提醒邮件启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
		    if(nowDate.substring(11, 13).equals("15"))
		    {	//每天上午10点执行	
		    	logger.info("定时发送考勤异常情况提醒邮件启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
		    	regularlyArAbnormalSentMail.regularlyArAbnormalSentMail();
		    }
			
		} catch (Exception e) {
			
			logger.error("regularlyArModifySentMail Error: " + e);
			throw new GlRuntimeException("regularlyArModifySentMail error.", e);
		}
	
	}
	

}
