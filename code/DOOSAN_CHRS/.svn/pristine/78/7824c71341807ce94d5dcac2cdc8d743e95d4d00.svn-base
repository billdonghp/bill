package com.ait.ga.cmd.asset;
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

public class RetrieveAssetListCmd extends GaAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute(
				"admin");
		SimpleMap parameterObject;

		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null
					&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request
						.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId",admin.getCpnyId());

			// retrieve record list
			List recordList = services.getAssetList(parameterObject,
					currentPage, pageSize);
			Object recordCount = services.getAssetListNumber(parameterObject);
			List recordName = services.getAssetNameList(parameterObject);

			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", recordCount.toString());
			request.setAttribute("recordName", recordName);
			
			//search parameter
			request.setAttribute("useDeptId", parameterObject.getString("useDeptId"));
			request.setAttribute("assetNo", parameterObject.getString("assetNo"));
			request.setAttribute("assetType", parameterObject.getString("assetType"));
			request.setAttribute("assetName", parameterObject.getString("assetName"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("status", parameterObject.getString("status"));
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupSize", pageGroupSize + "");

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieve present list by paging Exception. ", e);
		}
		this.putToolbarInfo(request);

		return "/ga_view_assetInfo.jsp?menu_code="+ parameterObject.getString("menu_code");
	}

}
