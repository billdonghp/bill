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
import com.ait.gm.dao.GMDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;



/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author Administrator (zhangji@ait.net.cn)
 * @Date 2010-6-4
 * 
 */
public class GmVegetableOpinionAddCommand extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GMDAO services = new GMDAO();
		SimpleMap parameterObject;
		parameterObject = ObjectBindUtil.getData(request);
		HttpSession session =request.getSession(true);
		AdminBean admin =(AdminBean)session.getAttribute("admin");
		String adminid=admin.getAdminID();
		String cpnyId=((AdminBean) request.getSession(false).getAttribute("admin")).getCpnyId();
		parameterObject.set("cpnyId", cpnyId);
		List gmVegetableALL = services.gmVegetableALL(parameterObject);
		request.setAttribute("gmVegetableALL", gmVegetableALL);
		request.setAttribute("admin", admin);

		return "gm/gm_vegetable_opinion_add.jsp?menu_code="+request.getParameter("menu_code");
		
	}

	
}
