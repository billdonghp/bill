package com.ait.ga.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.ess.business.EssServices;
import com.ait.gm.business.EateryServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class VoitureApply {
	private static final String sheetName = "VoitureApply";

	private static final String templatePath ="reports\\template\\ExcelVoiture.xls";

	///private static final String photoPath = "images\\main\\logo.jpg";

	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private String applyno = null;
	

	public void setApplyno(String applyno) {
		this.applyno = applyno;
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
		SimpleMap map = null;
		SimpleMap map1 = null;
		EateryServices es = new EateryServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("LEAVE_NO",applyno);
		parameterObject.set("applyId",applyno);
		List sm = es.getVoitureApplyInfo1(parameterObject);
		List car_name = es.getCar_name(parameterObject);
		SimpleMap sm1 = (SimpleMap)sm.get(0);
		String carStr = "";
		String VOITURE_BRAND = "";
		String VOITURE_NUMBER = "";
		String DISTINCTION="";
		String APPLY_DATE = "";
		String APPLY_STARTTIME = "";
		String CONTENT = "";
		String DRIVE_WAY = "";
		String NOTE = "";
		
		for(int i=0 ; i<sm.size() ; i++){
			map = (SimpleMap) sm.get(i);
			String applyId = map.getString("VOITURE_APPLYID");
			
			parameterObject.set("applyId", applyId);
			List voitureDetailList = es.getDistinctionList(parameterObject);
			map.set("voitureDetailList", voitureDetailList);
			int flag=1;
			for(int j=0 ; j<voitureDetailList.size() ; j++){
			map1 = (SimpleMap) voitureDetailList.get(j);
			 DISTINCTION = map1.getString("DISTINCTION");
			 APPLY_DATE = map1.getString("APPLY_DATE");
			 APPLY_STARTTIME = map1.getString("APPLY_STARTTIME");
			 CONTENT = map1.getString("CONTENT");
			 DRIVE_WAY = map1.getString("DRIVE_WAY");
			 NOTE = map1.getString("NOTE");
			
				 if("1".equals(DISTINCTION)){
					 if(flag==1){
					 	XlsUtil.setCellValue(3, 2, sm1.getString("CHINESENAME"),this.sheet); //申请人
						XlsUtil.setCellValue(3, 5, sm1.getString("APPLYER_DEPTNAME"),this.sheet); //部门
						XlsUtil.setCellValue(3, 9, sm1.getString("TELL_PHONE"),this.sheet); //用途
						XlsUtil.setCellValue(5, 9, sm1.getString("APPLY_USERSCOUNT")+"人",this.sheet); //人数
						//XlsUtil.setCellValue(4, 2, VOITURE_BRAND.length() > 0? VOITURE_BRAND.substring(0, VOITURE_BRAND.length()-1) : "",this.sheet); //车辆名称
						//XlsUtil.setCellValue(5, 2, VOITURE_NUMBER.length() > 0? VOITURE_NUMBER.substring(0, VOITURE_NUMBER.length()-1) : "",this.sheet); //车牌号码
						XlsUtil.setCellValue(4, 2, sm1.getString("LARDER"),this.sheet); 
						XlsUtil.setCellValue(4, 9, sm1.getString("LARDER_DEPTNAME"),this.sheet); 
						XlsUtil.setCellValue(5, 2, sm1.getString("LARDER_POST"),this.sheet);
						XlsUtil.setCellValue(7, 2, "√",this.sheet); //区分
						XlsUtil.setCellValue(8, 2, CONTENT,this.sheet); 
						XlsUtil.setCellValue(9, 2, DRIVE_WAY,this.sheet); 
						XlsUtil.setCellValue(10, 2, APPLY_DATE+" "+APPLY_STARTTIME,this.sheet);
						XlsUtil.setCellValue(11, 2, NOTE,this.sheet); 
					 }else if(flag==2){
						 	XlsUtil.setCellValue(21, 2, "√",this.sheet); //区分
							XlsUtil.setCellValue(22, 2, CONTENT,this.sheet); 
							XlsUtil.setCellValue(23, 2, DRIVE_WAY,this.sheet); 
							XlsUtil.setCellValue(24, 2, APPLY_DATE+" "+APPLY_STARTTIME,this.sheet);
							XlsUtil.setCellValue(25, 2, NOTE,this.sheet); 
							XlsUtil.setCellValue(17, 2, sm1.getString("CHINESENAME"),this.sheet); 
							XlsUtil.setCellValue(17, 5, sm1.getString("APPLYER_DEPTNAME"),this.sheet); 
							XlsUtil.setCellValue(17, 9, sm1.getString("TELL_PHONE"),this.sheet); 
							XlsUtil.setCellValue(19, 9, sm1.getString("APPLY_USERSCOUNT")+"人",this.sheet); 
							XlsUtil.setCellValue(18, 2, sm1.getString("LARDER"),this.sheet); 
							XlsUtil.setCellValue(18, 9, sm1.getString("LARDER_DEPTNAME"),this.sheet); 
							XlsUtil.setCellValue(19, 2, sm1.getString("LARDER_POST"),this.sheet); 
					 }
					}
					if("2".equals(DISTINCTION)){
						if(flag==1){
							XlsUtil.setCellValue(3, 2, sm1.getString("CHINESENAME"),this.sheet); //申请人
							XlsUtil.setCellValue(3, 5, sm1.getString("APPLYER_DEPTNAME"),this.sheet); //部门
							XlsUtil.setCellValue(3, 9, sm1.getString("TELL_PHONE"),this.sheet); //用途
							XlsUtil.setCellValue(5, 9, sm1.getString("APPLY_USERSCOUNT")+"人",this.sheet); //人数
							//XlsUtil.setCellValue(4, 2, VOITURE_BRAND.length() > 0? VOITURE_BRAND.substring(0, VOITURE_BRAND.length()-1) : "",this.sheet); //车辆名称
							//XlsUtil.setCellValue(5, 2, VOITURE_NUMBER.length() > 0? VOITURE_NUMBER.substring(0, VOITURE_NUMBER.length()-1) : "",this.sheet); //车牌号码
							XlsUtil.setCellValue(4, 2, sm1.getString("LARDER"),this.sheet); 
							XlsUtil.setCellValue(4, 9, sm1.getString("LARDER_DEPTNAME"),this.sheet); 
							XlsUtil.setCellValue(5, 2, sm1.getString("LARDER_POST"),this.sheet);
							XlsUtil.setCellValue(7, 5, "√",this.sheet); //区分
							XlsUtil.setCellValue(8, 2, CONTENT,this.sheet); 
							XlsUtil.setCellValue(9, 2, DRIVE_WAY,this.sheet); 
							XlsUtil.setCellValue(10, 2, APPLY_DATE+" "+APPLY_STARTTIME,this.sheet);
							XlsUtil.setCellValue(11, 2, NOTE,this.sheet); 
						}else if(flag==2){
							
							XlsUtil.setCellValue(21, 5, "√",this.sheet); //区分
							XlsUtil.setCellValue(22, 2, CONTENT,this.sheet); 
							XlsUtil.setCellValue(23, 2, DRIVE_WAY,this.sheet); 
							XlsUtil.setCellValue(24, 2, APPLY_DATE+" "+APPLY_STARTTIME,this.sheet);
							XlsUtil.setCellValue(25, 2, NOTE,this.sheet); 
							XlsUtil.setCellValue(17, 2, sm1.getString("CHINESENAME"),this.sheet); 
							XlsUtil.setCellValue(17, 5, sm1.getString("APPLYER_DEPTNAME"),this.sheet); 
							XlsUtil.setCellValue(17, 9, sm1.getString("TELL_PHONE"),this.sheet); 
							XlsUtil.setCellValue(19, 9, sm1.getString("APPLY_USERSCOUNT")+"人",this.sheet); 
							XlsUtil.setCellValue(18, 2, sm1.getString("LARDER"),this.sheet); 
							XlsUtil.setCellValue(18, 9, sm1.getString("LARDER_DEPTNAME"),this.sheet); 
							XlsUtil.setCellValue(19, 2, sm1.getString("LARDER_POST"),this.sheet); 
						}
						
					}
					flag++;
			 }
				
			}
		
		for(int z=0;z<car_name.size();z++){
			SimpleMap map2 = new SimpleMap();
			map2 = (SimpleMap)car_name.get(z);
			if(map2.getString("DRIVER")!=null){
				carStr +=map2.getString("DRIVER")+",";
			}
			VOITURE_BRAND += map2.getString("VOITURE_BRAND")+",";
			VOITURE_NUMBER += map2.getString("VOITURE_NUMBER")+",";
		}
		
		
		
	}	

}
