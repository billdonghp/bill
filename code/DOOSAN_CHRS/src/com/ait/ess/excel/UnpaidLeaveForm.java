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

public class UnpaidLeaveForm {
	
	private static final String sheetName= "UNPAID_LEAVE";
	private static final String templatePath="reports\\template\\UNPAID_LEAVE.xls";
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
		File template = new File(webRootPath + templatePath);
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);

		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("LEAVE_NO",leaveNo);	
		SimpleMap sm = (SimpleMap)essServices.getLeaveApplyInfo(parameterObject);
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
	
		XlsUtil.setCellValue(1, 2, sm.getString("CHINESENAME"), this.sheet);
		XlsUtil.setCellValue(2, 2, sm.getString("LEAVE_EMPID"), this.sheet);
		XlsUtil.setCellValue(3, 2, sm.getString("DEPTNAME"), this.sheet);
		XlsUtil.setCellValue(4, 0, "本人根据附后的材料提出休职 "+sm.getString("LEAVEDAY")+" 天的申请，望批准。", this.sheet);
		XlsUtil.setCellValue(6, 0, sm.getString("CODE_NAME"), this.sheet);
		XlsUtil.setCellValue(6, 2, sm.getString("LEAVE_FROM_TIME")+"至"+sm.getString("LEAVE_TO_TIME"), this.sheet);
		XlsUtil.setCellValue(6, 5, sm.getString("LEAVE_REASON"), this.sheet);
		XlsUtil.setCellValue(7, 2, sm.getString("CREATE_DATE"), this.sheet);
		
		Connection conn = null ;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try{
			conn = ConnBean.getConn();
			String sql= " SELECT A.APPLY_NO, HR.CHINESENAME, A.AFFIRM_LEVEL_ORIGINAL, "+
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
			
			XlsUtil.setCellValue(10, 0,sm.getString("DEPTNAME"),this.sheet);
			XlsUtil.setCellValue(10, 1,sm.getString("CHINESENAME"),this.sheet);
			XlsUtil.setCellValue(10, 2,level2,this.sheet);
			XlsUtil.setCellValue(10, 3,level3,this.sheet); 
			XlsUtil.setCellValue(10, 4,level4,this.sheet); 
			XlsUtil.setCellValue(10, 5,level5,this.sheet); 
			XlsUtil.setCellValue(10, 6,level7,this.sheet); 
			XlsUtil.setCellValue(12, 1,level9,this.sheet); 
			XlsUtil.setCellValue(12, 4,level6,this.sheet); 
			XlsUtil.setCellValue(12, 6,level8,this.sheet); 
		}
		catch(Exception e){
			e.printStackTrace() ;
		}
		finally {
			SqlUtil.close(rs, ps, conn);
		}
	}
	
}
