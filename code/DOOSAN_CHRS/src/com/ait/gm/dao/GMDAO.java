package com.ait.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class GMDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private static final Logger logger = Logger.getLogger(GMDAO.class);

	public GMDAO() {

		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}

	/**
	 * NEW retrieve overtime plan record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException        
	 */
	public List eatLookList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.eatLookList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatLookList by  Exception. ", e);
		}
		return result;
	}

	public List gmViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmViewList by  Exception. ", e);
		}
		return result;
	}

	/**
	 * NEW retrieve overtime plan record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object eatLookCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.eatLookCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatLookCount list count Exception. ", e);
		}
		return result;
	}

	public Object gmViewCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmViewCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmViewCount list count Exception. ", e);
		}
		return result;
	}

	public List gmType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmType ", e);
		}
		return result;
	}

	public void updatePeopleNum(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("gm.eatery.updatePeopleNum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updatePeopleNum ", e);
		}
	}

	public List shiftlook(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.shiftlook", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("shift ", e);
		}
		return result;
	}

	public List cardType(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.cardType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("cardType ", e);
		}
		return result;
	}

	public Object gmTypeUpdate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmTypeUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmType ", e);
		}
		return result;
	}

	public Object timeGmType(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.timeGmType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("timeGmType ", e);
		}
		return result;
	}

	public Object timeGmTypeCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.timeGmTypeCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("timeGmTypeCount Exception. ", e);
		}
		return result;
	}

	public List timeGmType1(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.timeGmType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("timeGmType ", e);
		}
		return result;
	}

	public List shift(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.shift", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("shift ", e);
		}
		return result;
	}

	public List gmTypeEatery(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmTypeEatery", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmTypeEatery ", e);
		}
		return result;
	}

	public Object validateGM(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.validateGM", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("Gmvalidate Exception. ", e);
		}
		return result;
	}

	public int deleteGm(Object parameterObject) throws GlRuntimeException {

		int result;
		try {
			commonSQLMapAdapter.startTransaction();

			this.deleteGm1(parameterObject);

			result = commonSQLMapAdapter.delete("gm.gmview.deleteGM", parameterObject);

			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("GM DELETE ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
		return result;
	}

	public int deleteGm1(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = commonSQLMapAdapter.delete("gm.gmview.deleteGm1", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("GM DELETE ", e);
		}
		return result;
	}

	public int deleteTypenandshiftDelete(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = commonSQLMapAdapter.delete("gm.gmview.deleteTypenandshiftDelete", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("GM DELETE ", e);
		}
		return result;
	}

	/**
	 * NEW insert AR_OVERTIME_PLAN data
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public Object insertGM(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.insertGm", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert GM data Exception. ", e);
		}
		return result;
	}

	public Object updatesaveGM(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.updatesaveGM", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updatesave GM data Exception. ", e);
		}
		return result;
	}

	/**
	 * NEW retrieveOTPlan data for update 
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object gmUpdate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrievegm data for update  Exception. ", e);
		}
		return result;
	}

	public void ShiftAndGmtypeAddCheckNo(List parameterList, boolean flag) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.insertForMulti("gm.gmview.ShiftAndGmtypeAddCheckNo", parameterList, flag);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrievegm data for update  Exception. ", e);
		}
	}

	public void ShiftAndGmtypeAddCheckYes(List parameterList, boolean flag) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.insertForMulti("gm.gmview.ShiftAndGmtypeAddCheckYes", parameterList, flag);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrievegm data for update  Exception. ", e);
		}
	}

	public void ShiftAndGmtypeUpdateNoCheckShift(List parameterList, boolean flag) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.insertForMulti("gm.gmview.ShiftAndGmtypeUpdateNoCheckShift", parameterList, flag);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("retrievegm data for update  Exception. ", e);
		}
	}

	//此餐别参照可就餐时间段
	public void ShiftAndGmtypeUpdateCheckNo(List parameterList, SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			commonSQLMapAdapter.delete("gm.gmview.deleteTypenandshiftDelete", parameterObject);

			this.ShiftAndGmtypeAddCheckNo(parameterList, false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	//	此餐别不参照可就餐时间段
	public void ShiftAndGmtypeUpdateCheckYes(List parameterList, SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			commonSQLMapAdapter.delete("gm.gmview.deleteTypenandshiftDelete", parameterObject);

			this.ShiftAndGmtypeAddCheckYes(parameterList, false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	//没选择班次
	public void ShiftAndGmtypeUpdateNoCheckShift(List parameterList, SimpleMap parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();

			commonSQLMapAdapter.delete("gm.gmview.deleteTypenandshiftDelete", parameterObject);

			this.ShiftAndGmtypeUpdateNoCheckShift(parameterList, false);

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}

	public Object gmUpdateCardApplicationEndDate(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.update("gm.gmview.gmUpdateCardApplicationEndDate", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmUpdateCardApplicationEndDate  Exception. ", e);
		}
		return result;
	}

	public Object deleteApply(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {
			result = commonSQLMapAdapter.update("gm.gmview.deleteApply", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deleteApply  Exception. ", e);
		}
		return result;
	}

	public List Gm_Type(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.GmType", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("cardType ", e);
		}
		return result;
	}

	public Object add_eateryExceptionInformation(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.update("gm.gmview.addExceptionInformation", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("add_eateryExceptionInformation appand error ", e);
		}
		return result;

	}

	public List adminDept(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.adminDept", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("cardType ", e);
		}
		return result;
	}

	public Object insertEateryException(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.insertEateryException", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertEateryException GM data Exception. ", e);
		}
		return result;
	}

	public int LookEateryExceptionCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.LookEateryExceptionCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("LookEateryExceptionCount list count Exception. ", e);
		}
		return result;
	}

	public List LookEateryExceptionList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.LookEateryExceptionList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find LookEateryExceptionList by  Exception. ", e);
		}
		return result;
	}

	public int deleteEateryException(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = commonSQLMapAdapter.delete("gm.gmview.deleteEateryException", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("GM deleteEateryException ", e);
		}
		return result;
	}

	//得到登陆者能管理的员工
	public List empName(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.empName", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empName ", e);
		}
		return result;
	}

	public Object insertEatApply(Object parameterObject) throws GlRuntimeException {

		Object a;

		try {
			a = commonSQLMapAdapter.insert("gm.gmview.insertEatApply", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertEatApply ", e);
		}
		return a;
	}

	public Object insertEatApplyEmp(Object parameterObject) throws GlRuntimeException {

		Object a;

		try {
			//commonSQLMapAdapter.startTransaction();
			a = commonSQLMapAdapter.insert("gm.gmview.insertEatApplyEmp", parameterObject);
			//a=commonSQLMapAdapter.insert("gm.gmview.insertEatApply", parameterObject);
			//commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertEatApplyEmp ", e);
		}
		/*finally {

		 try {
		 commonSQLMapAdapter.endTransation();
		 } catch (Exception e) {
		 logger.error("End transation. " + e.toString());
		 throw new GlRuntimeException("End transation Exception. ", e);
		 }
		 }*/
		return a;
	}

	public int selectSame(Object parameterObject) throws GlRuntimeException {

		int a;

		try {
			a = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.selectSame", parameterObject));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("selectSame ", e);
		}
		return a;
	}

	public int getSoonApplyId(Object parameterObject) throws GlRuntimeException {

		int a;

		try {
			a = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.getSoonApplyId"));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSoonApplyId ", e);
		}
		return a;
	}

	public int getSeq(Object parameterObject) throws GlRuntimeException {

		int a;

		try {
			a = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.getSeq"));
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getSeq ", e);
		}
		return a;
	}

	public List adminDeptList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.adminDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("adminDeptList ", e);
		}
		return result;
	}

	public List groupCompetenceDeptList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.groupCompetenceDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("groupCompetenceDeptList data Exception ", e);
		}
		return result;
	}

	public int ActualNumberList(Object parameterObject) throws GlRuntimeException {

		int result = 0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.ActualNumberList", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("ActualNumberList data Exception ", e);
		}
		return result;
	}

	public String groupCompetence(Object parameterObject) throws GlRuntimeException {

		String result = "";
		try {

			result = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("gm.eatery.groupCompetence", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("groupCompetence data Exception ", e);
		}
		return result;
	}

	//餐别对应的就餐人数
	public List eateryNum(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.confirmEateryList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryNum ", e);
		}
		return result;
	}

	public Object noStatisticDeptList(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.eatery.noStatisticDeptList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("noStatisticDeptList ", e);
		}
		return result;
	}

	public List StatisticList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.StatisticList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticList ", e);
		}
		return result;
	}

	public List StatisticListKO(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.StatisticListKO", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticList ", e);
		}
		return result;
	}

	public Object StatisticFlag(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.eatery.StatisticFlag", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticList ", e);
		}
		return result;
	}

	public Object StatisticFlagKO(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.eatery.StatisticFlagKO", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticList ", e);
		}
		return result;
	}

	public List StatisticConfimList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.StatisticConfimList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticConfimList ", e);
		}
		return result;
	}

	public List StatisticConfimListKO(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.StatisticConfimListKO", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticConfimListKO ", e);
		}
		return result;
	}

	public void StatisticConfim(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("gm.eatery.StatisticConfim", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticConfim ", e);
		}

	}

	public void StatisticConfimKO(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.update("gm.eatery.StatisticConfimKO", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("StatisticConfimKO ", e);
		}

	}

	public void confimArrangement(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();
			commonSQLMapAdapter.update("gm.eatery.deleteConfimArrangement", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.confimArrangement", parameterObject);
			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confimArrangement ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}
	public void insertApplyDetail(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.update("gm.eatery.deleteConfimList", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.insertApplyDetail", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.confimApplyDetail", parameterObject);
			commonSQLMapAdapter.commitTransation();
			commonSQLMapAdapter.endTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confimArrangement ", e);
		} 

	}
	public void insertApplyDetailKO(Object parameterObject) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.update("gm.eatery.deleteConfimListKO", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.insertApplyDetailKO", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.confimApplyDetailKO", parameterObject);
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confimArrangement ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}
	

	public void confimArrangementKO(Object parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();
			commonSQLMapAdapter.update("gm.eatery.deleteConfimArrangementKO", parameterObject);
			commonSQLMapAdapter.update("gm.eatery.confimArrangementKO", parameterObject);
			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confimArrangementKO ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}

	public void arrangementAdd(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("gm.eatery.arrangementAdd", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementAdd ", e);
		}

	}
	public void recipesLAdd(List parameterList) throws GlRuntimeException {

		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();
			
			Object parameterObject;
			for (int i = 0; i < parameterList.size(); i++) {

				parameterObject = parameterList.get(i);
				commonSQLMapAdapter.delete("gm.eatery.recipesDelete", parameterObject);
				commonSQLMapAdapter.insert("gm.eatery.recipesAdd", parameterObject);
			}

			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();		

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementAdd ", e);
		}

	}
	
	public void arrangementUpdate(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("gm.eatery.arrangementUpdate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementUpdate ", e);
		}

	}

	public void arrangementDelete(List parameterObject) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.updateForMulti("gm.eatery.arrangementDelete", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementDelete ", e);
		}

	}

	public List arrangementUpdateView(Object parameterObject) throws GlRuntimeException {

		List result = null;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.arrangementUpdateView", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementUpdateView ", e);
		}
		return result;
	}

	public int CheckArrangement(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.CheckArrangement", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("CheckArrangement ", e);
		}
		return result;
	}

	public int arrangementViewCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.arrangementViewCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementViewCount ", e);
		}
		return result;
	}

	public List arrangementViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.arrangementViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("arrangementViewList by  Exception. ", e);
		}
		return result;
	}

	//	判断该日是否已经进行过统计并且申报成功
	public int eat_flag(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.eatFlag", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatFlag ", e);
		}
		return result;
	}

	//	得到登陆者能管理的员工总数
	public int empNameCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.empNameCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("empName ", e);
		}
		return result;
	}

	public int ifApplyFlag(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.ifApplyFlag", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("ifApplyFlag ", e);
		}
		return result;
	}

	//进入计划申报界面初始化人数
	public int eateryNumCount(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.eateryNumCount", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryNumCount ", e);
		}
		return result;
	}

	//取得餐别表里的FROM  TO   时间
	public List gmFromToDate(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmFromToDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmFromToDate by  Exception. ", e);
		}
		return result;
	}

	//	取得就餐申请表里的创建时间
	public List gmEatApplyDate(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmEatApplyDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmEatApplyDate by  Exception. ", e);
		}
		return result;
	}

	//	得到实际就餐时间
	public String eatDate(Object parameterObject) throws GlRuntimeException {

		String result;
		try {

			result = (String) commonSQLMapAdapter.executeQuery("gm.gmview.eatDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eatDate ", e);
		}
		return result;
	}

	//	得到flag 用于判断是否提交过哦
	public String flag(Object parameterObject) throws GlRuntimeException {

		String result;
		try {

			result = (String) commonSQLMapAdapter.executeQuery("gm.gmview.flag", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("flag ", e);
		}
		return result;
	}

	public Object updatesaveStatistic(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.update("gm.gmview.updatesaveStatistic", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("updatesaveStatistic  Exception. ", e);
		}
		return result;
	}

	public int eateryPeople(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.eateryPeople", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eateryPeople by  Exception. ", e);
		}
		return result;
	}

	public int eateryPeopleNotProvisional(Object parameterObject) throws GlRuntimeException {

		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.eateryPeopleNotProvisional", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eateryPeopleNotProvisional by  Exception. ", e);
		}
		return result;
	}

	public List deptId(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.deptId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find deptId by  Exception. ", e);
		}
		return result;
	}

	public List gmId(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmid", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmid by  Exception. ", e);
		}
		return result;
	}

	public List getAllEmpDeptRtimeInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getAllEmpDeptRtimeInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find specialEateryReport by  Exception. ", e);
		}
		return result;
	}

	public List getOtAllEmpDeptRtimeInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getOtAllEmpDeptRtimeInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getOtAllEmpDeptRtimeInfo by  Exception. ", e);
		}
		return result;
	}

	public List getNonNormalAllEmpRtimeInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getNonNormalAllEmpRtimeInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getNonNormalAllEmpRtimeInfo by  Exception. ", e);
		}
		return result;
	}

	public List getOtInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getOtInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getOtInfo by  Exception. ", e);
		}
		return result;
	}

	public List getOtNotEatInfo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getOtNotEatInfo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getOtNotEatInfo by  Exception. ", e);
		}
		return result;
	}

	public List getNotEatApply(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getNotEatApply", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getNotApply by  Exception. ", e);
		}
		return result;
	}

	public String getAllEmp(Object parameterObject) throws GlRuntimeException {

		String result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.getAllEmp", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find specialEateryReport by  Exception. ", e);
		}
		return result;
	}

	public String getNotApply(Object parameterObject) throws GlRuntimeException {

		String result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.getNotApply", parameterObject).toString();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getNotApply by  Exception. ", e);
		}
		return result;
	}

	public List geteateryTypeNo(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.geteateryTypeNo", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find geteateryTypeNo by  Exception. ", e);
		}
		return result;
	}

	public List getEatApplyId(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getEatApplyId", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getEatApplyId by  Exception. ", e);
		}
		return result;
	}

	public List getAllDept() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getAllDept");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getAllDept by  Exception. ", e);
		}
		return result;
	}

	public List getAllEateryType() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getAllEateryType");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getAllEateryType by  Exception. ", e);
		}
		return result;
	}

	public List getAllCardType() throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getAllCardType");

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getAllCardType by  Exception. ", e);
		}
		return result;
	}

	public List getResult(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getResult", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResult by  Exception. ", e);
		}
		return result;
	}

	public List eateryCountsExcel(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.eateryCountsExcel", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResult by  Exception. ", e);
		}
		return result;
	}
	
	public List eateryTotalByDeptExcel(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.eateryTotalByDeptExcel", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResult by  Exception. ", e);
		}
		return result;
	}

	public List EateryMonthConsumeExcelDISD(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.EateryMonthConsumeExcelDISD", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResult by  Exception. ", e);
		}
		return result;
	}

	public List EateryMonthConsumeExcelDICC(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.EateryMonthConsumeExcelDICC", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResult by  Exception. ", e);
		}
		return result;
	}

	public List getResultTemporary(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getResultTemporary", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResultTemporary by  Exception. ", e);
		}
		return result;
	}

	public int getCountTable(Object parameterObject) throws GlRuntimeException {
		int result;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.gmview.getCountTable", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getCountTable by  Exception. ", e);
		}
		return result;
	}

	public void CreateTable(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.gmview.CreateTable", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find CreateTable by  Exception. ", e);
		}
	}

	public void DropTable(Object parameterObject) throws GlRuntimeException {
		try {

			commonSQLMapAdapter.update("gm.gmview.DropTable", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find DropTable by  Exception. ", e);
		}
	}

	public List resultMonth(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.resultMonth", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find resultMonth by  Exception. ", e);
		}
		return result;
	}

	public List company(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.company", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find resultMonth by  Exception. ", e);
		}
		return result;
	}

	public List resultEateryNum(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.resultEateryNum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find resultEateryNum by  Exception. ", e);
		}
		return result;
	}

	public List getResultTotal(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.getResultTotal", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find getResultTotal by  Exception. ", e);
		}
		return result;
	}

	public void addEatStatistic(List parameterObject, Object basedata) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();
			commonSQLMapAdapter.update("gm.gmview.deleteStatistic", basedata);
			Object parameter;
			for (int i = 0; i < parameterObject.size(); i++) {

				parameter = parameterObject.get(i);
				commonSQLMapAdapter.update("gm.gmview.insertStatistic", parameter);
			}

			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addEatStatistic Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}

	public void addEatStatisticKO(List parameterObject, Object basedata) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.startBatch();
			commonSQLMapAdapter.update("gm.gmview.deleteStatisticKO", basedata);
			Object parameter;
			for (int i = 0; i < parameterObject.size(); i++) {

				parameter = parameterObject.get(i);
				commonSQLMapAdapter.update("gm.gmview.insertStatisticKO", parameter);
			}

			commonSQLMapAdapter.executeBatch();
			commonSQLMapAdapter.commitTransation();
			
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("addEatStatisticKO Exception. ", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

	}
	
	public List eatApplyList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatApplyList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	public int confirmFlagToday(Object parameterObject) throws GlRuntimeException {
		int result=0;
		try {

			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.confirmFlagToday", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("confirmFlagToday  Exception. ", e);
		}
		return result;
	}
	
	public List eatConfirmList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatConfirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	
	public List eatListBus(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatListBus", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatListBus by  Exception. ", e);
		}
		return result;
	}
	
	public int eatApplySize(Object parameterObject) throws GlRuntimeException {
		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.eatApplySize", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	
	public int eatConfirmSize(Object parameterObject) throws GlRuntimeException {
		int result;
		try {
			result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.eatConfirmSize", parameterObject));

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	
	public void addEatApply(Object parameterObject) throws GlRuntimeException {
		int result;
		try {
			SimpleMap simpleMap = (SimpleMap)parameterObject;
			String cnt = simpleMap.getString("chNo");
			String[] chno = cnt.split(",");
			for(int i=0;i<chno.length;i++){
				simpleMap.set("PERSON_ID", simpleMap.get("PERSON_ID"+chno[i]));
				simpleMap.set("FOODTYPE", simpleMap.get("foodType"+chno[i]));
				String eatType = simpleMap.getString("eatType"+chno[i]);
				simpleMap.set("EATTYPE", simpleMap.getString("EATTYPE1"+i));
				simpleMap.set("CAR", simpleMap.getString("bus"+chno[i]));
				simpleMap.set("adminId", simpleMap.getString("adminId"));
				
				result = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("gm.eatery.eatApplySelectCount", simpleMap));
				//System.out.println(result+"--------------------------------");
				if(result == 0){
					commonSQLMapAdapter.update("gm.eatery.addEatApply", simpleMap);
				}

			}
			

		} catch (Exception e) {
			throw new GlRuntimeException("find addEatApply by  Exception. ", e);
		}
	}
	
	public void confirmUpdate(String[] parameterObject) throws GlRuntimeException {
		try {
			for(int i=0;i<parameterObject.length;i++){
				SimpleMap	simpleMap = new SimpleMap();
				simpleMap.set("applyNo", parameterObject[i]);
				commonSQLMapAdapter.update("gm.eatery.confirmUpdate", simpleMap);
				
			}		
			

		} catch (Exception e) {
			
			logger.error(e.toString());
			throw new GlRuntimeException("find confirmUpdate by  Exception. ", e);
		}
	}
	
	public void eat_confirmDel(Object parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.delete("gm.eatery.eat_confirmDel", parameterObject);
				
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find confirmUpdate by  Exception. ", e);
		}
	}
	
	public void eat_confirmDel(List parameterObject) throws GlRuntimeException {
		try {
			commonSQLMapAdapter.deleteForMulti("gm.eatery.eat_confirmDel", parameterObject);
				
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find confirmUpdate by  Exception. ", e);
		}
	}
	
	public List eatConfirmList1(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatConfirmList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	public List eatConfirmListSum(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatConfirmListSum", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	
	public List eatConfirmListSumReport(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.eatConfirmListSumReport", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eatApplyList by  Exception. ", e);
		}
		return result;
	}
	public List ImportExcelInterface(Object parameterObject) throws GlRuntimeException {
		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.eatery.ImportExcelInterface", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("ImportExcelInterface Exception. ", e);
		}
		return result;
	}
	
	public List gmVegetableViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmVegetableViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmVegetableViewList by  Exception. ", e);
		}
		return result;
	}
	
	public List gmVegetableALL(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmVegetableAll", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmVegetableALL by  Exception. ", e);
		}
		return result;
	}
	
	public Object insertGmVegetable(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.insertGmVegetable", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert GmVegetable data Exception. ", e);
		}
		return result;
	}
	public Object validateGmVegetable(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.validateGmVegetable", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validateGmVegetable Exception. ", e);
		}
		return result;
	}
	public Object gmGmVegetableById(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmGmVegetableById", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("rgmGmVegetableById  Exception. ", e);
		}
		return result;
	}
	
	
	
	public Object updateGmVegetable(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.updateGmVegetable", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update GmVegetable data Exception. ", e);
		}
		return result;
	}
	
	public int deleteGmVegetable(Object parameterObject) throws GlRuntimeException {
		
		int result;
		try {

			result = commonSQLMapAdapter.delete("gm.gmview.deleteGmVegetable", parameterObject);
	
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find deleteGmVegetable by  Exception. ", e);
		}
		return result;
	}
	
	public List gmMenuViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmMenuViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmMenuViewList by  Exception. ", e);
		}
		return result;
	}
	
	public Object validateGmMenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.validateGmMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("validateGmMenu Exception. ", e);
		}
		return result;
	}
	
	public Object insertGmMenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.insertGmMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert GmMenu data Exception. ", e);
		}
		return result;
	}
	
	public Object gmGmMenuById(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmGmMenuById", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmGmMenuById  Exception. ", e);
		}
		return result;
	}
	
	public Object updateGmMenu(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.updateGmMenu", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update GmMenu data Exception. ", e);
		}
		return result;
	}
	
	public int deleteGmMenu(Object parameterObject) throws GlRuntimeException {
		
		int result;
		try {

			result = commonSQLMapAdapter.delete("gm.gmview.deleteGmMenu", parameterObject);
	
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("deleteGmMenu by  Exception. ", e);
		}
		return result;
	}
	public Object insertGmVegetableOpinion(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.insert("gm.gmview.insertGmVegetableOpinion", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insertGmVegetableOpinion data Exception. ", e);
		}
		return result;
	}
	
	public List gmVegetableOpinionViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmVegetableOpinionViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmVegetableOpinionViewList by  Exception. ", e);
		}
		return result;
	}
	
	public List eateryExceptionViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.eateryExceptionViewList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eateryExceptionViewList by  Exception. ", e);
		}
		return result;
	}
	
	public List eateryExceptionExcelList(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.eateryExceptionExcelList", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find eateryExceptionExcelList by  Exception. ", e);
		}
		return result;
	}
	
	public List gmMenuViewPage(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmMenuViewPage", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("find gmMenuViewPage by  Exception. ", e);
		}
		return result;
	}
	
	public List gmVegetableByTypeAndDate(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("gm.gmview.gmVegetableByTypeAndDate", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmVegetableByTypeAndDate by  Exception. ", e);
		}
		return result;
	}
	
	public List getChangePostCodeNames(Object parameterObject) throws GlRuntimeException {

		List result;
		try {

			result = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getChangePostCodeNames", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("getChangePostCodeNames by  Exception. ", e);
		}
		return result;
	}
	
	public Object gmVegetableViewCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmVegetableViewCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmVegetableViewCount list count Exception. ", e);
		}
		return result;
	}
	
	public Object gmMenuViewCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmMenuViewCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmMenuViewCount list count Exception. ", e);
		}
		return result;
	}
	
	public Object gmVegetableOpinionViewCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.gmVegetableOpinionViewCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("gmVegetableOpinionViewCount list count Exception. ", e);
		}
		return result;
	}
	
	public Object eateryExceptionViewCount(Object parameterObject) throws GlRuntimeException {

		Object result;
		try {

			result = commonSQLMapAdapter.executeQuery("gm.gmview.eateryExceptionViewCount", parameterObject);

		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("eateryExceptionViewCount list count Exception. ", e);
		}
		return result;
	}
}
