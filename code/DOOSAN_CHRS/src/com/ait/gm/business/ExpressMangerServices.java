package com.ait.gm.business;

import java.util.List;

import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.EateryDAOFactory;
import com.ait.gm.dao.ExpressMangerDAO;
import com.ait.pu.dao.PuDAOFactory;
import com.ait.pu.dao.RoomSetupDAO;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-3-14
 * 
 */
public class ExpressMangerServices {

	public ExpressMangerServices() {

	}

	public int expressConfirmInfoListNumber(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressConfirmInfoListNumber(parameterObject);
	}

	public List expressConfirmInfoList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressConfirmInfoList(parameterObject, currentPage, pageSize);
	}
	public List gaReportList(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.gaReportList(parameterObject);
	}
	
	public Object expressConfirmInfo(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressConfirmInfo(parameterObject);
	}

	public boolean oingConfirm(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.oingConfirm(parameterObject);
	}

	public int expressSendListNumber(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressSendListNumber(parameterObject);
	}

	public List expressSendList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressSendList(parameterObject, currentPage, pageSize);
	}

	public boolean ingSend(Object parameterObject, String[] applnoAry) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.ingSend(parameterObject, applnoAry);
	}

	public List expressViewList(Object parameterObject, int currentPage, int pageSize) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressViewList(parameterObject, currentPage, pageSize);
	}
	public List EMSDetailsExcel(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.EMSDetailsExcel(parameterObject);
	}
	public List EMSTotalExcelList(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.EMSTotalExcelList(parameterObject);
	}
	public List EMSStatisticsExcel(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.EMSStatisticsExcel(parameterObject);
	}
	
	public int expressViewListNumber(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.expressViewListNumber(parameterObject);
	}

	public int costNumber(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.costNumber(parameterObject);
	}

	public boolean confirmCosts(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.confirmCosts(parameterObject);
	}
	public void saveCarManager(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		expressMangerDAO.saveCarManager(parameterObject);
	}
	
	public List getCarManager(Object parameterObject,int currentPage,int pageSize) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.getCarManager(parameterObject,currentPage,pageSize);
	}
	
	public int getCarManagerInt(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.getCarManagerInt(parameterObject);
	}
	
	public void expressInstallSave(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		expressMangerDAO.expressInstallSave(parameterObject);
	}
	
	public void expressInstallUpdate(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		expressMangerDAO.expressInstallUpdate(parameterObject);
	}
	
	public List getExpressInstall(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.getExpressInstall(parameterObject);
	}
	
	public List ViewexpressInstallUpdate(Object parameterObject) throws GlRuntimeException {

		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();

		return expressMangerDAO.ViewexpressInstallUpdate(parameterObject);
	}
	
	public void expressInstallDel(Object parameterObject) throws GlRuntimeException{		
		ExpressMangerDAO expressMangerDAO = (ExpressMangerDAO) EateryDAOFactory.getInstance().getExpressMangerDAO();
		expressMangerDAO.expressInstallDel(parameterObject);
	}

}
