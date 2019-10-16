package com.ait.ga.cmd.smock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.excel.util.ReportUtil;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;

public class DownloadSmockRelationTemplateCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = "SMOCKRELATION_DATA_Template.xls";

		String sheetName = "SMOCKRELATION_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString("员工职号","");
		columns.setString("工作服名称", "");
		columns.setString("型号(大写)", "");
		columns.setString("频率", "");
		columns.setString("频率单位","");
		columns.setString("备注", "");
		
		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}

}
