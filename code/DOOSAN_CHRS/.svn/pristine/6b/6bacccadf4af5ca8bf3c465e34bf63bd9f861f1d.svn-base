package com.ait.ga.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class SaleWastes {
	private static final String sheetName = "saleWastes";
	
	private static final String templatePath = "reports\\template\\saleWastes.xls";
	
	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private String applyno = null;
	
	private String cpny_id = null;

	public void setCpny_id(String cpny_id) {
		this.cpny_id = cpny_id;
	}

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public void setWorkBook(HSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public void setApplyno(String applyno) {
		this.applyno = applyno;
	}
	
	public void write(OutputStream out) throws IOException {
		this.workBook.write(out);
	}
	
	public void fillReport() throws FileNotFoundException, IOException, ServiceLocatorException, SQLException {
		File template = new File(webRootPath + templatePath);
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("waste_no",applyno);
		parameterObject.set("cpny_id",cpny_id);
		SimpleMap sm = (SimpleMap)essServices.getSaleWastes(parameterObject);
		SimpleMap smCom = (SimpleMap)essServices.getSaleWastesCom(parameterObject);

		XlsUtil.setCellValue(4, 9, sm.getString("WASTE_NO"),this.sheet); //发行编号
		XlsUtil.setCellValue(5, 2, sm.getString("CREATE_DATE"),this.sheet); //发行日期
		XlsUtil.setCellValue(6, 2, sm.getString("CITEREASONS"),this.sheet); //搬出原因
		XlsUtil.setCellValue(8, 0, sm.getString("WASTE_TYPE"),this.sheet); //品名
		XlsUtil.setCellValue(8, 3, sm.getString("WASTE_UNITS"),this.sheet); //单位
		XlsUtil.setCellValue(8, 5, sm.getString("WASTE_UNITPRICE"),this.sheet); //单价
		XlsUtil.setCellValue(8, 7, sm.getString("WASTE_NUMBER"),this.sheet); //数量
		XlsUtil.setCellValue(8, 9, sm.getString("WASTE_TOTAL"),this.sheet); //金额
		XlsUtil.setCellValue(2, 2, smCom.getString("CPNY_NAME"),this.sheet); //公司名称
		XlsUtil.setCellValue(2, 9, smCom.getString("ECN"),this.sheet); //企业法人登记号
		XlsUtil.setCellValue(3, 2, smCom.getString("CPNY_ADDR"),this.sheet); //公司地址
		XlsUtil.setCellValue(4, 2, smCom.getString("CORPORATIONBEHALF"),this.sheet); //法人代表	
		XlsUtil.setCellValue(15, 2, sm.getString("COMPANY_CUSTOMERS"),this.sheet); //搬出者company_customers
		XlsUtil.setCellValue(17, 2, sm.getString("OUT_STAFF"),this.sheet); //姓名
		XlsUtil.setCellValue(18, 2, sm.getString("CAR_NO"),this.sheet); //车辆
		/**
		 * 照片
		 */
		///XlsUtil.setPhoto(4, 16, 8, 3, new File(webRootPath + photoPath),this.sheet,this.workBook);
	}	
}
