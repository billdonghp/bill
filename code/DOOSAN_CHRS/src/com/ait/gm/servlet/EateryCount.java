package com.ait.gm.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.gm.business.EateryServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.StringUtil;
import com.ait.web.Command;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class EateryCount implements Command {


	SimpleMap parameterObject = new SimpleMap();
	EateryServices eatServic = new EateryServices();
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String applydate=request.getParameter("APPLY_DATE");
		parameterObject.set("applydate", applydate);
		eatServic.deleteEaterycount(parameterObject);
		String[]item1=request.getParameterValues("item1");
		for(int i=0;i<item1.length;i++){
			parameterObject.set("item", item1[i]);
			parameterObject.set("Breakfast", StringUtil.checkNull(request.getParameter(item1[i]+"Breakfast"),"0"));
			parameterObject.set("Lunch", StringUtil.checkNull(request.getParameter(item1[i]+"Lunch"),"0"));
			parameterObject.set("Dinner", StringUtil.checkNull(request.getParameter(item1[i]+"Dinner"),"0"));
			parameterObject.set("Night", StringUtil.checkNull(request.getParameter(item1[i]+"Night"),"0"));
			eatServic.insertEaterycountTB(parameterObject);
		}
		String[]item2=request.getParameterValues("item2");
		for(int y=0;y<item2.length;y++){
			parameterObject.set("item", item2[y]);
			parameterObject.set("cun", StringUtil.checkNull(request.getParameter(item2[y]),"0"));	
			eatServic.insertEaterycountJC(parameterObject);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		String maxDate=eatServic.getEaterycountMaxDate(parameterObject);
		try {
			today.setTime(df.parse(maxDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		today.add(Calendar.DATE, 1);
		request.setAttribute("applydate", df.format(today.getTime()));
		return "gm/MonthEateryReportSet.jsp";
	}

}
