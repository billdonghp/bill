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
import com.ait.evs.EvsGxjndj;
import com.ait.evs.Gxjsdj;
import com.ait.evs.business.EvsServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class EvsGjjsdjsavevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsGjjsdjsavevaluateCmd.class);
			
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
				String 	chinesename=request.getParameter("chinesename");
				String 	evperiodid=request.getParameter("evperiodid"); 
				String  pjwd1=request.getParameter("pjwd1");
				String  pjwd2=request.getParameter("pjwd2");
				String  pjwd3=request.getParameter("pjwd3");
				String  pjwd4=request.getParameter("pjwd4");
				String  sUMSTORE=request.getParameter("sumstore");
			EvsServices services = EvsServices.getInstance();
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			List listcht=new ArrayList();
			String[] craft = null;
			
			try { 
				List<Gxjsdj> list = new ArrayList<Gxjsdj>();
				craft  = request.getParameterValues("isChecked");
				if (craft != null) {
					for (int j = 0; j < craft.length; j++) {        
						Gxjsdj evsCraft = new Gxjsdj();
						ObjectBindUtil.setFormBean(request, evsCraft, "_"+craft[j]);
						evsCraft.setCodeid(craft[j]);
						evsCraft.setEmpName(chinesename);
						evsCraft.setEvperiodid(evperiodid);
						evsCraft.setPJWD1(pjwd1);
						evsCraft.setPJWD2(pjwd2);
						evsCraft.setPJWD3(pjwd3);
						evsCraft.setPJWD4(pjwd4);
						evsCraft.setSUMSTORE(sUMSTORE);
						evsCraft.setCreateBy(admin.getAdminID());
						list.add(evsCraft);
					}
					
					services.insertGjjsdj(list);
				}
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
			return "/evs0137.jsp";
		}
}
