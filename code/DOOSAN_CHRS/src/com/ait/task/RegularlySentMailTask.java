package com.ait.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;
/***********************************************************************
 *   
 *   ****所有，受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。   
 *   @copyright       Copyright: AIT
 *   @author         skulls(yangfan9336@gmail.com) 
 *   @create-time   2009-11-30   上午11:27:58   
 *   @revision      1.0
 ***********************************************************************/

public class RegularlySentMailTask  extends TimerTask{

	private static final Logger logger = Logger.getLogger(SparateDataTask.class);

	private RegularlySentMailProcessor processor = new RegularlySentMailProcessor();

	public void run() {

		processor.process();
	}
}
