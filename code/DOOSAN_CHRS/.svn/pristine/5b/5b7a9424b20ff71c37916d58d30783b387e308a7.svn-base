package com.ait.kpa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.regexp.RE;

import com.ait.ar.business.ArServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.NumberUtil;

public class PaCalc {

	private static ServiceLocator services;

	private ArServices arServices;

	public PaCalc() {
		try {
			services = ServiceLocator.getInstance();
			arServices = new ArServices();
		} catch (ServiceLocatorException ex) {
		}
	}

	Connection Conn = null; // 产生连接初始化的对象Conn

	// 返回连接
	public Connection Conn() throws Exception {
		Connection Conn = null;// 建立参数声明
		try {
			Conn = services.getConnection(); // 得到连接
		} catch (Exception e) {
			System.out.print("Conn Error:" + e);
		}
		return Conn;
	}

	// 返回全部记录，用于没有分页和搜索的情况
	public String CalcProcess(String Pamonth) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
		Connection conn = null ;
		CallableStatement cs = null; // 建立参数声明
		try
		{
			conn = Conn(); // 建立连接
			
				if (!this.getPayLockStatus(Pamonth)) {
					cs = conn.prepareCall("{call KPR_PACAL(?,?,?,?)}");
					cs.setString(1, Pamonth);
					cs.setString(2, "NORMAL");
					cs.setString(3, "NONE");
					cs.registerOutParameter(4, Types.VARCHAR);
					cs.execute();
					this.setResult(cs.getString(4)); // 产生实体对象
				}
				else
				{
					this.setResult("当月工资已锁定,不能进行计算!!!"); // 产生实体对象
				}

			return result;
		}catch (SQLException e) {
			return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
		} finally {
			SqlUtil.close(cs, conn);
		}
		
	}
	
	// 返回全部记录，用于没有分页和搜索的情况
	public String CalcBonusProcess(String Pamonth, String bonusType) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
		Connection conn = null ;
		CallableStatement cs = null; // 建立参数声明
		try
		{
			conn = Conn(); // 建立连接
			
			cs = conn.prepareCall("{call KPR_PACAL_BONUS(?,?,?,?,?)}");
			cs.setString(1, Pamonth);
			cs.setString(2, bonusType);			
			cs.setString(3, "NORMAL");
			cs.setString(4, "NONE");
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			this.setResult(cs.getString(5)); // 产生实体对象
			return result;
				
		}catch (SQLException e) {
			e.printStackTrace() ;
			return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
		} finally {
			SqlUtil.close(cs, conn);
		}
	}
	
	public String CalcDiffProcess(String Pamonth,String Pamonths) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
		Connection conn = null ;
		CallableStatement cs = null; // 建立参数声明
		try
		{
			conn = Conn(); // 建立连接
			
			cs = conn.prepareCall("{call KPR_PACAL_DIFF(?,?,?,?,?)}");
			cs.setString(1, Pamonth);
			cs.setString(2, Pamonths);			
			cs.setString(3, "NORMAL");
			cs.setString(4, "NONE");
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			this.setResult(cs.getString(5)); // 产生实体对象
			return result;
							
		}catch (SQLException e) {
			e.printStackTrace() ;
			return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
		} finally {
			SqlUtil.close(cs, conn);
		}
	}
	/**
	 * get pay lock status
	 * @param Pamonth
	 * @return
	 */
	public boolean getPayLockStatus(String Pamonth) {

		try {
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("PA_MONTH_STR", Pamonth);
			List list = arServices.kretrieveMonthlyStatusList(parameterObject);
			if (list == null || list.size() == 0) {
				return false ;
			} else {
				SimpleMap result = (SimpleMap) list.get(0);
				int lockFlag = Integer.parseInt(result.getString("PA_LOCK_FLAG"));
				if (lockFlag == 1)
					return true ;
				else
					return false ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true ;
		}
	}

	public String result = "";

	public String dept_id = "";

	public String getResult() {
		return editNull(this.result);
	}

	public String getdept() {
		return editNull(this.dept_id);
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setdept(String dept_id) {
		this.dept_id = dept_id;
	}

	private String Message(String s, String reg, String to) {
		RE re = new RE(reg);
		while (re.match(s)) {
			s = s.replaceAll(re.getParen(0), to);
		}
		return s;
	}

	public String editNull(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

}
