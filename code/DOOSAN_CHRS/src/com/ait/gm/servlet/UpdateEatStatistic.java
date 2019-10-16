package com.ait.gm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.core.exception.GlRuntimeException;
import com.ait.gm.dao.GMDAO;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
public class UpdateEatStatistic extends ArAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		SimpleMap parameterObject=null;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		MessageSource messageSource;
		try {
			String PeopleNum[]=request.getParameterValues("PeopleNum");
			String applyNo[] = request.getParameterValues("applyNo");
			for(int i=0;i<PeopleNum.length;i++){
				//bind parameter
				for(int j=0;j<applyNo.length;j++){
					parameterObject = ObjectBindUtil.getData(request);
					parameterObject.set("PeopleNum", PeopleNum[i]);
					parameterObject.set("applyNo", applyNo[i]);
					gm.updatesaveStatistic(parameterObject);
				}
			}

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert Gm Exception. ", e);
		}
		
		//修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/gmControlServlet?operation=eatStatistic&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}