package com.ait.ga.cmd.festivalpresent;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.GaServices;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;

public class InsertApplyFestivalPresentCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageSource messageSource;
		GaServices services = new GaServices();
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject;
		GaAffirm gaAffirm = new GaAffirm();
		EssSysparam essSysparam = (EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,admin.getCpnyId());

		try {

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("personId",admin.getPersonId());
			parameterObject.setString("cpnyId", admin.getCpnyId());
			
			// insert present
			String[] str = request.getParameterValues("affirmno");
			for(int i=0;i<str.length;i++){
				parameterObject.setString("presentObj", request.getParameter("presentObj"+str[i]));
				parameterObject.setString("schemeNo", request.getParameter("schemeName"+str[i]));
//				List gaAffirmList = gaAffirm.getAffirmor(request.getParameter("presentObj"+str[i]), "FestivalPresentApply", essSysparam.isContainsOwner());
//				Iterator iterator = gaAffirmList.iterator();
//				int j=0;
//				for(;iterator.hasNext();j++){
//					GaAffirmList gaAffirmList2 = (GaAffirmList)iterator.next();
//					parameterObject.set("affirmLevel"+j, gaAffirmList2.getAffirmLevel());
//					parameterObject.set("affirmId"+j, gaAffirmList2.getAffirmorId());
//				}
//				parameterObject.setInt("j", j);
				services.insertApplyFestivalPresent(parameterObject);
			}
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("Insert apply festival present Exception. ", e);
		}

		//添加成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.add_successfully"));
		request.setAttribute("url","gaControlServlet?operation=retrieveFestivalPresentApply&menu_code=" + parameterObject.getString("menu_code"));

		return "/inc/alertMessage.jsp";
	}
}
