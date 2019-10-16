package com.ait.ess.dao;

import java.util.HashMap;

import com.ait.ess.bean.EssLeaveBean;
import com.ait.ess.bean.EssMatchBean;
import com.ait.ess.bean.EssOverTimeBean;

/**
 * ESS申请校验规则的策略模式
 * EssArDAOHelper是默认的规则的实现，
 * 对于特殊的法人规则可以具体实现，在em2.xml中配置此策略即可。
 * 
 * @author lizaijun
 *
 */
public interface EssStrategyIntf {
	
	/**
	 * 检查加班申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap otApplyChecker(EssOverTimeBean essOverTimeBean);
	
	/**
	 * 检查休假申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap leaveApplyChecker(EssLeaveBean essLeaveBean);
	
	/**
	 * 检查值班申请是否合格, 并返回错误代码
	 * 
	 * @param essOverTimeBean
	 * @return 返回 HashMap
	 */
	public HashMap matchApplyChecker(EssMatchBean essMatchBean);
}
