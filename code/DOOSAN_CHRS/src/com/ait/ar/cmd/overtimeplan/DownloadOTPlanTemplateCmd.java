
package com.ait.ar.cmd.overtimeplan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.ar.servlet.ArAbstractCommand;
import com.ait.excel.util.ReportUtil;
import com.ait.i18n.MessageSource;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;

public class DownloadOTPlanTemplateCmd extends ArAbstractCommand {

	/* (non-Javadoc)
	 * @see com.ait.ar.servlet.ArAbstractCommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminBean admin = (AdminBean) request.getSession(false).getAttribute("admin");
		MessageSource messageSource = new MessageSource(admin.getLocale(), "UTF-8"); 
		
		String fileName = "OTPLAN_EMP_DATE.xls";

		String sheetName = "OTPLAN_EMP_DATE";

		SimpleMap columns = new SimpleMap();
		//职号
		columns.setString(messageSource.getMessage("display.mutual.emp_id"), "") ;
		columns.setString("加班上限", "") ;
		columns.setString("开始月份", "") ;
		columns.setString("结束月份", "") ;
		columns.setString("申请限制时间", "") ;
		
		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}

}

