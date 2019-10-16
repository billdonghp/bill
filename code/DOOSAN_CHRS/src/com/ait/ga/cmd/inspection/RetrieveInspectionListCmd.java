package com.ait.ga.cmd.inspection;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

public class RetrieveInspectionListCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GaServices services = new GaServices();
		AdminBean adminBean = (AdminBean)request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = null;
		
		try {
			//page logic
			UserConfiguration configuration = UserConfiguration.getInstance("/system.properties");
			int pageSize = configuration.getInt("page.style1.pagesize");
			int pageGroupSize = configuration.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			
			//If has currentPage set value into currentPage
			if(request.getParameter("currentPage")!=null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			//bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", adminBean.getAdminID());
			parameterObject.setString("cpnyId", adminBean.getCpnyId());
			
			//retrieve record list
			List recordList = services.getInspectionList(parameterObject, currentPage, pageSize);
			Object recordCount = services.getInspectionNumber(parameterObject);
			List recordAssetName = services.getAssetNameList(parameterObject);
			
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount",Integer.parseInt(recordCount.toString()));
			request.setAttribute("recordAssetName", recordAssetName);
			
			//search parameter
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("assetType", parameterObject.getString("assetType"));
			request.setAttribute("assetNo", parameterObject.getString("assetNo"));
			request.setAttribute("assetName",parameterObject.getString("assetName"));
			request.setAttribute("checkType", parameterObject.getString("checkType"));
			request.setAttribute("checkStatus",parameterObject.getString("checkStatus"));
			
			//page parameter
			request.setAttribute("pageSize", pageSize+"");
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage",currentPage);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieve inspection list cmd Exception. ",e);
		}
		
		this.putToolbarInfo(request);
		
		return "/ga_view_inspection.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
}
