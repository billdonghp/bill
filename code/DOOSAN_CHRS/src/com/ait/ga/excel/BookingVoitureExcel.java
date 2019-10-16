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

import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.gm.business.EateryServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class BookingVoitureExcel {

	private EssSysparam essSysparam = null;
	
	private static final String sheetName = "bookingVoiture";
	
	private static final String templatePath = "reports\\template\\bookingVoiture.xls";
	
	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private String applyno = null;
	
	private String cpny_id = null;
	
	private String person_id = null;

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

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
		EateryServices es = new EateryServices();

		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("applyno",applyno);
		parameterObject.set("cpny_id",cpny_id);
		GaAffirm  ga = new GaAffirm();
//		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpny_id);
//		List list =ga.getAffirmor(this.person_id,"VehicleApply",essSysparam.isContainsOwner());
		List list = es.getVoitureApply2(parameterObject);
		List smList = es.getVoitureApply1(parameterObject);
		String name = "";
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SimpleMap gaAffirmList = new SimpleMap();
				gaAffirmList = (SimpleMap)list.get(i);
				name += gaAffirmList.getString("NAME")+",";
			}
		}
		SimpleMap sm = new SimpleMap();
		sm = (SimpleMap)smList.get(0);
		if(name!=null && !name.equals("")){
			XlsUtil.setCellValue(0, 5, name.substring(0,name.length()-1),this.sheet); //管理
			XlsUtil.setCellValue(14, 5, name.substring(0,name.length()-1),this.sheet); //管理
		}else{
			XlsUtil.setCellValue(0, 5, "",this.sheet); //管理
			XlsUtil.setCellValue(14, 5, "",this.sheet); //管理
		}
		XlsUtil.setCellValue(2, 2, sm.getString("DEPTNAME"),this.sheet); //所属部门
		XlsUtil.setCellValue(2, 6, sm.getString("APPLY_DATE"),this.sheet); //申请日期
		XlsUtil.setCellValue(3, 6, sm.getString("LARDER"),this.sheet); //姓名
		
		XlsUtil.setCellValue(16, 2, sm.getString("DEPTNAME"),this.sheet); //所属部门
		XlsUtil.setCellValue(16, 6, sm.getString("APPLY_DATE"),this.sheet); //申请日期
		XlsUtil.setCellValue(17, 6, sm.getString("LARDER"),this.sheet); //姓名
		XlsUtil.setCellValue(6, 2, sm.getString("PURPOSE"), this.sheet);//用途
		XlsUtil.setCellValue(20, 2, sm.getString("PURPOSE"), this.sheet);//用途
		
		StringBuffer buffer = new StringBuffer(" 从 ").append(sm.getString("DEPARTURES")).append(" 到 ");
		buffer.append(sm.getString("DESTINATIONS")).append(" 共 ").append(sm.getString("KILOMETER")!=null?sm.getString("KILOMETER"):"").append(" 公里");
		XlsUtil.setCellValue(5, 2, buffer.toString(),this.sheet); //业务内容
		
		XlsUtil.setCellValue(19, 2, buffer.toString(),this.sheet); //业务内容
		
		XlsUtil.setCellValue(4, 6, sm.getString("APPLY_USERSCOUNT")+" 人",this.sheet); //乘车人数
		
		XlsUtil.setCellValue(18, 6, sm.getString("APPLY_USERSCOUNT")+" 人",this.sheet); //乘车人数
		
		XlsUtil.setCellValue(4, 2, sm.getString("CHINESENAME"),this.sheet); //申请人
		
		XlsUtil.setCellValue(18, 2, sm.getString("CHINESENAME"),this.sheet); //申请人
		
		StringBuffer buffer2 = new StringBuffer();
		StringBuffer buffer4 = new StringBuffer();
		String nian = sm.getString("APPLY_DATE").substring(0,4);
		String yue = sm.getString("APPLY_DATE").substring(5,7);
		String day = sm.getString("APPLY_DATE").substring(8,10);
		String hour = sm.getString("APPLY_STARTTIME").substring(0,2);
		String min = sm.getString("APPLY_STARTTIME").substring(3,5);
		buffer2.append(nian).append(" 年 ").append(yue).append(" 月 ").append(day);
		buffer2.append(" 日 ").append(hour).append(" 时 ").append(min).append(" 分");
		
		buffer4.append(sm.getString("APPLY_ENDDATE").substring(0,4)).append(" 年 ").append(sm.getString("APPLY_ENDDATE").substring(5,7)).append(" 月 ").append(sm.getString("APPLY_ENDDATE").substring(8,10));
		buffer4.append(" 日 ").append(sm.getString("APPLY_ENDTIME").substring(0,2)).append(" 时 ").append(sm.getString("APPLY_ENDTIME").substring(3,5)).append(" 分");
		
		XlsUtil.setCellValue(7, 3, buffer2.toString(),this.sheet); //预定使用时间 从
		XlsUtil.setCellValue(21, 3, buffer2.toString(),this.sheet); //预定使用时间 从
		
		XlsUtil.setCellValue(22, 3, buffer4.toString(),this.sheet); //预定使用时间 到
		XlsUtil.setCellValue(8, 3, buffer4.toString(),this.sheet); //预定使用时间 到
		
		List drivaList = es.getrivaList(parameterObject);
		String DRIVER="";
		String EMPNAME = "";
		for(int i=0;i<drivaList.size();i++){
			SimpleMap simpleMap1 = new SimpleMap();
			simpleMap1 = (SimpleMap)drivaList.get(i);
			if(simpleMap1.getString("DRIVER")!=null){
				DRIVER += simpleMap1.getString("DRIVER")+",";
			}
			if(simpleMap1.getString("EMPNAME")!=null){
				EMPNAME += simpleMap1.getString("EMPNAME")+",";
			}
		}
		if(!DRIVER.isEmpty()){
			XlsUtil.setCellValue(10, 6, DRIVER.substring(0,DRIVER.length()-1),this.sheet); //所用车号
			
			XlsUtil.setCellValue(24, 6, DRIVER.substring(0,DRIVER.length()-1),this.sheet); //所用车号
		}else{
			XlsUtil.setCellValue(10, 6, "",this.sheet); //所用车号
			
			XlsUtil.setCellValue(24, 6, "",this.sheet); //所用车号
		}
		if(!EMPNAME.isEmpty()){
			XlsUtil.setCellValue(10, 2, EMPNAME.substring(0,EMPNAME.length()-1), this.sheet); //姓名
			XlsUtil.setCellValue(24, 2, EMPNAME.substring(0,EMPNAME.length()-1), this.sheet); //姓名
		}else{
			XlsUtil.setCellValue(10, 2, "", this.sheet); //姓名
			XlsUtil.setCellValue(24, 2, "", this.sheet); //姓名
		}
		
		StringBuffer buffer3 = new StringBuffer();
		buffer3.append("      ").append("年").append("    ").append("月").append("    ").append("日").append("    ").append("时").append("    ").append("分");
		XlsUtil.setCellValue(11, 3, buffer3.toString(),this.sheet); //实际使用时间
		XlsUtil.setCellValue(12, 3, buffer3.toString(),this.sheet); //实际使用时间
		
		XlsUtil.setCellValue(25, 3, buffer3.toString(),this.sheet); //实际使用时间
		XlsUtil.setCellValue(26, 3, buffer3.toString(),this.sheet); //实际使用时间
	}	
	
}
