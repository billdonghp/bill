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

import com.ait.ess.business.EssServices;
import com.ait.ga.bean.GaAffirmList;
import com.ait.ga.business.SealMangerSerivces;
import com.ait.ga.cmd.visit.GaAffirm;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sysparam.EssSysparam;
import com.ait.sysparam.SysparamUtils;
import com.ait.util.ServiceLocatorException;
import com.ait.util.XlsUtil;

public class SealAffirmInfo {
private static final String sheetName = "seal_affirmInfo";
	
	private static final String templatePath = "reports\\template\\seal_affirmInfo.xls";
	
	private String webRootPath = null;
	
	private EssSysparam essSysparam = null;

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

		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("applyno",applyno);
		 SealMangerSerivces  smServices = new SealMangerSerivces();
		List allsealAffrimInfoList = smServices.allsealAffrimInfoList1(parameterObject);
		SimpleMap map = (SimpleMap)allsealAffrimInfoList.get(0);
		GaAffirm  ga = new GaAffirm();
		essSysparam=(EssSysparam) SysparamUtils.getSysparam(EssSysparam.class,cpny_id);
		List list =ga.getAffirmor(this.person_id,"SealApply",essSysparam.isContainsOwner());
		String name = "";
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				GaAffirmList gaAffirmList = new GaAffirmList();
				gaAffirmList = (GaAffirmList)list.get(i);
				name += gaAffirmList.getAffirmorName()+",";
			}
			name = name.substring(0,name.length()-1);
		}
		XlsUtil.setCellValue(2, 5, name,this.sheet); //管理
		XlsUtil.setCellValue(2, 1, map.getString("APPLYORDEPTID"),this.sheet); //部门
		XlsUtil.setCellValue(3, 1, map.getString("APPLYDATE"),this.sheet); //日期
		XlsUtil.setCellValue(3, 3, map.getString("CHINESENAME"),this.sheet); //经办人
		XlsUtil.setCellValue(5, 1, map.getString("CODE_NAME"),this.sheet); //使用章区分
		XlsUtil.setCellValue(5, 2, map.getString("SELECTDEPTID"),this.sheet); //对口部门
		XlsUtil.setCellValue(5, 3, map.getString("USEINFORMATION"),this.sheet); //内容
		XlsUtil.setCellValue(5, 4, map.getString("DRAFT_NO"),this.sheet); //起案号
		XlsUtil.setCellValue(5, 6, map.getString("USESHARES"),this.sheet); //份数
		XlsUtil.setCellValue(5, 7, map.getString("NOTE"),this.sheet); //备注
	}	

}
