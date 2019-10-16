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
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;

public class PaImportOtherEmailCommand implements Command {

	private static final Logger logger = Logger.getLogger(PaImportPayBankCommand.class);

	private static final FileItemFactory factory = new DiskFileItemFactory();

	private AdminBean admin = null ;
	
	private List<Map<String,String>> errorList = null ;
	
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			
			admin = (AdminBean) request.getSession().getAttribute("admin");
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					this.processExcel(item.getInputStream());
					message = "导入成功";
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			message = "导入失败,请检查文件";
		}
		request.setAttribute("errorList", errorList);
		request.setAttribute("alert", message);
		request.setAttribute("reload", "parent.opener.location.reload();parent.close()");
		return "/inc/alertPaImportPayBankMessage.jsp";
	}

	private void processExcel(InputStream in) throws Exception {
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
				String paMonth = null;
				String personId = null;
				String empId = null ;
				String chinesename = null ;
				String giveName = null ;//支给名称
				double PayAmount = 0.0 ;//个人福利合计
				double PerWelfareSum = 0.0 ;//个人福利合计
				double IncomeTax = 0.0 ;//个税
				double NetAmount = 0.0 ;//实发金额
				HSSFCell cell = row.getCell(0);

				
				// 得到工号empId
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					empId = StringUtils.trimToEmpty(row.getCell(0).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					empId = String.valueOf((int) row.getCell(0).getNumericCellValue());

				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					chinesename = StringUtils.trimToEmpty(row.getCell(1).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					chinesename = String.valueOf((int) row.getCell(1).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					paMonth = StringUtils.trimToEmpty(row.getCell(2).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					paMonth = String.valueOf((int) row.getCell(2).getNumericCellValue());
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					giveName = StringUtils.trimToEmpty(row.getCell(3).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					giveName = String.valueOf(row.getCell(3).getNumericCellValue());

				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					PayAmount = Double.parseDouble(row.getCell(4).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					PayAmount = (double) row.getCell(4).getNumericCellValue();
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					PerWelfareSum = Double.parseDouble(row.getCell(5).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					PerWelfareSum = (double) row.getCell(5).getNumericCellValue();
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					IncomeTax = Double.parseDouble(row.getCell(6).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					IncomeTax = (double) row.getCell(6).getNumericCellValue();
				
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					NetAmount = Double.parseDouble(row.getCell(7).getRichStringCellValue().getString());
				else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					NetAmount = (double) row.getCell(7).getNumericCellValue();
				
				if (StringUtils.isNotEmpty(empId)) {
					personId = dao.getPersonIdByEmpId(empId, admin.getCpnyId()) ; 
					if (personId.equals("ERROR")) {
						
						errorMap = new HashMap<String, String>();
						errorMap.put("empId", empId) ;
						errorMap.put("giveName", giveName) ;;
						
						
						errorList.add(errorMap) ;
					}else{
						if (StringUtils.isNotEmpty(personId) && StringUtils.isNotEmpty(paMonth) && StringUtils.isNotEmpty(giveName)) {
							
							dao.insertOther(paMonth, personId,giveName,Double.toString(PayAmount),Double.toString(PerWelfareSum),Double.toString(IncomeTax),Double.toString(NetAmount));
							logger.debug(empId + " | " + giveName + " | " + admin.getCpnyId());
						}
						else
							{
								errorMap = new HashMap<String, String>();
								errorMap.put("empId", empId) ;
								errorMap.put("payBank", giveName) ;
								
								errorList.add(errorMap) ;
							}
					}
				}
			}
		}
	}
}