package com.ait.ar.dao.staitem;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArStaItem;
import com.ait.ar.dao.ArStaItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.util.NumberUtil;
import com.ait.web.Command;

public class Update implements Command {
	private String returnR = null;

	public Update() {
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0 ;
		ArStaItem arStaItem = new ArStaItem();
		String fDate = "";
		String tDate = "";
		String menu_code = request.getParameter("menu_code");
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		ArStaItemBean dao = new ArStaItemBean();
		String itemName = request.getParameter("itemName");
		String itemEnName = request.getParameter("itemEnName");
		String itemKoName = request.getParameter("itemKoName");
		String oldItemName = request.getParameter("oldItemName");
		String staItemId = request.getParameter("staItemId");
		String unit = request.getParameter("unit");

		float minUnit = Float.parseFloat(request.getParameter("minUnit"));
		try {
			int fYear = Integer.parseInt(request.getParameter("fYear"));
			int fMonth = Integer.parseInt(request.getParameter("fMonth"));
			fDate = fYear + dao.getMonth(fMonth);
		} catch (Exception ex) {
			//Logger.getLogger(getClass()).error(ex.toString());
			fDate = "";
		}
		try {
			int tYear = Integer.parseInt(request.getParameter("tYear"));
			int tMonth = Integer.parseInt(request.getParameter("tMonth"));
			tDate = tYear + dao.getMonth(tMonth);
		} catch (Exception ex) {
			//Logger.getLogger(getClass()).error(ex.toString());
			tDate = "";
		}

		ArrayList values = new ArrayList();
		values.add(itemName);
		values.add(itemEnName);
		values.add(itemKoName);
		values.add(unit);
		values.add(new Float(minUnit));
		values.add(fDate);
		values.add(tDate);
		values.add(staItemId);
		
		arStaItem.setItemNo(itemNo) ;
		arStaItem.setItemName(itemName) ;
		arStaItem.setUnit(unit) ;
		arStaItem.setMinUnit(minUnit) ;
		arStaItem.setFromDate(fDate) ;
		arStaItem.setToDate(tDate) ;
		arStaItem.setStaItemId(staItemId) ;
		arStaItem.setOldItemName(oldItemName) ;
		arStaItem.setFormYear(NumberUtil.parseInt(request.getParameter("fYear"))) ;
		arStaItem.setFormMonth(NumberUtil.parseInt(request.getParameter("fMonth"))) ;
		arStaItem.setToYear(NumberUtil.parseInt(request.getParameter("tYear"))) ;
		arStaItem.setToYear(NumberUtil.parseInt(request.getParameter("tMonth"))) ;
		
		String url = "/arstaitem.jsp?menu_code=" + menu_code;
		try {
			count = dao.validateItemNameUpdate(arStaItem) ;
			if(count > 0)
			{
				url = "/arstaitem_m.jsp?menu_code=" + menu_code;
				request.setAttribute("error", "error") ;
				request.setAttribute("arStaItem", arStaItem) ;
			}
			else
			{
				dao.EditStaItem(values, itemNo);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug("Update attendance summary item Exception. " + e.toString());
			throw new GlRuntimeException("Update attendance summary item Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return page : " + url);
		return url;
	}

}
