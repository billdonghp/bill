package com.ait.evs.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ess.bean.EssAffirmor;
import com.ait.evs.EvsCraft;
import com.ait.evs.EvsGxjndj;
import com.ait.evs.Gxjsdj;
import com.ait.evs.business.EvsServices;
import com.ait.i18n.MessageSource;
import com.ait.mail.Mail;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.Command;

public class EvsGjjsdjsave2valuateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsGjjsdjsave2valuateCmd.class);
			
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
				String 	pjwd1=request.getParameter("pjwd1"); 
				String  pjwd2=request.getParameter("pjwd2");
				String  pjwd3 = request.getParameter("pjwd3");
				String  pjwd4 = request.getParameter("pjwd4");
				String  sumstore = request.getParameter("sumstore");
			    int   codeno = 0;
			    codeno = Integer.valueOf(request.getParameter("codeno"));
			SimpleMap simpleMap = new SimpleMap(); 
			simpleMap = ObjectBindUtil.getData(request);
			
			Connection conn = ConnBean.getConn();
			
			try { 
				
					Gxjsdj evsCraft = new Gxjsdj();
					evsCraft.setCodeNo(codeno);
					evsCraft.setPJWD1(pjwd1);
					evsCraft.setPJWD2(pjwd2);
					evsCraft.setPJWD3(pjwd3);
					evsCraft.setPJWD4(pjwd4);
					evsCraft.setSUMSTORE(sumstore);
					
					evsCraft.setUempid(admin.getAdminID());
										
					
					//services.deleteLine(evscodeid);
					//services.insertStype(list);
				
				
				   ////此处 应该一起提交
				   evsCraft.updateEvsGxjsdjById();
					
				
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
			request.setAttribute("update", 1);
			return "/evs0137.jsp";
		}
			
}
