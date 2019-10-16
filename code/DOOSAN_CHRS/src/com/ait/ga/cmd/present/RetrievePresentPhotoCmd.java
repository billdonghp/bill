/*
 * @(#)RetrievePresentAffirmListCmd.java 1.0 2009-7-16 下午04:51:15
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ga.cmd.present;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.business.GaServices;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;


/**
 * Copyright:   AIT (c)
 * Company:     AIT
 * @author kelly (wangliwei@ait.net.cn)
 * @Date 2009-7-16 下午04:51:15
 * @version 1.0
 * 
 */
public class RetrievePresentPhotoCmd extends GaAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ga.servlet.GaAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.putToolbarInfo(request);
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");

		
		Connection conn = ConnBean.getConn();
		PreparedStatement ps = null;
		String sql = "select * from FILE_OR_PHOTO_PATH t where t.documentno=?";
		List result = new ArrayList();
		ResultSet rs = null;
		SimpleMap sm = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("presentId"));
			rs = ps.executeQuery();
			while (rs.next()) {
				sm = new SimpleMap();
				sm.set("viewfilename",rs.getString("chinesename"));
				sm.set("filename", rs.getString("filename"));
				sm.set("fileaddress",rs.getString("pathaddress"));
				sm.set("documentno",rs.getString("documentno"));
				result.add(sm);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlUtil.close(rs,ps,conn);
		}

		request.setAttribute("resultsize", result.size());
		request.setAttribute("result", result);
		
			
		return "/ga_present_view_photo.jsp";
	}

}



