
package com.ait.pa.cmd.bonus.bonusparamdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.i18n.MessageSource;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;

public class PaBonusParamDataActionCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance() ;
		SimpleMap parameterObject ;
		AdminBean admin = (AdminBean)request.getSession().getAttribute("admin") ;
		MessageSource messageSource ;
		List<SimpleMap> parameterList = new ArrayList<SimpleMap>() ;
		
		try {
			messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			request.setAttribute("alert", messageSource.getMessage("alert.mutual.save_successfully"));
			request.setAttribute("url","/paControlServlet?operation=pa_bonus_param_data&configureNo=" + request.getParameter("configureNo")
					+"&pamonth=" + request.getParameter("pamonth") + "&statTypeCode=" + request.getParameter("statTypeCode")
			);
			
			// bind parameter
			String flag = request.getParameter("flag") ;
			
			String [] return_value = request.getParameterValues("return_value") ;
			String [] startMonth = request.getParameterValues("startMonth") ;
			String [] endMonth = request.getParameterValues("endMonth") ;
			String [] sdEdValue = request.getParameterValues("sdEdValue") ;
			String cpnyId=admin.getCpnyId();			
			if (flag.equals("insert"))
			{
				String configureNo = request.getParameter("configureNo") ;
				String [] field1 = request.getParameterValues("field1") ;
				String [] field2 = request.getParameterValues("field2") ;
				
				for (int i = 0 ; i < return_value.length ; i++ )
				{
					if((return_value[i] != null && return_value[i].length() > 0) || (sdEdValue[i] != null && sdEdValue[i].length() > 0))
					{
						parameterObject = new SimpleMap() ;
						parameterObject.set("param_no", configureNo) ;
						parameterObject.set("field1", field1[i]) ;
						parameterObject.set("field2", field2[i]) ;
						parameterObject.set("return_value", return_value[i]) ;
						parameterObject.set("startMonth", startMonth[i]) ;
						parameterObject.set("endMonth", endMonth[i]) ;
						parameterObject.set("sdEdValue", sdEdValue[i]) ;
						parameterObject.set("cpnyId", cpnyId);
						parameterList.add(parameterObject) ;
					}
				}
				
				services.insertPaBonusParamData(parameterList) ;
			}
			else if (flag.equals("modify"))
			{
				String [] param_data_no = request.getParameterValues("param_data_no");
				
				for (int i = 0 ; i < param_data_no.length ; i++ )
				{
					parameterObject = new SimpleMap() ;
					parameterObject.set("param_data_no", param_data_no[i]) ;
					parameterObject.set("return_value", return_value[i]) ;
					parameterObject.set("startMonth", startMonth[i]) ;
					parameterObject.set("endMonth", endMonth[i]) ;
					parameterObject.set("sdEdValue", sdEdValue[i]) ;
					parameterList.add(parameterObject) ;	
				}
				
				services.updatePaBonusParamData(parameterList) ;
			}
			else if (flag.equals("Initial"))
			{
				parameterObject = ObjectBindUtil.getData(request);
			    parameterObject.set("cpnyId", admin.getCpnyId());
				services.paBonusParamInitial(parameterObject) ;
				
				request.setAttribute("alert", "输入项目初始化成功!");
				request.setAttribute("url","/paControlServlet?operation=pa_bonus_param&menu_code=" + request.getParameter("menu_code")
						+"&pamonth=" + request.getParameter("pamonth") + "&statTypeCode=" + request.getParameter("statTypeCode")
				);
			}
			

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa bonus param action Exception. ", e);
		}
		//添加成功
		
		
		return "/inc/alertMessage.jsp";
	}
}