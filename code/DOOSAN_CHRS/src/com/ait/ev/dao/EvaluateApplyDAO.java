package com.ait.ev.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ev.bean.EvaluateAffirm;
import com.ait.ev.bean.EvaluateAffirmParam;
import com.ait.ev.bean.EvaluateAffirmor;
import com.ait.ev.bean.EvaluateInfo;
import com.ait.ev.bean.EvaluateItem;
import com.ait.ev.bean.EvaluateSetItem;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.bean.PresentBean;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2011-10-11
 * 
 */
public class EvaluateApplyDAO {

	private CommonSQLMapAdapter commonSQLMapAdapter = null;

	private Logger logger = null;

	public EvaluateApplyDAO() {
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	
	public List getEvaluateApplyList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyList data Exception. ", e);
		}
		return list;
	}
	
	public int getEvaluateApplyListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateApplyListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyListCnt data Exception. ", e);
		}
		return temp;
	}
	
	
	
	public List getEvaluateApplyDeptList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyDeptList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyDeptList data Exception. ", e);
		}
		return list;
	}
	
	
	public List getEvaluateAffirmDeptList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmDeptList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmDeptList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateAffirmSetList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmSetList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmSetList data Exception. ", e);
		}
		return list;
	}
	
	
	
	public int getEvaluateAffirmSetListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateAffirmSetListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmSetListCnt data Exception. ", e);
		}
		return temp;
	}
	
	public List getEvaluateItemSetList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateItemSetList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateItemSetList data Exception. ", e);
		}
		return list;
	}
	
	public int getEvaluateItemSetListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateItemSetListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateItemSetListCnt data Exception. ", e);
		}
		return temp;
	}
	
	public List getEvaluatePositionSetList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluatePositionSetList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluatePositionSetList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryPositionList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryPositionList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryPositionList data Exception. ", e);
		}
		return list;
	}
	
	
	public List getEvaluateItemList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateItemList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateItemList data Exception. ", e);
		}
		return list;
	}
	
	
	
	public List getEvaluateApplyPositionList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyPositionList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyPositionList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateAffirmPositionList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmPositionList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmPositionList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateApplyItem(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyItem", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyItem data Exception. ", e);
		}
		
		return list;
	}
	
	public List getEvaluateAffirmItem(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmItem", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmItem data Exception. ", e);
		}
		
		return list;
	}
	
	public String getEvaluateApplyItemId(Object parameterObject) throws GlRuntimeException {
		String temp = "";
		try {
			temp = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateApplyItemId", parameterObject));

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateApplyItemId data Exception. ", e);
		}
		
		return temp;
	}

	public List getEvaluateAffirmorList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmorList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmorList data Exception. ", e);
		}
		return list;
	}
	
	
	public List getEvaluateAffirmorAllList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmorAllList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmorAllList data Exception. ", e);
		}
		return list;
	}
	
	/**
	 * 批量进行休假申请
	 * 
	 * @param list
	 * @return list
	 */
	public List doEvaluateApplyByBatch(List parameterObject) throws GlRuntimeException {

//		HashMap result = new HashMap();
//		result.put("errcode", new Integer(0));
		
		List<EvaluateInfo> listResult = new ArrayList<EvaluateInfo>();

		try {
			// 取得插入的申请数据的申请号			
			commonSQLMapAdapter.startTransaction();

			Iterator iterator = parameterObject.iterator();

			while (iterator.hasNext()) {
				EvaluateInfo evi = (EvaluateInfo) iterator.next();
				///int sequence = this.getSequence("ESS_OTAPPLY_SEQ");
				///leaveBean.setLeaveNo(sequence);

				// 取出决裁者列表
				List affirmorList = evi.getAffirmorList();
				Iterator affirmorIter = affirmorList.iterator();
				int applyNo=this.getSequence("EV_DETAIL_APPLY_SEQ");
				evi.setNo(applyNo);
				
				// 插入一条休假申请
				commonSQLMapAdapter.insert("ev.evaluateapply.insertEvDetailApply", evi);
				
				listResult.add(evi);
				
				List<EvaluateAffirmor> list = new ArrayList<EvaluateAffirmor>();
				
				while (affirmorIter.hasNext()) {

					EvaluateAffirmor affirmor = (EvaluateAffirmor) affirmorIter.next();
					affirmor.setApplyNo(applyNo);
					affirmor.setCreatedBy(evi.getCreatedBy());
					list.add(affirmor);
				}

				// 插入决裁者列表
				this.insertAffirmorInfo(affirmorList, false);
			}

			commonSQLMapAdapter.commitTransation();

		} catch (Exception e) {

//			result.put("errcode", new Integer(-1));
//			result.put("errmsg", e.toString());
			e.printStackTrace();
			logger.error("Transation rollback. " + e.toString());
			// throw new GlRuntimeException("Apply overtime by batch Exception.
			// ",e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}

		return listResult;
	}
	
	/**
	 * insert affirmor information
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertAffirmorInfo(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {

		try {

			commonSQLMapAdapter.insertForMulti("ev.evaluateapply.insertAffirmorInfo", parameterObject, isAutoTrans);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("insert affirmor information Exception. ", e);
		}
	}
	
	/**
	 * 取得序号值
	 * 
	 * @param seqName
	 *            序号名称
	 * @return
	 */
	private int getSequence(String seqName) {
		int result = 0;
		String sql = seqName+".NEXTVAL";
		SimpleMap map = new SimpleMap();
		try {
			map.set("sql", sql);
			Object obj = commonSQLMapAdapter.executeQuery("ev.evaluateapply.getSequence", map);
			result = Integer.parseInt(obj.toString());
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			throw new GlRuntimeException("取得序列号失败", e);
		}
		return result;
	}
	
	public List getEvaluateAffirmList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryDetailList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryDetailList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryDetailList data Exception. ", e);
		}
		return list;
	}
	
	
	public List getEvaluateQueryResultList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryResultList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryResultList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryNoApplyList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryNoApplyList", parameterObject, currentPage, pageSize);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryNoApplyList data Exception. ", e);
		}
		return list;
	}
	
	
	public List getEvaluateQueryDetailExcelList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryDetailList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryDetailList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryResultExcelList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryResultList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryResultList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryNoApplyExcelList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryNoApplyList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryNoApplyList data Exception. ", e);
		}
		return list;
	}
	
	public List getEvaluateQueryResultExcelSumList(Object parameterObject) throws GlRuntimeException {
		List list = null;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateQueryResultExcelSumList", parameterObject);

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryResultExcelSumList data Exception. ", e);
		}
		return list;
	}
	
	public int getEvaluateAffirmListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateAffirmListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateAffirmListCnt data Exception. ", e);
		}
		return temp;
	}
	
	public int getEvaluateQueryDetailListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateQueryDetailListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryDetailListCnt data Exception. ", e);
		}
		return temp;
	}
	
	public int getEvaluateQueryResultListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateQueryResultListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryResultListCnt data Exception. ", e);
		}
		return temp;
	}
	
	public int getEvaluateQueryNoApplyListCnt(Object parameterObject) throws GlRuntimeException {
		int temp = 0;
		try {
			temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateQueryNoApplyListCnt", parameterObject).toString());

		} catch (Exception e) {

			logger.error(e.toString());
			throw new GlRuntimeException("getEvaluateQueryNoApplyListCnt data Exception. ", e);
		}
		return temp;
	}
	
	
	public List getAffirmorList(int applyNo) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = " SELECT EV_AFFIRM.EV_AFFIRM_NO, EV_AFFIRM.APPLY_NO, EV_AFFIRM.AFFIRMOR_ID, TO_CHAR(EV_AFFIRM.UPDATE_DATE,'YYYY-MM-DD HH24:MI') AS UPDATE_DATE,  "
			        +" HR_EMPLOYEE.CHINESENAME,hr_employee.chinese_pinyin, hr_employee.koreanname,  "
			        +" EV_AFFIRM.AFFIRM_LEVEL, EV_AFFIRM.AFFIRM_FLAG,  EV_AFFIRM.AFFIRM_REMARKS  "
			        +" FROM EV_AFFIRM, HR_EMPLOYEE "
			        +" WHERE EV_AFFIRM.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id  " 
			        +" AND EV_AFFIRM.APPLY_NO = ? ORDER BY EV_AFFIRM.AFFIRM_LEVEL  ";
		
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, applyNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvaluateAffirmor evaluateAffirmor = new EvaluateAffirmor();
				evaluateAffirmor.setAffirmNo(rs.getInt("EV_AFFIRM_NO"));
				evaluateAffirmor.setApplyNo(rs.getInt("APPLY_NO"));
				evaluateAffirmor.setAffirmorId(rs.getString("AFFIRMOR_ID"));
				evaluateAffirmor.setUpdateDate(rs.getString("UPDATE_DATE")) ;
				
				evaluateAffirmor.setAffirmorName(rs.getString("CHINESENAME"));

				evaluateAffirmor.setAffirmorEnName(rs.getString("CHINESE_PINYIN"));
				evaluateAffirmor.setAffirmorKorName(rs.getString("KOREANNAME"));

				evaluateAffirmor.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
				evaluateAffirmor.setAffirmFlag(rs.getInt("AFFIRM_FLAG"));
				
				evaluateAffirmor.setRemark(rs.getString("AFFIRM_REMARKS"));
				list.add(evaluateAffirmor);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	

	public List getAffirmSetAffirmorList(String deptId,String position) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT HE.EMPID, HE.CHINESENAME, E.AFFIRM_LEVEL FROM EV_AFFIRM_RELATION E, HR_EMPLOYEE HE, "
	    +"HR_DEPARTMENT HD, SY_CODE  S,HR_DEPARTMENT  HRD	 WHERE E.AFFIRM_DEPTID = HD.DEPTID "
	    +"AND E.AFFIRMOR_ID = HE.PERSON_ID	 AND E.AFFIRM_POSITION = S.CODE_ID	AND HD.FOURTHDEPT = HRD.DEPTID(+) "
	    +"AND E.AFFIRM_DEPTID = ? AND  'C_12004'||substr(E.AFFIRM_POSITION,8,13) = 'C_12004' || substr(?, 8, 13) ";
	   
		
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptId);
			pstmt.setString(2, position);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvaluateAffirm evaluateAffirm = new EvaluateAffirm();
				evaluateAffirm.setAffirmorId(rs.getString("EMPID"));
				evaluateAffirm.setAffirmorName(rs.getString("CHINESENAME"));
				evaluateAffirm.setLevel(rs.getInt("AFFIRM_LEVEL"));
				
				list.add(evaluateAffirm);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	public List getItemSetItemList(String month,String deptId,String position) {
		List list = new ArrayList();
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select T.EVALUATE_ITEM_ID, E.ITEM_NAME,  T.EVALUATE_ITEM_PROPORTION	  from ev_item_relation t, ev_item e "
			+"where t.evaluate_item_id = e.item_id and t.EVALUATE_DEPTID = ? and t.EVALUATE_POSITION = ? and t.EVALUATE_MONTH = ? order by e.id";
	   
		
		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deptId);
			pstmt.setString(2, position);
			pstmt.setString(3, month);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EvaluateItem evaluateItem = new EvaluateItem();
				evaluateItem.setItemId(rs.getString("EVALUATE_ITEM_ID"));
				evaluateItem.setItemName(rs.getString("ITEM_NAME"));
				evaluateItem.setItemProportion(rs.getString("EVALUATE_ITEM_PROPORTION"));
				
				list.add(evaluateItem);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
	
	/**
	 * 进行评价功能决裁 (更新决裁信息的决裁结果)
	 * 
	 * @param EssAfformParam[]
	 * @param AffirmType
	 *            申请类型 无
	 * @param flag
	 *            决裁结果: 1 通过; 2 否决
	 */
	public int doEvaluateAffirmList(List parameterObject, int flag) {
		int result = 0 ;
		List<EvaluateAffirmParam> paramList = new ArrayList<EvaluateAffirmParam>();
	
		Iterator iterator = parameterObject.iterator();
		while (iterator.hasNext()) {

			EvaluateAffirmParam p = (EvaluateAffirmParam) iterator.next();
			
//			if(isEvaluateNoAffirm(p.getApplyNo())){//判断如果该条信息没有被决裁过，插入备份表中
//				this.insertEvaluateBackInfo(p.getApplyNo());
//			}
			
			
			result = doEvaluateAffirm(p, flag,  paramList);
		}
		
		return result ;
	}
	
	/**
	 * 判断 是否是没有决裁过
	 * 
	 * @param affirmNo
	 *            决裁信息序号
	 */
	private boolean isEvaluateNoAffirm(int affirmNo) {
		Logger.getLogger(getClass()).debug("Checking if this is the Evaluate No Affirm........");
		boolean result = false;
		Connection conn = ConnBean.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			String sql = "SELECT MAX(EV.AFFIRM_FLAG) FROM EV_AFFIRM EV WHERE EV.APPLY_NO = ? GROUP BY EV.APPLY_NO  ";

		Logger.getLogger(getClass()).debug(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, affirmNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					Logger.getLogger(getClass()).debug("Is No Affirm !");
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return result;
	}

		
		
		
		/**
		 * 进行评价功能 决裁 (更新决裁信息的决裁结果)
		 * 
		 * @param affirmNo
		 *            决裁信息序号
		 * @param flag
		 *            决裁结果: 1 通过; 2 否决
		 */
		private int doEvaluateAffirm(EvaluateAffirmParam p, int flag, List<EvaluateAffirmParam> paramList) {
			EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,ApplicationContext.getTheadLocal().getCpnyId());
			int result = 0;
			int affirmNo = p.getAffirmNo();
			int applyNo = p.getApplyNo();
			String adminId = p.getAdminId();
			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;

			String sql = "UPDATE EV_AFFIRM SET AFFIRM_FLAG = ?, UPDATE_DATE = SYSDATE, UPDATE_BY = ?  WHERE EV_AFFIRM_NO = ?";
			
			String insertSqlBack = "INSERT INTO EV_DETAIL_BACK T "
			+" (T.APPLY_NO,T.EVALUATE_MONTH,T.EMPID,T.DEPTID,T.DEPTNAME,T.POSITION,T.AMOUNT,T.PLACE,T.LAST_MONTH_PLACE,T.ACTIVITY,T.REMARK,T.CREATE_DATE,T.CREATED_BY, "
			+" T.TOTAL,T.ITEM0,T.ITEM1,T.ITEM2,T.ITEM3,T.ITEM4,T.ITEM5,T.ITEM6,T.ITEM7,T.ITEM8,T.ITEM9,T.ITEM10,T.ITEM11,T.ITEM12,T.ITEM13,T.ITEM14,T.ITEM15,T.ITEM16,T.ITEM17,T.ITEM18,T.ITEM19,T.ITEM20,"
			+" T.ITEM21,T.ITEM22,T.ITEM23,T.ITEM24,T.ITEM25,T.ITEM26,T.ITEM27,T.ITEM28,T.ITEM29,T.ITEM30,T.ITEM31,T.ITEM32,T.ITEM33,T.ITEM34,T.ITEM35,T.ITEM36,T.ITEM37,T.ITEM38,T.ITEM39,T.AFFIRMOR_ID,T.AFFIRM_LEVEL ) "
			+" SELECT E.APPLY_NO,E.EVALUATE_MONTH,E.EMPID,E.DEPTID,E.DEPTNAME,E.POSITION,E.AMOUNT,E.PLACE,E.LAST_MONTH_PLACE,E.ACTIVITY,E.REMARK,E.CREATE_DATE,E.CREATED_BY, "
			+" E.TOTAL,E.ITEM0,E.ITEM1,E.ITEM2,E.ITEM3,E.ITEM4,E.ITEM5,E.ITEM6,E.ITEM7,E.ITEM8,E.ITEM9,E.ITEM10,E.ITEM11,E.ITEM12,E.ITEM13,E.ITEM14,E.ITEM15,E.ITEM16,E.ITEM17,E.ITEM18,E.ITEM19,E.ITEM20,"
			+" E.ITEM21,E.ITEM22,E.ITEM23,E.ITEM24,E.ITEM25,E.ITEM26,E.ITEM27,E.ITEM28,E.ITEM29,E.ITEM30,E.ITEM31,E.ITEM32,E.ITEM33,T.ITEM34,T.ITEM35,T.ITEM36,T.ITEM37,T.ITEM38,T.ITEM39,EF.AFFIRMOR_ID,EF.AFFIRM_LEVEL "
			+" FROM EV_DETAIL_APPLY E, EV_AFFIRM EF WHERE E.APPLY_NO = EF.APPLY_NO AND E.APPLY_NO = ? AND EF.EV_AFFIRM_NO = ? ";
			
			
			Logger.getLogger(getClass()).debug(sql);
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, flag);
				pstmt.setString(2, adminId);
				//pstmt.setString(3, p.getRemark());
				pstmt.setInt(3, affirmNo);
				result = pstmt.executeUpdate();
				
				if(result == 1 && flag==2){
					this.doEvaluateConfirm(applyNo, flag,true);
				}
	            if(result == 1 && flag==1 && this.isEvaluateLastAffirmor(affirmNo)){
					this.doEvaluateConfirm(applyNo, flag,true);
					
					//this.updateEvaluateInfoByParam(p);
				}
	            if(result == 1 && flag==1 && !this.isEvaluateLastAffirmor(affirmNo)){
					this.doEvaluateConfirm(applyNo, flag,false);
					
					//this.updateEvaluateInfoByParam(p);
				}
