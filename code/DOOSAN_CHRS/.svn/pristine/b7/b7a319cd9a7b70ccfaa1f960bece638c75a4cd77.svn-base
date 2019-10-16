package com.ait.ga.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.dao.GaDAOFactory;
import com.ait.ga.dao.asset.AssetDAO;
import com.ait.ga.dao.certificate.CertificateDAO;
import com.ait.ga.dao.festivalpresent.FestivalPresentDAO;
import com.ait.ga.dao.food.FoodDAO;
import com.ait.ga.dao.inspection.InspectionDAO;
import com.ait.ga.dao.present.PresentDAO;
import com.ait.ga.dao.smock.SmockDAO;
import com.ait.ga.dao.smock.SmockProvideDAO;
import com.ait.ga.dao.smock.SmockRelationDAO;
import com.ait.ga.dao.visit.ListRecordDAO;
import com.ait.ga.dao.washhouse.WashhouseDAO;
import com.ait.sqlmap.util.SimpleMap;

/**
 * <p>
 * Title:Arservices
 * </p>
 * 
 * <p>
 * Description: Arservices 是一个代理类。它将考勤模块的数据库操作与
 * 前台操作连接起来。考勤模块的所有数据库的操作方法都可以通过这个代理来 执行。前台操作和servlet操作都直接与Arservices这个代理进行联系。
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * 
 * <p>
 * Company: AIT
 * </p>
 */
public class GaServices {

	public GaServices() {
	}

