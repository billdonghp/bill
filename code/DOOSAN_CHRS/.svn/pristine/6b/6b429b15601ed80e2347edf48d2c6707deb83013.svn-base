package com.ait.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2015-1-14   上午08:16:58   
 *   @revision      1.0
 ***********************************************************************/

public class RegularlyEvMydMailTask  extends TimerTask{

	private static final Logger logger = Logger.getLogger(SparateDataTask.class);

	private RegularlyEvMydMailTaskProcessor processor = new RegularlyEvMydMailTaskProcessor();

	public void run() {

		processor.process();
	}
}
