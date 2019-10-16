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

public class VoiTureCost {
	
	private static final String sheetName = "VoiTureCost";

	private static final String templatePath ="reports\\template\\VoiTureCost.xls";

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
		List carList = es.getCarList(parameterObject);
		for(int i=0;i<carList.size();i++){
			SimpleMap simpleMap = (SimpleMap)carList.get(i);
			XlsUtil.setCellValue(3+i, 0, simpleMap.getString("VOITURE_BRAND")+simpleMap.getString("VOITURE_NUMBER"),this.sheet); //车辆名称
			parameterObject.set("code", "voitureResumeCode3");
			parameterObject.set("voiture_id", simpleMap.getInt("VOITURE_ID")+"");
			int ry = es.getRy(parameterObject);//燃油费
			XlsUtil.setCellValue(3+i, 1, ry+"",this.sheet); //
			parameterObject.set("code", "voitureResumeCode8");
			int ry1 = es.getRy(parameterObject);//耗油量
			XlsUtil.setCellValue(3+i, 2, ry1+"",this.sheet); 
			int ryInt = es.getRyInt(parameterObject);//天数
			parameterObject.set("code", "voitureResumeCode2");
			XlsUtil.setCellValue(3+i, 3, ryInt+"",this.sheet); 
			int ry2 = es.getRy(parameterObject);//里程数
			XlsUtil.setCellValue(3+i, 4, ry2+"",this.sheet); 
			if(ry2!=0&&ryInt!=0){
				XlsUtil.setCellValue(3+i, 5, ry2/ryInt+"",this.sheet);//日运行里程
			}else{
				XlsUtil.setCellValue(3+i, 5, "",this.sheet);//日运行里程
			}
			if(ry1!=0&&ryInt!=0){
				XlsUtil.setCellValue(3+i, 6, ry1/ryInt+"",this.sheet);//日耗油量
			}else{
				XlsUtil.setCellValue(3+i, 6, "",this.sheet);//日耗油量
			}
			if(ry2!=0&&ry1!=0){
				XlsUtil.setCellValue(3+i, 7, ry2/ry1+"",this.sheet);//公里每升
			}else{
				XlsUtil.setCellValue(3+i, 7, "",this.sheet);//公里每升
			}
		}
	}

	public void setCpny_id(String cpny_id) {
		this.cpny_id = cpny_id;
	}	

}
