/**
 * @Copyright 
 * @author qinxd
 * @date 2006-9-6
 */
package com.ait.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.core.exception.GlRuntimeException;
import com.ait.web.poi.PoiReportFactory;

/**
 * 通用创建excel的servlet
 * @version 
 */
public class CommonXlsServlet  extends HttpServlet {

	private static final long serialVersionUID = 5311336691916852074L;
	
	private final static Logger log = Logger.getLogger(CommonXlsServlet.class);
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        log.debug("PoiReprotServlet run...");
        SqlAndTitle sqlAndTitle = CommonXlsSqlHelper.getSqlAndTitle(request);
        if (sqlAndTitle == null)
        	throw new GlRuntimeException("创建Excel报表触发Key找不到异常，请与管理员联系！");
        String filename = sqlAndTitle.getFilename() + ".xls";
        log.debug("filename == " + filename);
        response.setHeader("Content-Type", "application/vnd.ms-excel; charset=UTF-8");
 		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");
		OutputStream os = response.getOutputStream();
		try {
			HSSFWorkbook wb = PoiReportFactory.getReprotCreator().createXls(sqlAndTitle);
            if (wb != null)
            	wb.write(os);            
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex);
			throw new GlRuntimeException("创建Excel报表触发异常，请与管理员联系！");
		} finally {
			os.close();			
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


}
