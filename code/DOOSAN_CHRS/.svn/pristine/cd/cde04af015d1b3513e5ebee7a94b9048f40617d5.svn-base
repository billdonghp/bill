
package com.ait.pa.cmd.paramItem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.pa.business.PaServices;
import com.ait.pa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class PaParamItemCmd extends PaAbstractCommand {

	private AdminBean admin = null ; 
	
	private PaServices services = PaServices.getInstance();
	
	private SimpleMap parameterObject = new SimpleMap() ;
	
	private String method = null ;
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		String url = "" ;
		
		admin = (AdminBean)request.getSession(true).getAttribute("admin");
		
		method = StringUtil.checkNull(request.getParameter("method"));
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.set("CPNY_ID", admin.getCpnyId());
			
			if(method.equals("list") || method.length() == 0){
				url = this.list(request, response) ;
			}
			else 
			{
				url = "/inc/alertMessage.jsp" ;
				if (method.equals("add"))
				{
					
				}
				else if (method.equals("update"))
				{
					
				}
				else if (method.equals("save"))
				{
					
				}
				else if (method.equals("delete"))
				{

				}
			}
			
			
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode")) ;
			request.setAttribute("cpnyId", admin.getCpnyId());

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa param item Exception. ", e);
		}

		return url ; 
	}
	
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String list(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			int resultCount = 0;
			currentPage = NumberUtil.parseInt(request.getParameter("currentPage"), 0);
			
			List<SimpleMap> paParamItimList  = this.services.retrievePaParamList(parameterObject, currentPage, pageSize) ;
			
			resultCount = this.services.retrievePaParamListCnt(parameterObject) ;
			
			request.setAttribute("paParamItimList", paParamItimList) ;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa param item list Exception. ", e);
		}
		return "/pa_param_item.jsp?menu_code=" + parameterObject.getString("menu_code") ;
	}
}