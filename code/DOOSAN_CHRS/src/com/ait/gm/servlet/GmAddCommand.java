package com.ait.gm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.gm.bean.GmBean;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;



/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (yangxiaohui@ait.net.cn)
 * @Date 2008-2-20
 * 
 */
public class GmAddCommand extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		String content = request.getParameter("content");
		if(content.equals("time") && content!=null){//得到开始结束时间

			List listMM=DateUtil.getTimePerMMList();
			
			List listHH=DateUtil.getTimePerHourList();

			request.setAttribute("listMM",listMM);
			request.setAttribute("listHH",listHH);
			return "gm/gm_add.jsp?menu_code="+request.getParameter("menu_code");
		}else{
			return "/error.jsp";
		}
		
		
	}
	public List gmType(){
		List result = new ArrayList();
		Connection conn = ConnBean.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		String sql="select t.code_id,t.code_name from sy_code t where t.parent_code='EateryType'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				GmBean gb= new GmBean();
				gb.setGm_id_pk(rs.getString("code_id"));
				gb.setGm_eatery_type(rs.getString("code_name"));
				result.add(gb);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).debug(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, stmt, conn);
		}
		return result;
		
	}
	
}
