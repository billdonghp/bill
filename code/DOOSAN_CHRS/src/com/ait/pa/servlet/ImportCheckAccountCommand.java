package com.ait.pa.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.ait.pa.dao.report.PaImportPayBankDAO;
import com.ait.sqlmap.util.ObjectBindUtil;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class ImportCheckAccountCommand implements Command {

	private static final Logger logger = Logger.getLogger(PaImportPayBankCommand.class);

	private static final FileItemFactory factory = new DiskFileItemFactory();

	private AdminBean admin = null ;
	
	private List<Map<String,String>> errorList = null ;
	
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		SimpleMap parameterObject;
		try {

			parameterObject = ObjectBindUtil.getData(request);
			admin = (AdminBean) request.getSession().getAttribute("admin");
			String PersonId = admin.getAdminID();
			String cpnyId = admin.getCpnyId();
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					this.processExcel(item.getInputStream(),PersonId,cpnyId);
					message = "导入成功！";
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			message = "导入失败,请检查文件格式";
		}
		request.setAttribute("errorList", errorList);
		request.setAttribute("alert", message);
		request.setAttribute("reload", "parent.opener.location.reload();parent.close()");
		return "/inc/alertPaImportPayBankMessage.jsp";
	}

	private void processExcel(InputStream in,String PersonId,String cpnyId) throws Exception {
		Map<Integer, String> dates = new HashMap<Integer, String>();
		Map<String, String> errorMap = null ;
		
		errorList = new ArrayList<Map<String,String>>() ;
		
		HSSFWorkbook book = new HSSFWorkbook(in);
		HSSFSheet sheet = book.getSheetAt(0); //只处理第一个sheet
		PaImportPayBankDAO dao = PaImportPayBankDAO.getInstance();
		
		for (int i = 1; i <= 65535; ++i) {
			HSSFRow row = sheet.getRow(i);
			if (row == null)
			{
				break;
			}
			else {
				String bankname = null ;//銀行名稱ID
				String account = null ;//賬號
				String checktype = null ;//支票類型ID
				String checkaccount = null ;//支票號
				String entrydate = null ;//錄入日期
				String note = null ;//備註
				String activity = null ;//支票狀態
				String cpny_Id = null ;//支票所属法人
				HSSFCell cell = row.getCell(0);

				
				// 得到工号empId
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					bankname = StringUtils.trimToEmpty(row.getCell(0).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					bankname = String.valueOf((int) row.getCell(0).getNumericCellValue());

				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					account = StringUtils.trimToEmpty(row.getCell(1).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					account = String.valueOf((int) row.getCell(1).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					checktype = StringUtils.trimToEmpty(row.getCell(2).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					checktype = String.valueOf((int) row.getCell(2).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					checkaccount = StringUtils.trimToEmpty(row.getCell(3).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					checkaccount = String.valueOf(row.getCell(3).getNumericCellValue());

				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					entrydate = StringUtils.trimToEmpty(row.getCell(4).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					entrydate = String.valueOf(row.getCell(4).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					note = StringUtils.trimToEmpty(row.getCell(5).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					note = String.valueOf(row.getCell(5).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					activity = StringUtils.trimToEmpty(row.getCell(6).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					activity = String.valueOf(row.getCell(6).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					cpny_Id = StringUtils.trimToEmpty(row.getCell(7).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					cpny_Id = String.valueOf(row.getCell(7).getNumericCellValue());
				
						if (StringUtils.isNotEmpty(bankname) && StringUtils.isNotEmpty(account) && StringUtils.isNotEmpty(checktype) && StringUtils.isNotEmpty(checkaccount)) {
							
							dao.importCheckAccount(bankname, account,checktype,checkaccount,entrydate,note,activity,PersonId,cpny_Id);
							logger.debug(bankname + " | " + account + " | " + cpny_Id);
						}
						else
							{
								errorMap = new HashMap<String, String>();
								errorMap.put("empId", bankname) ;
								errorMap.put("payBank", account) ;
								
								errorList.add(errorMap) ;
							}
					}
		}
	}
}