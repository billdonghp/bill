package com.ait.ar.dao.item;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.ar.dao.ArItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int count = 0 ;
		ArItem info = new ArItem();
		ArItemBean dao = new ArItemBean();
		String menu_code = request.getParameter("menu_code");
		String itemName = request.getParameter("itemName");
		String itemEnName = request.getParameter("itemEnName");
		String itemKorName = request.getParameter("itemKorName");
		String itempGroupCode = request.getParameter("itempGroupCode");
		String itemId = request.getParameter("itemId");
		String shortName = request.getParameter("shortName");
		int activity = Integer.parseInt(request.getParameter("activity"));
		String description = request.getParameter("description");
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		String adminId = admin.getAdminID();
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		ArrayList values = new ArrayList();
		values.add(new Integer(itemNo));
		values.add(itemName);
		values.add(itemEnName);
		values.add(itemKorName);
		values.add(shortName);
		values.add(description);
		values.add(new Integer(activity));
		values.add(itempGroupCode);
		values.add(itemId);
		values.add(adminId);
		
		info.setItemNo(itemNo) ;
		info.setItemName(itemName) ;
		info.setItemGroupCode(itempGroupCode) ;
		info.setItemId(itemId) ;
		info.setShortName(shortName) ;
		info.setActivity(activity) ;
		info.setDescription(description) ;
		String url = "/aritemlist.jsp?menu_code=" + menu_code;
		try {
			count = dao.validateItemNameUpdate(info) ;
			if(count > 0)
			{
				url = "/aritem_m.jsp?menu_code=" + menu_code ;
				request.setAttribute("arItem", info) ;
				request.setAttribute("error", "error") ;
			}
			else
			{
				dao.editArItem(values);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug("Update attendance item Exception. " + e.toString());
			throw new GlRuntimeException("Update attendance item Exception.", e);
		}
		Logger.getLogger(getClass()).debug("return page : " + url);
		return url;
	}
}
