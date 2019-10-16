package com.ait.pu.dao;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;
import org.apache.log4j.Logger;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class RoomSetupDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private Logger logger = null;

	public RoomSetupDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List roomSetup(Object parameterObject ,int currentPage , int pageSize) throws GlRuntimeException {
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.conferenceroom.roomSetupList", parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"SearchAllVisiterManagementInformation data Exception. ", e);
		}
		return result;
	}
	
	public int roomSetupCount(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("pu.conferenceroom.roomSetupNumber",parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"SearchAllVisiterManagementInformationCount data Exception. ", e);
		}
		return result;
	}
	
	public Object saveRoomSetup(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("pu.conferenceroom.saveRoomSetup", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("saveRoomSetup by paging Exception. ", e);
		}
		return result;
	}
	
	public Object saveUpdateRoomSetup(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.insert("pu.conferenceroom.saveUpdateRoomSetup", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("saveUpdateRoomSetup by paging Exception. ", e);
		}
		return result;
	}
	
	public Object delRoomSetup(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.delete("pu.conferenceroom.delRoomSetup", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delRoomSetup by paging Exception. ", e);
		}
		return result;
	}
	
	public List getRoomSetup(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("pu.conferenceroom.getRoomSetup", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getRoomSetup by paging Exception. ", e);
		}
		return result;
	}

}