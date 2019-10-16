
package com.ait.pa.cmd.bonus.bonusParamItemConfigure;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.util.NumberUtil;
import com.ait.util.StringUtil;

public class PaBonusParamItemConfigureCmd extends PaAbstractCommand {

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
			
			String msg = "";
			MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8");
			
			
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("CPNY_ID", admin.getCpnyId());
			
			if(method.equals("list") || method.length() == 0){
				// 取日期参数
				GregorianCalendar currentDay = new GregorianCalendar();
				int m = currentDay.get(Calendar.MONTH) + 1;
				int y = currentDay.get(Calendar.YEAR);

				String month = ("0" + String.valueOf(m)).substring(("0" + String.valueOf(m)).length() - 2, ("0" + String.valueOf(m)).length());
				String year = String.valueOf(y);

				String arMonth = year + month;

				if (request.getParameter("month") != null && request.getParameter("year") != null) {
					try {
						month = request.getParameter("month");
						year = request.getParameter("year");

						arMonth = year + month;

					} catch (Exception e) {
					}
				}
				
				parameterObject.setString("PA_MONTH_STR", arMonth) ;
				url = this.list(request, response) ;
				
				request.setAttribute("year", year);
				request.setAttribute("month", month);
			}
			else 
			{
				url = "/inc/alertMessage.jsp" ;
				request.setAttribute("url","/paControlServlet?operation=paBonusParamItemConfigureList&menu_code=" + parameterObject.getString("menu_code") );
				
				if (method.equals("add"))
				{
					int errCount = this.services.validateAddPaBonusParamConfigure(parameterObject) ;
					
					if (errCount > 0){
						request.setAttribute("alert", "添加失败,添加的信息已存在!");
						request.setAttribute("reload","history.back();");
						request.removeAttribute("url");
					}
					else{
						this.insert(request, response) ;
						msg = messageSource.getMessage("alert.mutual.save_successfully");
						request.setAttribute("alert", msg);
						
					}
				}
				else if (method.equals("addView"))
				{
					url = this.addView(request, response) ;
				}
				else if (method.equals("updateView"))
				{
					url = this.updateView(request, response) ;
				}
				else if (method.equals("update"))
				{
					int errCount = this.services.validateUpdatePaBonusParamConfigure(parameterObject) ;
					
					if (errCount > 0){
						request.setAttribute("alert", "修改失败,修改的信息已存在!");
						request.setAttribute("reload","history.back();");
						request.removeAttribute("url");
					}
					else{
						this.update(request, response) ;
					
						msg = messageSource.getMessage("alert.mutual.save_successfully");
						request.setAttribute("alert", msg);
					}
					
				}
				else if (method.equals("delete"))
				{
					this.delete(request, response) ;
					
					msg = messageSource.getMessage("alert.mutual.delete_successfully");
					
					request.setAttribute("alert", msg);
				}
			}
			
			String cpnyDiff = parameterObject.getString("cpnyDiff") ;
			
			if (cpnyDiff != null && cpnyDiff.length() > 0)
				request.setAttribute("cpnyDiff",cpnyDiff);
			else
				request.setAttribute("cpnyDiff",admin.getCpnyId());
			
			
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("PARAM_ID", parameterObject.getString("PARAM_ID"));
			//request.setAttribute("CPNY_ID", admin.getCpnyId());

		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("pa param item Exception. ", e);
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
			
			List<SimpleMap> paBonusParamItimConfigureList  = this.services.retrievePaBonusParamConfigureList(parameterObject, currentPage, pageSize) ;
			
			resultCount = this.services.retrievePaBonusParamConfigureListCnt(parameterObject) ;
			
			request.setAttribute("paParamItimConfigureList", paBonusParamItimConfigureList) ;
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
			
			
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa param item Configure list Exception. ", e);
		}
		return "/pa_bonus_param_item_configure.jsp?menu_code=" + parameterObject.getString("menu_code") ;
	}
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			this.services.deletePaBonusParamConfigure(parameterObject) ;
			
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("delete pa bonus param item Configure Exception. ", e);
		}
		return null ;
	}
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String updateView(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			List<SimpleMap> paBonusParamItimConfigureList  = this.services.retrievePaBonusParamConfigureList(parameterObject) ;
			
			request.setAttribute("paParamItimConfigure", paBonusParamItimConfigureList.get(0)) ;
			
			
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa param item Configure Exception. ", e);
		}
		return "/pa_bonus_param_item_configure_update.jsp?menu_code=" + parameterObject.getString("menu_code") ;
	}
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String update(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			this.services.updatePaBonusParamConfigure(parameterObject) ;
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("update pa bonous param item Configure Exception. ", e);
		}
		return null ;
	}
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String addView(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			List<SimpleMap> paBonusParamItimList  = this.services.retrievePaBonusParamList(parameterObject) ;
			
			request.setAttribute("paParamItim", paBonusParamItimList.get(0)) ;
			
			
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa param item Configure Exception. ", e);
		}
		return "/pa_bonus_param_item_configure_add.jsp?menu_code=" + parameterObject.getString("menu_code") ;
	}
	
	// 取得所有工资输入项目数据,区分公司与公共
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			this.services.insertPaBonusParamConfigure(parameterObject) ;
		}
		catch(Exception e)
		{
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("insert pa bonus param item Configure Exception. ", e);
		}
		return null ;
	}
}