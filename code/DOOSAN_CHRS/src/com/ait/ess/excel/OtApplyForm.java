package com.ait.ess.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.XlsUtil;
import com.ait.utils.ConnBean;

/**
 * @author zhangji
 * @date 2012-03-30
 */
public class OtApplyForm {

	private static final String sheetName = "otapply";

	private static final String templatePath ="reports\\template\\otapplyform.xls";
	

	///private static final String photoPath = "images\\main\\logo.jpg";

	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private int otNo = 0;

	public void setOtNo(int otNo) {
		this.otNo = otNo;
	}

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public void write(OutputStream out) throws IOException {
		this.workBook.write(out);
	}

	public void fillReport(HttpServletRequest request) throws FileNotFoundException, IOException, ServiceLocatorException, SQLException {
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		File template = new File(webRootPath + templatePath);
		
	
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("OT_NO",otNo);	
	
		SimpleMap sm = (SimpleMap)essServices.getOtApplyInfo(parameterObject);
		System.out.println(sm.getString("CODE_NAME")+this.sheet+this.workBook.getSheet(sheetName)+template+this.workBook);
			XlsUtil.setCellValue(2, 2, sm.getString("CODE_NAME"),this.sheet); //休假类型
			XlsUtil.setCellValue(3, 2, sm.getString("DEPTNAME"),this.sheet); //课组
			XlsUtil.setCellValue(3, 9, sm.getString("FOURTHDEPTNAME"),this.sheet); //部门
			XlsUtil.setCellValue(4, 2, sm.getString("LEAVE_EMPID"),this.sheet); //职号
			XlsUtil.setCellValue(4, 9, sm.getString("CHINESENAME"),this.sheet); //姓名
			XlsUtil.setCellValue(5, 2, sm.getString("APPLY_OT_REMARK"),this.sheet); //休假原因
			XlsUtil.setCellValue(6, 3, sm.getString("OTFROMTIME"),this.sheet); //开始时间
			XlsUtil.setCellValue(6, 9, sm.getString("OTLENGTH"),this.sheet); //天数
			XlsUtil.setCellValue(7, 3, sm.getString("OTTOTIME"),this.sheet); //结束时间
			//XlsUtil.setCellValue(7, 9, sm.getString("OTLENGTH"),this.sheet); //小时数	
	
		
		Connection conn = null ;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try{
		
			conn = ConnBean.getConn();
			String sql= " SELECT A.APPLY_NO, HR.CHINESENAME, A.AFFIRM_LEVEL_ORIGINAL, "+
            " (SELECT HR.CHINESENAME  FROM SY_ADMIN SY, HR_EMPLOYEE HR  WHERE SY.ADMINID = HR.PERSON_ID "+
            " AND HR.CPNY_ID = '"+admin.getCpnyId()+"' AND INSTR((',' || screen_grant_no || ','),',' || (SELECT A.SCREEN_GRANT_NO "+
            " FROM SY_LOGIN_SCREEN A WHERE A.SCREEN_GRANT_NAME = '考勤担当' AND A.CPNY_ID ='"+admin.getCpnyId()+"') || ',') > 1 AND ROWNUM = 1) AFFIRMOR "+
            " FROM ESS_AFFIRM A, HR_EMPLOYEE HR  WHERE A.APPLY_NO = "+otNo+" AND A.AFFIRMOR_ID = HR.PERSON_ID ";			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String level1="";
			String level2="";
			String level3="";
			String level4="";
			String level5="";
			String level6="";
			String level7="";
			String level8="";
			String level9="";
			while(rs.next()){
				
				int AFFIRM_LEVEL_ORIGINAL =  rs.getInt("AFFIRM_LEVEL_ORIGINAL");
				String  CHINESENAME =  rs.getString("CHINESENAME");
				level9=  rs.getString("AFFIRMOR");
				if(AFFIRM_LEVEL_ORIGINAL==1)
					level1 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==2)
					level2 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==3)
					level3 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==4)
					level4 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==5)
					level5 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==6)
					level6 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==7)
					level7 =CHINESENAME;
				else if(AFFIRM_LEVEL_ORIGINAL==8)
					level8 =CHINESENAME;
				else 
					System.out.println("取出决裁者错误");
								
			   
			}
		
				XlsUtil.setCellValue(12, 2,level1,this.sheet);
				XlsUtil.setCellValue(12, 4,level2,this.sheet); 
				XlsUtil.setCellValue(12, 6,level3,this.sheet); 
				XlsUtil.setCellValue(12, 8,level4,this.sheet); 
				XlsUtil.setCellValue(12, 10,level5,this.sheet); 
			
			/**
			 * 照片
			 */
			///XlsUtil.setPhoto(4, 16, 8, 3, new File(webRootPath + photoPath),this.sheet,this.workBook);
		}
		catch(Exception e){
			e.printStackTrace() ;
		}
		finally {
			SqlUtil.close(rs, ps, conn);
		}
	}	
}