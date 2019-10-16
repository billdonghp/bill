package com.ait.reports.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import com.ait.core.exception.GlRuntimeException;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
import com.ait.reports.reportservices.EatReportService;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.web.Command;

public class EatReport implements Command {

	private EatReportService eatServices;


	// private String adminID;

	private String page; // 要转向的页面

	public EatReport() {
		eatServices = new EatReportService();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String returnPage = null;

		// adminID = admin.getAdminID();
		page = StringUtil.checkNull(request.getParameter("page"));// 从request中得到的页面名称
		if (page.equals("eatReport")) { // 就餐报表
			returnPage = this.eatReport(request);
		} else {
			returnPage = "/error.jsp";
		}
		Logger.getLogger(getClass()).debug("returnPage : " + returnPage);
		return returnPage;
	}

	private String eatReport(HttpServletRequest request) {
      
		String url= "/inc/commonReport.jsp";
		SimpleMap parameterObject = new SimpleMap();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource("report", admin.getLocale(), "UTF-8"); 
		// get search parameter
		String supervisor = ((AdminBean) request.getSession().getAttribute(
				"admin")).getAdminID();
		String language=((AdminBean) request.getSession().getAttribute("admin")).getLanguagePreference();
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String arMonth = year
				+ ("0" + month).substring(("0" + month).length() - 2,
						("0" + month).length());

		try {   
				
			
				List eatLookList = eatServices.eatReport(parameterObject);

				String fileName = "就餐差异明细对比表.xls";

				String sheetName = "EATINT_DIFFERENCE_STATEMENTS";


				// make annual report
				ReportUtil.makeReport(request, eatLookList, fileName,
						sheetName, 1, 1);
			
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception. " + e.toString());
			throw new GlRuntimeException(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception.", e);
		}

		return url;

	}
	
	public List returnEateryType() {
	      List result=null;
		try{
			
			result = eatServices.eateryType();
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception. " + e.toString());
			throw new GlRuntimeException(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception.", e);
		}

	return result;

	}
	
	public List empid(Object parameterObject) {
	      List result=null;
		try{
			
			result = eatServices.empid(parameterObject);
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception. " + e.toString());
			throw new GlRuntimeException(
					"Export EATINT_DIFFERENCE_STATEMENTS report Exception.", e);
		}

	return result;

	}
}
