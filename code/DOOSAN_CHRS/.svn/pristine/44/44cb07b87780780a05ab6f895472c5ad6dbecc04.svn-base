package com.ait.ess.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.ar.dao.DBConnection;
import com.ait.ess.entity.EssAffirm;
import com.ait.util.StringUtil;

/**
 * 分页类
 * 
 * @author wangzhenjia
 * 
 */
public class PaginationSupport {
	private DBConnection db = new DBConnection();

	private ResultSet rs = null;

	private int current = 0;

	private int size = 0;

	private int pagenum = 0;

	private int count = 0;

	/**
	 * 取得需要数据的总数
	 * 
	 * @param table
	 * @param where
	 * @return
	 */
	public int getAllNoteNumber(String sql) {

		try {
			db.getConnectionOracle();
			rs = db.getResultSet(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			db.closeConnection();
		}
		return count;
	}

	public Vector pageOpSerchOne(String sql, int page, int records) {
		current = page;
		size = records;
		Vector vect = new Vector();
		try {
			if (sql.indexOf("OtApply") == -1 && sql.indexOf("LeaveApply") == -1) {

				sql = sql.toLowerCase();
			}

			String c_sql = sql.substring(sql.indexOf("from"));

			c_sql = "select count(*) " + c_sql;

			getAllNoteNumber(c_sql);
			int temp = this.getCount();
			db.getConnectionOracle();
			rs = db.getResultSet(sql);
			/**
			 * 算出要抛出多少行数据
			 */
			int rows = (page - 1) * records;
			rows++;

			while (rows > 0) {
				rs.next();// 一直往前移动 直到指定行数
				rows--;
			} // 用来调整指针，使指针指向查询页数的记录条上；

			int j = 0;

			do {

				if (rs == null || j == records || temp == 0
						|| page > this.getPagenum()) {
					break;
				}
				j++;

				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();

				Hashtable hash = new Hashtable();
				for (int i = 1; i <= cols; i++) {

					String field = this.toString(rsmd.getColumnName(i));

					String value = this.toString(rs.getString(i));

					hash.put(field, value);
				}
				vect.add(hash);

			} while (rs.next());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {

			db.closeConnection();

		}

		return vect;
	}

	//add by lvhongbin at 2005-11-14
	//为决裁情况察看>休假信息/加班信息使用
	public Vector pageOpSerchOne(int page, int records, String adminId,
			String applyType, String sqlBody) {
		current = page;
		size = records;
		String sqlString = "select * " + "from (" + "select * from ("
				+ "select rownum record_id,a.* " + "from (" + //分页头
				//被分页的结果集
				sqlBody + ") a) b ) c " + //分页尾
				"where rownum <= " + records + " " + "and c.record_id >= "
				+ records * (current - 1) + " " + "order by ACTIVITY";
		Logger.getLogger(getClass()).debug(sqlString);//查询结果集语句
		String rowCntSqlString = "select count(*) "
				+ sqlString.substring(sqlString.indexOf("from"));
		Logger.getLogger(getClass()).debug(rowCntSqlString);//计算行数语句
		//int temp = getAllNoteNumber(rowCntSqlString);

		Vector vect = null;
		Connection conn = null ;
		Statement state= null ;
		ResultSet rs = null ;
		try {
			conn = db.getConnectionOracle();//get new object!
			state = conn.createStatement();
			rs = state.executeQuery(sqlString);
			if (rs != null) {
				vect = new Vector();
				while (rs.next()) {
					Map record = new HashMap();
					//休假信息
					if (applyType.equalsIgnoreCase("OtApply")) {
						record.put("EMPID", rs.getString("EMPID"));//EmpID
						record.put("CHINESENAME", rs.getString("CHINESENAME"));//ChineseName
						record.put("APPLY_OT_NO", rs.getString("APPLY_OT_NO"));//OtApplySEQ
						record.put("CREATE_DATE", rs.getString("CREATE_DATE"));//OtApplyDate
						record.put("APPLY_OT_DATE", rs
								.getString("APPLY_OT_DATE"));//OtDate
						record.put("OT_DEDUCT_TIME", rs
								.getString("OT_DEDUCT_TIME"));//OtDeductTime
						record
								.put("OT_FROM_TIME", rs
										.getString("OT_FROM_TIME"));//OtStartTime
						record.put("OT_TO_TIME", rs.getString("OT_TO_TIME"));//OtEndTime
						record.put("APPLY_OT_REMARK", rs
								.getString("APPLY_OT_REMARK"));//OtWorkContent
						record.put("ACTIVITY", rs.getString("ACTIVITY"));//Activity
						record.put("UPDATED_BY", rs.getString("UPDATED_BY"));//UpdatedBy
						record.put("APPLY_OT_TYPE", rs
								.getString("APPLY_OT_TYPE"));//OtType
						record.put("APPLY_OT_FLAG", rs
								.getString("APPLY_OT_FLAG"));//OtNextDays
						List affirmStatusList = getAffirmStatusList(applyType,
								rs.getString("APPLY_OT_NO"));//获取决裁子列表
						record.put("AffirmStatusList", affirmStatusList);
					}
					//加班信息
					else if (applyType.equalsIgnoreCase("LeaveApply")) {
						record.put("LEAVE_NO", rs.getString("LEAVE_NO"));//记录编号
						record.put("UPDATED_BY", rs.getString("UPDATED_BY"));//更新提交人
						record.put("ACTIVITY", rs.getString("ACTIVITY"));//决裁状态
						record.put("CHINESENAME", rs.getString("CHINESENAME"));//显示人名
						record.put("EMPID", rs.getString("EMPID"));//员工编号
						record.put("LEAVE_TYPE_NAME", rs
								.getString("LEAVE_TYPE_NAME"));//休假类型 
						record.put("APPLY_TIME", rs.getDate("APPLY_TIME")
								.toString());
						record.put("SET_LEAVE_DAYS", rs
								.getString("SET_LEAVE_DAYS"));
						record.put("LEAVE_FROM_TIME", rs.getDate(
								"LEAVE_FROM_TIME").toString());
						record.put("LEAVE_TO_TIME", rs.getDate("LEAVE_TO_TIME")
								.toString());
						record
								.put("LEAVE_REASON", rs
										.getString("LEAVE_REASON"));//
						List affirmStatusList = getAffirmStatusList(applyType,
								rs.getString("LEAVE_NO"));//获取决裁子列表
						record.put("AffirmStatusList", affirmStatusList);
					}
					vect.add(record);
				}
				
			}
		} catch (Exception ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		finally{
			try{
				if (rs != null)
					rs.close();
				if (state != null)
					state.close();
				if (conn != null)
					conn.close();
				}
			catch(Exception e){}
		}
		return vect;
	}

	public String toString(String str) {

		if (str == null)
			str = "";
		if (str.equals("null"))
			str = "";
		str = str.trim();

		return str;
	}

	/**
	 * 得到页的总数
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 得到当前页
	 * 
	 * @return
	 */
	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * 得到总共几页
	 * 
	 * @return
	 */
	public int getPagenum() {
		int c = this.getCount();
		int s = this.getSize();
		if (c % s == 0)
			this.pagenum = c / s;
		else if (c % s != 0)
			this.pagenum = c / s + 1;
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	/**
	 * 得到每页显示多少
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	//add by lvhongbin at 2005-11-13
	//添加裁决情况的子列表
	public List getAffirmStatusList(String applyType, String seqNo)
			throws Exception {
		List list = new ArrayList();
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		String sql = "select * " + "from ess_affirm_v " + "where APPLY_TYPE='"
				+ applyType + "' " + "and apply_no='" + seqNo + "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			Logger
					.getLogger(getClass())
					.debug(
							"add sub list get connection and create new statement and result set...");
			con = db.getConnectionOracle();//return new object
			state = con.createStatement();
			rs = state.executeQuery(sql);
			while (rs.next()) {
				list.add(this.createAffirmRelation(rs));
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			//SqlUtil.close(rs,state,con);
			if (rs != null)
				rs.close();
			if (state != null)
				state.close();
			if (con != null)
				con.close();
		}
		return list;
	}

	private EssAffirm createAffirmRelation(ResultSet rs) {
		EssAffirm affirm = new EssAffirm();
		try {
			affirm.setActivity(rs.getInt("ACTIVITY"));
			affirm.setAffirmFlag(rs.getInt("Affirm_flag"));
			affirm.setAffirmLevel(rs.getInt("Affirm_level"));
			affirm.setApplyNo(rs.getInt("Apply_no"));
			affirm.setEssAffirmNo(rs.getInt("Ess_affirm_no"));
			affirm.setAffirmorId(StringUtil.checkNull(rs
					.getString("Affirmor_id")));
			affirm.setAffirmorName(StringUtil.checkNull(rs
					.getString("Affirmor_name")));
			affirm.setApplyType(StringUtil
					.checkNull(rs.getString("Apply_type")));
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		}
		return affirm;
	}
}
