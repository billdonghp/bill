
package com.ait.kpa.cmd.diff.diffitem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ait.core.exception.GlRuntimeException;
import com.ait.kpa.business.PaServices;
import com.ait.kpa.servlet.PaAbstractCommand;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;

public class RetrieveDiffItemListCmd extends PaAbstractCommand {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.putToolbarInfo(request);
		
		PaServices services = PaServices.getInstance();
		SimpleMap parameterObject = new SimpleMap() ;
		List diffItemList = new ArrayList() ;
	    
		try {
			// bind parameter
			parameterObject = ObjectBindUtil.getData(request);
			
			/* paging logic */
			UserConfiguration config = UserConfiguration.getInstance("/system.properties");
			int pageSize = config.getInt("page.style1.pagesize");
			int pageGroupSize = config.getInt("page.style1.pagegroupsize");
			int currentPage = 0;
			// if has currentpage set value into currentPage
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals(""))
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
//			 取得数据行数
			diffItemList = services.retrieveDiffItemList(parameterObject,currentPage,pageSize) ;
			int resultCount = services.retrieveDiffItemListCnt(parameterObject);

			// 如果"当前页"大于最大页数,取最后一页
			if (currentPage > (resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize) && resultCount != 0) {
				
				currentPage = resultCount % pageSize == 0 ? resultCount / pageSize - 1 : resultCount / pageSize;
			}
			
			
			request.setAttribute("diffItemList", diffItemList);
			request.setAttribute("search", parameterObject.getString("search"));
			// paging parameter
			request.setAttribute("resultCount", resultCount);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageGroupsize", pageGroupSize);
		} catch (Exception e) {

			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("retrieve pa bonus item Exception. ", e);
		}

		return "/kpa_diff_item.jsp?menu_code=" + request.getParameter("menu_code") ; 
	}
}