	/**
	 * visit record list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize 
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List empAllList(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.empAllList(parameterObject);
	}

	public List visitRecordList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.visitRecordList(parameterObject, currentPage, pageSize);
	}

	/**
	 * visit record list count
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object visitRecordListCount(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.visitRecordListCount(parameterObject);
	}

	public void visitRecordAdd(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		listRecordDAO.visitRecordAdd(parameterObject);
	}

	public void visitRecordUpdate(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		listRecordDAO.visitRecordUpdate(parameterObject);
	}

	public Object cardApplicationCount(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.cardApplicationCount(parameterObject);
	}

	public List cardApplication(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.cardApplication(parameterObject, currentPage, pageSize);
	}

	public List getAllCardStatus(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.getAllCardStatus(parameterObject);
	}

	public List getCardEndDate() throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.getCardEndDate();
	}

	public void updateCardStatus(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		listRecordDAO.updateCardStatus(parameterObject);
	}

	public List getFirstAffirmEmail(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.getFirstAffirmEmail(parameterObject);
	}

	public List getFirstAffirmEmail1(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.getFirstAffirmEmail1(parameterObject);
	}

	public void AddMaintenance(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		listRecordDAO.AddMaintenance(parameterObject);
	}

	public List troubleManger(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.troubleManger(parameterObject, currentPage, pageSize);
	}

	public int troubleMangerInt(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.troubleMangerInt(parameterObject);
	}

	public void UpdatevoitureManger(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		listRecordDAO.UpdatevoitureManger(parameterObject);
	}

	public List UpdateViewVoitureManger(Object parameterObject) throws GlRuntimeException {

		ListRecordDAO listRecordDAO = (ListRecordDAO) GaDAOFactory.getInstance().getListRecordDAO();

		return listRecordDAO.UpdateViewVoitureManger(parameterObject);
	}

	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentList(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentList(parameterObject);
	}
	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List ytglPresentList(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.ytglPresentList(parameterObject);
	}

	/**
	 * Retrieve present list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveUpCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrieveUpCheckAccount(parameterObject);
	}

	/**
	 * Retrieve present list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrievePresentList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Retrieve present list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List ytglPresentList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.ytglPresentList(parameterObject, currentPage, pageSize);
	}

	/**
	 * Retrieve present list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrievePresentListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentListCnt(parameterObject);
	}
	/**
	 * Retrieve present list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object ytglPresentListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.ytglPresentListCnt(parameterObject);
	}

	/**
	 * Retrieve BankAccount list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveBankAccountList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrieveBankAccountList(parameterObject, currentPage, pageSize);
	}

	/**
	 * Retrieve BankAccount list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveCheckAccountList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrieveCheckAccountList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Retrieve BankAccount list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveBankAccountListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrieveBankAccountListCnt(parameterObject);
	}
	
	/**
	 * Retrieve BankAccount list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveCheckAccountListCnt(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrieveCheckAccountListCnt(parameterObject);
	}

	/**
	 * Insert present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresent(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresent(parameterObject);
	}
	public void insertPresent1(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresent1(parameterObject);
	}
	public void insertCheckBank(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertCheckBank(parameterObject);
	}
	public void insertCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertCheckAccount(parameterObject);
	}
	public void updateSaveCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updateSaveCheckAccount(parameterObject);
	}
	public void insertPresent2(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresent2(parameterObject);
	}
	public void insertPresent3(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresent3(parameterObject);
	}

	/**
	 * Update CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresent(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresent(parameterObject);
	}
	
	/**
	 * Update CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentYtgl(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentYtgl(parameterObject);
	}

	/**
	 * Update CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCheckBank(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updateCheckBank(parameterObject);
	}

	/**
	 * delete CheckBank
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCheckAccount(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.deleteCheckAccount(parameterObject);
	}

	/**
	 * udpate present multi
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresent(List parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresent(parameterObject);
	}

	/**
	 * Retrieve present apply list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List<SimpleMap> retrievePresentApplyList(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentApplyList(parameterObject);
	}

	/**
	 * Retrieve present apply list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrievePresentApplyList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentApplyList(parameterObject, currentPage, pageSize);
	}

	/**
	 * Retrieve present apply count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrievePresentApplyCnt(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentApplyCnt(parameterObject);
	}

	/**
	 * Retrieve present affirm list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentAffirmList(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentAffirmList(parameterObject);
	}

	/**
	 * Retrieve present apply detail
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentApplyDetail(parameterObject);
	}

	/**
	 * Retrieve present information
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrievePresentInformation(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentInformation(parameterObject);
	}
	
	/**
	 * Retrieve present apply quentity
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrievePresentApplyQuentity(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.retrievePresentApplyQuentity(parameterObject);
	}

	/**
	 * Insert present apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentApply(SimpleMap parameterObject) throws GlRuntimeException {

		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentApply(parameterObject);
	}
	
	/**
	 * Insert present apply detial
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentApplyDetail(parameterObject);
	}
	
	/**
	 * Insert present apply detail multi
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentApplyDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentApplyDetail(parameterObject, isAutoTrans);
	}
	
	/**
	 * Insert present affirm
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertPresentAffirm(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentAffirm(parameterObject);
	}
	
	/**
	 * Insert present affirm by list
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentAffirm(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentAffirm(parameterObject, isAutoTrans);
	}
	
	/**
	 * apply present
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyPresent(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.applyPresent(parameterObject);
	}
	
	/**
	 * update present affirm
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentAffirm(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentAffirm(parameterObject);
	}
	
	/**
	 * Update present affirm By batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePresentAffirm(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentAffirm(parameterObject, isAutoTrans);
	}
	
	/**
	 * update present apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentApply(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentApply(parameterObject);
	}
	
	/**
	 * Update present apply by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePresentApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentApply(parameterObject, isAutoTrans);
	}
	
	/**
	 * affirm present by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void affirmPresent(List parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.affirmPresent(parameterObject);
	}
	
	/**
	 * affirm present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void affirmPresent(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.affirmPresent(parameterObject);
	}
	
	/**
	 * Update present detail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updatePresentDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentDetail(parameterObject);
	}
	
	/**
	 * Update present detail by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updatePresentDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.updatePresentDetail(parameterObject, isAutoTrans);
	}

	/**
	 * Insert present by apply
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentByApply(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentByApply(parameterObject);
	}
	
	/**
	 * Insert present by apply list
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertPresentByApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.insertPresentByApply(parameterObject, isAutoTrans);
	}
	
	/**
	 * Confirm present
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmPresent(SimpleMap parameterObject, boolean opFlag) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.confirmPresent(parameterObject, opFlag);
	}
	
	/**
	 * Confirm present by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmPresent(List parameterObject, boolean opFlag) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		dao.confirmPresent(parameterObject, opFlag);
	}
	
	/**
	 * get affirm mail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getAffirmMail(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.getAffirmMail(parameterObject);
	}
	
	/**
	 * Get present quentity statistics
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentQuentStatistics(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.getPresentQuentStatistics(parameterObject);
	}
	
	/**
	 * Get present send statistics
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentSendStatistics(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.getPresentSendStatistics(parameterObject);
	}
	
	/**
	 * Get present send detail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getPresentSendDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		PresentDAO dao = (PresentDAO) GaDAOFactory.getInstance().getPresentDAO();

		return dao.getPresentSendDetail(parameterObject);
	}
	
	/**
	 * Retrieve asset list number
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int getAssetListNumber(Object parameterObject) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		return assetDAO.getAssetListNumber(parameterObject);
	}
	
	/**
	 * Retrieve asset list
	 * @param parameterObject
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List getAssetList(Object parameterObject) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		return assetDAO.getAssetList(parameterObject);
	}
	
	/**
	 * Retrieve asset list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getAssetList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		return assetDAO.getAssetList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Insert asset
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertAsset(Object parameterObject) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		assetDAO.insertAsset(parameterObject);
	}
	
	/**
	 * Update asset
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateAsset(Object parameterObject) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		assetDAO.updateAsset(parameterObject);
	}
	
	/**
	 * Delete asset
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteAsset(Object parameterObject) throws GlRuntimeException {

		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		assetDAO.deleteAsset(parameterObject);
	}
	
	/**
	 * Retrieve asset name list
	 * 
	 * @return List
	 * @throws GlRuntiemException
	 */
	public List getAssetNameList(Object parameterObject)throws GlRuntimeException{
		
		AssetDAO assetDAO = (AssetDAO) GaDAOFactory.getInstance().getAssetDAO();
		return assetDAO.getAssetNameList(parameterObject);
	}
	
