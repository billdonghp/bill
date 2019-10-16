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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.ar.bean.Annual;
import com.ait.ar.dao.AnnualBean;
import com.ait.ess.business.EssServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.DateUtil;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.util.XlsUtil;
import com.ait.utils.ConnBean;


public class AnnualVacationForm {
	
	private static final String sheetName = "ANNUAL_VACATION_APPLY";

	private static final String templatePath ="reports\\template\\ANNUAL_VACATION_APPLY.xls";

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
		File template = new File(webRootPath + templatePath);
		this.workBook = new HSSFWorkbook(new FileInputStream(template));
		this.sheet = this.workBook.getSheet(sheetName);
		
		AdminBean admin = (AdminBean)request.getSession(false).getAttribute("admin");
		EssServices essServices = new EssServices();
		SimpleMap parameterObject = new SimpleMap();	
		parameterObject.set("LEAVE_NO",leaveNo);	
		SimpleMap sm = (SimpleMap)essServices.getLeaveApplyInfo(parameterObject);
		SimpleMap H9sm = (SimpleMap)essServices.getLeaveApplyH9Info(parameterObject);
		
		AnnualBean annualBean= new AnnualBean();
		Annual annual=  (Annual)annualBean.empAnnualLeft(StringUtil.checkNull(sm.getString("PERSON_ID"),admin.getAdminID()),new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        double allTotVaction=annual.getAnnualHours();
        double lastTotVaction=annual.getAnnualHoursLeft();
        double userTotVaction=annual.getAnnualHoursUsed();
		
		XlsUtil.setCellValue(1, 3, sm.getString("CHINESENAME"),this.sheet); //姓名
		XlsUtil.setCellValue(1, 9, sm.getString("LEAVE_EMPID"),this.sheet); //职号
		XlsUtil.setCellValue(1, 15, sm.getString("POST_CODE"), this.sheet);
		XlsUtil.setCellValue(2, 3, sm.getString("FOURTHDEPTNAME"),this.sheet); //部门
		XlsUtil.setCellValue(2, 9, sm.getString("DEPTNAME"),this.sheet); //课组
		XlsUtil.setCellValue(2, 15, sm.getString("CREATE_DATE"),this.sheet); //申请日期
		XlsUtil.setCellValue(3, 3, H9sm.getString("BUSINESS"),this.sheet); //担当业务
		XlsUtil.setCellValue(4, 3, annual.getWorkYear() + " 年", this.sheet);
		XlsUtil.setCellValue(4, 7, allTotVaction+" 天", this.sheet);
		XlsUtil.setCellValue(4, 11, NumberUtil.formatNumber(annual.getArAnnualHoursUsed()/8,"0")+" 天", this.sheet);
		XlsUtil.setCellValue(4, 15, lastTotVaction+" 天", this.sheet);
		XlsUtil.setCellValue(5, 3, sm.getString("LEAVE_FROM_TIME")+"至"+sm.getString("LEAVE_TO_TIME")+", 共"+sm.getString("LEAVEDAY")+"天", this.sheet);
		
		XlsUtil.setCellValue(6, 3, H9sm.getString("CONTACT_MODE"),this.sheet); //联系方式
		XlsUtil.setCellValue(7, 5, H9sm.getString("AGENT_NAME"),this.sheet); //代理人姓名
		XlsUtil.setCellValue(7, 10, H9sm.getString("AGENT_EMPID"),this.sheet); //代理人职号
		XlsUtil.setCellValue(7, 15, H9sm.getString("AGENT_POSITION"),this.sheet); //代理人职位
		XlsUtil.setCellValue(8, 5, H9sm.getString("AGENT_OFFICE_PHONE"),this.sheet); //代理人办公室电话
		XlsUtil.setCellValue(8, 12, H9sm.getString("AGENT_CELLPHONE"),this.sheet); //代理人手机
		XlsUtil.setCellValue(9, 3, H9sm.getString("OTHERBUSINESS"),this.sheet); //其他事项
		
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
		
			XlsUtil.setCellValue(11, 3,level3,this.sheet);
			XlsUtil.setCellValue(11, 5,level4,this.sheet);
			XlsUtil.setCellValue(11, 8,level5,this.sheet);
			XlsUtil.setCellValue(11, 11,level7,this.sheet); 
			XlsUtil.setCellValue(11, 14,level8,this.sheet); 
			XlsUtil.setCellValue(13, 3,level9,this.sheet); 
			XlsUtil.setCellValue(13, 11,level6,this.sheet); 
		
		}catch(Exception e){
			e.printStackTrace() ;
		}finally {
			SqlUtil.close(rs, ps, conn);
		}
	}
}
