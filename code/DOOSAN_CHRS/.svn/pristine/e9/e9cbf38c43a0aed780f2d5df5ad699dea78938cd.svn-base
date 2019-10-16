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

public class RetrieveFestivalPresentConfrimViewCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		List recordDetaiList = null;
		List SchemeTotalPrice = null;
		try {
			UserConfiguration userConfiguration = UserConfiguration.getInstance("/system.properties");
			int pageSize = userConfiguration.getInt("page.style1.pagesize");
			int pageGroupSize = userConfiguration.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			
			if(request.getParameter("currentPage")!= null && !request.getParameter("currentPage").equals("")){
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			String cpnyId = request.getParameter("cpnyId")!=null?request.getParameter("cpnyId"):admin.getCpnyId();
			parameterObject = ObjectBindUtil.getData(request);
			
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			parameterObject.setString("affirmViewId", admin.getPersonId());
			
			UserConfiguration userConfig;
			userConfig = UserConfiguration.getInstance("/system.properties");
			String[] sgNo = admin.getScreenGrantNo().split(",");
			boolean b = false;
			for (int i = 0; i < sgNo.length; i++) {
			if (("," + userConfig.getString("common.role.affirmInfo").trim() + ",").indexOf(","+sgNo[i].trim()+",") > -1 ){
				b = true;
			parameterObject.set("affirmViewId", "");
			parameterObject.set("ADMIN_ID", admin.getAdminID());
			}
		}
			
			List recordList = services.selectFestivalConfrimView(parameterObject, currentPage, pageSize);
			int recordCount = NumberUtil.parseInt(services.selectFestivalConfrimViewCut(parameterObject));
			List affirmorList = services.getFestivalPresentApplyAffirmor(null);
			
			request.setAttribute("affirmorList", affirmorList);
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", recordCount+"");
		
			if(recordList.size()>0){
				recordDetaiList = services.selectFestivalSchemeDetaiList(parameterObject);
				SchemeTotalPrice = services.getSchemeTotalPrice();
			}
			request.setAttribute("SchemeTotalPrice", SchemeTotalPrice);
			request.setAttribute("recordDetaiList", recordDetaiList);
			request.setAttribute("empId", request.getParameter("empId"));
			request.setAttribute("DeptId", request.getParameter("DeptId"));
			request.setAttribute("empStatus", request.getParameter("empStatus"));
			request.setAttribute("flag", request.getParameter("flag"));
			request.setAttribute("currentName", admin.getChineseName());
			request.setAttribute("cpnyId", cpnyId);
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage", currentPage);
			
			request.setAttribute("adminID", admin.getAdminID());
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException(
					"retrieve festival present confrim view data Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_view_festival_present_confrim.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
}
