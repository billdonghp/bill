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

public class RetrieveFestivalPresentConfirmCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GaServices services = new GaServices();
		SimpleMap parameterObject = null;
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		List recordDetaiList = null;
		List SchemeTotalPrice = null;
		//List<SimpleMap> recordApplyList = new ArrayList<SimpleMap>();
		String recordCount = null;
		try {
			UserConfiguration userConfiguration = UserConfiguration.getInstance("/system.properties");
			int pageSize = userConfiguration.getInt("page.style1.pagesize");
			int pageGroupSize = userConfiguration.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			
			if(request.getParameter("currentPage")!= null && !request.getParameter("currentPage").equals("")){
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			String cpnyId = request.getParameter("cpnyId") != null ? request.getParameter("cpnyId") : admin.getCpnyId();
			parameterObject = ObjectBindUtil.getData(request);
			
			parameterObject.setString("adminId", admin.getAdminID());
			parameterObject.setString("cpnyId", cpnyId);
			
			List recordList = services.selectFestivalConfrimView(parameterObject, currentPage, pageSize);
			recordCount = (services.selectFestivalConfrimViewCut(parameterObject)).toString();
//			List affirmorList = services.getFestivalPresentApplyAffirmor(null);
//			
//			Iterator iterator = recordList.iterator();
//			for(;iterator.hasNext();){
//				SimpleMap simpleMap = (SimpleMap)iterator.next();
//				int affirmFlag = Integer.parseInt(simpleMap.getString("AFFIRM_FLAG"));
//				if(affirmFlag != 0){
//					recordApplyList.add(simpleMap);
//					recordCount++;
//				}
//			}
			
//			request.setAttribute("affirmorList", affirmorList);
			request.setAttribute("recordApplyList", recordList);
			request.setAttribute("recordCount", recordCount+"");
		
			if(recordList.size()>0){
				recordDetaiList = services.selectFestivalSchemeDetaiList(parameterObject);
				SchemeTotalPrice = services.getSchemeTotalPrice();
			}
			request.setAttribute("SchemeTotalPrice", SchemeTotalPrice);
			request.setAttribute("recordDetaiList", recordDetaiList);
			request.setAttribute("currentId", admin.getPersonId());
			
			request.setAttribute("empId", request.getParameter("empId"));
			request.setAttribute("DeptId", request.getParameter("DeptId"));
			request.setAttribute("personId", request.getParameter("personId"));
			request.setAttribute("flag", request.getParameter("flag"));
			request.setAttribute("currnetId", admin.getPersonId());
			request.setAttribute("cpnyId", cpnyId);
			
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupSize", pageGroupSize);
			request.setAttribute("currentPage", currentPage);
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			new GlRuntimeException(
					"retrieve festival present affirm data Exception. ",e);
		}
		this.putToolbarInfo(request);
		return "/ga_festival_present_confirm.jsp?menu_code="+ parameterObject.getString("menu_code");
	}
}
