package com.ait.ar.cmd.idcard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ait.ar.business.ArServices;
import com.ait.ar.dao.ArImportShiftDAO;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sqlmap.util.UserConfiguration;
import com.ait.sy.bean.AdminBean;
import com.ait.web.Command;
import com.ait.util.ConnBean;
import com.ait.util.SqlConnBean;
import com.ait.util.StringUtil;

public class ImportAttRecordCommand implements Command {

	private static final Logger logger = Logger.getLogger(ImportAttRecordCommand.class);

	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	
	private UserConfiguration importAttRecordConfig = null ;

	private AdminBean admin = null ;
	
	private ArServices services = null ;
	
	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		String returnpage ="";
		try {
			 returnpage = this.detail(request);
			 System.out.println("导入成功");
			 message = "导入成功";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			message = "导入失败,请检查文件";
		}
		request.setAttribute("alert", message);
		request.setAttribute("url","/ar/ar_importAttRecord.jsp?menu_code=ar0213");
		
		return returnpage;
	}
	
	public String ceshi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = "";
		try {
			String importDate = request.getParameter("startDate") ;
			
			admin = (AdminBean) request.getSession().getAttribute("admin");
			
			services = new ArServices() ;
			
			importAttRecordConfig = UserConfiguration.getInstance("/importAttRecordConfig.properties"); 
			
			List<SimpleMap> insertList = checkAttRecord(request) ;
			
			logger.debug("insert record count = " + insertList.size()) ;
			
			services.insertAttRecords(insertList) ;
			
			message = "导入成功,日期 " + importDate + " ,导入 " + insertList.size() + " 条记录";
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			message = "导入失败,请检查文件";
		}

		request.setAttribute("alert", message);
		request.setAttribute("url","/ar/ar_importAttRecord.jsp?menu_code=ar0213");
		
		return "/inc/alertMessage.jsp";
	
	}
	
	private List<SimpleMap> checkAttRecord(HttpServletRequest request) throws Exception {
		List<SimpleMap> insertList = new ArrayList<SimpleMap>() ;
		
		ArImportShiftDAO dao = ArImportShiftDAO.getInstance();
		
		Connection sConn = null;
		PreparedStatement sPstmt = null;
		ResultSet sRs = null;
		
		try {
			logger.debug(" CpnyId = " + admin.getCpnyId());
			
			String sourceDbUrl = StringUtils.trimToEmpty(importAttRecordConfig.getString(admin.getCpnyId() + ".db.url"));
			String sourceDbUsername = StringUtils.trimToEmpty(importAttRecordConfig.getString(admin.getCpnyId() + ".db.username"));
			String sourceDbPassword = StringUtils.trimToEmpty(importAttRecordConfig.getString(admin.getCpnyId() + ".db.password"));
			String sqlRetrieve = StringUtils.trimToEmpty(importAttRecordConfig.getString(admin.getCpnyId() + ".sql.retrieve"));
			
			
			// 导入刷卡,参数
			String importDate = request.getParameter("startDate") ;
			
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			
			sConn = DriverManager.getConnection(sourceDbUrl, sourceDbUsername, sourceDbPassword);
			sPstmt = sConn.prepareStatement(sqlRetrieve);
			sPstmt.setString(1, importDate);
			sPstmt.setString(2, importDate);
			sRs = sPstmt.executeQuery();
			
			String personId = "" ;
			while (sRs.next()){
				// 得到personId,根据empId得到数据可能会存在问题
				personId = dao.getPersonIdByEmpId(sRs.getString("empId"), admin.getCpnyId()) ; 
				if (!personId.equals("ERROR")) {
					
					SimpleMap parameterObject = new SimpleMap();
					parameterObject.set("personId", personId) ;
					parameterObject.set("cardNo", sRs.getString("cardNo")) ;
					parameterObject.set("rTime", sRs.getTimestamp("rTime")) ;
					parameterObject.set("doorType", StringUtil.checkNull(sRs.getString("doorType"))) ;
					parameterObject.set("interfaceRecordId", StringUtil.checkNull(sRs.getString("interfaceRecordId"))) ;
					parameterObject.set("cpnyId", admin.getCpnyId()) ;
					
					// 判断刷卡记录是否存在
					int checkFlag = services.checkAttRecord(parameterObject) ;
					
					if (checkFlag == 0){
						insertList.add(parameterObject) ;
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
			logger.error(admin.getCpnyId() + " 导入刷卡失败! "+ e.toString(), e);
		} finally {
			DbUtils.closeQuietly(sRs);
			DbUtils.closeQuietly(sPstmt);
			DbUtils.closeQuietly(sConn);
		}
		
		return insertList ;
	}
	
//	static class TimerDao extends TimerTask{
private String detail(HttpServletRequest request) {
		
		// 导入刷卡,参数
		String startDate = request.getParameter("startDate") ;
		
		String  endDate = request.getParameter("endDate") ;
		Connection sqlServerConn = null;
		Connection oracleConn = null;
		//PreparedStatement sqlServerState = null;
		PreparedStatement oracleState = null;
		ResultSet sqlServerResult = null;
		ResultSet oracleResult = null;
		
		
		PreparedStatement sqlServerRetrieve = null;
		ResultSet sqlServerRetrieveResult = null;
		PreparedStatement oracleStateEmpId = null;
		ResultSet oracleResultEmpId = null;
		PreparedStatement oracleStateCheck = null;
		ResultSet oracleResultCheck = null;
		
		try {
			oracleConn = ConnBean.getConn();
			sqlServerConn = SqlConnBean.getConn();
			
			oracleConn.setAutoCommit(false) ;
			sqlServerConn.setAutoCommit(false) ;
						
			
			String sqlRetrieve = "select dev_serial AS interfaceRecordId, sj AS rTime, (dt_user.user_no) AS empId,(dt_user.personid) AS personId,user_card as cardNO," + 
				"case when fx=1 then 'IN' when fx=2 then 'OUT' END  AS doorType from kt_jl left join dt_user on kt_jl.user_serial=dt_user.user_serial WHERE sj "
				+ " BETWEEN convert(datetime,'"+startDate+"') " + 
				" AND convert(datetime,'"+endDate+"') " + 
				//" and user_card='D479545D' "+
			   // " and dt_user.user_no in('2003024','2003255','2611418','2004008','2613074','2613196') "+
				//" and dt_user.personid in('1503987') "+
				"AND USER_NO IS NOT NULL  AND SJ IS NOT NULL";
			
			System.out.println("1111"+sqlRetrieve);
			sqlServerRetrieve = sqlServerConn.prepareStatement(sqlRetrieve);
			
			sqlServerRetrieveResult = sqlServerRetrieve.executeQuery() ;
			
			String oracleGetMaxRecordsSql = " select t.person_id from hr_employee t where t.person_id = get_personid3(?) " ;
			oracleStateEmpId = oracleConn.prepareStatement(oracleGetMaxRecordsSql) ;
			
			String oracleInsertArRecords = "INSERT INTO AR_MAC_RECORDS (RECORD_NO, CARD_NO, R_TIME, ACTIVE, INSERT_BY, EMPID, INSERT_TIME,CPNY_ID,interface_record_id,DOOR_TYPE,DATA_ID) " + 
			   " SELECT  AR_EATERY_RECORDS_SEQ.nextval, ?, to_date(substr(?,0,19),'yyyy-mm-dd hh24:mi:ss'), '1', 'M',get_personid3(?), sysdate,get_cpnyid(?),? ,?,'DICC' FROM DUAL  WHERE get_personid3(?) <> 'ERROR' and get_cpnyid(?) <> 'ERROR' " ;  //WHERE get_personid3(?) <> 'ERROR' and get_cpnyid(?) <> 'ERROR'
			oracleState = oracleConn.prepareStatement(oracleInsertArRecords) ;
			
			
			String oracleCheckAttRecordSql = " 	SELECT COUNT(T.EMPID) AS EMPID FROM AR_MAC_RECORDS T  WHERE T.R_TIME = to_date( substr(?,0,19),'yyyy-mm-dd hh24:mi:ss')  AND T.EMPID = get_personid3(?) " ;
			oracleStateCheck = oracleConn.prepareStatement(oracleCheckAttRecordSql) ;	
			
			int i=0;
			String personId = "" ;
			while (sqlServerRetrieveResult.next()){
				
				i++;
					
				oracleStateEmpId.setString(1, sqlServerRetrieveResult.getString("personId"));
				oracleResultEmpId = oracleStateEmpId.executeQuery() ;	
				
				
				
				
				if (oracleResultEmpId.next()) {
					
					
					oracleStateCheck.setString(1, sqlServerRetrieveResult.getString("RTIME"));
					oracleStateCheck.setString(2, sqlServerRetrieveResult.getString("personId"));
					//System.out.println(i);
					//System.out.println(sqlServerRetrieveResult.getString("RTIME"));
					//System.out.println(sqlServerRetrieveResult.getString("personId"));
					oracleResultCheck = oracleStateCheck.executeQuery() ;
					
					while(oracleResultCheck.next())
					{
					//System.out.println("-------------------------"+oracleResultCheck.getInt("EMPID"));	
						if(oracleResultCheck.getInt("EMPID") == 0){
							oracleState.setString(1,sqlServerRetrieveResult.getString("cardNO")) ;
							//System.out.println(sqlServerRetrieveResult.getString("cardNO"));
							oracleState.setString(2,sqlServerRetrieveResult.getString("rTime")) ;
							///System.out.println(sqlServerRetrieveResult.getString("rTime"));
							oracleState.setString(3,sqlServerRetrieveResult.getString("personId")) ;
							oracleState.setString(4,sqlServerRetrieveResult.getString("personId")) ;
							//System.out.println(sqlServerRetrieveResult.getString("empId"));
							oracleState.setString(5,sqlServerRetrieveResult.getString("interfaceRecordId")) ;
							//System.out.println(sqlServerRetrieveResult.getString("interfaceRecordId"));
							
							oracleState.setString(6,sqlServerRetrieveResult.getString("doorType")) ;
							
							oracleState.setString(7,sqlServerRetrieveResult.getString("personId")) ;

							oracleState.setString(8,sqlServerRetrieveResult.getString("personId")) ;
							
							oracleState.addBatch() ;
						}
						
					}
					
				}
				
			}
		
			
			oracleState.executeBatch() ;
			//sqlServerState.execute() ;
			
			sqlServerConn.commit() ;
			oracleConn.commit() ;

			System.out.println("------------Finish-----------");
		} catch (Exception e) {
			try
			{
				oracleConn.rollback() ;
				sqlServerConn.rollback() ;
			}catch(Exception ee){}
			e.printStackTrace();
		}
		finally
		{
			try
			{
				//sqlServerState.close() ;
				sqlServerResult.close() ;
				oracleResult.close() ;
				oracleState.close() ;
				
				sqlServerConn.close();
				oracleConn.close();
				
				
				oracleStateEmpId.close();
				oracleResultCheck.close();
				
			}catch(Exception e){}
		}
	
		return "/inc/alertMessage.jsp";
	}
}