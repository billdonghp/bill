package com.ait.ga.cmd.smock;

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

public class RetrieveSmockProvideListCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		GaServices services = new GaServices();
		SimpleMap parameterObject;
		try {
			
			/* paging logic */
			UserConfiguration config = UserConfiguration
					.getInstance("/system.properties");
			int pageSize = config.getInt("page.style6.pagesize");
			int pageGroupSize = config.getInt("page.style6.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null&& !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId",admin.getCpnyId());

			// retrieve record list
			List recordList = services.selectSmockProvideList(parameterObject, currentPage, pageSize);
			Object recordCount = services.selectSmockProvideCut(parameterObject);
			List periodList = services.getPeriodNameList(parameterObject);
			
			request.setAttribute("periodList", periodList);
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", Integer.parseInt(recordCount.toString()));
			
			//search parameter
			request.setAttribute("smockName", parameterObject.getString("smockName"));
			request.setAttribute("startDate", parameterObject.getString("startDate"));
			request.setAttribute("endDate", parameterObject.getString("endDate"));
			request.setAttribute("empId", parameterObject.getString("empId"));
			request.setAttribute("DeptId", parameterObject.getString("DeptId"));
			request.setAttribute("personId", parameterObject.getString("personId"));
			request.setAttribute("provideStatus", parameterObject.getString("provideStatus"));
			request.setAttribute("period", parameterObject.getString("period"));
			
			// paging parameter
			request.setAttribute("currentPage", currentPage + "");
			request.setAttribute("pageSize", pageSize + "");
			request.setAttribute("pageGroupSize", pageGroupSize + "");
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"retrieve smock provide data Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_view_smockProvide.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
	
	

}
