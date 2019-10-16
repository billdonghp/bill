package com.ait.gm.servlet;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.bean.GmBean;
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil ;
import com.ait.util.StringUtil ;

public class EatApply extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		String adminNo = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminNo();
		SimpleMap parameterObject;
		SimpleMap map = null;
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
			parameterObject.set("AdminId", adminId);
			parameterObject.set("adminNo", adminNo);
			
			String current_date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new GregorianCalendar().getTime());

			parameterObject.setString("current_date", current_date);
			// retrieve attendance record list
			Object eatLookCount = 0;
			String deptid = request.getParameter("deptlist");
			request.setAttribute("deptid", deptid);
			if(deptid == null || deptid.length() == 0){
				deptid = ((AdminBean) request.getSession(false).getAttribute("admin")).getDeptID();
			}
			List admin_dept = gm.adminDept(parameterObject);
			List gm_type = gm.Gm_Type(parameterObject);
			
			int gmtype = gm_type.size();
			
			request.setAttribute("gmtypeSize", gmtype+"");
			
			int total_peoples = 0; 
			parameterObject.set("deptId",deptid);
			int ifApplyFlag = gm.ifApplyFlag(parameterObject);
			for(int i=0;i<gmtype;i++){
				map=(SimpleMap)gm_type.get(i);
				
				parameterObject.set("GmTypeid",map.getString("GM_ID"));
				
			
				List empName = gm.empName(parameterObject);
				
				request.setAttribute("empNameSize", empName.size()+"");
				
				int numChushihua = gm.eateryNumCount(parameterObject);
				total_peoples = total_peoples + numChushihua;
				
				List gmFromToDate = gm.gmFromToDate(parameterObject);
				
				String fromDate = ((SimpleMap)gmFromToDate.get(0)).getString("GM_FROM_DATE");
				
				int fromdate=NumberUtil.parseInt(fromDate.replace(":", ""));//参别表里的  开始时间   拆分后的

				String applyyear=DateUtil.formatDate(java.util.Calendar.getInstance().getTime(), "yyyy-MM-dd").replace("-", "");
				String applyday=DateUtil.formatDate(java.util.Calendar.getInstance().getTime(), "HH:mm").replace(":", "");
				
				int eatdate = 0;
				if(fromdate>NumberUtil.parseInt(applyday)){
					eatdate = NumberUtil.parseInt(applyyear);
					parameterObject.set("eatdate", eatdate);
				}
				if(fromdate<NumberUtil.parseInt(applyday)){
					eatdate = NumberUtil.parseInt(applyyear)+1;
					parameterObject.set("eatdate", eatdate);
				}
				
				//String eatDate = gm.eatDate(parameterObject);
				
				map.set("empNameList", empName);
				map.set("numChushihua", numChushihua);
				map.set("eatDate", eatdate);
			}
			
			
			request.setAttribute("total_peoples", total_peoples) ;
			request.setAttribute("gm_type", gm_type) ;	
			request.setAttribute("admin_dept", admin_dept) ;
			request.setAttribute("deptid", deptid) ;
			request.setAttribute("ifApplyFlag", ifApplyFlag) ;
			
			request.setAttribute("recordCount", NumberUtil.parseInt(eatLookCount)) ;

			// paging parameter
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve overtime plan list by paging Exception. ", e);
		}

		return "gm/eatApply.jsp?menu_code=" + parameterObject.getString("menu_code");
	}
}