	/**
	 * Retrieve inspection list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getInspectionList(Object parameterObject)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		return inspectionDAO.retrieveInspectionList(parameterObject);
	}
	
	/**
	 * Retrieve inspection list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return List
	 * @throws GlRuntimeException
	 */
	public List getInspectionList(Object parameterObject,int currentPage ,int pageSize)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		return inspectionDAO.retrieveInspectionList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Retrieve inspection number
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public Object getInspectionNumber(Object parameterObject)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		return inspectionDAO.retrieveInspectionNumber(parameterObject);
	}
	
	/**
	 * Insert inspection
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertInspection(Object parameterObject)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		inspectionDAO.insertInspection(parameterObject);
	}
	
	/**
	 * update inspection
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateInspection(Object parameterObject)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		inspectionDAO.updateInspection(parameterObject);
	}
	
	/**
	 * Confirm inspection 
	 * 
	 * @param Object
	 * 
	 */
	public void confirmInspection(Object parameterObject)throws GlRuntimeException{
	
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		inspectionDAO.confirmInspection(parameterObject);
	}
	/**
	 * delete inspection
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteInspection(Object parameterObject)throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		inspectionDAO.deleteInspection(parameterObject);
	}
	
	/**
	 * seatch asset list
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List searchAssetList (Object parameterObject) throws GlRuntimeException{
		
		InspectionDAO inspectionDAO = (InspectionDAO)GaDAOFactory.getInstance().getInspectionDAO();
		return inspectionDAO.searchAsset(parameterObject);
	}
	
	/**
	 * select asset test
	 * 
	 * @param parameterObject
	 * @return int
	 * @throws GlRuntimeException
	 */
	public int selectAssetTest(Object parameterObject)throws GlRuntimeException{
		
		AssetDAO assetDAO = (AssetDAO)GaDAOFactory.getInstance().getAssetDAO();
		return assetDAO.selectAssetTest(parameterObject);
	}
	
	/**
	 * select smock list
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockList(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.selectSmockList(parameterObject);
	}
	
	/**
	 * select smock list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockLsit(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.selectSmockList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select smock number
	 * 
	 * @param parameterObject
	 * @return Object
	 * @throws GlRuntimeException
	 */
	public Object selectSmockNumber(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.selectSmockNumber(parameterObject);
	}
	
	/**
	 * insert smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmock(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO) GaDAOFactory.getInstance().getSmockDAO();
		smockDAO.insertSmock(parameterObject);
	}
	
	/**
	 * update smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSmock(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		smockDAO.updateSmock(parameterObject);
	}
	
	/**
	 * delete smock
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmock(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		smockDAO.deleteSmcok(parameterObject);
	}
	
	/**
	 * Retrieve clothing list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveClothingList(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveClothingList(parameterObject);
	}
	
	/**
	 * Retrieve clothing list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveClothingList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveClothingList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Retrieve clothing list count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveClothingListCnt(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveClothingListCnt(parameterObject);
	}
	
	/**
	 * Retrieve clothing code
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List retrieveClothingCode(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveClothingCode(parameterObject);
	}
	
	/**
	 * Insert clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertClothing(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.insertClothing(parameterObject);
	}
	
	/**
	 * Update clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateClothing(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.updateClothing(parameterObject);
	}
	
	/**
	 * Delete clothing
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteClothing(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.deleteClothing(parameterObject);
	}
	
	/**
	 * Retrieve wash apply list
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveWashApplyList(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveWashApplyList(parameterObject);
	}
	
	/**
	 * Retrieve wash apply list by paging
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveWashApplyList(SimpleMap parameterObject, int currentPage, int pageSize) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveWashApplyList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * Retrieve wash apply list
	 * 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List retrieveWashApplyExcelList(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveWashApplyExcelList(parameterObject);
	}
	
	/**
	 * Retrieve wash apply count
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object retrieveWashApplyCnt(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveWashApplyCnt(parameterObject);
	}
	
	/**
	 * Retrieve wash apply detail
	 * 
	 * @param parameterObject
	 * @return
	 */
	public List retrieveWashApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.retrieveWashApplyDetail(parameterObject);
	}
	
	/**
	 * Insert wash apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertWashApply(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.insertWashApply(parameterObject);
	}
	
	/**
	 * Insert wash apply detial
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertWashApplyDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.insertWashApplyDetail(parameterObject);
	}
	
	/**
	 * Insert wash apply detail multi
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void insertWashApplyDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.insertWashApplyDetail(parameterObject, isAutoTrans);
	}
	
	/**
	 * Get current apply No
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Object getCurrApplyNo() throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.getCurrApplyNo();
	}
	
	/**
	 * apply wash
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public Object applyWash(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.applyWash(parameterObject);
	}
	
	/**
	 * update wash apply
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashApply(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.updateWashApply(parameterObject);
	}
	
	/**
	 * Update wash apply by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashApply(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.updateWashApply(parameterObject, isAutoTrans);
	}
	
	/**
	 * Update wash detail
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateWashDetail(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.updateWashDetail(parameterObject);
	}
	
	/**
	 * Update wash detail by batch
	 * 
	 * @param parameterObject
	 * @param isAutoTrans
	 * @throws GlRuntimeException
	 */
	public void updateWashDetail(List parameterObject, boolean isAutoTrans) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.updateWashDetail(parameterObject, isAutoTrans);
	}
	
	/**
	 * Confirm wash by batch
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void confirmWash(List parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		washhouseDAO.confirmWash(parameterObject);
	}
	
	/**
	 * get wash affirm mail
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashAffirmMail(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.getWashAffirmMail(parameterObject);
	}
	
	/**
	 * get wash apply Statistics by person
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashStatisticsByPerson(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.getWashStatisticsByPerson(parameterObject);
	}
	
	/**
	 * get wash apply Statistics by department
	 * 
	 * @param parameterObject
	 * @return
	 * @throws GlRuntimeException
	 */
	public List getWashStatisticsByDept(SimpleMap parameterObject) throws GlRuntimeException {
		
		WashhouseDAO washhouseDAO = (WashhouseDAO)GaDAOFactory.getInstance().getWashhouseDAO();
		return washhouseDAO.getWashStatisticsByDept(parameterObject);
	}
	
	/**
	 * select smock relation list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockRelationList(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.selectSmockRelationList(parameterObject);
	}
	
	/**
	 * select smock relation list
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockRelationList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.selectSmockRelationList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select smock relation number
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectSmockRelationNumber(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO) GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.selectSmockRelationNumber(parameterObject);
	}
	
	/**
	 * insert smock relation 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmockRelation(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		smockRelationDAO.insertSmockRelation(parameterObject);
	}
	
	/**
	 * update smock relation
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSmockRelation(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		smockRelationDAO.updateSmockRelation(parameterObject);
	}
	
	/**
	 * delete smock relation
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmockRelation(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		smockRelationDAO.deleteSmcokRelation(parameterObject);
	}
	
	/**
	 * get smock name list
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockNameList()throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.getSmockNameList();
	}
	
	/**
	 * get employee info
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getEmployeeInfoList(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.getEmployeeInfoList(parameterObject);
	}
	
	/**
	 * get smock relation unit 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockRelationUnitList(Object parameterObject)throws GlRuntimeException{
		
		SmockRelationDAO smockRelationDAO = (SmockRelationDAO)GaDAOFactory.getInstance().getSmockRelationDAO();
		return smockRelationDAO.getSmockRelationUnitList(parameterObject);
	}
	
	/**
	 * select smock provide list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideList(Object parameterObject)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		return smockProvideDAO.selectSmockProvideList(parameterObject);
	}
	
	/**
	 * select smock provide list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideList(Object parameterObject,int currentPage, int pageSize)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		return smockProvideDAO.selectSmockProvideList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * insert smock provide 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertSmockProvide(Object parameterObject)throws GlRuntimeException {
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		smockProvideDAO.insertSmockProvide(parameterObject);
	}	
	
	/**
	 * select smock provide cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectSmockProvideCut(Object parameterObject)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		return smockProvideDAO.selectSmockProvideCut(parameterObject);
	}
	
	/**
	 * select smock provide history record
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectSmockProvideHistoryRecord(Object parameterObject)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		return smockProvideDAO.selectSmockProvideHistoryRecord(parameterObject);
	}
	
	/**
	 * delete smock provide 
	 * 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteSmockProvide(Object parameterObject)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		smockProvideDAO.deleteSmockProvide(parameterObject);
	}
	
	/**
	 * get period name list 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getPeriodNameList(Object parameterObject)throws GlRuntimeException{
		
		SmockProvideDAO smockProvideDAO = (SmockProvideDAO)GaDAOFactory.getInstance().getSmockProvideDAO();
		return smockProvideDAO.getPeriodNameList(parameterObject);
	}
	
	/**
	 * get smock code name
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockCodeName(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.getSmockCodeName(parameterObject);
	}
	
	/**
	 * get smock total record
	 * 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockTotalRecord(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.getSmockTotalRecord(parameterObject);
	}
	
	/**
	 * get smock provide record 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSmockProvideRecord(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.getSmockProvideRecord(parameterObject);
	}
	
	/**
	 * select festival present list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.selectFestivalPresentList(parameterObject);
	}
	
	/**
	 * select festival present list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.selectFestivalPresentList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select festival present cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalPresentCut(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.selectFestivalPresentCut(parameterObject);
	}
	
	/**
	 * insert festival present 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFestivalPresent(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.insertFestivalPresent(parameterObject);
	}
	
	/**
	 * update fesival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalPresent(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.updateFestivalPresent(parameterObject);
	}
	
	/**
	 * delete festival present
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalPresent(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.deleteFestivalPresent(parameterObject);
	}
	
	/**
	 * select festival scheme list .
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalSchemeList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalSchemeDAO.selectFestivalSchemeList(parameterObject);
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
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalSchemeDAO.selectFestivalSchemeList(parameterObject,
				currentPage, pageSize);
	}
	
	/**
	 * select festival scheme count .
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalSchemeCut(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalSchemeDAO.selectFestivalSchemeCut(parameterObject);
	}
	
	/**
	 * insert festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFestivalScheme(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalSchemeDAO.insertFestivalScheme(parameterObject);
	}
	
	/**
	 * update festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalScheme(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalSchemeDAO.updateFestivalScheme(parameterObject);
	}
	
	/**
	 * delete festival scheme .
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalScheme(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalSchemeDAO.deleteFestivalScheme(parameterObject);
	}
	
	/**
	 * get festival present name
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentName(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalSchemeDAO.getFestivalPresentName(parameterObject);
	}
	
	/**
	 * select festival scheme detail list .
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List selectFestivalSchemeDetaiList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalSchemeDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalSchemeDAO.selectFestivalSchemeDetaiList(parameterObject);
	}
	
	/**
	 * select festival present apply list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentApplyList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivaPresentApplyDAO.selectFestivalPresentApplyList(parameterObject);
	}
	
	/**
	 * select festival present apply list by paging
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalPresentApplyList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivaPresentApplyDAO.selectFestivaPresentApplyList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select festival present apply count
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalPresentApplyCut(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivaPresentApplyDAO.selectFesivalPresentApplyCut(parameterObject);
	}
	
	/**
	 * get scheme name
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSchemeName(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivaPresentApplyDAO.getSchemeName(parameterObject);
	}
	
	/**
	 * get scheme detail list
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getSchemeDetaiList(Object parameterObject)throws GlRuntimeException {
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivaPresentApplyDAO.getSchemeDetaiList(parameterObject);
	}
	
	/**
	 * insert apply festival present 
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertApplyFestivalPresent(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivaPresentApplyDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivaPresentApplyDAO.insertApplyFestivalPresent(parameterObject);
	}
	
	/**
	 * select festival confrim view
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFestivalConfrimView(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentConfrimViewDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentConfrimViewDAO.selectFestivalConfrimView(parameterObject);
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
		
		FestivalPresentDAO festivalPresentConfrimViewDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentConfrimViewDAO.selectFestivalConfrimView(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select festival confrim view count
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFestivalConfrimViewCut(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentConfrimViewDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentConfrimViewDAO.selectFestivalConfrimViewCut(parameterObject);
	}
	
	/**
	 * delete festival present apply
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFestivalPresentApply(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentConfrimViewDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentConfrimViewDAO.deleteFestivalPresentApply(parameterObject);
	}
	
	/**
	 * get scheme tatal price 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getSchemeTotalPrice()throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentConfrimViewDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentConfrimViewDAO.getSchemeTotalPrice();
	}
	
	/**
	 * get festival present apply affirmor
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentApplyAffirmor(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.getFestivalPresentApplyAffirmor(parameterObject);
	}
	
	/**
	 * affirm festival present
	 * @param parameterObject
	 */
	public void affirmFestivalPresent(Object parameterObject){
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.affirmFestivalPresent(parameterObject);
	}
	
	/**
	 * update festival present apply for confirm
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFestivalPresentApplyForConfirm(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.updateFestivalPresentApplyForConfirm(parameterObject);
	}
	
	/**
	 * get festival present provide reprot list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentProvideReportList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.getFestivalPresentProvideReportList(parameterObject);
	}
	
	/**
	 * get festival present scheme report list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentSchemeReportList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.getFestivalPresentSchemeReportList(parameterObject);
	}
	
	/**
	 * get festival present quentity report list
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getFestivalPresentQuentityReportList(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		return festivalPresentDAO.getFestivalPresentQuentityReportList(parameterObject);
	}
	
	/**
	 * select certificate list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectCertificateList(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		return certificateDAO.selectCertificateList(parameterObject);
	}
	
	/**
	 * select certificate list by paging
	 * @param paramterObject
	 * @param currnetPage
	 * @param pageSize
	 * @return
	 * @throws GlRuntimeException
	 */
	public List selectCertificateList(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		return certificateDAO.selectCertificateList(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select certificate count
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectCertificateCount(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		return certificateDAO.selectCertificateCount(parameterObject);
	}
	
	/**
	 * insert certificate
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificateInfo(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		certificateDAO.insertCertificateInfo(parameterObject);
	}
	
	/**
	 * update certificate
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateCertificate(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		certificateDAO.updateCertificate(parameterObject);
	}
	
	/**
	 * delter certificate
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteCertificateAllData(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		certificateDAO.deleteCertificateAllData(parameterObject);
	}
	
	/**
	 * get certificate name
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getCertificateName(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		return certificateDAO.getCertificateName(parameterObject);
	}
	
    public List getPresentName(Object parameterObject)throws GlRuntimeException{
		PresentDAO presentDAO = (PresentDAO)GaDAOFactory.getInstance().getPresentDAO();
		return presentDAO.getPresentName(parameterObject);
	}
	/**
	 * insert certificate novation for update
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertCertificateNovationForUpdate(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		certificateDAO.insertCertificateNovationForUpdate(parameterObject);
	}
	
	/**
	 * get certificate update history
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List getCertificateUpdateHistory(Object parameterObject)throws GlRuntimeException{
		
		CertificateDAO certificateDAO = (CertificateDAO)GaDAOFactory.getInstance().getCertificateDAO();
		return certificateDAO.getCertificateUpdateHistory(parameterObject);
	}
	
	/**
	 * select food product by paging 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodProduct(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodProduct(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select food product 
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodProduct(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodProduct(parameterObject);
	}
	
	/**
	 * select food product cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFoodProductCut(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodProductCut(parameterObject);
	}
	
	/**
	 * insert food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFoodProduct(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.insertFoodProduct(parameterObject);
	}
	
	/**
	 * update food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFoodProduct(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.updateFoodProduct(parameterObject);
	}
	
	/**
	 * delete food product
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFoodProduct(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.deleteFoodProduct(parameterObject);
	}
	
	
	/**
	 * select food scheme by paging 
	 * @param parameterObject
	 * @param currentPage
	 * @param pageSize
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodScheme(Object parameterObject, int currentPage, int pageSize)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodScheme(parameterObject, currentPage, pageSize);
	}
	
	/**
	 * select food scheme
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodScheme(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodScheme(parameterObject);
	}
	
	/**
	 * select food scheme cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFoodSchemeCut(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodSchemeCut(parameterObject);
	}
	
	/**
	 * insert food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertFoodScheme(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.insertFoodScheme(parameterObject);
	}
	
	/**
	 * update food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateFoodScheme(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.updateFoodScheme(parameterObject);
	}
	
	/**
	 * delete food scheme
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void deleteFoodScheme(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.deleteFoodScheme(parameterObject);
	}
	
	/**
	 * select food detail
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDetail(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodDetail(parameterObject);
	}
	
	/**
	 * get food product list
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List getFoodProductList(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.getFoodProductList(parameterObject);
	}
	
	/**
	 * insert data for apply food
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void insertDataForApplyFood(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		foodDAO.insertDataForApplyFood(parameterObject);
	}
	
	/**
	 * select food apply
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodApply(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodApply(parameterObject);
	}
	
	/**
	 * select food apply cut
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object selectFoodApplyCut(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodApplyCut(parameterObject);
	}
	
	/**
	 * select food dept report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDeptReport(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodDeptReport(parameterObject);
	}
	
	/**
	 * select food detail report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodDetailReport(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodDetailReport(parameterObject);
	}
	
	/**
	 * get food scheme list
	 * @param parameterObject
	 * @return list 
	 * @throws GlRuntimeException
	 */
	public List selectSchemeReport(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectSchemeReport(parameterObject);
	}
	
	/**
	 * select food total report
	 * @param parameterObject
	 * @return list
	 * @throws GlRuntimeException
	 */
	public List selectFoodReport(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.selectFoodReport(parameterObject); 
	}
	
	
	/**
	 * get food cheme list
	 * @param parameterObject
	 * @return List 
	 * @throws GlRuntimeException
	 */
	public List getFoodSchemeList(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.getFoodSchemeList(parameterObject);
	}
	
	/**
	 * get food apply quentity
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object getFoodApplyQuentity(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.getFoodApplyQuentity(parameterObject);
	}
	
	/**
	 * get food apply price 
	 * @param parameterObject
	 * @return object
	 * @throws GlRuntimeException
	 */
	public Object getFoodApplyPrice(Object parameterObject)throws GlRuntimeException{
		
		FoodDAO foodDAO = (FoodDAO)GaDAOFactory.getInstance().getFoodDAO();
		return foodDAO.getFoodApplyPrice(parameterObject);
	}
	
	/**
	 * update scheme activity
	 * @param parameterObject
	 * @throws GlRuntimeException
	 */
	public void updateSchemeActivity(Object parameterObject)throws GlRuntimeException{
		
		FestivalPresentDAO festivalPresentDAO = (FestivalPresentDAO)GaDAOFactory.getInstance().getFestivalPresentDAO();
		festivalPresentDAO.updateSchemeActivity(parameterObject);
	}
	
	public List getSmockPeriodReport(Object parameterObject)throws GlRuntimeException{
		
		SmockDAO smockDAO = (SmockDAO)GaDAOFactory.getInstance().getSmockDAO();
		return smockDAO.getSmockPeriodReport(parameterObject);
	}
}