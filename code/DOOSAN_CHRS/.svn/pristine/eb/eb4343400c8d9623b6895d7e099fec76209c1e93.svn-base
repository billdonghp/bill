package com.ait.pu.dao.conferenceroom;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

import org.apache.log4j.Logger;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-20
 * 
 */
public class ConferenceRoomDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private Logger logger = null;

	public ConferenceRoomDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public int getConferenceRoomConfirmListNumber(Object parameterObject)
			throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.conferenceRoomConfirmListNumber",
					parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConferenceRoomConfirmListNumber data Exception. ", e);
		}
		return temp;

	}
	
	public int getConferenceRoomConfirmListNumber1(Object parameterObject)
			throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.conferenceRoomConfirmListNumber1",
					parameterObject).toString());
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConferenceRoomConfirmListNumber data Exception. ", e);
		}
		return temp;
		
		}

	public List getConferenceRoomConfirmList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.conferenceRoomConfirmList", parameterObject,currentPage,
					pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConferenceRoomConfirmList data Exception. ", e);
		}
		return temp;

	}
	
	public List getConferenceRoomConfirmList1(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.conferenceRoomConfirmList1", parameterObject,currentPage,
					pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConferenceRoomConfirmList data Exception. ", e);
		}
		return temp;

	}
	
	public List getConferenceRoomConfirmList1Excel(Object parameterObject) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.conferenceRoomConfirmList1", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"getConferenceRoomConfirmList data Exception. ", e);
		}
		return temp;

	}
	
	public int getviewRoomInfoNumber(Object parameterObject)
	throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.getviewRoomInfoNumber",
					parameterObject).toString());
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getviewRoomInfoNumber data Exception. ", e);
		}
		return temp;
		
		}
		
	public List getviewRoomInfoList(Object parameterObject,
			int currentPage, int pageSize) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.getviewRoomInfoList", parameterObject,currentPage,
					pageSize);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getviewRoomInfoList data Exception. ", e);
		}
		return temp;

	}
	public List getviewRoomInfoForReport(Object parameterObject) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.getviewRoomInfoForReport", parameterObject);
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getviewRoomInfoForReport data Exception. ", e);
		}
		return temp;

		}
	public int getequipsCount(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.getequipsCount",
					parameterObject).toString());
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getequipsCount data Exception. ", e);
		}
		return temp;
		
		}
	
	public List equipsList(Object parameterObject) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.equipsList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"equipsList data Exception. ", e);
		}
		return temp;

	}
	
	public List equipsApplyList(Object parameterObject) throws GlRuntimeException {
		List temp = null;
		try {
			temp = commonSQLMapAdapter.executeQueryForMulti(
					"pu.conferenceroom.equipsApplyList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException(
					"equipsList data Exception. ", e);
		}
		return temp;

	}
	
	public boolean  oingConfirmConferenceRoom(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
		commonSQLMapAdapter.update("pu.conferenceroom.oingConfirmConferenceRoom", parameterObject);
		temp=true;

		} catch (Exception e) {
          temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"oingConfirmConferenceRoom data Exception. ", e);
		}
		return temp;

	}
	
	public boolean  saveUpdate(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
		commonSQLMapAdapter.update("pu.conferenceroom.saveUpdate", parameterObject);
		temp=true;

		} catch (Exception e) {
          temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"saveUpdate data Exception. ", e);
		}
		return temp;

	}
	
	public boolean  deleteApply(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
		commonSQLMapAdapter.update("pu.conferenceroom.deleteApply", parameterObject);
		temp=true;

		} catch (Exception e) {
          temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteApply data Exception. ", e);
		}
		return temp;

	}
	
	public boolean  deleteApplyroom(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
		commonSQLMapAdapter.update("pu.conferenceroom.deleteApplyroom", parameterObject);
		temp=true;

		} catch (Exception e) {
          temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException(
					"deleteApply data Exception. ", e);
		}
		return temp;

	}
	
	public Object  CheckRoom(Object parameterObject) throws GlRuntimeException {
		Object temp = null;
		try {
			temp = commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.checkRoomList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException(
					"checkRoomList data Exception. ", e);
		}
		return temp;

	}
	
	public List  getapplyemail(Object parameterObject) throws GlRuntimeException {
		List  temp= null;
		try {
			temp=commonSQLMapAdapter.executeQueryForMulti("pu.conferenceroom.getapplyemail", parameterObject);

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException(
					"getapplyemail data Exception. ", e);
		}
		return temp;
	}
	
	public int CheckRoomCount(Object parameterObject)throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.CheckRoomCount",
					parameterObject).toString());
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"CheckRoomCount data Exception. ", e);
		}
		return temp;

    }
	
	public int CheckRoomCountForupdate(Object parameterObject)throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery(
					"pu.conferenceroom.CheckRoomCountForupdate",
					parameterObject).toString());
		
		} catch (Exception e) {
		
			logger.error(e.toString());
			throw new GlRuntimeException(
					"CheckRoomCountForupdate data Exception. ", e);
		}
		return temp;

    }
	

}