//	           
	            
	            pstmt = conn.prepareStatement(insertSqlBack);//插入备份信息
	            pstmt.setInt(1, applyNo);
	            pstmt.setInt(2, affirmNo);
				
			    pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(pstmt, conn);
			}
			return result;
		}
		
		/**
		 * 
		 * 
		 * @param affirmNo
		 *            决裁信息序号
		 * @param flag
		 *            决裁结果 1 通过; 2 否决
		 */
		public int doEvaluateConfirm(int affirmNo, int flag,boolean LastAffirmor) {
			int result = 0;
//			int result1 = 0;
			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;
//			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "";
//			String sql1 = "";
			sql = "UPDATE EV_DETAIL_APPLY SET ACTIVITY = ? WHERE APPLY_NO = ? and ACTIVITY<>? ";
			//sql1 = "UPDATE AR_DETAIL_BACK T SET T.FLAG = ? WHERE T.AR_PK_NO = ? ";
			//String sql2 = "UPDATE AR_DETAIL T SET (T.AR_ITEM_NO, T.QUANTITY, T.LOCK_YN, T.MOD_DATE, T.UNIT, T.FLAG) = "
			//	    +"(SELECT B.AR_ITEM_NO, B.QUANTITY, B.LOCK_YN, SYSDATE, B.UNIT, 2 FROM AR_DETAIL_BACK B WHERE B.AR_PK_NO = ? AND B.FLAG = 0 ) WHERE T.PK_NO = ? AND T.FLAG = 0 ";
			
			try {
				if( flag == 2 ){
					
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, flag);
					pstmt.setInt(2, affirmNo);
					pstmt.setInt(3, flag);
					result = pstmt.executeUpdate();
					
//					Logger.getLogger(getClass()).debug(sql);
//					pst = conn.prepareStatement(sql1sql);
//					pst.setInt(1, flag);
//					pst.setInt(2, affirmNo);
//					result1 = pst.executeUpdate();
					
				}
	            if( flag == 1 && LastAffirmor){
					
	            	Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, flag);
					pstmt.setInt(2, affirmNo);
					pstmt.setInt(3, flag);
					
					result = pstmt.executeUpdate();
					
//					Logger.getLogger(getClass()).debug(sql1);
//					pst = conn.prepareStatement(sql1);
//					pst.setInt(1, flag);
//					pst.setInt(2, affirmNo);
//					
//					result1 = pst.executeUpdate();
					
				}
