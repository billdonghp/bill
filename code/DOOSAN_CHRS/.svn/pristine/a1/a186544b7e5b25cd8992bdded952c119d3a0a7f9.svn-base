package com.ait.itfc.task;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskListener implements ServletContextListener {

	private static final Timer timer = new Timer();

	private static final ImportTask task = new ImportTask();

	/**
	 * 执行间隔时间(小时)
	 */
	private static final int period = 8;

	/**
	 * 第一次执行时间(小时), 应小于period值, 否则会错过第一次运行
	 */
	private static final int firstHour = 0;

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		/**
		 * 第一次运行时间
		 */
		Calendar firstTime = Calendar.getInstance();
		firstTime.set(Calendar.MINUTE, 0);
		firstTime.set(Calendar.SECOND, 0);
		firstTime.set(Calendar.MILLISECOND, 0);
		firstTime.set(Calendar.HOUR_OF_DAY, (firstTime.get(Calendar.HOUR_OF_DAY) - firstHour + period) / period * period);
		firstTime.add(Calendar.HOUR_OF_DAY, firstHour);

		timer.schedule(task, firstTime.getTime(), period * 60 * 60 * 1000);
	}
}