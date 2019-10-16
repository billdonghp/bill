package com.ait.pa.cmd.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-04-19
 * 
 */
public class PayCalculationSigns extends PaAbstractCommand {

	private PaServices paServices = null;
	
	PaServices paService = PaServices.getInstance();

	public PayCalculationSigns() {
		paServices = PaServices.getInstance();
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		AdminBean admin = (AdminBean) session.getAttribute("admin");
		String adminid = admin.getAdminID();
		String content = request.getParameter("content");
		String returnPage = null;
		SimpleMap parameterObject = new SimpleMap();
		
		if (!content.equals("")&& content.equals("PayCalculationSignsForSearch")) {// 工资计算表识搜索
			returnPage = this.PayCalculationSignsForSearch(request, admin);
		} else if (!content.equals("")&& content.equals("PayCalculationSignsForUpdate")) {
			returnPage = this.PayCalculationSignsForUpdate(request, admin);
		} else  if(!content.equals("")&& content.equals("PayCalculationSignsPage")){
			parameterObject.setString("cpny_id", admin.getCpnyId());
			List postgradeYear = paService.getPostGradeYear(parameterObject);
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			int resultCount=0;			
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("postgradeYearList",postgradeYear);
			request.setAttribute("cpnyId", admin.getCpnyId());
			request.setAttribute("joinType", request.getParameter("joinType"));

			returnPage ="/pa0208.jsp";
		} else{
			returnPage = "error.jsp";
		}
		return returnPage;
	}

	public String PayCalculationSignsForSearch(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = ObjectBindUtil.getData(request);
			SimpleMap attributeObject = ObjectBindUtil.getAttributeBox(request);
			parameterObject.setString("deptID", StringUtil.checkNull(parameterObject.getString("deptID"),attributeObject.getString("deptID")));
			parameterObject.setString("key", StringUtil.checkNull(parameterObject.getString("key"),attributeObject.getString("key")));
			parameterObject.setString("person_id", StringUtil.checkNull(parameterObject.getString("person_id"),attributeObject.getString("person_id")));
			parameterObject.setString("flag", StringUtil.checkNull(parameterObject.getString("flag"),attributeObject.getString("flag")));
			parameterObject.setString("year", StringUtil.checkNull(parameterObject.getString("year"),attributeObject.getString("year")));
			
			int pageSize =20;
			int pageGroupSize =10;
			int currentPage = 0;
			parameterObject.put("cpny_id", admin.getCpnyId());
		
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			
			List employeeListForFlag = null;
			int resultCount;
			
			if(admin.getCpnyId().equals("59000000")){
				
				employeeListForFlag=paServices.PayCalculationSignsForSearchDici(parameterObject,currentPage,pageSize);
				resultCount = paServices.PayCalculationSignsForSearchNumberDici(parameterObject);
			}else{
				
				employeeListForFlag=paServices.PayCalculationSignsForSearch(parameterObject,currentPage,pageSize);
				resultCount = paServices.PayCalculationSignsForSearchNumber(parameterObject);
			}
			List postgradeYear = paService.getPostGradeYear(parameterObject);
			
			request.setAttribute("postgradeYearList",postgradeYear);
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode"));
			request.setAttribute("deptID", parameterObject.getString("deptID"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("person_id", parameterObject.getString("person_id"));
			request.setAttribute("flag", parameterObject.getString("flag"));
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("joinType", parameterObject.getString("joinType"));
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			request.setAttribute("employeeListForFlag", employeeListForFlag);
			request.setAttribute("cpnyId", admin.getCpnyId());
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"PayCalculationSignsForSearch happens Exception. ", e);
		}

		return "/pa0208.jsp";

	}

	public String PayCalculationSignsForUpdate(HttpServletRequest request,
			AdminBean admin) {
		try {

			SimpleMap parameterObject = null;
			String[] args = request.getParameterValues("selectC");
			int size=args!=null?args.length:0;
			
			if(admin.getCpnyId().equals("59000000")){
				
				for (int i=0;i<size;i++) {
					parameterObject= new SimpleMap();
					parameterObject.set("empid",request.getParameter("empid"+args[i]));
					parameterObject.set("calcFlag",request.getParameter("calcFlag"+args[i]));
					parameterObject.set("POST_GRADE_YEAR",request.getParameter("POST_GRADE_YEAR"+args[i]));
					parameterObject.set("INSR_AREA",request.getParameter("INSR_AREA"+args[i]));
					parameterObject.set("person_id",request.getParameter("person_id"+args[i]));
					parameterObject.set("ko_calc_flag",request.getParameter("koCalcFlag"+args[i]));
					paServices.PayCalculationSignsForUpdateDici(parameterObject);
				}	

			}else{
				for (int i=0;i<size;i++) {
					parameterObject= new SimpleMap();
					parameterObject.set("empid",request.getParameter("empid"+args[i]));
					parameterObject.set("calcFlag",request.getParameter("calcFlag"+args[i]));
					parameterObject.set("POST_GRADE_YEAR",request.getParameter("POST_GRADE_YEAR"+args[i]));
					parameterObject.set("INSR_AREA",request.getParameter("INSR_AREA"+args[i]));
					parameterObject.set("person_id",request.getParameter("person_id"+args[i]));
					paServices.PayCalculationSignsForUpdate(parameterObject);
				}
			}
			
			request.setAttribute("statTypeCode", request.getParameter("statTypeCode"));
			request.setAttribute("deptID", request.getParameter("deptID"));
			request.setAttribute("key", request.getParameter("key"));
			request.setAttribute("person_id", request.getParameter("person_id"));
			request.setAttribute("flag", request.getParameter("flag"));
			request.setAttribute("year", request.getParameter("year"));
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException(
					"PayCalculationSignsForUpdate happens Exception. ", e);
		}
		return this.PayCalculationSignsForSearch(request, admin);
	}

}
