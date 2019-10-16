package com.ait.pa.cmd.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.ait.utils.ConnBean;
import com.ait.web.Command;

public class SynchronizePaDataCmd implements Command  {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String returnPage="";
		String content = request.getParameter("content");
		if(!content.equals("") && content.equals("synchronizePaData")){
			returnPage=this.SynchronizePaData(request, response);
		}else{
			returnPage="/error.jsp";
		}
		return returnPage;
	}
	
	
	public  Connection getConn() {
		DataSource ds = null;
		Connection conn = null;
		try {
			Logger.getLogger(ConnBean.class).debug("get diy  db connection from jndi resource......");
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/DIY_PA");
			conn = ds.getConnection();
		} catch (NamingException namex) {
			Logger.getLogger(ConnBean.class).error(namex.toString());
		} catch (SQLException sqlex) {
			Logger.getLogger(ConnBean.class).error(sqlex.toString());
		}
		return conn;
	}
	public String SynchronizePaData(HttpServletRequest request, HttpServletResponse response) {
		String paType=request.getParameter("paType");
		String statTypeCode=request.getParameter("statTypeCode");
		String statTypeName="";
		if(statTypeCode.equals("C_12067_1330306")){
			statTypeName="工厂";
		}else if(statTypeCode.equals("C_12067_1330308")){
			statTypeName="支社";
		}else{
			statTypeName="其他";
		}
		String errorMsg=request.getParameter("year")+"年"+request.getParameter("month")+"月 "+statTypeName+(paType.equals("1")?"工资":"奖金")+"数据不存在！";
		
		Connection chrsConn = ConnBean.getConn();
		Connection humanConn = this.getConn();
		PreparedStatement queryPST = null;
		PreparedStatement insertPST = null;
		PreparedStatement updatePST = null;
		ResultSet rs=null;
		int updateResult =0;
		String querySql = "  SELECT T.PAY03_PAYYM, T.PAY03_PAYMT, T.PAY03_EMPNO, T.PAY03_SEQNO, T.PAY03_NAME, T.PAY03_EMPCL,  "
					   	+ "  T.PAY03_DEP,  T.PAY03_DNM, T.PAY03_DEPCD,"
						+ "  T.PAY03_DEPNM, T.PAY03_OVPY1, T.PAY03_OVPY2, T.PAY03_INTOT, T.PAY03_OLDDE, T.PAY03_MINDE,        "
						+ "  T.PAY03_EMPDE, T.PAY03_TAX,T.PAY03_GJJ, T.PAY03_GQGLF, T.PAY03_GSFDBX, T.PAY03_AREACL            "
						+ "  FROM V_PA_HISTORY_DIY T " 
						+ "  WHERE T.PAY03_PAYYM=? AND T.PAY03_PAYMT=? AND T.statTypeCode=? ";
		
		String insertSql = " INSERT INTO PAY03_TBL(PAY03_PAYYM,PAY03_PAYMT,PAY03_EMPNO,PAY03_SEQNO,PAY03_NAME,PAY03_EMPCL, PAY03_POSIT,PAY03_PAYCL, "
						+ "  PAY03_DEP,PAY03_DNM,PAY03_DEPCD,PAY03_DEPNM,PAY03_SEX, PAY03_MARCD, PAY03_ENTDT, PAY03_BSPAY, PAY03_WKPAY, PAY03_INSPY, "
						+ "  PAY03_HSPAY,PAY03_LIVPY,PAY03_BSTOT,PAY03_OVPY1,PAY03_OVPY2,PAY03_TRSPY,PAY03_SPCPY,PAY03_MIDPY, "
						+ "  PAY03_ETCPY,PAY03_ERRPY,PAY03_ABSPY,PAY03_PAY1,PAY03_PAY2,PAY03_PAY3,PAY03_ETTOT,PAY03_INTOT,PAY03_ABSDY, "
						+ "  PAY03_OLDDE,PAY03_MINDE,PAY03_EMPDE,PAY03_EDUTX,PAY03_HOUDE,PAY03_LATDE,PAY03_PRVDE,PAY03_OUTDE, "
						+ "  PAY03_ETCDE,PAY03_WRMDE,PAY03_ERRDE,PAY03_TAX,PAY03_DEL1,PAY03_DEL2,PAY03_DEL3,PAY03_DETOT,PAY03_REAPY,PAY03_GJJ,PAY03_GQGLF,PAY03_GSFDBX,PAY03_AREACL) VALUES(?,?,?,?,?,?, "
						+ "  ' ',0,?,?,?,?,' ',' ',SYSDATE,0,0,0,0,0,0,?,?,0,0,0,0,0,0,0,0,0,0,?,0,?,?,?,0,0,0,0,0,0,0,0,?,0,0,0,0,0,?,?,?,?) ";
		
		String updateSql= "  UPDATE PAY03_TBL  T SET T.PAY03_NAME=?,T.PAY03_EMPCL=?,T.PAY03_DEP=?,T.PAY03_DNM=?, " 
		            	+ "  T.PAY03_DEPCD=?,T.PAY03_DEPNM=?,T.PAY03_OVPY1=?,T.PAY03_OVPY2=?,T.PAY03_INTOT=?,T.PAY03_OLDDE=?," 
			            + "  T.PAY03_MINDE=?,T.PAY03_EMPDE=?,T.PAY03_TAX=?,T.PAY03_GJJ=?,T.PAY03_GQGLF=?,T.PAY03_GSFDBX=?,T.PAY03_AREACL=? " 
			            + "  WHERE T.PAY03_PAYYM=? AND T.PAY03_PAYMT=? AND T.PAY03_EMPNO=? AND T.PAY03_SEQNO=?";
		
		try {			
			queryPST=chrsConn.prepareStatement(querySql);
			insertPST=humanConn.prepareStatement(insertSql);
			updatePST=humanConn.prepareStatement(updateSql);
			
			queryPST.setString(1,request.getParameter("year")+request.getParameter("month"));
			queryPST.setString(2,paType);
			queryPST.setString(3,statTypeCode);
			rs=queryPST.executeQuery();
	             while(rs.next()){		
	            	 updatePST.setString(1, conversionCharter(rs.getString("PAY03_NAME")));
	            	 updatePST.setString(2, conversionCharter(rs.getString("PAY03_EMPCL")));
	            	 updatePST.setString(3, conversionCharter(rs.getString("PAY03_DEP")));
	            	 updatePST.setString(4, conversionCharter(rs.getString("PAY03_DNM")));         	      	
	            	 updatePST.setString(5, conversionCharter(rs.getString("PAY03_DEPCD")));
	            	 updatePST.setString(6, conversionCharter(rs.getString("PAY03_DEPNM")));
	            	 updatePST.setDouble(7, rs.getDouble("PAY03_OVPY1"));
	            	 updatePST.setDouble(8, rs.getDouble("PAY03_OVPY2"));
	            	 updatePST.setDouble(9, rs.getDouble("PAY03_INTOT"));
	            	 updatePST.setDouble(10, rs.getDouble("PAY03_OLDDE"));
	            	 updatePST.setDouble(11, rs.getDouble("PAY03_MINDE"));
	            	 updatePST.setDouble(12, rs.getDouble("PAY03_EMPDE"));
	            	 updatePST.setDouble(13, rs.getDouble("PAY03_TAX"));
	            	 updatePST.setDouble(14, rs.getDouble("PAY03_GJJ"));
	            	 updatePST.setDouble(15, rs.getDouble("PAY03_GQGLF"));
	            	 updatePST.setDouble(16, rs.getDouble("PAY03_GSFDBX"));
	            	 updatePST.setString(17, conversionCharter(rs.getString("PAY03_AREACL")));
	            	 updatePST.setString(18, conversionCharter(rs.getString("PAY03_PAYYM")));
	            	 updatePST.setString(19, conversionCharter(rs.getString("PAY03_PAYMT")));
	            	 updatePST.setString(20, conversionCharter(rs.getString("PAY03_EMPNO")));
	            	 updatePST.setDouble(21, rs.getInt("PAY03_SEQNO"));
	            	 updateResult=updatePST.executeUpdate();
	            	 if(updateResult==0){
	            		 insertPST.setString(1, conversionCharter(rs.getString("PAY03_PAYYM")));
	            		 insertPST.setString(2, conversionCharter(rs.getString("PAY03_PAYMT")));
	            		 insertPST.setString(3, conversionCharter(rs.getString("PAY03_EMPNO")));
	            		 insertPST.setDouble(4, rs.getInt("PAY03_SEQNO"));
	            		 insertPST.setString(5, conversionCharter(rs.getString("PAY03_NAME")));
	            		 insertPST.setString(6, conversionCharter(rs.getString("PAY03_EMPCL")));
	            		 insertPST.setString(7, conversionCharter(rs.getString("PAY03_DEP")));
	            		 insertPST.setString(8, conversionCharter(rs.getString("PAY03_DNM")));         	      	
	            		 insertPST.setString(9, conversionCharter(rs.getString("PAY03_DEPCD")));
	            		 insertPST.setString(10, conversionCharter(rs.getString("PAY03_DEPNM")));
	            		 insertPST.setDouble(11, rs.getDouble("PAY03_OVPY1"));
	            		 insertPST.setDouble(12, rs.getDouble("PAY03_OVPY2"));
	            		 insertPST.setDouble(13, rs.getDouble("PAY03_INTOT"));
	            		 insertPST.setDouble(14, rs.getDouble("PAY03_OLDDE"));
	            		 insertPST.setDouble(15, rs.getDouble("PAY03_MINDE"));
	            		 insertPST.setDouble(16, rs.getDouble("PAY03_EMPDE"));
	            		 insertPST.setDouble(17, rs.getDouble("PAY03_TAX"));
	            		 insertPST.setDouble(18, rs.getDouble("PAY03_GJJ"));
	            		 insertPST.setDouble(19, rs.getDouble("PAY03_GQGLF"));
	            		 insertPST.setDouble(20, rs.getDouble("PAY03_GSFDBX"));
	            		 insertPST.setString(21, conversionCharter(rs.getString("PAY03_AREACL")));
	            		 insertPST.executeUpdate();
	            	 }
	            	 errorMsg= "同步"+request.getParameter("year")+"年"+request.getParameter("month")+"月 "+statTypeName+(paType.equals("1")?"工资":"奖金")+"数据成功！";
				 }		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorMsg= "同步"+request.getParameter("year")+"年"+request.getParameter("month")+"月 "+statTypeName+(paType.equals("1")?"工资":"奖金")+"数据失败！";
			e.printStackTrace();
		}finally{
			try {
				updatePST.close();
				insertPST.close();
				humanConn.close();
				chrsConn.close();
				queryPST.close();
				queryPST.close();
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		request.setAttribute("year", request.getParameter("year"));
		request.setAttribute("month",request.getParameter("month"));
		request.setAttribute("paType", paType);
		request.setAttribute("statTypeCode",statTypeCode);
		request.setAttribute("errorMsg", errorMsg);
		return "/pa0508.jsp";
		
	}
	
	public String conversionCharter(String str){
		String result="";
		try {
			result = new String(str.getBytes("GBK"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
