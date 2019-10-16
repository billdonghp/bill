package com.ait.task;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ev.dao.RegularlyEv;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2014-8-1   下午15:27:58     
 *   @revision      1.0
 ***********************************************************************/
public class RegularlyEvTaskProcessor {
	
private static final Logger logger = Logger.getLogger(SparateDataProcessor.class);
	
	private RegularlyEv regularlyEv= new RegularlyEv();
	
	public void process() {	
	    	
		
		try {
			SimpleDateFormat pOutformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String nowDate=pOutformatter.format(new Date());	
			logger.info("定时更新评价系统时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
			System.out.println("当前时间更新评价："+nowDate.substring(8, 10)+"号,"+nowDate.substring(11, 13)+"点。");
		   //原版   
			if(nowDate.substring(8, 10).equals("20")&&nowDate.substring(11, 13).equals("23"))
//		    	if(nowDate.substring(8, 10).equals("22")&&nowDate.substring(11, 13).equals("02"))
		    {	//每月20日23:00AM更新评价系统	 
		    	logger.info("定时更新评价系统时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())) ;
			  regularlyEv.regularlyEv();
		    }
			
		} catch (Exception e) {
			
			logger.error("regularlyEv Error: " + e);
			throw new GlRuntimeException("regularlyEv error.", e);
		}
		
	
	}
	
}
