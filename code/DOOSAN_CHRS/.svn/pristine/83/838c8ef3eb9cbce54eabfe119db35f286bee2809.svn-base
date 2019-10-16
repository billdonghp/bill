package com.ait.ga.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ait.core.exception.GlRuntimeException;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.util.XlsUtil;
import com.ait.utils.ConnBean;

public class PresentApplyExcel {
	
	private final String sheetName = "PRESENT_APPLY";
	private final String templatePath = "reports\\template\\PRESENT_APPLY.xls";
	private String webRootPath = null;
	private HSSFWorkbook workBook = null;
	private HSSFSheet sheet = null;
	
	public void setWebRootPath(String webRootPath){
		
		this.webRootPath = webRootPath;
	}
	
	public void write(OutputStream out)throws IOException {
		
		this.workBook.write(out);
	}
	
	public void fillReport(HttpServletRequest request)throws Exception{
		
		File templateFile = new File(this.webRootPath+this.templatePath);
		this.workBook = new HSSFWorkbook(new FileInputStream(templateFile));
		this.sheet = this.workBook.getSheet(sheetName);
		AdminBean admin  = (AdminBean)request.getSession(false).getAttribute("admin");
		SimpleMap parameterObject = new SimpleMap();
		
		parameterObject.setString("SEQ_NO", request.getParameter("applyNo"));
		parameterObject.setString("detailNo", request.getParameter("detailNo"));
		parameterObject.setString("adminId", admin.getAdminID());
		parameterObject.setString("ADMINID", admin.getAdminID());
		parameterObject.setString("cpnyId", admin.getCpnyId());
		parameterObject.setInt("active", 1);
		
		SimpleMap map = this.getPresentInfoList(parameterObject);
		
		
		XlsUtil.setCellValue(1, 2, map.getString("DEPTNAME"), this.sheet);
		XlsUtil.setCellValue(2, 2, map.getString("APPLY_DATE"), this.sheet);
		XlsUtil.setCellValue(3, 2, map.getString("QUENTITY")+map.getString("UNIT"), this.sheet);
		XlsUtil.setCellValue(4, 2, map.getString("PRESENT_OBJECT"), this.sheet);
		XlsUtil.setCellValue(5, 2, map.getString("REASON"), this.sheet);
		XlsUtil.setCellValue(3, 7, map.getString("USE_DATE"), this.sheet);
		XlsUtil.setCellValue(2, 7, map.getString("PRESENT_NAME"), this.sheet);
		XlsUtil.setCellValue(5, 11, map.getString("REMARK"), this.sheet);
		XlsUtil.setCellValue(1, 11, map.getString("DPETNAME"), this.sheet);
		XlsUtil.setCellValue(2, 11, map.getString("RK_QUENTITY")+map.getString("UNIT"), this.sheet);
		XlsUtil.setCellValue(4, 11, map.getString("EMP_NAME"), this.sheet);
		XlsUtil.setCellValue(2, 15, map.getString("PZ_QUENTITY")+map.getString("UNIT"), this.sheet);
		XlsUtil.setCellValue(3, 15, map.getString("CK_QUENTITY")+map.getString("UNIT"), this.sheet);
		XlsUtil.setCellValue(4, 15, map.getString("SY_QUENTITY")+map.getString("UNIT"), this.sheet);
	}
	
