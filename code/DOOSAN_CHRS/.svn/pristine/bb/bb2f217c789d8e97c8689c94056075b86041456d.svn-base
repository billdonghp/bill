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
import com.ait.evs.business.EvsServices; 
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class EvsircraftdeletegzCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsircraftdeletegzCmd.class);
			
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
				try { 	
						EvsCraft evsCraft = new EvsCraft();
						evsCraft.setEvsparentcode("");
						evsCraft.setEvscodeid(evscodeid);
						evsCraft.setEmpid(admin.getAdminID());
						evsCraft.setUempid(admin.getAdminID());
						
					
						evsCraft.deleteEvsCraftById(); 
				} catch (Exception e) {
					logger.error(e.toString());
					request.setAttribute("update", 2);
					throw new GlRuntimeException(
							"The information Exception when running the IsertExpiredContract. ",
							e);
				}
			
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			
			String retuns ="";
			if(menu_code!=null&&menu_code.equals("evs0136"))
				retuns ="/evs0136.jsp";
			else 
				retuns ="/evs0125.jsp";
			return retuns;
		}
}
