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
import com.ait.web.Command;
import com.ait.util.NumberUtil ;

public class Add implements Command {
	private String returnR = null;

	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int count = 0 ;
		ArStaItem arStaItem = new ArStaItem();
		String fDate = "";
		String tDate = "";
		String cpnyId=request.getParameter("cpnyId");
		String menu_code = request.getParameter("menu_code");
		ArStaItemBean dao = new ArStaItemBean();
		String itemName = request.getParameter("itemName");
		String itemEnName = request.getParameter("itemEnName");
		String itemKoName = request.getParameter("itemKoName");
		String unit = request.getParameter("unit");
		String staItemId = request.getParameter("staItemId");

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
		values.add("ar_sta_item_seq.nextval");
		values.add(itemName);
		values.add(itemEnName);
		values.add(itemKoName);
		values.add(unit);
		values.add(new Float(minUnit));
		values.add(fDate);
		values.add(tDate);
		values.add(staItemId);
		values.add(cpnyId);
		
		arStaItem.setCpnyId(cpnyId);
		arStaItem.setItemName(itemName) ;
		arStaItem.setUnit(unit) ;
		arStaItem.setMinUnit(minUnit) ;
		arStaItem.setStaItemId(staItemId) ;
		arStaItem.setFormYear(NumberUtil.parseInt(request.getParameter("fYear"))) ;
		arStaItem.setFormMonth(NumberUtil.parseInt(request.getParameter("fMonth"))) ;
		arStaItem.setToYear(NumberUtil.parseInt(request.getParameter("tYear"))) ;
		arStaItem.setToYear(NumberUtil.parseInt(request.getParameter("tMonth"))) ;
		
		String url = "/arstaitem.jsp?menu_code=" + menu_code;
		try {
			count = dao.validateItemNameInsert(arStaItem) ;
			if(count > 0)
			{
				url = "/arstaitem_a.jsp?menu_code=" + menu_code;
				request.setAttribute("error", "error") ;
				request.setAttribute("arStaItem", arStaItem) ;
			}
			else
			{
				dao.AddStaItem(values);
			}
			
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug("Insert attendance summary item Exception. " + e.toString());
			throw new GlRuntimeException("Insert attendance summary item Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return page is : " + url);
		return url;
	}
}
