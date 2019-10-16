package com.ait.gm.dao.sealdao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAO;
import com.ait.gm.dao.EateryDAOFactory;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-28
 * 
 */
public class SealManagementDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EateryDAO.class);

	public SealManagementDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	public List getAllWaitConfirmList(Object parameterObject,int currentPage,int pageSize)throws GlRuntimeException  {
			List list= null;
			try {
				list=commonSQLMapAdapter.executeQueryForMulti("gm.sealmanagement.allWaitConfirmList",parameterObject, currentPage, pageSize);

			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("allWaitConfirmList Exception. ", e);
			}
	        return list;
	}	
	
	public List getAllWaitConfirmList(Object parameterObject)throws GlRuntimeException  {
		List list= null;
		try {
			list=commonSQLMapAdapter.executeQueryForMulti("gm.sealmanagement.allWaitConfirmList",parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllWaitConfirmList Exception. ", e);
		}
        return list;
	}	


	public int getAllWaitConfirmListNumber(Object parameterObject)throws GlRuntimeException  {
		int temp= 0;
		try {
			temp=NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.sealmanagement.allWaitConfirmListNumber",
					parameterObject).toString());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getAllWaitConfirmListNumber Exception. ", e);
		}
        return temp;
	} 
	public boolean oingConfirm(Object parameterObject)throws GlRuntimeException  {
		boolean temp = false;
		try {
			commonSQLMapAdapter.update("gm.sealmanagement.oingConfirm",
					parameterObject);
			temp = true;
		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException("oingConfirm Exception. ", e);
		}
        return temp;
	} 

}
