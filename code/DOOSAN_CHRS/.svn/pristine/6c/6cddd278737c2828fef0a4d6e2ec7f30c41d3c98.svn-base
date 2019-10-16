package com.ait.util;

import org.apache.log4j.Logger;

public class PaCalcUtil {
	
	private static final Logger logger = Logger.getLogger(PaCalcUtil.class);
	
	private static int paCalcFlag = 0 ; // 工资是否在进行计算的标示
	
	private static int paReplenishmentCalcFlag = 0 ; // 工资补税是否在进行计算的标示
	
	private static int paBonusCalcFlag = 0 ; // 工资奖金是否在进行计算的标示
	
	private static int arCalcFlag = 0 ; // 考勤汇总是否在进行计算的标示
	
	public static synchronized final int getPaCalcFlag() {
		int flag = paCalcFlag ;
		
		//logger.debug("getpaCalcFlag1: " + paCalcFlag) ;
		
		paCalcFlag = paCalcFlag == 0 ? 1 : paCalcFlag ; 
		
		logger.debug("工资计算标示: " + flag) ;
		//logger.debug("getpaCalcFlag2: " + paCalcFlag) ;
		return flag ;
	}
	
	public static synchronized final void setPaCalcFlag(int calcFlagValue) {
		paCalcFlag = calcFlagValue ;
		logger.debug("setpaCalcFlag: " + paCalcFlag) ;
	}
	
	public static synchronized final int getPaReplenishmentCalcFlag() {
		int flag = paReplenishmentCalcFlag ;
		
		paReplenishmentCalcFlag = paReplenishmentCalcFlag == 0 ? 1 : paReplenishmentCalcFlag ; 
		
		//logger.debug("工资补税计算标示: " + paReplenishmentCalcFlag) ;
		return paReplenishmentCalcFlag ;
	}
	
	public static synchronized final void setPaReplenishmentCalcFlag(int calcFlagValue) {
		paReplenishmentCalcFlag = calcFlagValue ;
	}
	
	public static synchronized final int getPaBonusCalcFlag() {
		int flag = paBonusCalcFlag ;
		
		paBonusCalcFlag = paBonusCalcFlag == 0 ? 1 : paBonusCalcFlag ; 
		
		logger.debug("工资奖金计算标示: " + flag) ;
		return flag ;
	}
	
	public static synchronized final void setPaBonusCalcFlag(int calcFlagValue) {
		paBonusCalcFlag = calcFlagValue ;
	}
	
	
	public static synchronized final int getArCalcFlag() {
		int flag = arCalcFlag ;
		
		arCalcFlag = arCalcFlag == 0 ? 1 : arCalcFlag ; 
		
		logger.debug("考勤计算标示: " + flag) ;
		return flag ;
	}
	
	public static synchronized final void setArCalcFlag(int calcFlagValue) {
		arCalcFlag = calcFlagValue ;
	}
}
