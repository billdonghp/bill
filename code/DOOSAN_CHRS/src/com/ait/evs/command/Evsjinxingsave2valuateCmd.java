package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.EvsCraft;
import com.ait.evs.Evsupcode;
import com.ait.evs.business.EvsServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class Evsjinxingsave2valuateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvslineaddvaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
			   String ev_emp_id = request.getParameter("ev_emp_id");
			   String ev_period_id = request.getParameter("ev_period_id");
			   String ev_type_id = request.getParameter("ev_type_id");
				String craftid = request.getParameter("craftid");	
				 String skileid = request.getParameter("skileid");
			   String qiancount = request.getParameter("shuliang");
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			 simpleMap.put("empID", ev_emp_id);
			 simpleMap.put("period", ev_period_id);
			 simpleMap.put("evTypeId", ev_type_id);
			List listcht=new ArrayList();
			Evsupcode evsUpcode = new Evsupcode();
			Object searchEmpList ;
			List searchCraftList;
			List searchTypetionList;
			List searchEvsSkilelist;
			List searchSkitypeList;
			List searchLineList;
			List searchAircraftList;
			List searchProcessList;
			List searchJobcontentList;
			List searchCount;
			List searchEvsQualificationList;
			List searchEvsPurposeList;
			try { 
					 searchCount =  services.getEvsCountEmp(simpleMap);
					 searchEmpList = services.getEvsempcodeList(simpleMap);
					 searchCraftList = services.getEvsCraftList();//查询工种
					 searchSkitypeList = services.getEvsSkitypeList(); //查询技能类型
					 searchLineList = services.getEvsLineList(); //查询Line线
					 searchAircraftList = services.getEvsAircraftList(); //查询机种
					 searchProcessList = services.getEvsProcesstList(); //查询工序
					 searchJobcontentList = services.getEvsJobcontentList(); //查询作业内容
					 searchTypetionList = services.getEvsTypetionList();
					 searchEvsSkilelist = services.getEvsSkilelist();
					 searchEvsQualificationList = services.getEvsQualificationList();//职业资格
					 searchEvsPurposeList = services.getEvsPurposeList();//培训目标
				}
		     catch (Exception e) {
				logger.error(e.toString());
				request.setAttribute("update", 2);
				throw new GlRuntimeException(
						"The information Exception when running the IsertExpiredContract. ",
						e);
			}
			resultCount = listcht.size();
			request.setAttribute("searchEmpList", searchEmpList); 
			request.setAttribute("searchCraftList", searchCraftList); 
			request.setAttribute("searchSkitypeList", searchSkitypeList); 
			request.setAttribute("searchLineList", searchLineList); 
			request.setAttribute("searchAircraftList", searchAircraftList); 
			request.setAttribute("searchProcessList", searchProcessList); 
			request.setAttribute("searchJobcontentList", searchJobcontentList); 
			request.setAttribute("searchTypetionList", searchTypetionList); 
			request.setAttribute("searchEvsSkilelist", searchEvsSkilelist); 
			request.setAttribute("searchEvsQualificationList", searchEvsQualificationList); 
			request.setAttribute("searchEvsPurposeList", searchEvsPurposeList); 
			request.setAttribute("listcht", listcht); 
			request.setAttribute("searchCount", searchCount); //数据库原有的数据有几条查几条
			request.setAttribute("countemp", qiancount);     
			request.setAttribute("craftid", craftid); 
			request.setAttribute("skileid", skileid); 
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			request.setAttribute("update", 1);
			return "/evs0202_eval.jsp";
		}
}
