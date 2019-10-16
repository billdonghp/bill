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
 * @author yangxiaohui
 * @date 2009-06-29
 */
public class LeaveApplyForm {

	private static final String sheetName = "leaveapply";

	private static final String templatePath ="reports\\template\\leaveapplyform.xls";
	
	private static final String templatePathDIY ="reports\\template\\leaveapplyformDIY.xls";
	
	private static final String templatePathDISD ="reports\\template\\leaveapplyformDISD.xls";


	///private static final String photoPath = "images\\main\\logo.jpg";

	private String webRootPath = null;

	private HSSFWorkbook workBook = null;

	private HSSFSheet sheet = null;

	private int leaveNo = 0;

	public void setLeaveNo(int leaveNo) {
		this.leaveNo = leaveNo;
	}

	

	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}

	public void write(OutputStream out) throws IOException {
		this.workBook.write(out);
	}

	public void fillReport(HttpServletRequest request) throws FileNotFoundException, IOException, ServiceLocatorException, SQLException {
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		File template =null;
		if(admin.getCpnyId().equals("60000000")){
			template = new File(webRootPath + templatePathDIY);
		}else if(admin.getCpnyId().equals("63000000")){
			template = new File(webRootPath + templatePathDISD);
		}else{
			template = new File(webRootPath + templatePath);
		}
	
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("LEAVE_NO",leaveNo);	
	    
		SimpleMap sm = (SimpleMap)essServices.getLeaveApplyInfo(parameterObject);
		System.out.println(sm.getString("CODE_NAME")+this.sheet+sheetName);
		if(admin.getCpnyId().equals("63000000")){
			XlsUtil.setCellValue(2, 0, sm.getString("DISD_DEPT_REPORT_NO"),this.sheet); //休假类型
			XlsUtil.setCellValue(3, 2, sm.getString("CODE_NAME"),this.sheet); //休假类型
			XlsUtil.setCellValue(4, 2, sm.getString("DEPTNAME"),this.sheet); //课组
			XlsUtil.setCellValue(4, 9, sm.getString("FOURTHDEPTNAME"),this.sheet); //部门
			XlsUtil.setCellValue(5, 2, sm.getString("LEAVE_EMPID"),this.sheet); //职号
			XlsUtil.setCellValue(5, 9, sm.getString("CHINESENAME"),this.sheet); //姓名
			XlsUtil.setCellValue(6, 2, sm.getString("LEAVE_REASON"),this.sheet); //休假原因
			XlsUtil.setCellValue(7, 3, sm.getString("LEAVE_FROM_TIME"),this.sheet); //开始时间
			XlsUtil.setCellValue(7, 9, sm.getString("LEAVEDAY"),this.sheet); //天数
			XlsUtil.setCellValue(8, 3, sm.getString("LEAVE_TO_TIME"),this.sheet); //结束时间
			XlsUtil.setCellValue(8, 9, sm.getString("LEAVEHOUR"),this.sheet); //小时数	
		}else{
			XlsUtil.setCellValue(2, 2, sm.getString("CODE_NAME"),this.sheet); //休假类型
			XlsUtil.setCellValue(3, 2, sm.getString("DEPTNAME"),this.sheet); //课组
			XlsUtil.setCellValue(3, 9, sm.getString("FOURTHDEPTNAME"),this.sheet); //部门
			XlsUtil.setCellValue(4, 2, sm.getString("LEAVE_EMPID"),this.sheet); //职号
			XlsUtil.setCellValue(4, 9, sm.getString("CHINESENAME"),this.sheet); //姓名
			XlsUtil.setCellValue(5, 2, sm.getString("LEAVE_REASON"),this.sheet); //休假原因
			XlsUtil.setCellValue(6, 3, sm.getString("LEAVE_FROM_TIME"),this.sheet); //开始时间
			XlsUtil.setCellValue(6, 9, sm.getString("LEAVEDAY"),this.sheet); //天数
			XlsUtil.setCellValue(7, 3, sm.getString("LEAVE_TO_TIME"),this.sheet); //结束时间
			XlsUtil.setCellValue(7, 9, sm.getString("LEAVEHOUR"),this.sheet); //小时数	
		}
		
		
		Connection conn = null ;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try{
		
			conn = ConnBean.getConn();
			String sql= " SELECT A.APPLY_NO, HR.CHINESENAME,  HR.DUTY_CODE, A.AFFIRM_LEVEL_ORIGINAL, "+
            " (SELECT HR.CHINESENAME  FROM SY_ADMIN SY, HR_EMPLOYEE HR  WHERE SY.ADMINID = HR.PERSON_ID "+
            " AND HR.CPNY_ID = '"+admin.getCpnyId()+"' AND INSTR((',' || screen_grant_no || ','),',' || (SELECT A.SCREEN_GRANT_NO "+
            " FROM SY_LOGIN_SCREEN A WHERE A.SCREEN_GRANT_NAME = '考勤担当' AND A.CPNY_ID ='"+admin.getCpnyId()+"') || ',') > 1 AND ROWNUM = 1) AFFIRMOR "+
            " FROM ESS_AFFIRM A, HR_EMPLOYEE HR  WHERE A.APPLY_NO = "+leaveNo+" AND A.AFFIRMOR_ID = HR.PERSON_ID ";			
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
			String name1="";
			String name2="";
			String name3="";
			String name4="";
			String name5="";
			String name6="";
			String name7="";
			String name8="";
			String name9="";
			while(rs.next()){
				String dutyCode = rs.getString("DUTY_CODE");
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
				
				if("C_10001_10741".equals(dutyCode))
					name1 =CHINESENAME;
				else if("C_12005_1331763".equals(dutyCode)||"C_12005_93819".equals(dutyCode))
					name2 =CHINESENAME;
				else if("C_12005_17".equals(dutyCode))
					name3 =CHINESENAME;
				else if("C_12005_1330057".equals(dutyCode))
					name4 =CHINESENAME;
				else if("C_12005_93775".equals(dutyCode))
					name5 =CHINESENAME;
				else 
					System.out.println("取出决裁者错误");
								
			   
			}
			if(admin.getCpnyId().equals("60000000")){
				XlsUtil.setCellValue(12, 2,level1,this.sheet);
				XlsUtil.setCellValue(12, 4,level2,this.sheet); 
				XlsUtil.setCellValue(12, 6,level3,this.sheet); 
				XlsUtil.setCellValue(12, 8,level4,this.sheet); 
				XlsUtil.setCellValue(12, 10,level5,this.sheet); 
				XlsUtil.setCellValue(12, 11,level6,this.sheet); 
				
			}else if(admin.getCpnyId().equals("63000000")){
				XlsUtil.setCellValue(13, 2,level1,this.sheet);
				XlsUtil.setCellValue(13, 4,level2,this.sheet); 
				XlsUtil.setCellValue(13, 6,level3,this.sheet); 
				XlsUtil.setCellValue(13, 8,level4,this.sheet); 
				XlsUtil.setCellValue(13, 10,level5,this.sheet); 
			}
			else{
				XlsUtil.setCellValue(12, 2,name1,this.sheet);
				XlsUtil.setCellValue(12, 4,name2,this.sheet); 
				XlsUtil.setCellValue(12, 6,name3,this.sheet); 
				XlsUtil.setCellValue(12, 8,name4,this.sheet); 
				XlsUtil.setCellValue(12, 10,name5,this.sheet); 
			}
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