//				if( flag == 1 && !LastAffirmor){
//					
//					Logger.getLogger(getClass()).debug(sql);
//					pstmt = conn.prepareStatement(sql);
//					pstmt.setInt(1, flag);
//					pstmt.setInt(2, affirmNo);
//					
//					result = pstmt.executeUpdate();
//					
//					Logger.getLogger(getClass()).debug(sql1);
//					pst = conn.prepareStatement(sql1);
//					pst.setInt(1, flag);
//					pst.setInt(2, affirmNo);
//					
//					result1 = pst.executeUpdate();
//					
//				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}
			
		
		/**
		 * 
		 * 
		   更新评价信息
		 */
		public int updateEvaluateInfo(EvaluateInfo p) {
			int result = 0;

			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			String sql = "";
			sql =   "UPDATE EV_DETAIL_APPLY T"+
					"   SET T.ITEM1  = ?,"+
					"       T.ITEM2  = ?,"+
					"       T.ITEM3  = ?,"+
					"       T.ITEM4  = ?,"+
					"       T.ITEM5  = ?,"+
					"       T.ITEM6  = ?,"+
					"       T.ITEM7  = ?,"+
					"       T.ITEM8  = ?,"+
					"       T.ITEM9  = ?,"+
					"       T.ITEM10 = ?,"+
					"       T.ITEM11 = ?,"+
					"       T.ITEM12 = ?,"+
					"       T.ITEM13 = ?,"+
					"       T.ITEM14 = ?,"+
					"       T.ITEM15 = ?,"+
					"       T.ITEM16 = ?,"+
					"       T.ITEM17 = ?,"+
					"       T.ITEM18 = ?,"+
					"       T.ITEM19 = ?,"+
					"       T.ITEM20 = ?,"+
					"       T.ITEM21 = ?,"+
					"       T.ITEM22 = ?,"+
					"       T.ITEM23 = ?,"+
					"       T.ITEM24 = ?,"+
					"       T.ITEM25 = ?,"+
					"       T.ITEM26 = ?,"+
					"       T.ITEM27 = ?,"+
					"       T.ITEM28 = ?,"+
					"       T.ITEM29 = ?,"+
					"       T.ITEM30 = ?,"+
					"       T.ITEM31 = ?,"+
					"       T.ITEM32 = ?,"+
					"       T.ITEM33 = ?,"+
					"       T.ITEM34 = ?,"+
					"       T.ITEM35 = ?,"+
					"       T.ITEM36 = ?,"+
					"       T.ITEM37 = ?,"+
					"       T.ITEM38 = ?,"+
					"       T.ITEM39 = ?,"+
					"       T.ITEM40 = ?,"+
					"       T.ITEM41 = ?,"+
					"       T.ITEM42 = ?,"+
					"       T.ITEM43 = ?,"+
					"       T.ITEM44 = ?,"+
					"       T.ITEM45 = ?,"+
					"       T.ITEM46 = ?,"+
					"       T.ITEM47 = ?,"+
					"       T.ITEM48 = ?,"+
					"       T.ITEM49 = ?,"+
					"       T.ITEM50 = ?,"+
					"       T.ITEM51 = ?,"+
					"       T.ITEM52 = ?,"+
					"       T.ITEM53 = ?,"+
					"       T.ITEM54 = ?,"+
					"       T.ITEM55 = ?,"+
					"       T.ITEM56 = ?,"+
					"       T.ITEM57 = ?,"+
					"       T.ITEM58 = ?,"+
					"       T.ITEM59 = ?,"+
					"       T.ITEM60 = ?,"+
					"       T.ITEM61 = ?,"+
					"       T.ITEM62 = ?,"+
					"       T.ITEM0 = ?, "+
					"       T.TOTAL = ?, "+
					"       T.REMARK = ? "+
					" WHERE T.APPLY_NO = ?";
		
			try {
				
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					int i=1;
					pstmt.setDouble(i++, p.getITEM1());
					pstmt.setDouble(i++, p.getITEM2());
					pstmt.setDouble(i++, p.getITEM3());
					pstmt.setDouble(i++, p.getITEM4());
					pstmt.setDouble(i++, p.getITEM5());
					pstmt.setDouble(i++, p.getITEM6());
					pstmt.setDouble(i++, p.getITEM7());
					pstmt.setDouble(i++, p.getITEM8());
					pstmt.setDouble(i++, p.getITEM9());
					pstmt.setDouble(i++, p.getITEM10());
					pstmt.setDouble(i++, p.getITEM11());
					pstmt.setDouble(i++, p.getITEM12());
					pstmt.setDouble(i++, p.getITEM13());
					pstmt.setDouble(i++, p.getITEM14());
					pstmt.setDouble(i++, p.getITEM15());
					pstmt.setDouble(i++, p.getITEM16());
					pstmt.setDouble(i++, p.getITEM17());
					pstmt.setDouble(i++, p.getITEM18());
					pstmt.setDouble(i++, p.getITEM19());
					pstmt.setDouble(i++, p.getITEM20());
					pstmt.setDouble(i++, p.getITEM21());
					pstmt.setDouble(i++, p.getITEM22());
					pstmt.setDouble(i++, p.getITEM23());
					pstmt.setDouble(i++, p.getITEM24());
					pstmt.setDouble(i++, p.getITEM25());
					pstmt.setDouble(i++, p.getITEM26());
					pstmt.setDouble(i++, p.getITEM27());
					pstmt.setDouble(i++, p.getITEM28());
					pstmt.setDouble(i++, p.getITEM29());
					pstmt.setDouble(i++, p.getITEM30());
					pstmt.setDouble(i++, p.getITEM31());
					pstmt.setDouble(i++, p.getITEM32());
					pstmt.setDouble(i++, p.getITEM33());
					pstmt.setDouble(i++, p.getITEM34());
					pstmt.setDouble(i++, p.getITEM35());
					pstmt.setDouble(i++, p.getITEM36());
					pstmt.setDouble(i++, p.getITEM37());
					pstmt.setDouble(i++, p.getITEM38());
					pstmt.setDouble(i++, p.getITEM39());
					pstmt.setDouble(i++, p.getITEM40());
					pstmt.setDouble(i++, p.getITEM41());
					pstmt.setDouble(i++, p.getITEM42());
					pstmt.setDouble(i++, p.getITEM43());
					pstmt.setDouble(i++, p.getITEM44());
					pstmt.setDouble(i++, p.getITEM45());
					pstmt.setDouble(i++, p.getITEM46());
					pstmt.setDouble(i++, p.getITEM47());
					pstmt.setDouble(i++, p.getITEM48());
					pstmt.setDouble(i++, p.getITEM49());
					pstmt.setDouble(i++, p.getITEM50());
					pstmt.setDouble(i++, p.getITEM51());
					pstmt.setDouble(i++, p.getITEM52());
					pstmt.setDouble(i++, p.getITEM53());
					pstmt.setDouble(i++, p.getITEM54());
					pstmt.setDouble(i++, p.getITEM55());
					pstmt.setDouble(i++, p.getITEM56());
					pstmt.setDouble(i++, p.getITEM57());
					pstmt.setDouble(i++, p.getITEM58());
					pstmt.setDouble(i++, p.getITEM59());
					pstmt.setDouble(i++, p.getITEM60());
					pstmt.setDouble(i++, p.getITEM61());
					pstmt.setDouble(i++, p.getITEM62());
					pstmt.setDouble(i++, p.getITEM0());
					pstmt.setDouble(i++, p.getTOTAL());
					pstmt.setString(i++, p.getREMARK());
					pstmt.setInt(i++, p.getNo()); 

					result = pstmt.executeUpdate();
			
				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}
		
		
		/**
		 * 
		 * 
		   更新标志
		 */
		public int updateEvaluateFlagInfo(EvaluateInfo p) {
			int result = 0;

			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			String sql = "";
			sql =   "UPDATE EV_DETAIL_APPLY T"+
					"   SET T.ACTIVITY  = 0,T.REMARK = ? "+
					" WHERE T.APPLY_NO = ?";
		
			try {
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, p.getREMARK()); 
					pstmt.setInt(2, p.getNo()); 
					result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}
		
		/**
		 * 
		 * 
		   插入备份评价信息
		 */
		public int insertEvaluateBackInfo(int applyNo) {
			int result = 0;

			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;
			
			
			ResultSet rs = null;
			
			try {
				
			String insertSqlBack = "INSERT INTO EV_DETAIL_BACK T "
		        +"(T.APPLY_NO,T.EVALUATE_MONTH,T.EMPID,T.DEPTID,T.DEPTNAME,T.POSITION,T.AMOUNT,T.PLACE,T.LAST_MONTH_PLACE,T.ACTIVITY,T.REMARK,T.CREATE_DATE,T.CREATED_BY, "
		        +"T.TOTAL,T.ITEM0,T.ITEM1,T.ITEM2,T.ITEM3,T.ITEM4,T.ITEM5,T.ITEM6,T.ITEM7,T.ITEM8,T.ITEM9,T.ITEM10,T.ITEM11,T.ITEM12,T.ITEM13,T.ITEM14,T.ITEM15,T.ITEM16,T.ITEM17,T.ITEM18,T.ITEM19,T.ITEM20," 
		        +"T.ITEM21,T.ITEM22,T.ITEM23,T.ITEM24,T.ITEM25,T.ITEM26,T.ITEM27,T.ITEM28,T.ITEM29,T.ITEM30,T.ITEM31,T.ITEM32,T.ITEM33,T.ITEM34,T.ITEM35,T.ITEM36,T.ITEM37,T.ITEM38,T.ITEM39,T.AFFIRMOR_ID,T.AFFIRM_LEVEL ) "
		        +"SELECT E.APPLY_NO,E.EVALUATE_MONTH,E.EMPID,E.DEPTID,E.DEPTNAME,E.POSITION,E.AMOUNT,E.PLACE,E.LAST_MONTH_PLACE,E.ACTIVITY,E.REMARK,E.CREATE_DATE,E.CREATED_BY, "
		        +"E.TOTAL,E.ITEM0,E.ITEM1,E.ITEM2,E.ITEM3,E.ITEM4,E.ITEM5,E.ITEM6,E.ITEM7,E.ITEM8,E.ITEM9,E.ITEM10,E.ITEM11,E.ITEM12,E.ITEM13,E.ITEM14,E.ITEM15,E.ITEM16,E.ITEM17,E.ITEM18,E.ITEM19,E.ITEM20," 
		        +"E.ITEM21,E.ITEM22,E.ITEM23,E.ITEM24,E.ITEM25,E.ITEM26,E.ITEM27,E.ITEM28,E.ITEM29,E.ITEM30,E.ITEM31,E.ITEM32,E.ITEM33,T.ITEM34,T.ITEM35,T.ITEM36,T.ITEM37,T.ITEM38,T.ITEM39,E.CREATED_BY,1 "
		        +"FROM EV_DETAIL_APPLY E WHERE  E.APPLY_NO = ?  ";	
			 Logger.getLogger(getClass()).debug(applyNo+insertSqlBack);
			 pstmt = conn.prepareStatement(insertSqlBack);//插入备份信息
	         pstmt.setInt(1, applyNo);
	         result = pstmt.executeUpdate();
	
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}
		
		
		/**
		 * 
		 * 
		   更新评价信息
		 */
		public int updateEvaluateInfoByParam(EvaluateAffirmParam p) {
			int result = 0;

			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;

			ResultSet rs = null;
			String sql = "";
			sql =   "UPDATE EV_DETAIL_APPLY T"+
					"   SET T.ITEM1  = ?,"+
					"       T.ITEM2  = ?,"+
					"       T.ITEM3  = ?,"+
					"       T.ITEM4  = ?,"+
					"       T.ITEM5  = ?,"+
					"       T.ITEM6  = ?,"+
					"       T.ITEM7  = ?,"+
					"       T.ITEM8  = ?,"+
					"       T.ITEM9  = ?,"+
					"       T.ITEM10 = ?,"+
					"       T.ITEM11 = ?,"+
					"       T.ITEM12 = ?,"+
					"       T.ITEM13 = ?,"+
					"       T.ITEM14 = ?,"+
					"       T.ITEM15 = ?,"+
					"       T.ITEM16 = ?,"+
					"       T.ITEM17 = ?,"+
					"       T.ITEM18 = ?,"+
					"       T.ITEM19 = ?,"+
					"       T.ITEM20 = ?,"+
					"       T.ITEM21 = ?,"+
					"       T.ITEM22 = ?,"+
					"       T.ITEM23 = ?,"+
					"       T.ITEM24 = ?,"+
					"       T.ITEM25 = ?,"+
					"       T.ITEM26 = ?,"+
					"       T.ITEM27 = ?,"+
					"       T.ITEM28 = ?,"+
					"       T.ITEM29 = ?,"+
					"       T.ITEM30 = ?,"+
					"       T.ITEM31 = ?,"+
					"       T.ITEM32 = ?,"+
					"       T.ITEM33 = ?,"+
					"       T.ITEM34 = ?,"+
					"       T.ITEM35 = ?,"+
					"       T.ITEM36 = ?,"+
					"       T.ITEM37 = ?,"+
					"       T.ITEM38 = ?,"+
					"       T.ITEM39 = ?,"+
					"       T.ITEM0 = ?,"+
					"       T.TOTAL = ?, "+
					"       T.REMARK = ? "+
					" WHERE T.APPLY_NO = ?";
		
			try {
				
					Logger.getLogger(getClass()).debug(sql);
					pstmt = conn.prepareStatement(sql);
					int i=1;
					pstmt.setDouble(i++, p.getItem1());
					pstmt.setDouble(i++, p.getItem2());
					pstmt.setDouble(i++, p.getItem3());
					pstmt.setDouble(i++, p.getItem4());
					pstmt.setDouble(i++, p.getItem5());
					pstmt.setDouble(i++, p.getItem6());
					pstmt.setDouble(i++, p.getItem7());
					pstmt.setDouble(i++, p.getItem8());
					pstmt.setDouble(i++, p.getItem9());
					pstmt.setDouble(i++, p.getItem10());
					pstmt.setDouble(i++, p.getItem11());
					pstmt.setDouble(i++, p.getItem12());
					pstmt.setDouble(i++, p.getItem13());
					pstmt.setDouble(i++, p.getItem14());
					pstmt.setDouble(i++, p.getItem15());
					pstmt.setDouble(i++, p.getItem16());
					pstmt.setDouble(i++, p.getItem17());
					pstmt.setDouble(i++, p.getItem18());
					pstmt.setDouble(i++, p.getItem19());
					pstmt.setDouble(i++, p.getItem20());
					pstmt.setDouble(i++, p.getItem21());
					pstmt.setDouble(i++, p.getItem22());
					pstmt.setDouble(i++, p.getItem23());
					pstmt.setDouble(i++, p.getItem24());
					pstmt.setDouble(i++, p.getItem25());
					pstmt.setDouble(i++, p.getItem26());
					pstmt.setDouble(i++, p.getItem27());
					pstmt.setDouble(i++, p.getItem28());
					pstmt.setDouble(i++, p.getItem29());
					pstmt.setDouble(i++, p.getItem30());
					pstmt.setDouble(i++, p.getItem31());
					pstmt.setDouble(i++, p.getItem32());
					pstmt.setDouble(i++, p.getItem33());
					pstmt.setDouble(i++, p.getItem0());
					pstmt.setDouble(i++, p.getTotal());
					pstmt.setString(i++, p.getRemark());
					pstmt.setInt(i++, p.getApplyNo()); 
					result = pstmt.executeUpdate();
			
				
	            

				
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}
		
		/**
		 * 判断 是否是最后一级决裁者
		 * 
		 * @param affirmNo
		 *            决裁信息序号
		 */
		private boolean isEvaluateLastAffirmor(int affirmNo) {
			Logger.getLogger(getClass()).debug("Checking if this is the Evaluate Last Affirmor........");
			boolean result = false;
			Connection conn = ConnBean.getConn();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//String sql = "SELECT A.APPLY_NO " + "FROM ESS_AFFIRM A, ESS_AFFIRM B " + "WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL " + "AND A.APPLY_NO(+) = B.APPLY_NO " + "AND A.APPLY_TYPE(+) = B.APPLY_TYPE " + "AND B.ESS_AFFIRM_NO = ?";
			String sql = "SELECT A.APPLY_NO FROM EV_AFFIRM A, EV_AFFIRM B WHERE A.AFFIRM_LEVEL(+) > B.AFFIRM_LEVEL AND A.APPLY_NO(+) = B.APPLY_NO  AND B.EV_AFFIRM_NO = ?";

			Logger.getLogger(getClass()).debug(sql);
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, affirmNo);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getString(1) == null) {
						Logger.getLogger(getClass()).debug("Is Last Affirmor !");
						result = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Logger.getLogger(getClass()).debug(e.toString());
			} finally {
				SqlUtil.close(rs, pstmt, conn);
			}
			return result;
		}

		public List getEvaluateNextAffirmor(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateNextAffirmor", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateNextAffirmor data Exception. ", e);
			}
			return list;
		}
		
		public List getEvaluateDeptAndPosition(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateDeptAndPosition", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateDeptAndPosition data Exception. ", e);
			}
			return list;
		}
		
		public List getEvaluateApplyNoForMail(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyNoForMail", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateApplyNoForMail data Exception. ", e);
			}
			return list;
		}
		
	
		public List getEvaluateApplyor(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyor", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateApplyor data Exception. ", e);
			}
			return list;
		}
		
		public List getEvaluateApplyNoCreateBy(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateApplyNoCreateBy", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateApplyNoCreateBy data Exception. ", e);
			}
			return list;
		}
		
		public Object setToEmail(Object parameterObject) throws GlRuntimeException {

			Object result;
			try {

				result = commonSQLMapAdapter.executeQuery("ev.evaluateapply.setToEmail", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException(" Exception. ", e);
			}
			return result;
		}
		
		public List applyEvaluateResult(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.applyEvaluateResult", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("get applyEvaluateResult data Exception. ", e);
			}
			return list;
		}	
		
		public List applyEvaluateResultNoAffirmor(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.applyEvaluateResultNoAffirmor", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("get applyEvaluateResultNoAffirmor data Exception. ", e);
			}
			return list;
		}	
		
		public List getEvaluateDetailList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateDetailList", parameterObject, currentPage, pageSize);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateDetailList data Exception. ", e);
			}
			return list;
		}
		
		public int getEvaluateDetailListCnt(Object parameterObject) throws GlRuntimeException {
			int temp = 0;
			try {
				temp = NumberUtil.parseInt(commonSQLMapAdapter.executeQuery("ev.evaluateapply.getEvaluateDetailListCnt", parameterObject).toString());

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("getEvaluateDetailListCnt data Exception. ", e);
			}
			return temp;
		}
		
		
		/**
		 *  (更新未决裁信息的结果)
		 * 
		 * @param EssAfformParam[]
		 * @param AffirmType
		 *            申请类型 无
		 * @param flag
		 *            决裁结果: 1 通过; 2 否决
		 */
		public int updateEvaluateDetail(List parameterObject) {
			int result = 0 ;
			
			Iterator iterator = parameterObject.iterator();
			while (iterator.hasNext()) {
				EvaluateInfo p = (EvaluateInfo) iterator.next();
				result = updateEvaluateInfo(p);
			}
			
			
//			List<EvaluateAffirmParam> paramList = new ArrayList<EvaluateAffirmParam>();
//			for (int i = 0; i < params.length; i++) {
//				EvaluateAffirmParam p = params[i];
//				if(StringUtil.checkNull(p.getApplyNo())!=null && p.getApplyNo()>0){
//				result = updateEvaluateInfo(p);
//				}
//			}
			return result ;
		}
		
		
		
		/**
		 *  (更新信息的标志)
		 * 
		 * @param flag
		 *            决裁结果: 1 通过; 2 否决
		 */
		public int updateEvaluateDetailFlag(List parameterObject) {
			int result = 0 ;
			
			Iterator iterator = parameterObject.iterator();
			while (iterator.hasNext()) {
				EvaluateInfo p = (EvaluateInfo) iterator.next();
				result = updateEvaluateFlagInfo(p);
				
				this.insertEvaluateBackInfo(p.getNo());//插入备份表中
			}	

			return result ;
		}
		
		public void updateEvaluateDetailSortGongren(Object parameterObject) throws GlRuntimeException {
			
			try {
				commonSQLMapAdapter.update("ev.evaluateapply.updateEvaluateDetailSortGongren", parameterObject);
			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("updateEvaluateDetailSort data Exception. ", e);
			}
			
		}
		
		public void updateEvaluateDetailSortNoGongren(Object parameterObject) throws GlRuntimeException {
			
			try {
				commonSQLMapAdapter.update("ev.evaluateapply.updateEvaluateDetailSortNoGongren", parameterObject);
			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("updateEvaluateDetailSort data Exception. ", e);
			}
			
		}
		
		public void updateEvaluateDetailSortZero(Object parameterObject) throws GlRuntimeException {
			
			try {
				commonSQLMapAdapter.update("ev.evaluateapply.updateEvaluateDetailSortZero", parameterObject);
			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("updateEvaluateDetailSortZero data Exception. ", e);
			}
			
		}
		
		
		public List getEvAffirmorEvaluateAffirm(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvAffirmorEvaluateAffirm", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("get getEvAffirmorEvaluateAffirm data Exception. ", e);
			}
			return list;
		}	
		
		
		public void insertAffirmSetInfo(Object parameterObject,List eaList)throws GlRuntimeException{

			SimpleMap paratmerList =new SimpleMap();
			paratmerList=(SimpleMap)parameterObject;
			try {
				commonSQLMapAdapter.startTransaction();
				
				for(int i=0;i<eaList.size();i++){
					EvaluateAffirm ea = new EvaluateAffirm();
					ea = (EvaluateAffirm)eaList.get(i);
					 SimpleMap parameterObject1 = new SimpleMap();
					 parameterObject1.put("DeptId", ea.getDeptId());
					 parameterObject1.put("selectPosition", ea.getPosition());
					 parameterObject1.put("empID", ea.getAffirmorId());
					 parameterObject1.put("cpnyId", paratmerList.get("cpnyId"));
					 parameterObject1.put("level", ea.getLevel());
					 parameterObject1.put("personId", paratmerList.get("personId"));
					 
					 commonSQLMapAdapter.insert("ev.evaluateapply.insertAffirmSetInfo", parameterObject1);
				}
				
				
				commonSQLMapAdapter.commitTransation();
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("insert Affirm Set Info data Exception. ", e);
			}finally {
				try {
					commonSQLMapAdapter.endTransation();
				} catch (Exception e) {
					logger.error("End transation. " + e.toString());
					throw new GlRuntimeException("End transation Exception. ", e);
				}
			}

		}
		
		
		public void insertItemSetInfo(Object parameterObject,List esList)throws GlRuntimeException{
			SimpleMap paratmerList =new SimpleMap();
			paratmerList=(SimpleMap)parameterObject;
			try {
				commonSQLMapAdapter.startTransaction();
				
				for(int i=0;i<esList.size();i++){
					EvaluateSetItem es = new EvaluateSetItem();
					es = (EvaluateSetItem)esList.get(i);
					 SimpleMap parameterObject1 = new SimpleMap();
					 parameterObject1.put("DeptId", es.getDeptId());
					 parameterObject1.put("selectPosition", es.getPosition());
					 parameterObject1.put("yearMonth", es.getMonth());
					 parameterObject1.put("selectItem", es.getItemId());
					 parameterObject1.put("proportion", es.getProportion());
					 parameterObject1.put("personId", paratmerList.get("personId"));
					 
				commonSQLMapAdapter.insert("ev.evaluateapply.insertItemSetInfo", parameterObject1);
			}
				commonSQLMapAdapter.commitTransation();
			} catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("insert Item Set Info data Exception. ", e);
			}finally {
				try {
					commonSQLMapAdapter.endTransation();
				} catch (Exception e) {
					logger.error("End transation. " + e.toString());
					throw new GlRuntimeException("End transation Exception. ", e);
				}
			}

		}
		
		public List getEvaluateAffirmSetInfo(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateAffirmSetInfo", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("get getEvaluateAffirmSetInfo data Exception. ", e);
			}
			return list;
		}	
		
		public List getEvaluateItemSetInfo(Object parameterObject) throws GlRuntimeException {
			List list = null;
			try {
				list = commonSQLMapAdapter.executeQueryForMulti("ev.evaluateapply.getEvaluateItemSetInfo", parameterObject);

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("get getEvaluateItemSetInfo data Exception. ", e);
			}
			return list;
		}	
		
		public void updateAffirmSetInfo(Object parameterObject)throws GlRuntimeException{
			try{
				commonSQLMapAdapter.update("ev.evaluateapply.updateAffirmSetInfo", parameterObject);
			}catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("update Affirm Set Info data Exception. ",e);
			}
		}
		
		public void updateItemSetInfo(Object parameterObject)throws GlRuntimeException{
			try{
				commonSQLMapAdapter.update("ev.evaluateapply.updateItemSetInfo", parameterObject);
			}catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("update Item Set Info data Exception. ",e);
			}
		}
		
		public void deleteAffirmSetInfo(Object parameterObject)throws GlRuntimeException{
			try{
				commonSQLMapAdapter.update("ev.evaluateapply.deleteAffirmSetInfo", parameterObject);
			}catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("delete Affirm Set Info data Exception. ",e);
			}
		}
		
		public void deleteItemSetInfo(Object parameterObject)throws GlRuntimeException{
			try{
				commonSQLMapAdapter.update("ev.evaluateapply.deleteItemSetInfo", parameterObject);
			}catch (Exception e) {
				logger.error(e.toString());
				throw new GlRuntimeException("delete Item Set Info data Exception. ",e);
			}
		}
		
		public void initalEvParamItem(String evmonth,String cpnyId) {
		    Connection con = null;
		    CallableStatement cs = null; // 建立参数声明
		    try {
		    	Logger.getLogger(getClass()).debug("call ev_item_param_initial(?,?,?) ") ;
		    	Logger.getLogger(getClass()).debug("evmonth = " + evmonth) ;
		    	
		    	
		        con = ConnBean.getConn();
		        cs = con.prepareCall("{call EV_ITEM_PARAM_INITIAL(?,?)}");
		        cs.setString(1, evmonth);
		        cs.setString(2, cpnyId);
		        cs.executeUpdate();
		    } catch (Exception ex) {
		        Logger.getLogger(getClass()).debug(
		                "EvItemParam_inital=" + ex.getMessage());
		    } finally {
		        SqlUtil.close(cs, con);
		    }
		}
		
	
		
		public List getEvaluateItem(String yearMonth, String deptId, String position,String admin,int level) {
			List list = new ArrayList();
			Connection conn = ConnBean.getConn();
			Connection conn2 = ConnBean.getConn();
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			String sql="";	
			String deptString="";
		try{
			
			if("".equals(deptId)|| deptId == null){
				if(level==1){
					sql = "SELECT  MAX(T.AFFIRM_DEPTID) AS AFFIRM_DEPTID FROM EV_AFFIRM_RELATION T  WHERE T.AFFIRMOR_ID = ? AND T.AFFIRM_LEVEL = 1" ;
				}else{
					sql = "SELECT  MAX(T.AFFIRM_DEPTID) AS AFFIRM_DEPTID FROM EV_AFFIRM_RELATION T  WHERE T.AFFIRMOR_ID = ? AND T.AFFIRM_LEVEL <> 1" ;
				}

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, admin);
				//pstmt.setInt(2, level);
				rs = pstmt.executeQuery();
				while (rs.next()){
					deptString = rs.getString("AFFIRM_DEPTID");
				}
			}else{
				
				deptString = deptId;
			}

		   if (!"".equals(deptString) && deptString != null) {// 决裁对象为部门				
			  sql = " SELECT HD.DEPTID FROM HR_DEPARTMENT HD START WITH HD.DEPTID = ?" + 
	                " CONNECT BY PRIOR HD.PARENT_DEPT_ID = HD.DEPTID" ;
					
			        pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, deptString);
					rs = pstmt.executeQuery();
					Logger.getLogger(getClass()).debug("SQL3: " + sql);
					Logger.getLogger(getClass()).debug("deptId: " + deptString);
				while (rs.next()){

					sql = " SELECT distinct EI.ITEM_ID, EI.ITEM_NAME,EI.ID,EIR.EVALUATE_ITEM_PROPORTION "+
			              " FROM EV_ITEM_RELATION EIR, EV_ITEM EI "+
						  " WHERE EIR.EVALUATE_ITEM_ID = EI.ITEM_ID "+
						  " AND EIR.EVALUATE_MONTH = ? "+
						  " AND 'C_12004' || substr(EIR.EVALUATE_POSITION, 8, 13) = ? "+
						  " AND EIR.EVALUATE_DEPTID = ? " +
						  " ORDER BY EI.ID ASC";
		          
					Logger.getLogger(getClass()).debug("SQL4: " + sql);
					Logger.getLogger(getClass()).debug("DEPTID: " + rs.getString("DEPTID"));
					Logger.getLogger(getClass()).debug("YEARMONTH: " + yearMonth);
					Logger.getLogger(getClass()).debug("POSITION: " + position);
					pstmt2 = conn2.prepareStatement(sql);
					pstmt2.setString(1, yearMonth);
					pstmt2.setString(2, position);
					pstmt2.setString(3, rs.getString("DEPTID"));
					
					String a=rs.getString("DEPTID");
					
					rs2 = pstmt2.executeQuery();
					while (rs2.next())
					{

					   EvaluateItem ei=new EvaluateItem();	
					   ei.setItemId(rs2.getString("ITEM_ID"));
					   ei.setItemName(rs2.getString("ITEM_NAME"));
					   ei.setItemProportion(rs2.getString("EVALUATE_ITEM_PROPORTION"));
					   list.add(ei);

					}
					if (list.size() > 0)
					{
						break ;
					}
				}
			} 
	   
		} catch (SQLException se) {
			se.printStackTrace();
			Logger.getLogger(getClass()).debug(se.toString());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs2,pstmt2,conn2);
			SqlUtil.close(rs, pstmt, conn);
		}
			return list;
	}
		
		public String retrieveEvAffirmAdmin(Object parameterObject) throws GlRuntimeException {
			String empid = "";
			try {
				empid = StringUtil.checkNull(commonSQLMapAdapter.executeQuery("ev.evaluateapply.retrieveEvAffirmAdmin", parameterObject));

			} catch (Exception e) {

				logger.error(e.toString());
				throw new GlRuntimeException("retrieveEvAffirmAdmin data Exception. ", e);
			}
			
			return empid;
		}	
	
}
