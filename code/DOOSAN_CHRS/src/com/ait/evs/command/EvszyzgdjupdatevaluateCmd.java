package com.ait.evs.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.evs.business.EvsServices; 
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class EvszyzgdjupdatevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvszyzgdjupdatevaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				String menu_code = request.getParameter("menu_code"); ; 
				String evscodeid=request.getParameter("evscodeid");
			EvsServices services = EvsServices.getInstance();
			SimpleMap parameterObject;
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			simpleMap.put("evscodeid", evscodeid);
			Object searchLine = services.getEvsgxjndjList(simpleMap);  
			List<SimpleMap> searchExpiredLine2 = services.getEvscodeList5(simpleMap);  
			List<SimpleMap> searchExpiredLine3 = services.getStypeEvscodeList(simpleMap);  
			request.setAttribute("searchLine", searchLine); 
			request.setAttribute("search2Line2", searchExpiredLine2); 
			request.setAttribute("search3Line3", searchExpiredLine3); 
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			return "/evs0134_m.jsp";
		}
}
