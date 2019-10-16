package com.ait.ga.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.gm.business.EateryServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class VoiTureDetail {
	private static final String sheetName = "VoiTureDetail";

	private static final String templatePath ="reports\\template\\VoiTureDetail.xls";

	///private static final String photoPath = "images\\main\\logo.jpg";

	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private String stratdate = null;
	
	private String enddate = null;
	
	private String cpny_id = null;

	public void setStratdate(String stratdate) {
		this.stratdate = stratdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public void write(OutputStream out) throws IOException {
		this.workBook.write(out);
	}
	
	public void fillReport() throws FileNotFoundException, IOException, ServiceLocatorException, SQLException {
		File template = new File(webRootPath + templatePath);
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EateryServices es = new EateryServices();
		SimpleMap parameterObject = new SimpleMap();
		parameterObject.set("stratdate", stratdate);
		parameterObject.set("enddate", enddate);
		parameterObject.set("cpny_id", cpny_id);
		List VoiTureDetail = es.getVoiTureDetail(parameterObject);
		for(int i=0;i<VoiTureDetail.size();i++){
			SimpleMap simpleMap = (SimpleMap)VoiTureDetail.get(i);
			XlsUtil.setCellValue(3+i, 0, i+1+"",this.sheet);//序号
			XlsUtil.setCellValue(3+i, 1, simpleMap.getString("VOITURE_BRAND"),this.sheet);//车辆名称
			XlsUtil.setCellValue(3+i, 2, simpleMap.getString("VOITURE_NUMBER"),this.sheet);//车牌号码
			XlsUtil.setCellValue(3+i, 3, simpleMap.getString("CHINESENAME"),this.sheet);//申请人
			XlsUtil.setCellValue(3+i, 4, simpleMap.getString("DEPTNAME"),this.sheet);//申请部门
			XlsUtil.setCellValue(3+i, 5, simpleMap.getString("APPLY_USERSCOUNT"),this.sheet);//用车人数
			XlsUtil.setCellValue(3+i, 6, simpleMap.getString("DEPARTURES"),this.sheet);//出发地
			XlsUtil.setCellValue(3+i, 7, simpleMap.getString("DESTINATIONS"),this.sheet);//目的地
			XlsUtil.setCellValue(3+i, 8, simpleMap.getString("KILOMETER"),this.sheet);//预计行程
			XlsUtil.setCellValue(3+i, 9, simpleMap.getString("PURPOSE"),this.sheet);//用途
			XlsUtil.setCellValue(3+i, 10, simpleMap.getString("DRIVER_NAME"),this.sheet);//司机
			XlsUtil.setCellValue(3+i, 11, simpleMap.getString("BOOKING_DATE"),this.sheet);//使用日期
			XlsUtil.setCellValue(3+i, 12, simpleMap.getString(""),this.sheet);//备注
		}
	}

	public void setCpny_id(String cpny_id) {
		this.cpny_id = cpny_id;
	}	


}
