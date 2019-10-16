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

public class EvsRightsavevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsRightsavevaluateCmd.class);
			
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
				String  evscodename="";
				String  descriptio = request.getParameter("empId");
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			List listcht=new ArrayList();
			String[] craft = null;
			
			try { 
				List<EvsCraft> list = new ArrayList<EvsCraft>();
			     if(evscodeid!=null&&evscodeid.equals("1"))
			    	 evscodename ="数据导出权限";
			     else if(evscodeid!=null&&evscodeid.equals("2"))
			    	 evscodename ="邮件接收人权限";
						EvsCraft evsCraft = new EvsCraft();
					
						evsCraft.setEvsparentcode(descriptio);
						evsCraft.setDepth(evscodeid);
						//evsCraft.setEvscodeid(evscodeid);
						evsCraft.setEvscodename(evscodename);
						evsCraft.setDescriptio("");
						evsCraft.setCpnyid(cpnyid);
						evsCraft.setEmpid(admin.getAdminID());
						evsCraft.setEvsencodename("");
						evsCraft.setFlag("RS");
						list.add(evsCraft);
					
					
					services.insertLiner(list);
				
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
			return "/evs0136.jsp";
		}
}
