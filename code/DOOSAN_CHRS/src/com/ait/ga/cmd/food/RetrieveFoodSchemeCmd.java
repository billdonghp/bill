package com.ait.ga.cmd.food;

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

public class RetrieveFoodSchemeCmd extends GaAbstractCommand{

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
			List recordList = services.selectFoodScheme(parameterObject, currentPage, pageSize);
			Object recordCount = services.selectFoodSchemeCut(parameterObject);
			List foodDetaiList = services.selectFoodDetail(parameterObject);
			
			request.setAttribute("recordList", recordList);
			request.setAttribute("foodDetaiList", foodDetaiList);
			request.setAttribute("recordCount",recordCount.toString());
			
			//page parameter
			request.setAttribute("pageSize", pageSize+"");
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage",currentPage);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"Retrieve food scheme list cmd Exception. ",e);
		}
		
		this.putToolbarInfo(request);
		
		return "/ga_food_scheme_view.jsp?menu_code="+ parameterObject.getString("menu_code");
	}


}
