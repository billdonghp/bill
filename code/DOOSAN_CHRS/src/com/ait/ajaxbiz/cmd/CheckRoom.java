/*
 * @(#)ViewBaseInfoCmd.java 1.0 2006-12-22 下午01:03:53
 *
 *Copyright 2001 - 2006 AIT. All Rights Reserved.
 */
package com.ait.ajaxbiz.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.pu.services.ConferenceRoomServices;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.StringUtil;
import com.ait.web.Command;

/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author wangliwei (wangliwei@ait.net.cn)
 * @Date 2006-12-22 下午01:03:53
 * @version 1.0
 * 
 */
public class CheckRoom implements Command {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ait.web.Command#execute(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
    	SimpleMap simpleMap = new SimpleMap();

    	String roomname = request.getParameter("roomname");
    	
		// create parameter object
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.setString("ADMINID", admin.getAdminID()) ;		
		parameterObject.setString("roomname", roomname) ;
		ConferenceRoomServices roomServices = new ConferenceRoomServices();
		
		response.setContentType("text/xml;charset=UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer str = new StringBuffer(5000) ;
		int checkRooms=0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date  Formatstartdate = new Date();
		Date  Formatenddate = new Date();
		try {
			Formatstartdate=sdf.parse(request.getParameter("applydate"));
			Formatenddate=sdf.parse(request.getParameter("enddate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double vra = DateUtil.DateDiff(Formatstartdate,Formatenddate,"DAY");
		for(int i=0;i<(vra+1);i++){
			Calendar today = Calendar.getInstance();
			today.setTime(Formatstartdate);
			today.add(today.DATE, i);	
			parameterObject.set("Formatdate",sdf.format(today.getTime()));
			parameterObject.set("starttime",request.getParameter("begintime"));
			parameterObject.set("endtime",request.getParameter("endtime"));			
			if(request.getParameter("isUpdate")!=null &&request.getParameter("isUpdate").equals("isUpdate")){
				parameterObject.set("applyno",request.getParameter("applyno"));
				checkRooms += roomServices.CheckRoomCountForupdate(parameterObject);
			}else{
				checkRooms += roomServices.CheckRoomCount(parameterObject);
			}
		}
	
		if (checkRooms != 0){
			//simpleMap = (SimpleMap)roomServices.CheckRoom(parameterObject);
			str.append("");
			str.append(" <input type='hidden' name='checkRooms1' id='checkRooms1' value='0'/>") ;
		}else{
			str.append("");
			str.append(" <input type='hidden' name='checkRooms1' id='checkRooms1' value='1'/>") ;
		}
			
    	out.println(str.toString());
        out.close();
        
        return "" ;
	}
}