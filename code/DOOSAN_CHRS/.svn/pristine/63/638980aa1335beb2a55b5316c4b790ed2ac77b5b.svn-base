package com.ait.ar.dao.formular;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArStaFormularBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;
import com.ait.web.Command;
import com.ait.ar.dao.ArItemBean ;
import com.ait.ar.dao.ArStaItemBean ;

public class Add implements Command {
	public Add() {
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 字符集转换
		/*
		 * 将中文转换成,id的形式,用String的替换方法,有够累!!!!
		 * 数据库中的存储过程，有此定义
		 * id = "ATT_ITEM." ; name = "考勤明细." ;
    	 * id = "STA_ITEM." ; name = "考勤汇总." ;
		 */
		String condition_cn = request.getParameter("condition");
		String formular_cn = request.getParameter("formular");
		String condition_en = request.getParameter("condition");
		String formular_en = request.getParameter("formular");
		ArItemBean aritem = new ArItemBean() ;
		ArStaItemBean arstaitem = new ArStaItemBean() ;
		
		int itemNo = 0;
		ArrayList<String> values = new ArrayList<String>();
		ArStaFormularBean r = new ArStaFormularBean();
		try {
			ArrayList items = aritem.getItemList();
			ArrayList staitems = arstaitem.getStaItemList();
			ArrayList paList = arstaitem.getPA_DISTINCT_LIST();

			itemNo = NumberUtil.parseInt(StringUtil.checkNull(request.getParameter("itemNo")));
			
			if(condition_en != null && condition_en.length() > 0)
				condition_en = r.formatConditionOrFormular(condition_en, items, staitems, paList) ;
			if(formular_en != null && formular_en.length() > 0)
				formular_en =  r.formatConditionOrFormular(formular_en, items, staitems, paList) ;
			values.add((new Integer(itemNo)).toString());
			values.add(condition_en);
			values.add(formular_en);
			values.add(condition_cn);
			values.add(formular_cn);
			
			r.Addformular(values);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug(
					"Add summary item formular Exception. " + e.toString());
			throw new GlRuntimeException(
					"Add summary item formular Exception. ", e);
		}

		Logger.getLogger(getClass()).debug("return #");
		return "#";

	}
}
