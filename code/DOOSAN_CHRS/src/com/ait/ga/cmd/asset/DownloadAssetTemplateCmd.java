package com.ait.ga.cmd.asset;
/**
 * Copyright: AIT (c) Company: AIT
 * 
 * @author  wangbin
 * @Date 2009-7-10
 * 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ait.excel.util.ReportUtil;
import com.ait.ga.servlet.GaAbstractCommand;
import com.ait.sqlmap.util.SimpleMap;

public class DownloadAssetTemplateCmd extends GaAbstractCommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fileName = "ASSET_DATA_Template.xls";

		String sheetName = "ASSET_DATA";

		SimpleMap columns = new SimpleMap();
		columns.setString("资产类型", "");
		columns.setString("资产编号", "");
		columns.setString("名称", "");
		columns.setString("起案编号", "");
		columns.setString("品牌", "");
		columns.setString("规格", "");
		columns.setString("产品号", "");
		columns.setString("数量", "");
		columns.setString("单价", "");
		columns.setString("购买日期", "");
		columns.setString("保修期", "");
		columns.setString("购买厂家", "");
		columns.setString("固定资产编号", "");
		columns.setString("使用位置", "");
		columns.setString("使用部门", "");
		columns.setString("管理担当", "");
		columns.setString("预点检日期", "");
		columns.setString("备注", "");
		
		ReportUtil.makeReport(request, columns, fileName, sheetName, 1, 1);

		return "/inc/commonReport.jsp";
	}
}
