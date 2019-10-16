package com.ait.task;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ev.dao.RegularlyEvMail;
import com.ait.ev.dao.RegularlyEvMydMail;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2015-1-14   上午08:16:58      
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyEvMydMailTaskProcessor {
	
private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private RegularlyEvMydMail regularlyEvMail = new RegularlyEvMydMail();
	
	public void process() {	
	    	
		
		try {
			SimpleDateFormat pOutformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String nowDate=pOutformatter.format(new Date());	
			logger.info("定时邮件提醒参观者申请满意度调查录入时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
			System.out.println("当前时间满意度："+nowDate.substring(8, 10)+"号,"+nowDate.substring(11, 13)+"点。");
		    //if(nowDate.substring(8, 10).equals("21")&&nowDate.substring(11, 13).equals("02"))
			
			// 原版
//			if(Integer.parseInt(nowDate.substring(8, 10))>=11 && Integer.parseInt(nowDate.substring(8, 10))<=21 && (nowDate.substring(11, 13).equals("07") || nowDate.substring(11, 13).equals("12")))
				if( nowDate.substring(11, 13).equals("07") || nowDate.substring(11, 13).equals("14"))
		    {	//按照2封/日的频度发送（07:00&13:00定时发送）
		    	logger.info("定时邮件提醒参观者申请满意度调查录入时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
		    	regularlyEvMail.RegularlyEvMydMail();
		    	
		    	System.out.println("满意度发送邮件今天");
		    }
			
		} catch (Exception e) {
			
			logger.error("regularlyEv Error: " + e);
			throw new GlRuntimeException("regularlyEv error.", e);
		}
		
	
	}
	
}
