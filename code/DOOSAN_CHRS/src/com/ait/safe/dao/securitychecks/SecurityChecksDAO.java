package com.ait.safe.dao.securitychecks;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-21
 * 
 */
public class SecurityChecksDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private Logger logger = null;

	public SecurityChecksDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public int getSecurityChecksListNumber(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("safe.securitychecks.securityChecksListNumber", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSecurityChecksListNumber data Exception. ", e);
		}
		return temp;
	}

	public List getSecurityChecksList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.securityChecksList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSecurityChecksList data Exception. ", e);
		}
		return list;
	}
	
	public List getSecurityChecksList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.securityChecksList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSecurityChecksList data Exception. ", e);
		}
		return list;
	}
	
	public List getSecurityChecksTotalExcelList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getSecurityChecksTotalExcelList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSecurityChecksTotalExcelList data Exception. ", e);
		}
		return list;
	}
	
	public Object getSecurityChecks(Object parameterObject) throws GlRuntimeException {
		Object result = null;
		try {
			result = commonSQLMapAdapter.executeQuery("safe.securitychecks.securityChecksList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getSecurityChecks data Exception. ", e);
		}
		return result;
	}

	public List getAnSecurityChecksInformation(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.AnSecurityChecksInformation", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getAnSecurityChecksInformation data Exception. ", e);
		}
		return list;
	}

	public List selectEmpidLStr(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.selectEmpidLStr", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("selectEmpidLStr data Exception. ", e);
		}
		return list;
	}

	public List getAnSecurityInformation(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getAnSecurityInformation", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getAnSecurityInformation data Exception. ", e);
		}
		return list;
	}

	public List getAnviewCorrectivePlan(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getAnviewCorrectivePlan", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getAnviewCorrectivePlan data Exception. ", e);
		}
		return list;
	}

	public List getAnviewCorrectivePlan_plan(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getAnviewCorrectivePlan_plan", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getAnviewCorrectivePlan_plan data Exception. ", e);
		}
		return list;
	}

	public List oingConfirm(Object parameterObject, String DOCUMENTNO, String falg) throws GlRuntimeException {
		boolean temp = false;
		List list = null;
		try {
			//退回，重新添加记录
			if (falg.equals("2")) {
				list = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getOing", parameterObject);
				List list1 = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.PHOTO", parameterObject);
				SimpleMap simpleMap = new SimpleMap();
				SimpleMap simpleMap1 = new SimpleMap();
				simpleMap = (SimpleMap) list.get(0);

				simpleMap.set("DOCUMENTNO", DOCUMENTNO);
				simpleMap1.set("DOCUMENTNO1", DOCUMENTNO);
				commonSQLMapAdapter.startTransaction();
				for (int i = 0; i < list1.size(); i++) {
					simpleMap1 = (SimpleMap) list1.get(i);
					simpleMap1.set("DOCUMENTNO1", DOCUMENTNO);
					commonSQLMapAdapter.update("safe.securitychecks.savePHOTO", simpleMap1);
				}
				commonSQLMapAdapter.update("safe.securitychecks.saveOing", simpleMap);
				commonSQLMapAdapter.update("safe.securitychecks.oingConfirm", parameterObject);
				commonSQLMapAdapter.commitTransation();
			} else {
				commonSQLMapAdapter.update("safe.securitychecks.oingConfirm", parameterObject);
			}
			temp = true;

		} catch (Exception e) {

			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException("oingConfirm data Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return list;
	}

	public boolean oingConfirm1(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
			commonSQLMapAdapter.update("safe.securitychecks.oingConfirm", parameterObject);
			temp = true;

		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException("oingConfirm data Exception. ", e);
		}
		return temp;
	}

	public boolean deleteSecurityCheck(Object parameterObject) throws GlRuntimeException {
		boolean temp = false;
		try {
			commonSQLMapAdapter.delete("safe.securitychecks.deleteSecurityCheck", parameterObject);
			temp = true;

		} catch (Exception e) {
			temp = false;
			logger.error(e.toString());
			throw new GlRuntimeException("oingConfirm data Exception. ", e);
		}
		return temp;
	}

	public String getEmail(Object parameterObject) throws GlRuntimeException {
		String result = null;
		try {
			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("safe.securitychecks.getEmail", parameterObject));

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEmail data Exception. ", e);
		}
		return result;
	}

	public List getQueryView(Object parameterObject) throws GlRuntimeException {
		List result = null;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("safe.securitychecks.getQueryView", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getQueryView data Exception. ", e);
		}
		return result;
	}

	public void updateView(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.update("safe.securitychecks.updateView", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("updateView data Exception. ", e);
		}
	}
}
