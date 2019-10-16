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

public class EvsgzsavevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsgzaddvaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				String menu_code = request.getParameter("menu_code");
				String 	evscodeid=request.getParameter("evscodeid");
				String 	cpnyid=request.getParameter("cpnyId"); 
				String  evscodename=request.getParameter("evscodename");
				String  evsencodename = request.getParameter("evsencodename");
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			List listcht=new ArrayList();
			String[] craft = null;
			
			try { 
				List<EvsCraft> list = new ArrayList<EvsCraft>();
		    //	craft  = request.getParameterValues("isChecked");
				//if (craft != null) {
					//for (int j = 0; j < craft.length; j++) {        
						EvsCraft evsCraft = new EvsCraft();
						//ObjectBindUtil.setFormBean(request, evsCraft, "_"+craft[j]);
						evsCraft.setEvsparentcode("CRAFT");
						evsCraft.setEvscodeid(evscodeid);
						evsCraft.setEvscodename(evscodename);
						evsCraft.setCpnyid(cpnyid);
						evsCraft.setEmpid(admin.getAdminID());
						evsCraft.setEvsencodename(evsencodename);
						evsCraft.setFlag("CT");   /////
						evsCraft.setDepth("0");
						list.add(evsCraft);
					//}
					
					services.insertLine(list);
				//}
			} catch (Exception e) {
				logger.error(e.toString());
				request.setAttribute("update", 2);
				throw new GlRuntimeException(
						"The information Exception when running the IsertExpiredContract. ",
						e);
			}
			resultCount = listcht.size();
			request.setAttribute("Linelist", listcht); 
			request.setAttribute("resultCount", resultCount);                    
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);  
			request.setAttribute("update", 1);
			return "/evs0125.jsp";
		}
}
