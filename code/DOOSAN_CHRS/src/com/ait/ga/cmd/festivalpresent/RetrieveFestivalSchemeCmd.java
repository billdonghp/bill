package com.ait.ga.cmd.festivalpresent;

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
import com.ait.util.NumberUtil;

public class RetrieveFestivalSchemeCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		List recordDetaiList = null;

		try {
			UserConfiguration userConfiguration = UserConfiguration.getInstance("/system.properties");
			int pageSize = userConfiguration.getInt("page.style1.pagesize");
			int pageGroupSize = userConfiguration.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			
			if(request.getParameter("currentPage")!= null && !request.getParameter("currentPage").equals("")){
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			List recordList = services.selectFestivalSchemeList(parameterObject, currentPage, pageSize);
			if(recordList.size()>0){
				recordDetaiList = services.selectFestivalSchemeDetaiList(parameterObject);
			}
			
			int recordCount = NumberUtil.parseInt(services.selectFestivalSchemeCut(parameterObject));
			
			request.setAttribute("recordDetaiList", recordDetaiList);
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", recordCount+"");
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage", currentPage);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException(
					"retrieve festival scheme list Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_view_festival_scheme.jsp?menu_code="+ parameterObject.getString("menu_code");
	}

}
