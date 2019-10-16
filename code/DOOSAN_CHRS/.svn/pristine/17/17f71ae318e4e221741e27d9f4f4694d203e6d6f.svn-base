package com.ait.pa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.regexp.RE;

import com.ait.ar.business.ArServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.util.NumberUtil;
import com.ait.util.PaCalcUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class PaCalc {

	private static ServiceLocator services;

	private ArServices arServices;
	
	private static final Logger logger = Logger.getLogger(PaCalc.class);

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

			Logger.getLogger(getClass()).error("Conn Error:" + e);
		}
		return Conn;
	}

	// 返回全部记录，用于没有分页和搜索的情况
	public String CalcProcess(String Pamonth, String statTypeCode,String calType, String cpnyId, String retroactiveTaxType, String specialPaMonth, String retroactiveTaxPaMonth) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
		Connection conn = null ;
		CallableStatement cs = null; // 建立参数声明
			
		if (calType != null && calType.equals("special")) 
		{
			if(retroactiveTaxType != null && retroactiveTaxType.length() > 0)
			{	
				if (PaCalcUtil.getPaReplenishmentCalcFlag() == 0 )
				{
					try{
						conn = Conn(); // 建立连接
						cs = conn.prepareCall("{call PR_PACAL_REPLENISHMENT(?, ?, ?, ?, ?, ?, ?)}");
						cs.setString(1, specialPaMonth);
						cs.setString(2, statTypeCode);
						cs.setString(3, cpnyId);
						cs.setString(4, retroactiveTaxType);
						cs.setString(5, retroactiveTaxPaMonth);
						cs.setString(6, "NONE");
						cs.registerOutParameter(7, Types.VARCHAR);
						cs.execute();
						this.setResult(cs.getString(7)); // 产生实体对象
					}catch (SQLException e) {
						return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
					} finally {
						SqlUtil.close(cs, conn);
						PaCalcUtil.setPaReplenishmentCalcFlag(0) ;
					}
				}
				else
				{
					return "对不起,工资补税正在计算,请稍后再进行计算." ;
				}
			}
		}
		else if (calType != null && calType.equals("normal"))
		{
			if (!this.getPayLockStatus(Pamonth,statTypeCode,cpnyId)) {
				
				if (PaCalcUtil.getPaCalcFlag() == 0 )
				{
					try{
						
							conn = Conn(); // 建立连接
							logger.debug("call PR_PACAL......") ;
	 ;						cs = conn.prepareCall("{call PR_PACAL(?, ?, ?, ?, ?, ?)}");
							cs.setString(1, Pamonth);
							cs.setString(2, statTypeCode);
							cs.setString(3, cpnyId);
							cs.setString(4, "NORMAL");
							cs.setString(5, "NONE");
							cs.registerOutParameter(6, Types.VARCHAR);
							cs.execute();
							this.setResult(cs.getString(6)); // 产生实体对象
						
					}catch (SQLException e) {
						return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
					} finally {
						SqlUtil.close(cs, conn);
						PaCalcUtil.setPaCalcFlag(0) ;
					}
				}
				else
				{
					return "对不起,工资正在计算,请稍后再进行计算." ;
				}
			}
			else
			{
				this.setResult("当月工资已锁定,不能进行计算!!!"); // 产生实体对象
			}
		}
			
		return result ;
	}
	
	// 返回全部记录，用于没有分页和搜索的情况
	public String CalcBonusProcess(String Pamonth, String statTypeCode, String cpnyId, String bonusType, String bonusNo) throws Exception { // 搜索信息方法(搜索方式,搜索信息,开始日期,结束日期,分页)
		Connection conn = null ;
		CallableStatement cs = null; // 建立参数声明
		if (PaCalcUtil.getPaBonusCalcFlag() == 0 )
		{
			try
			{
				
				conn = Conn(); // 建立连接
				
				cs = conn.prepareCall("{call PR_PACAL_BONUS(?, ?, ?, ?, ?, ?, ?, ?)}");
				cs.setString(1, Pamonth);
				cs.setString(2, statTypeCode);
				
				cs.setString(3, bonusType);
				cs.setInt(4, NumberUtil.parseInt(bonusNo));
				cs.setString(5, cpnyId);
				
				cs.setString(6, "NORMAL");
				cs.setString(7, "NONE");
				cs.registerOutParameter(8, Types.VARCHAR);
				cs.execute();
				this.setResult(cs.getString(8)); // 产生实体对象
				return result;
			}catch (SQLException e) {
				e.printStackTrace() ;
				return this.Message(e.getLocalizedMessage(), "ORA-([0-9]*):", "<br>");
			} finally {
				SqlUtil.close(cs, conn);
				PaCalcUtil.setPaBonusCalcFlag(0) ;
			}
		}
		else
		{
			return "对不起,工资奖金正在计算,请稍后再进行计算." ;
		}
	}
	/**
	 * get pay lock status
	 * @param Pamonth
	 * @return
	 */
	public boolean getPayLockStatus(String Pamonth,String statTypeCode,String cpnyId) {

		try {
			SimpleMap parameterObject = new SimpleMap();
			parameterObject.setString("PA_MONTH_STR", Pamonth);
			parameterObject.setString("STAT_TYPE_CODE", statTypeCode);
			parameterObject.setString("CPNY_ID", cpnyId);
			
			List list = arServices.retrieveMonthlyStatusList(parameterObject);
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
