package com.ait.ar.dao.ardate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArDate;
import com.ait.ar.dao.ArDateBean;
import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.sy.bean.AdminBean;
import com.ait.util.StringUtil;
import com.ait.util.NumberUtil;

public class Add extends ArAbstractCommand {
	private String returnS = null;

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		MessageSource messageSource ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		String menu_code = request.getParameter("menu_code");
		
		ArDateBean dao = new ArDateBean();
		ArDate arDate = new ArDate() ;

		String statCode = request.getParameter("empDiffTypeCode");
		arDate.setCode(statCode) ;
		String describe = StringUtil.checkNull(request.getParameter("describe"));
		arDate.setDescribe(describe) ;
		int fYear = NumberUtil.parseInt(request.getParameter("fYear"));
		int fMonth = NumberUtil.parseInt(request.getParameter("fMonth"));
		int fDay = NumberUtil.parseInt(request.getParameter("fDay"));
		String fromDate = fYear + "-" + fMonth + "-" + fDay;
		arDate.setFromDate(fromDate) ;
		int tYear = NumberUtil.parseInt(request.getParameter("tYear"));
		int tMonth = NumberUtil.parseInt(request.getParameter("tMonth"));
		int tDay = NumberUtil.parseInt(request.getParameter("tDay"));
		String toDate = tYear + "-" + tMonth + "-" + tDay;
		arDate.setToDate(toDate) ;
		int starDay = Integer.parseInt(request.getParameter("starDay"));
		arDate.setStarDate(starDay) ;
		int endDay = Integer.parseInt(request.getParameter("endDay"));
		arDate.setEndDate(endDay) ;
		arDate.setCpnyId(request.getParameter("cpnyId"));
		
		try {
			
			int result = dao.validateAddDate(arDate) ;
			if (result != 0) {
//				所添加的员工区分已存在
				messageSource = new MessageSource("ar",admin.getLocale(), "UTF-8");
				request.setAttribute("alert", "所添加的员工区分已存在");
				request.setAttribute("reload","history.back();");
				return "/inc/alertMessage.jsp";
			}		

			dao.addDate(arDate);

		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert ar_statistic record Exception. ", e);
		}
//		添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","ar/ardatelist.jsp?menu_code=" + menu_code);

		return "/inc/alertMessage.jsp";
	}
}
