
package com.ait.pa.cmd.paEmail;

import java.io.IOException;
import java.util.ArrayList;
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

public class ViewPaEmailcmdCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		AdminBean adminBean = new AdminBean();
		adminBean = (AdminBean) request.getSession().getAttribute("admin") ;
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List paMailList = new ArrayList() ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			parameterObject.setString("cpny_id", adminBean.getCpnyId());
			if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_HISTORY"))
			{
				parameterObject.setString("bonusTypeCode", "BonusType") ;
				parameterObject.setInt("bonusNo", 0) ;
			}
			else if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_REPLENISHMENT_HISTORY"))
			{
				parameterObject.setString("bonusTypeCode", "BonusType00") ;
				parameterObject.setInt("bonusNo", 0) ;
			}
			
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style2.pagesize");
			int pageGroupSize = config.getInt("page.style2.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
//			 取得数据行数
			if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_HISTORY"))
			{
				paMailList = services.retrievePaInfoForSendMail(parameterObject,currentPage,pageSize) ;
			}
			else if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_BONUS_HISTORY"))
			{
				paMailList = services.retrievePaBonusInfoForSendMail(parameterObject,currentPage,pageSize) ;
			}
			else if (parameterObject.getString("tableName") != null && parameterObject.getString("tableName").equals("PA_REPLENISHMENT_HISTORY"))
			{
				paMailList = services.retrievePaReplenishmentInfoForSendMail(parameterObject,currentPage,pageSize) ;
			}
			
			int resultCount = services.retrievePaMailCount(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {
				
				currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
			}
			
			
			request.setAttribute("paMailList", paMailList);
			request.setAttribute("tableName", parameterObject.getString("tableName"));
			request.setAttribute("bonusTypeCode", parameterObject.getString("bonusTypeCode"));
			request.setAttribute("bonusNo", parameterObject.getString("bonusNo"));
			request.setAttribute("statTypeCode", parameterObject.getString("statTypeCode"));
			request.setAttribute("year", parameterObject.getString("year"));
			request.setAttribute("month", parameterObject.getString("month"));
			request.setAttribute("deptId", parameterObject.getString("deptId"));
			request.setAttribute("key", parameterObject.getString("key"));
			request.setAttribute("person_id", parameterObject.getString("person_id"));
			request.setAttribute("paMonth", parameterObject.getString("paMonth"));
			request.setAttribute("activity", parameterObject.getString("activity"));
			request.setAttribute("supplyFlag", parameterObject.getString("supplyFlag"));
			
			
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa email Exception. ", e);
		}

		return "/pa_email.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}