	private SimpleMap getPresentInfoList(SimpleMap parameterObject){
		
		Connection conn = null;
		Statement st = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SimpleMap map = new SimpleMap();
		String present_id = null;
		
		String sql_1 = " SELECT B.PRESENT_ID, GET_CODE_NAME(B.PRESENT_ID) PRESENT_NAME, GET_CODE_NAME(F.UNIT) UNIT ,B.QUENTITY QUENTITY, "+
	       			" A.PRESENT_OBJECT PRESENT_OBJECT, GET_EMP_NAME(A.CREATED_BY) EMP_NAME, D.DEPTNAME DEPTNAME, A.REMARK REMARK, "+
	                " A.REASON REASON, TO_CHAR(A.CREATE_DATE, 'YYYY-MM-DD') APPLY_DATE, TO_CHAR(A.USE_DATE, 'YYYY-MM-DD') USE_DATE "+
	                " FROM GA_PRESENT_APPLY A, GA_PRESENT_APPLY_DETAIL B, HR_EMPLOYEE C, HR_DEPARTMENT D,(SELECT DISTINCT E.PRESENT_ID, E.UNIT "+
	                " FROM GA_PRESENT E WHERE E.CPNY_ID = "+"'"+parameterObject.getString("cpnyId")+"'"+") F WHERE A.SEQ_NO = B.APPLY_NO AND A.CREATED_BY = C.PERSON_ID "+
	                " AND C.DEPTID = D.DEPTID AND B.PRESENT_ID = F.PRESENT_ID AND A.ACTIVITY = 1 AND C.CPNY_ID = "+"'"+parameterObject.getString("cpnyId")+"'"+ 
	                " AND B.SEQ_NO = "+"'"+parameterObject.getString("detailNo")+"'";

		String sql_2 = " SELECT SUM(DECODE(A.DATA_TYPE,'StockType001',A.QUENTITY,0)) - SUM(DECODE(A.DATA_TYPE,'StockType002',A.QUENTITY,0)) SY_QUENTITY,"+
			       " SUM(DECODE(A.DATA_TYPE,'StockType001',A.QUENTITY,0)) RK_QUENTITY,"+
			       " SUM(DECODE(A.DATA_TYPE,'StockType002',A.QUENTITY,0)) CK_QUENTITY "+
				   " FROM GA_PRESENT A WHERE A.CPNY_ID = "+"'"+parameterObject.getString("cpnyId")+"'"+" AND   A.PRESENT_ID = ?";
		
		String sql_3 = " SELECT (SELECT NVL(SUM(NVL(B.QUENTITY, 0)), 0) FROM GA_PRESENT_APPLY A, GA_PRESENT_APPLY_DETAIL B "+
         			" WHERE A.SEQ_NO = B.APPLY_NO AND A.CONFIRM_FLAG = 0 AND A.AFFIRM_FLAG < 2 AND A.ACTIVITY = 1 AND (SELECT T.CPNY_ID "+
                    " FROM HR_EMPLOYEE T WHERE T.PERSON_ID = A.CREATED_BY) = "+"'"+parameterObject.getString("cpnyId")+"'"+" AND B.PRESENT_ID = ?) APPLY_QUENTITY, "+
                    " (SELECT NVL(SUM(NVL(B.QUENTITY, 0)), 0) FROM GA_PRESENT_APPLY A, GA_PRESENT_APPLY_DETAIL B WHERE A.SEQ_NO = B.APPLY_NO "+
                    " AND A.AFFIRM_FLAG = 1 AND A.ACTIVITY = 1 AND (SELECT T.CPNY_ID FROM HR_EMPLOYEE T "+
                    " WHERE T.PERSON_ID = A.CREATED_BY) = "+"'"+parameterObject.getString("cpnyId")+"'"+
                    " AND B.PRESENT_ID = ?) PZ_QUENTITY, E.DEPTNAME DPETNAME FROM GA_PRESENT C, HR_EMPLOYEE D, HR_DEPARTMENT E "+
                    " WHERE C.CREATED_BY = D.PERSON_ID AND D.DEPTID = E.DEPTID ";

		try{
			conn = ConnBean.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql_1);
			if(rs.next()){
				map.setString("PRESENT_NAME", rs.getString("PRESENT_NAME"));
				map.setString("QUENTITY", rs.getString("QUENTITY"));
				map.setString("PRESENT_OBJECT", rs.getString("PRESENT_OBJECT"));
				map.setString("EMP_NAME", rs.getString("EMP_NAME"));
				map.setString("DEPTNAME", rs.getString("DEPTNAME"));
				map.setString("REMARK", rs.getString("REMARK"));
				map.setString("REASON", rs.getString("REASON"));
				map.setString("APPLY_DATE", rs.getString("APPLY_DATE"));
				map.setString("USE_DATE", rs.getString("USE_DATE"));
				map.setString("UNIT", rs.getString("UNIT"));
				present_id = rs.getString("PRESENT_ID");
			}
			pst = conn.prepareStatement(sql_2);
			pst.setString(1, present_id);
			rs = pst.executeQuery();
			if(rs.next()){
				map.setString("TMP_QUENTITY", rs.getString("SY_QUENTITY"));
				map.setString("RK_QUENTITY", rs.getString("RK_QUENTITY"));
				map.setString("CK_QUENTITY", rs.getString("CK_QUENTITY"));
			}
			pst = conn.prepareStatement(sql_3);
			pst.setString(1, present_id);
			pst.setString(2, present_id);
			rs = pst.executeQuery();
			if(rs.next()){
				map.set("SY_QUENTITY", (Integer.parseInt(map.getString("TMP_QUENTITY"))-rs.getInt("APPLY_QUENTITY")));
				map.setString("PZ_QUENTITY", rs.getString("PZ_QUENTITY"));
				map.setString("DPETNAME", rs.getString("DPETNAME"));
			}
			
		}catch(Exception e){
			Logger.getLogger(getClass()).error(e.toString());
			throw new GlRuntimeException("get present info error .",e);
		}finally{
			SqlUtil.close(rs,pst);
			SqlUtil.close(st,conn);
		}
		return map;
	}

}
