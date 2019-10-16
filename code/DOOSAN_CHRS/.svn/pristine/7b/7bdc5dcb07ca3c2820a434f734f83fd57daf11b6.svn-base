package com.ait.evs.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.evs.Gxjsdj;
import com.ait.evs.business.EvsServices; 
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.utils.ConnBean;
import com.ait.web.Command;

public class EvsGjjsRemarkupdatevaluateCmd  implements Command{

				private static final Logger logger = Logger
				.getLogger(EvsGjjsRemarkupdatevaluateCmd.class);
			
			@Override
			public String execute(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
				MessageSource messageSource ;
				int pageSize = 0;
				int pageGroupSize = 0;
				int currentPage = 0;
				int resultCount = 0;   
				AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
				String remark = request.getParameter("gjjsRemark");
						
			Connection conn = ConnBean.getConn();
			
			try { 
				
					Gxjsdj evsCraft = new Gxjsdj();
					evsCraft.setRemark(remark);										
					evsCraft.setUempid(admin.getAdminID());
													
				   ////此处 应该一起提交
				   evsCraft.updateEvsGxjsdjRemark();
					
				
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
