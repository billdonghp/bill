package com.ait.gm.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-14
 * 
 */
public class ExpressMangerDAO {
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EateryDAO.class);

	public ExpressMangerDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public int expressConfirmInfoListNumber(Object parameterObject)throws GlRuntimeException  {
		int temp= 0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.express.expressConfirmInfoListNumber",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addExpressApply Exception. ", e);
		}
        return temp;
	}
	
	public List expressConfirmInfoList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.expressConfirmInfoList",parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressConfirmInfoList Exception. ", e);
		}
        return list;
	}
	public List gaReportList(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.gaReportList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gaReportList Exception. ", e);
		}
        return list;
	}
	
	public Object expressConfirmInfo(Object parameterObject)throws GlRuntimeException  {
		Object result= null;
		try {
			result = commonSQLMapAdapter.executeQuery("gm.express.expressConfirmInfoList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get Express ConfirmInfo Exception. ", e);
		}
        return result;
	}
	
	public boolean oingConfirm(Object parameterObject)throws GlRuntimeException  {
		boolean  temp= false;
		try {
			commonSQLMapAdapter.update("gm.express.oingConfirm",parameterObject);
             temp=true;
		} catch (Exception e) {
			temp=false;
			logger.error(e.toString());
			throw new GlRuntimeException("addExpressApply Exception. ", e);
		}
        return temp;
	}
	public int expressSendListNumber(Object parameterObject)throws GlRuntimeException  {
		int temp= 0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.express.expressSendListNumber",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addExpressApply Exception. ", e);
		}
        return temp;
	}
	public List expressSendList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.expressSendList", parameterObject,currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addExpressApply Exception. ", e);
		}
        return list;
	}
	public boolean ingSend(Object parameterObject,String[]applnoAry)throws GlRuntimeException  {
		boolean  temp= false;
		int flag=0;
		SimpleMap dateMap = (SimpleMap) parameterObject;
		try {
			for(int i=0;i<applnoAry.length;i++){
				dateMap.set("selectApplno", applnoAry[i]);	
			    commonSQLMapAdapter.update("gm.express.ingSend",dateMap);
			    flag+=1;
			}
			if(flag==applnoAry.length){
             temp=true;
			}
		} catch (Exception e) {
			temp=false;
			logger.error(e.toString());
			throw new GlRuntimeException("addExpressApply Exception. ", e);
		}
        return temp;
	}
	public List expressViewList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.expressViewList",parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressViewList Exception. ", e);
		}
        return list;
	}
	public List EMSDetailsExcel(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.EMSDetailsExcel",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EMSDetailsExcel Exception. ", e);
		}
        return list;
	}
	public List EMSTotalExcelList(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.EMSTotalExcelList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EMSTotalExcelList Exception. ", e);
		}
        return list;
	}
	public List EMSStatisticsExcel(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.express.EMSStatisticsExcel",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("EMSStatisticsExcel Exception. ", e);
		}
        return list;
	}
	
	
	public int expressViewListNumber(Object parameterObject)throws GlRuntimeException  {
		int temp= 0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.express.expressViewListNumber",
					parameterObject).toString());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressViewListNumber Exception. ", e);
		}
        return temp;
	}
	
	public int costNumber(Object parameterObject)throws GlRuntimeException  {
		int temp= 0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.express.costNumber",
					parameterObject).toString());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("costNumber Exception. ", e);
		}
        return temp;
	}
	
	public boolean confirmCosts(Object parameterObject)throws GlRuntimeException  {
		boolean  temp= false;
		try {
			commonSQLMapAdapter.update("gm.express.confirmCosts",parameterObject);
             temp=true;
		} catch (Exception e) {
			temp=false;
			logger.error(e.toString());
			throw new GlRuntimeException("confirmCosts Exception. ", e);
		}
        return temp;
	}
	
	public void saveCarManager(Object parameterObject)throws GlRuntimeException  {
		try {
			commonSQLMapAdapter.insert("gm.express.saveCarManager",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("saveCarManager Exception. ", e);
		}
	}
	
	public List getCarManager(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException  {
		List list= null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.express.getQueryCarManager",parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getQueryCarManager Exception. ", e);
		}
        return list;
	}
	
	public int getCarManagerInt(Object parameterObject)throws GlRuntimeException  {
		int temp=0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.express.getQueryCarManagerInt",
					parameterObject).toString());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getQueryCarManagerInt Exception. ", e);
		}
        return temp;
	}
	
	public void expressInstallSave(Object parameterObject)throws GlRuntimeException  {
		try {
			commonSQLMapAdapter.insert("gm.express.expressInstallSave",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressInstallUpdate Exception. ", e);
		}
	}
	
	public void expressInstallUpdate(Object parameterObject)throws GlRuntimeException  {
		try {
			commonSQLMapAdapter.insert("gm.express.expressInstallUpdate",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressInstallUpdate Exception. ", e);
		}
	}
	
	public List getExpressInstall(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.express.getExpressInstall",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getExpressInstall Exception. ", e);
		}
        return list;
	}
	
	public List ViewexpressInstallUpdate(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("gm.express.ViewexpressInstallUpdate",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("ViewexpressInstallUpdate Exception. ", e);
		}
        return list;
	}
	
	public Object expressInstallDel(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("gm.express.expressInstallDel", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("expressInstallDel by paging Exception. ", e);
		}
		return result;
	}
	
}
