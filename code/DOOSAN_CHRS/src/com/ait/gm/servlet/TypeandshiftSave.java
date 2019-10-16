package com.ait.gm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;

public class TypeandshiftSave extends ArAbstractCommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.putToolbarInfo(request);
		GMDAO gm = new GMDAO();
		String SHIFT_NAMES[]=request.getParameterValues("SHIFT_NO");
		SimpleMap parameterObject = null;
		SimpleMap map = null;
		String adminId = ((AdminBean) request.getSession(false).getAttribute("admin")).getAdminID();
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin");
		String checkBox1=(String)request.getParameter("checkBox1");
		
		MessageSource messageSource;
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");

			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("AdminID", adminId);
			parameterObject.set("STARTTIME", parameterObject.getString("listHHS") +":"+ parameterObject.getString("listMMS"));
			parameterObject.set("ENDTIME", parameterObject.getString("listHHE") +":"+ parameterObject.getString("listMME"));
			
			List<SimpleMap> parameterList = new ArrayList<SimpleMap>() ;
			
			
			if(SHIFT_NAMES==null){
				map = new SimpleMap() ;
				map.putAll(parameterObject);
				parameterList.add(map) ;
				gm.ShiftAndGmtypeUpdateNoCheckShift(parameterList,parameterObject);
			}else{
				for (int i = 0 ; i < SHIFT_NAMES.length ; i ++){
					map = new SimpleMap() ;
					map.putAll(parameterObject);
					map.setString("SHIFT_NO", SHIFT_NAMES[i]) ;
					parameterList.add(map) ;
				}
			}
			if(checkBox1.equals("0")){
				gm.ShiftAndGmtypeUpdateCheckNo(parameterList,parameterObject);
			}else if(checkBox1.equals("1")){
				gm.ShiftAndGmtypeUpdateCheckYes(parameterList,parameterObject);
			}else if(SHIFT_NAMES==null || checkBox1.equals("0")){
				gm.ShiftAndGmtypeUpdateCheckNo(parameterList,parameterObject);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("typeandshiftSAVE by No Exception. ",
					e);
		}
//		修改成功
		messageSource = new MessageSource(admin.getLocale(), "UTF-8");
		request.setAttribute("alert", messageSource.getMessage("alert.mutual.modify_successfully"));
		request.setAttribute("url","/gmControlServlet?operation=GM_AND_SHIFT&menu_code=" + parameterObject.getString("menu_code"));
		return "/inc/alertMessage.jsp";
	}
}
