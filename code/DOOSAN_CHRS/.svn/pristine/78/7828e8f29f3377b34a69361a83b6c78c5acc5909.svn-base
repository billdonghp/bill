package com.ait.ar.arBalance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import com.ait.utils.ConnBean;

public class Dao_ardetail{
	
//	static class TimerDao extends TimerTask{
	public Dao_ardetail(){

      Connection ait_conn = null;
	  Connection dicc_conn = null;
      Statement ps_ait = null;
	  Statement ps_dicc = null;
      ResultSet rs_dicc = null;
	  ResultSet rs_count = null;
      ResultSet rs_sql_new_att = null;
      String sql_insert = "";
	  String sql_del = "";
			
			String sql_zifu = "";
			String sql_ora = "select * from pay06_tbl where PAY06_YYMMDD>=to_date('2008-06-21','yyyy-mm-dd') and PAY06_YYMMDD<=to_date('2008-07-25','yyyy-mm-dd') order by pay06_yymmdd,pay06_empno";
			String counts = "select count(*) as counts from pay06_tbl where PAY06_YYMMDD>=to_date('2008-06-21','yyyy-mm-dd') and PAY06_YYMMDD<=to_date('2008-07-25','yyyy-mm-dd') order by pay06_yymmdd,pay06_empno";
			try{
				dicc_conn = DiccConnBean.getConn();
				ait_conn = ConnBean.getConn();
				ait_conn.setAutoCommit(false) ;
		        ps_dicc = dicc_conn.createStatement();
				ps_ait = ait_conn.createStatement();
				rs_count = ps_dicc.executeQuery(counts);
				rs_count.next();
				if(rs_count.getInt("counts") != 0){
					rs_dicc = ps_dicc.executeQuery(sql_ora);
					sql_del = "delete ar_pay_tbl";
					ps_ait.executeQuery(sql_del);
					while(rs_dicc.next()){
						PreparedStatement pstmt = null;
						sql_insert = "insert into ar_pay_tbl (pay_yymmdd,pay_empno,pay_ugotm,pay_exttm,pay_suntm,pay_holtm,pay_day,pay_night,pay_late,pay_early,pay_jia,pay_dep,pay_dnm,pay_depcd,pay_depnm,pay_nightbzh,pay_jiatm) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pstmt=ait_conn.prepareStatement(sql_insert);
						pstmt.setDate(1, rs_dicc.getDate("pay06_yymmdd"));
						pstmt.setString(2, "ic"+rs_dicc.getString("pay06_empno"));
						pstmt.setDouble(3, rs_dicc.getDouble("pay06_ugotm"));
						pstmt.setDouble(4, rs_dicc.getDouble("pay06_exttm"));
						pstmt.setDouble(5, rs_dicc.getDouble("pay06_suntm"));
						pstmt.setDouble(6, rs_dicc.getDouble("pay06_holtm"));
						pstmt.setDouble(7, rs_dicc.getDouble("pay06_day"));
						pstmt.setDouble(8, rs_dicc.getDouble("pay06_night"));
						pstmt.setDouble(9, rs_dicc.getDouble("pay06_late"));
						pstmt.setDouble(10, rs_dicc.getDouble("pay06_early"));
						pstmt.setDouble(11, rs_dicc.getDouble("pay06_jia"));
						pstmt.setString(12, rs_dicc.getString("pay06_dep"));
						pstmt.setString(13, rs_dicc.getString("pay06_dnm"));
						pstmt.setString(14, rs_dicc.getString("pay06_depcd"));
						pstmt.setString(15, rs_dicc.getString("pay06_depnm"));						
						pstmt.setString(16, rs_dicc.getString("pay06_nightbzh"));					
						pstmt.setDouble(17, rs_dicc.getDouble("pay06_jiatm"));					

						pstmt.executeUpdate();

						pstmt.close();
					}
					rs_dicc.close();
				}

		        ps_ait.close() ;
		  	    ps_dicc.close() ;
		        
		        ait_conn.commit() ;
			}catch (Exception e) {
	            e.printStackTrace();
			}
			finally
			{
				try
				{
					dicc_conn.close();
					ait_conn.close() ;
				}
				catch(Exception e){}
				
			}
		}
//	}
	
		public static void main(String[] args) {
		new Dao_ardetail();
		//Timer timer = new Timer();
    //    timer.scheduleAtFixedRate(new TimerDao(), 14400000, 86400000);
	}
}