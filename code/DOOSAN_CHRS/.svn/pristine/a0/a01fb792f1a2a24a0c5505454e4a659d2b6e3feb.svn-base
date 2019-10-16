package com.ait.ga.cmd.smock;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-23
 * 
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;

public class InsertSmockCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;

		try {
//			 bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			if(this.checkForInsertScheme(request)){
				
				parameterObject.setString("personId",admin.getPersonId());
				parameterObject.setString("cpnyId", admin.getCpnyId());
				
				// insert present
				services.insertSmock(parameterObject);
			}else{
//				添加失败
				messageSource = new MessageSource(admin.getLocale(), "UTF-8");
				request.setAttribute("alert", "添加失败,此信息已存在 !");
				request.setAttribute("url","gaControlServlet?operation=retrieveSmockList&menu_code=" + parameterObject.getString("menu_code"));

				return "/inc/alertMessage.jsp";
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert Smock Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveSmockList&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
	
	private boolean checkForInsertScheme(HttpServletRequest request)throws GlRuntimeException{
		
		String smockName = request.getParameter("smockName");
		String cpnyId = ((AdminBean)request.getSession(false).getAttribute("admin")).getCpnyId();
		boolean checkFlag =false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnBean.getConn();
			String sql = "SELECT COUNT(*) cut FROM GA_SMOCK A WHERE A.SMOCK_NAME = '"+smockName+"' AND A.CPNY_ID = '"+cpnyId+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("cut")== 0){
					checkFlag = true;
				}
			}
		} catch (SQLException e) {
			
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("check for insert scheme data Exception. ",e);
		}finally{
			SqlUtil.close(rs, ps, conn);
		}
		
		return checkFlag;
	}
	
	
}
