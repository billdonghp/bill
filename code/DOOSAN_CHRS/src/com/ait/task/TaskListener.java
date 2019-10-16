package com.ait.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.net.InetAddress; 

import org.apache.log4j.Logger;

public class TaskListener implements ServletContextListener {

	private static final Logger logger = Logger.getLogger(TaskListener.class);
	
	//private TaskSysparam taskSysparam;
	
	private static Timer timer;
	
	private static SparateDataTask sparateDataTask = new SparateDataTask(); 

	private static RegularlyEvTask regularlyEvTask = new RegularlyEvTask();
	
	private static RegularlyEvMailTask regularlyEvMailTask = new RegularlyEvMailTask();
    
	private static RegularlyEvMydMailTask regularlyEvMydMailTask = new RegularlyEvMydMailTask();
	//private static RegularlySentMailTask regularlySentMailTask = new RegularlySentMailTask();
	
	private static RegularlyOtSentMailTask regularlyOtSentMailTask = new RegularlyOtSentMailTask();
	
	private static RegularlyArModifySentMailTask regularlyArModifySentMailTask = new RegularlyArModifySentMailTask();
	
	private static RegularlyArAbnormalSentMailTask regularlyArAbnormalSentMailTask = new RegularlyArAbnormalSentMailTask();

	public void contextDestroyed(ServletContextEvent arg0) {

		if (timer != null) {
			timer.cancel();
			logger.debug("Timer Canceled");
		}
		logger.debug("web container contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		timer = new Timer(true);
		
	
		try {
			
			
			InetAddress addr=InetAddress.getLocalHost();
			String localHostName = addr.getHostName();
			System.out.println("localHostName:"+addr.getHostName());

			//DICC 生产1部、2部 DISC 加班申请 第二天11点未决裁，发送领导提醒
			//定时任务执行时间是根据主机名为ait-app-was-1(28服务器)的时间定时执行的  dicc-was-2(29服务器)
			//if (1 > 0 && "dicc-was-2".equals(localHostName)) {
				Calendar today = Calendar.getInstance();
				
				today.set(Calendar.HOUR_OF_DAY, 0);
				today.set(Calendar.MINUTE, 5);
				today.set(Calendar.SECOND, 0);
				today.set(Calendar.MILLISECOND, 0);
				today.add(Calendar.HOUR, 24);	
				System.out.println("*********************************");
				System.out.println(today.getTime());
				//限制多久执行一次  每天11点未决裁，发送领导提醒
				timer.schedule(regularlyOtSentMailTask, today.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				logger.debug("REGULARLY OT SENT MAIL TASK"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
				
				Calendar today1 = Calendar.getInstance();
				//修改考勤明细申请每天上午10点执行发邮件提醒决裁	
				today1.set(Calendar.HOUR_OF_DAY, 0);
				today1.set(Calendar.MINUTE, 10);
				today1.set(Calendar.SECOND, 0);
				today1.set(Calendar.MILLISECOND, 0);
				today1.add(Calendar.HOUR, 24);	
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println(today1.getTime());
				timer.schedule(regularlyArModifySentMailTask, today1.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				logger.debug("REGULARLY AR MODIFY SENT MAIL TASK"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
				
				
				Calendar today2 = Calendar.getInstance();
				//考勤异常每天15点执行发邮件提醒	
				today2.set(Calendar.HOUR_OF_DAY, 0);
				today2.set(Calendar.MINUTE, 5);
				today2.set(Calendar.SECOND, 0);
				today2.set(Calendar.MILLISECOND, 0);
				today2.add(Calendar.HOUR, 24);	
				System.out.println("###################################^");
				System.out.println(today2.getTime());
				timer.schedule(regularlyArAbnormalSentMailTask, today2.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				logger.debug("REGULARLY AR ABNORMAL SENT MAIL TASK"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);

				
				Calendar today3 = Calendar.getInstance();
				//每月21日2:00AM更新评价系统	
				today3.set(Calendar.MONTH, 0);
				today3.set(Calendar.HOUR_OF_DAY, 21);
				today3.set(Calendar.MINUTE, 2);
				today3.set(Calendar.SECOND, 0);
				today3.set(Calendar.MILLISECOND, 0);
				today3.add(Calendar.HOUR, 24);	
				System.out.println("$$$$$$$$$$$$$");
				System.out.println(today3.getTime());
				timer.schedule(regularlyEvTask, today3.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				logger.debug("REGULARLY TO EV"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
				
				
				Calendar today5 = Calendar.getInstance();
				//每月11日~21日按照2封/日的频度发送（07:00&12:00定时发送）提醒工人评价未完成评价/决裁
				today5.set(Calendar.MONTH, 0);
				today5.set(Calendar.HOUR_OF_DAY, 11);
				today5.set(Calendar.MINUTE, 7);
				today5.set(Calendar.SECOND, 0);
				today5.set(Calendar.MILLISECOND, 0);
				today5.add(Calendar.HOUR, 24);	
				System.out.println("$$$$$$$$$$$$$");
				System.out.println(today5.getTime());
				timer.schedule(regularlyEvMailTask, today5.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				//timer.schedule(regularlyEvMailTask, 1000 * 60 );//1分钟执行一次
				logger.debug("REGULARLY TO EV_MAIL"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
				
				
				Calendar today6 = Calendar.getInstance();
				//提醒参观者申请 满意度录入
				today6.set(Calendar.MONTH, 0);
				today6.set(Calendar.HOUR_OF_DAY, 11);
				today6.set(Calendar.MINUTE, 7);
				today6.set(Calendar.SECOND, 0);
				today6.set(Calendar.MILLISECOND, 0);
				today6.add(Calendar.HOUR, 24);	
				System.out.println("$$$$$$$$$$$$$");
				timer.schedule(regularlyEvMydMailTask, today6.getTime(),1000 * 60 * 60 * 1);//1个小时执行一次
				//timer.schedule(regularlyEvMailTask, 1000 * 60 );//1分钟执行一次
				logger.debug("REGULARLY TO EV_MAIL"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
				
				
				Calendar time = Calendar.getInstance();
				
				time.set(Calendar.HOUR_OF_DAY, 0);
				time.set(Calendar.MINUTE, 10);
				time.set(Calendar.SECOND, 0);
				time.set(Calendar.MILLISECOND, 0);
				time.add(Calendar.HOUR, 24);
				//系统定时任务:
				//1定时将去年今日的考勤明细导入历史明细表中，再删除考勤明细表中去年今天的信息；
				//2定时将去年今日的刷卡导入历史刷卡表中AR_MAC_RECORDS_HISTORY，再删除去年今天的刷卡信息；
				//3定时将去年今日的吃饭刷卡导入历史吃饭刷卡表中AR_EATERY_RECORDS_HISTORY，再删除去年今天的吃饭刷卡信息；
				//4DICC,DISD Sales&Marketing 人员信息更新到 DICI中，为了可以使用行政管理模块
				//5将DICI李卉李部长信息更新到DISD,DICC中，为了对新添加的5个公章进行决裁（后来直接在表中添加的，没有通过系统）
				timer.schedule(sparateDataTask, time.getTime(), 1000 * 60 * 60 * 24);
				
				logger.debug("SPARATE DATA TASK"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000 * 60 * 60 * 24);
		//	}
			
		/*	// 提醒 mailtask
			if (1 > 0) {
				Calendar today = Calendar.getInstance();
				
				today.set(Calendar.HOUR_OF_DAY, 0);
				today.set(Calendar.MINUTE, 0);
				today.set(Calendar.SECOND, 0);
				today.set(Calendar.MILLISECOND, 0);
				today.add(Calendar.HOUR, 24);				
				timer.schedule(regularlySentMailTask, today.getTime(),1000*60*60*1);//1个小时执行一次
				logger.debug("regularlySentMailTask"+"[DELAY]:"+ 24 + "HOUR [PERIOD]"+ 1000*60*60*1);
			}
			*/
/*			Calendar today = Calendar.getInstance();	
			timer.schedule(regularlySentMailTask, today.getTime(),1000*60*60*1);//1个小时执行一次
*/			
			// sparate data task
//			if (1 > 0) {
//				
//			}
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Unable to initialize Schedule.");
		}
		logger.debug("web container contextInitialized");
	}
}