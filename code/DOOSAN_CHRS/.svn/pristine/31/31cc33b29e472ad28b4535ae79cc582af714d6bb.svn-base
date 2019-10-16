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

public class RetrieveFestivalPresentApplyCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
//		GaAffirm gaAffirm = new GaAffirm();
//		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());
		
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
			
			List recordList = services.selectFestivalPresentApplyList(parameterObject, currentPage, pageSize);
			int recordCount = NumberUtil.parseInt(services.selectFestivalPresentApplyCut(parameterObject));
			List schemeNameList = services.getSchemeName(parameterObject);
			
			request.setAttribute("schemeNameList", schemeNameList);
			request.setAttribute("recordList", recordList);
			request.setAttribute("recordCount", recordCount+"");
			
//			for(int j=0;j<recordList.size();j++){
//				SimpleMap simpleMap = (SimpleMap)recordList.get(j);
//				List affirmor=gaAffirm.getAffirmor(simpleMap.getString("PERSON_ID"), "FestivalPresentApply", essSysparam.isContainsOwner());
//				request.setAttribute("affirmor"+j, affirmor);
//			}
//			
			request.setAttribute("empId", request.getParameter("empId"));
			request.setAttribute("personId", request.getParameter("personId"));
			request.setAttribute("DeptId", request.getParameter("DeptId"));
			request.setAttribute("empStatus", request.getParameter("empStatus"));
			request.setAttribute("division", request.getParameter("division"));
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage", currentPage);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException(
					"retrieve apply festival present list Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_apply_festival_present.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
}
