package com.ait.task;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ev.dao.RegularlyEvMail;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2015-1-14   上午08:16:58      
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyEvMailTaskProcessor {
	
private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private RegularlyEvMail regularlyEvMail = new RegularlyEvMail();
	
	public void process() {	
	    	
		
		try {
			SimpleDateFormat pOutformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String nowDate=pOutformatter.format(new Date());	
			logger.info("定时邮件提醒工人评价未完成评价/决裁时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
			System.out.println("当前时间工人评价："+nowDate.substring(8, 10)+"号,"+nowDate.substring(11, 13)+"点。");
		    //if(nowDate.substring(8, 10).equals("21")&&nowDate.substring(11, 13).equals("02"))
			
			// 原版
//			if(Integer.parseInt(nowDate.substring(8, 10))>=11 && Integer.parseInt(nowDate.substring(8, 10))<=21 && (nowDate.substring(11, 13).equals("07") || nowDate.substring(11, 13).equals("12")))
				if(Integer.parseInt(nowDate.substring(8, 10))>=22 && (nowDate.substring(11, 13).equals("07") || nowDate.substring(11, 13).equals("12")))
		    {	//每月11日~21日按照2封/日的频度发送（07:00&13:00定时发送） 邮件提醒工人评价未完成评价/决裁(已保存未决裁的)
		    	logger.info("定时邮件提醒工人评价未完成评价/决裁时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
		    	regularlyEvMail.RegularlyEvMail();
		    	
		    	System.out.println("工人发送邮件今天");
		    }
			
		} catch (Exception e) {
			
			logger.error("regularlyEv Error: " + e);
			throw new GlRuntimeException("regularlyEv error.", e);
		}
		
	
	}
	
}
