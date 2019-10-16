package com.ait.ar.dao.formular;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.dao.ArItemBean;
import com.ait.ar.dao.ArStaFormularBean;
import com.ait.ar.dao.ArStaItemBean;
import com.ait.core.exception.GlRuntimeException;
import com.ait.web.Command;

public class Update implements Command {
	public Update() {
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition_cn = request.getParameter("condition").trim();
		String formular_cn = request.getParameter("formular").trim();
		String condition_en = request.getParameter("condition").trim();
		String formular_en = request.getParameter("formular").trim();
		ArItemBean aritem = new ArItemBean() ;
		ArStaItemBean arstaitem = new ArStaItemBean() ;

		try {
			ArrayList items = aritem.getItemList();
			ArrayList staitems = arstaitem.getStaItemList();
			ArrayList paList = arstaitem.getPA_DISTINCT_LIST();

			int itemNo = Integer.parseInt(request.getParameter("itemNo"));
			int formularNo = Integer.parseInt(request.getParameter("formularNo"));
			ArStaFormularBean r = new ArStaFormularBean();
			condition_en = r.formatConditionOrFormular(condition_en, items, staitems, paList) ;
			formular_en =  r.formatConditionOrFormular(formular_en, items, staitems, paList) ;
			
			ArrayList values = new ArrayList();
			values.add(condition_en);
			values.add(formular_en);
			values.add(condition_cn);
			values.add(formular_cn);
			values.add(new Integer(formularNo).toString());
			values.add(new Integer(itemNo).toString());
			
			r.Editformular(values);
		} catch (Exception e) {

			Logger.getLogger(getClass()).debug("Update summary item formular Exception. " + e.toString());
			throw new GlRuntimeException("Update summary item formular Exception. ", e);
		}
		Logger.getLogger(getClass()).debug("return #");
		return "#";
	}
}
