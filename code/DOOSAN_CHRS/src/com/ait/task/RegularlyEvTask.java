package com.ait.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         (shihuili@ait.net.cn) 
 *   @create-time   2014-8-1   下午15:27:58   
 *   @revision      1.0
 ***********************************************************************/

public class RegularlyEvTask  extends TimerTask{

	private static final Logger logger = Logger.getLogger(SparateDataTask.class);

	private RegularlyEvTaskProcessor processor = new RegularlyEvTaskProcessor();

	public void run() {

		processor.process();
	}
}
