package com.ait.ga.dao.festivalpresent;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.core.CommonSQLMapAdapter;
import com.ait.sqlmap.util.SimpleMap;

public class FestivalPresentDAO {
	
	private CommonSQLMapAdapter commonSQLMapAdapter = null;
	private static final Logger logger = Logger.getLogger(FestivalPresentDAO.class);
	
	public FestivalPresentDAO(){
		commonSQLMapAdapter = new CommonSQLMapAdapter();
	}
	/**
	 * select festival present 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentList(Object parameterObject)throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalPresent", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival present data Exception.",e);
		}
		return list ;
	}
	
	/**
	 * select festival present by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list ;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalPresent", parameterObject, currentPage, pageSize);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival present by paging data Exception. ",e);
		}
		return list ;
	}
	
	/**
	 * select festival present Cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalPresentCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try{
			obj = commonSQLMapAdapter.executeQuery("ga.festivalpresent.selectFestivalPresentCut", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select fesival present cut data Exception.",e);
		}
		return obj;
	}
	
	/**
	 * insert festival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFestivalPresent(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.insert("ga.festivalpresent.insertFestivalPresent", parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert festival present data Exception. ",e);
		}
	}
	
	/**
	 * update festival present 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalPresent(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.festivalpresent.updateFestivalPresent", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update festival present data Exception. ",e);
		}
	}
	
	/**
	 * delete festival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalPresent(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.festivalpresent.deleteFestivalPresent", parameterObject);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * select festival scheme list .
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalSchemeList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalScheme", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival scheme data Exception.",e);
		}
		return list;
	}
	
	/**
	 * select festival scheme list by paging .
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalSchemeList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalScheme", parameterObject, currentPage, pageSize);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival scheme by paging data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * select festival scheme count .
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalSchemeCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.festivalpresent.selectFestivalSchemeCut",parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival scheme cut data Exception. ",e);
		}
		return obj;
	}
	
	/**
	 * insert festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFestivalScheme(Object parameterObject)throws GlRuntimeException{
		try{
			SimpleMap simpleMap = new SimpleMap();
			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.insert("ga.festivalpresent.insertFestivalScheme",parameterObject);
			
			for (int i = 0; i <= Integer.parseInt(((SimpleMap)parameterObject).getString("maxRowNum")); i++) {
				
				simpleMap.setString("presentNo", ((SimpleMap)parameterObject).getString("presentNo"+i));
				simpleMap.setString("quentity", ((SimpleMap)parameterObject).getString("quentity"+i));
				commonSQLMapAdapter.insert("ga.festivalpresent.insertFestivalSchemeDetail", simpleMap);
			}	
			commonSQLMapAdapter.commitTransation();
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("insert festival scheme data Exception. ",e);
		}finally{
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	
	/**
	 * update festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalScheme(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.festivalpresent.updateFestivalScheme", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update festival scheme data Exception. ",e);
		}
	}
	
	/**
	 * delete festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalScheme(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.startTransaction();
			//delete scheme
			commonSQLMapAdapter.update("ga.festivalpresent.deleteFestivalScheme",parameterObject);
			//delete scheme detail
			commonSQLMapAdapter.update("ga.festivalpresent.deleteFestivalSchemeDetial",parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete festival scheme data Exception. ",e);
		}
		finally{
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * get festival present name .
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentName(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getFestivalPresentName", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get festival present name data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * select festival scheme detail list .
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalSchemeDetaiList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list  = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalSchemeDetail", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival scheme detail data Exception. ",e);
		}
		return list ;
	}
	
	/**
	 * select festival confrim view
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalConfrimView(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalConfrimView", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival confrim view data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * select festival confrim view by paging 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalConfrimView(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalConfrimView", parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival confrim view by paging data Exception .",e);
		}
		return list ;
	}
	
	/**
	 * select festival confrim view count 
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalConfrimViewCut(Object parameterObject)throws GlRuntimeException{
		Object obj ;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.festivalpresent.selectFestivalConfrimViewCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival confrim view count data Exception.",e);
		}
		return obj;
	}
	
	/**
	 * delete festival present apply
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalPresentApply(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.update("ga.festivalpresent.deleteFestivalPresentApply",parameterObject);
			commonSQLMapAdapter.update("ga.festivalpresent.deleteFestivalPresentAffirm",parameterObject);
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("delete festival present apply data Exception. ",e);
		}
		finally{
			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * get scheme tatal price 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSchemeTotalPrice()throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getSchemeTotalPrice");
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get scheme total price data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * select festival present apply list
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentApplyList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalPresentApply", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival present apply list data Exception. ",e);	
		}
		return list;
	}
	
	/**
	 * select fsetival present apply list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivaPresentApplyList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.selectFestivalPresentApply", parameterObject, currentPage, pageSize);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival present apply list by paging data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * select festival present apply count
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFesivalPresentApplyCut(Object parameterObject)throws GlRuntimeException{
		Object obj;
		try {
			obj = commonSQLMapAdapter.executeQuery("ga.festivalpresent.selectFestivalPresentApplyCut", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("select festival present apply count data Exception. ",e);
		}
		return obj;
	}
	
	/**
	 * get scheme name 
	 * @return list
	 */
	public List getSchemeName(Object parameterObject){
		List list;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getSchemeName",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get scheme name data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * get scheme detail list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSchemeDetaiList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getSchemeDetail", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get scheme detail data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * insert apply festival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertApplyFestivalPresent(Object parameterObject)throws GlRuntimeException{
		try {
//			SimpleMap simpleMap = new SimpleMap();
//			commonSQLMapAdapter.startTransaction();
			
			commonSQLMapAdapter.insert("ga.festivalpresent.insertApplyFestivalPresent", parameterObject);
//			simpleMap.setString("personId", ((SimpleMap)parameterObject).getString("personId"));
//			for (int i = 0; i < ((SimpleMap)parameterObject).getInt("j"); i++) {
//			
//				simpleMap.setString("affirmLevel", ((SimpleMap)parameterObject).getString("affirmLevel"+i));
//				simpleMap.setString("affirmId", ((SimpleMap)parameterObject).getString("affirmId"+i));
//				commonSQLMapAdapter.insert("ga.festivalpresent.insertFestivalPresentAffirm",simpleMap);
//			}
//			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error("Apply present error." + e);
			throw new GlRuntimeException("Apply present error.", e);
//		} finally {
//
//			try {
//				commonSQLMapAdapter.endTransation();
//			} catch (Exception e) {
//				logger.error("End transation. " + e.toString());
//				throw new GlRuntimeException("End transation Exception. ", e);
//			}
		}
	}
	
	
	/**
	 * get festival present apply affirmor
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentApplyAffirmor(Object parameterObject)throws GlRuntimeException{
		List list ;
		try{
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getFestivalPresentApplyAffirmor",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get festival present apply affirmor",e);
		}
		return list;
	}
	
	/**
	 * affirm festival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void affirmFestivalPresent(Object parameterObject)throws GlRuntimeException{
		try {
			
			commonSQLMapAdapter.startTransaction();
			commonSQLMapAdapter.update("ga.festivalpresent.updateFestivalPresentAffirm", parameterObject);
			List affirmorList = this.getFestivalPresentApplyAffirmor(parameterObject);
			int flag=0,i=0;
			Iterator iter = affirmorList.iterator();
			for (; iter.hasNext();i++) {
				SimpleMap simpleMap = (SimpleMap) iter.next();
				if(Integer.parseInt(simpleMap.getString("AFFIRM_FLAG"))!=0){
					flag++;
				}
			}
			if(flag == i){
				((SimpleMap)parameterObject).setInt("applyFlag", 1);
				this.UpdateFestivalPresentApplyForAffirm(parameterObject);
			}
			commonSQLMapAdapter.commitTransation();
		} catch (Exception e) {
			logger.error("Apply present error." + e);
			throw new GlRuntimeException("Apply present error.", e);
		} finally {

			try {
				commonSQLMapAdapter.endTransation();
			} catch (Exception e) {
				logger.error("End transation. " + e.toString());
				throw new GlRuntimeException("End transation Exception. ", e);
			}
		}
	}
	
	/**
	 * update festival present apply for affirm
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void UpdateFestivalPresentApplyForAffirm(Object parameterObject)throws GlRuntimeException{
		try {
			commonSQLMapAdapter.update("ga.festivalpresent.updateFestivalPresentApplyForAffirm", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update festival present apply for affirm data Exception. ",e);
		}
	}
	
	/**
	 * update festival present apply for confirm
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalPresentApplyForConfirm(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.festivalpresent.updateFestivalPresentApplyForConfirm",parameterObject);
		}catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("update festival present apply for confirm data Exception.",e);
		}
	}
	
	/**
	 * get festival present provide report list
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentProvideReportList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getFestivalPresentProvideReportList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get festival present provide reprot list data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get festival present scheme report list 
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentSchemeReportList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list  = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getFestivalPresentSchemeReportList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get festival present scheme reprot list data Exception.",e);
		}
		return list;
	}
	
	/**
	 * get festival present quentity report list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentQuentityReportList(Object parameterObject)throws GlRuntimeException{
		List list;
		try {
			list  = commonSQLMapAdapter.executeQueryForMulti("ga.festivalpresent.getFestivalPresentQuentityReportList", parameterObject);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new GlRuntimeException("get festival present quentity report list data Exception. ",e);
		}
		return list;
	}
	
	/**
	 * update scheme activity 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSchemeActivity(Object parameterObject)throws GlRuntimeException{
		try{
			commonSQLMapAdapter.update("ga.festivalpresent.updateSchemeActivity", parameterObject);
		}catch(Exception e){
			logger.error(e.toString());
			throw new GlRuntimeException("update scheme activity data Exception. ",e);
		}
	}
}
