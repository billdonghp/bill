package com.ait.gm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.gm.dao.GMDAO;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class UpdateCardApplicationEndDate extends ArAbstractCommand {

	GaServices gaServices = new GaServices();
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		if(!content.equals("") && content.equals("updateApply")){			
			returnPage= this.updateApply(request,admin);
		}else if(!content.equals("") && content.equals("deleteApply")){
			returnPage= this.deleteApply(request,admin);
		}else{
			return "error.jsp";
		}
		return returnPage;
	}
	public String updateApply(HttpServletRequest request,AdminBean admin){
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		
		int gm_id = NumberUtil.parseInt(request.getParameter("gm_id"));
		
		String endDate = request.getParameter("enddate_" + gm_id);
		
		String cardStatus = request.getParameter("cardStatus_" + gm_id);
		
		String cardNoId = request.getParameter("cardNoId_" + gm_id);
		
		String cardNo = request.getParameter("cardNo_" + gm_id);
		
		MessageSource messageSource ;		
		try {

			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("GM_ID", gm_id);
			parameterObject.set("ENDDATE",endDate);
			parameterObject.set("cardStatus", cardStatus);
			parameterObject.set("cardNoId", cardNoId);
			parameterObject.set("cardNo", cardNo);
			
			// retrieve attendance record list
			
			gm.gmUpdateCardApplicationEndDate(parameterObject);
			
			
			
			Object cardCount=gaServices.cardApplicationCount(parameterObject);
			List allRecords=gaServices.cardApplication(parameterObject, currentPage, pageSize);
			
			List allCardStatus = gaServices.getAllCardStatus(parameterObject);			
			request.setAttribute("allCardStatus", allCardStatus);
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("CardId", request.getParameter("CardId"));
			request.setAttribute("CardNo", request.getParameter("CardNo"));					
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate",  request.getParameter("endDate"));			
			request.setAttribute("cardCount", NumberUtil.parseInt(cardCount));
			request.setAttribute("allRecords", allRecords);
			request.setAttribute("cardStatus", request.getParameter("cardStatus"));
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshift by No Exception. ",
					e);
		}
//		保存成功
		/*messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "保存成功");
		request.setAttribute("url","/gaControlServlet?operation=view&content=cardapplicationview&menu_code=" + parameterObject.getString("menu_code"));*/

		return "/ga_card_application_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
	
	public String deleteApply(HttpServletRequest request,AdminBean admin){
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject = null;
		
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		
		int gm_id = NumberUtil.parseInt(request.getParameter("gm_id"));	
		MessageSource messageSource ;		
		try {

			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("supervisor", adminId);
			parameterObject.set("GM_ID", gm_id);			
			gm.deleteApply(parameterObject);			
			Object cardCount=gaServices.cardApplicationCount(parameterObject);
			List allRecords=gaServices.cardApplication(parameterObject, currentPage, pageSize);
			
			List allCardStatus = gaServices.getAllCardStatus(parameterObject);
			request.setAttribute("allCardStatus", allCardStatus);
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("CardId", request.getParameter("CardId"));
			request.setAttribute("CardNo", request.getParameter("CardNo"));					
			request.setAttribute("startDate", request.getParameter("startDate"));
			request.setAttribute("endDate",  request.getParameter("endDate"));			
			request.setAttribute("cardCount", NumberUtil.parseInt(cardCount));
			request.setAttribute("allRecords", allRecords);
			request.setAttribute("cardStatus", request.getParameter("cardStatus"));
			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshift by No Exception. ",
					e);
		}
//		保存成功
		/*messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", "删除成功");
		request.setAttribute("url","/gaControlServlet?operation=view&content=cardapplicationview&menu_code=" + parameterObject.getString("menu_code"));*/

		return "/ga_card_application_view.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}