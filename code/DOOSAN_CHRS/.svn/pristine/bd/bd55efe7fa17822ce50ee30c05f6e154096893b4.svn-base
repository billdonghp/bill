package com.ait.gm.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class EateryDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(EateryDAO.class);

	public EateryDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	public List confirmEateryList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.confirmEateryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit eatery list by paging Exception. ", e);
		}
		return result;
	}
	
	public List confirmDeptList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eateryConfirmDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit dept list by paging Exception. ", e);
		}
		return result;
	}
	
	public int eateryPeopleNum(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.eateryPeopleNum", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryPeopleNum by paging Exception. ", e);
		}
		return result;
	}
	
	public Object confirmEateryCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.eatery.confirmEateryCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit confirmEateryCount by paging Exception. ", e);
		}
		return result;
	}
	
	public void insertStatistic(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("gm.eatery.insertStatistic", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertStatistict by paging Exception. ", e);
		}
	}
	public void deleteEaterycount(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("gm.eatery.deleteEaterycountTB", parameterObject);
			commonSQLMapAdapter.delete("gm.eatery.deleteEaterycountJC", parameterObject);
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertStatistict by paging Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	public void insertEaterycountTB(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("gm.eatery.insertEaterycountTB", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertStatistict by paging Exception. ", e);
		}
	}
	public void insertEaterycountJC(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insert("gm.eatery.insertEaterycountJC", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertStatistict by paging Exception. ", e);
		}
	}
	public String getEaterycountMaxDate(Object parameterObject) throws GlRuntimeException {
            String maxDate="";
		try {

			maxDate=commonSQLMapAdapter.executeQuery("gm.eatery.getEaterycountMaxDate", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertStatistict by paging Exception. ", e);
		}
		return maxDate;
	}
	
	public List getEatType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.gmid", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit confirmEateryCount by paging Exception. ", e);
		}
		return result;
	}
	
	public List getTempCardType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.eatingCard.tempcardgmid", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Visit confirmEateryCount by paging Exception. ", e);
		}
		return result;
	}
	
	public List getCheckAccount(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ga.check.getCheckAccount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCheckAccount by paging Exception. ", e);
		}
		return result;
	}
	
	public int updateCheckAccount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = commonSQLMapAdapter.update("ga.check.updateCheckAccount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCheckAccount by paging Exception. ", e);
		}
		return result;
	}
	
	public List getFoodType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getFoodType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getFoodType by paging Exception. ", e);
		}
		return result;
	}

	public String getconfirm(Object parameterObject) throws GlRuntimeException {

		String result="";;
		try {

			result = (String)commonSQLMapAdapter.executeQuery("ga.conferenceroom.getconfirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getconfirm by paging Exception. ", e);
		}
		return result;
	}

	public String getapplyemail(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.getapplyemail", parameterObject),"").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getapplyemail by paging Exception. ", e);
		}
		return result;
	}
	public String getupaffrimemail(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.getupaffrimemail", parameterObject),"").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getupaffrimemail by paging Exception. ", e);
		}
		return result;
	}
	
	public String getupaffrimemail1(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.getupaffrimemail1", parameterObject),"").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getupaffrimemail1 by paging Exception. ", e);
		}
		return result;
	}
	
	public String getupaffrimemaildriverot(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.getupaffrimemaildriverot", parameterObject),"").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getupaffrimemaildriverot by paging Exception. ", e);
		}
		return result;
	}
	
	public String getupaffrimemailbusarrange(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.getupaffrimemailbusarrange", parameterObject),"").toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getupaffrimemailbusarrange by paging Exception. ", e);
		}
		return result;
	}
	
	public void confirmExpressAffirm(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("gm.eatery.confirmExpressAffirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confirmExpressAffirm by paging Exception. ", e);
		}

	}
	public void confirmExpressApply(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("gm.eatery.confirmExpressApply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confirmExpressApply by paging Exception. ", e);
		}

	}
	
	public List getTopAffirmId(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getTopAffirmId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getTopAffirmId by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDept(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getDept", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDept by paging Exception. ", e);
		}
		return result;
	}
	public List getRseult(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getRseult", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getRseult by paging Exception. ", e);
		}
		return result;
	}
	
	public List getResultTotal(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getResultTotal", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getResultTotal by paging Exception. ", e);
		}
		return result;
	}
	public void voitureApply(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateVoitureApply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateVoitureApply by paging Exception. ", e);
		}
	}
	
	public void driverOtApply(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateDriverOtApply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateDriverOtApply by paging Exception. ", e);
		}
	}
	
	public void busArrangeApply(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateBusArrangeApply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateBusArrangeApply by paging Exception. ", e);
		}
	}
	
	public void updateVoitureAffirm(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateVoitureAffirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateVoitureAffirm by paging Exception. ", e);
		}
	}
	
	public void updateDriverOtAffirm(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateDriverOtAffirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateDriverOtAffirm by paging Exception. ", e);
		}
	}
	
	public void updateBusArrangeAffirm(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.eatery.updateBusArrangeAffirm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updateBusArrangeAffirm by paging Exception. ", e);
		}
	}
	
	public int getVoitureListInt(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.getVoitureListInt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureListInt by paging Exception. ", e);
		}
		return result;
	}
	
	public int getDriverOtListInt(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.getDriverOtListInt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDriverOtListInt by paging Exception. ", e);
		}
		return result;
	}
	
	public int getBusArrangeListInt(Object parameterObject) throws GlRuntimeException {
		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.getBusArrangeListInt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getBusArrangeListInt by paging Exception. ", e);
		}
		return result;
	}
	
	public List getVoitureListList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getVoitureListList", parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureListList list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDriverOtListList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getDriverOtListList", parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDriverOtListList list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getBusArrangeListList(Object parameterObject,int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getBusArrangeListList", parameterObject,currentPage,pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getBusArrangeListList list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allvoitureApproval(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.allvoitureApproval", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvoitureApproval list by paging Exception. ", e);
		}
		return result;
	}
	public List getDistinctionList(Object parameterObject) throws GlRuntimeException {
		
		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getDistinctionList", parameterObject);
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allvoitureApproval list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allDriverOtApproval(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.allDriverOtApproval", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allDriverOtApproval list by paging Exception. ", e);
		}
		return result;
	}
	
	public List allBusArrangeApproval(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.allBusArrangeApproval", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("allBusArrangeApproval list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getCar_name(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getCar_name", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCar_name list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getVoitureApplyInfo1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getVoitureApplyInfo1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureApplyInfo1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getCarList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getCarList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getCarList list by paging Exception. ", e);
		}
		return result;
	}
	
	public int getRy(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.getRy", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getRy list by paging Exception. ", e);
		}
		return result;
	}
	
	public int getRyInt(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.getRyInt", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getRyInt int by paging Exception. ", e);
		}
		return result;
	}
	
	public List getVoiTureDetail(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getVoiTureDetail", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureApply1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public void delvoiture(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.delete("gm.eatery.delvoiture", parameterObject);
			commonSQLMapAdapter.delete("gm.eatery.delvoiture1", parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("delvoiture list by paging Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	public List getVoitureApply1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getVoitureApply1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureApply1 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getVoitureApply2(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getVoitureApply2", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getVoitureApply2 list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getrivaList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getrivaList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDrivaList list by paging Exception. ", e);
		}
		return result;
	}
	
	public List getDeptname(Object parameterObject) throws GlRuntimeException {

		List result;
		try {
			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.getDeptname", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getDeptname list by paging Exception. ", e);
		}
		return result;
	}
}
