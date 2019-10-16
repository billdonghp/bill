package com.ait.evs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ait.sy.bean.AdminBean;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EvsCraft implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;
	
	private String evscodeid;
	private String evscodename;
	private String evsparentcode;
	private String evsparentcode2;
	private String evsdepth;  
	private String cpnyid;
	private String activity;
	private String cpnyname;
	private String empid;
	private String createtime;
	private String uempid;
	private String updatetime;
	private String descriptio;
	private String flag;
	private String message;// 返回值说明操作状态
	private String evsencodename;
	private String status;
	private String depth;
	 
 
	public EvsCraft() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsCraft(String evscodeid,String code_name,String evsencodename,String cpnyid) {
		 this.evsencodename = evsencodename;
		 this.evscodeid = evscodeid;
		 this.evscodename = code_name;
		 this.cpnyid = cpnyid;
		 this.message = "操作成功";
		 
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EvsCraft( String evscodeid,String code_name, 
			String evsparentcode,String evsencodename,String evsdepth,String cpnyid,String cpnyname,String empid,String createtime,String flag) {
		 this.evsencodename = evsencodename; 
		 this.evscodeid = evscodeid;
		 this.evscodename = code_name;
		 this.evsparentcode = evsparentcode;
		 this.evsdepth = evsdepth; 
		 this.cpnyid = cpnyid;
		 this.empid = empid;
		 this.createtime = createtime;
		 this.flag = flag;
		 this.message = "操作成功";
		 
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 

 
	public EvsCraft(String evscodeid) {
		this.evscodeid = evscodeid;

		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 取得工种列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSCODE_LIST = " SELECT  t.code_id as code_name  FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG = 'CT' AND T.PARENT_CODE = 'CRAFT'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	/**
	 * 取得工序技能等级列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvsgxjndjList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSCODE_LIST = " SELECT  t.dengji as code_name  FROM EVS_GXJNDJ T WHERE t.status!='0' and T.CODE_FLAG = 'JC' ";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	/**
	 * 取得职业资格等级列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvszyzgdjList(String craft) throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 String GET_EVSCODE_LIST = " SELECT  t.dengji as code_name  FROM EVS_GXJNDJ T WHERE t.status!='0' and T.CODE_FLAG = 'CT' ";

		try {
			if(craft!=null)
				GET_EVSCODE_LIST = GET_EVSCODE_LIST + " and code_id ='"+craft+"' ";
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	/**
	 * 取得作业类型列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvsTypeOpacodeList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSCODE_LIST = " SELECT  t.code_name  FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG = 'TO'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	/**
	 * 取得工种列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeList2() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSCODE_LIST = " SELECT  t.code_name  as code_name FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG = 'CT' AND T.CODE_ID = 'CRAFT'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	/**
	 * 取得工种列表 在Line添加页面
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeList3() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSCODE_LIST = " SELECT  t.code_id as code_id,t.code_name  as code_name FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG = 'CT'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSCODE_LIST);
			rs = pstmt.executeQuery();
			  ResultSetMetaData md = rs.getMetaData(); 
			   int columnCount = md.getColumnCount(); 
			while (rs.next()) {
				 Map map = new HashMap(); 
				    for (int i = 1; i <= columnCount; i++) { 
				     map.put(md.getColumnName(i), rs.getObject(i)); 
				lEvcodeid.add(map); 
			   }
			}
			return lEvcodeid; 
          /*
          
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
				lEvcodeid.add(rs.getString("code_id")); 
				Map    map   =   new   HashMap(); 
	               map.put("list", lEvcodeid);
	            lEvcodeid = (List)map.get("list");
			}
			return lEvcodeid;
			lEvcodeid.add(rs.getString("code_id")); 
			lEvcodeid.add(rs.getString("code_name")); 
               Map    map   =   new   HashMap(); 
               map.put("list", lEvcodeid);
            lEvcodeid = (List)map.get("list");
            
           */
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	
	
	/**
	 * 在更新页面取得相应的工种设置数据
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public void getEvsCraftByID() throws DataAccessException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "select t.code_id,t.code_name," 
						  + "t.parent_code,t.depth,t.create_date," 
			              + "t.created_by,t.activity,nvl(t.code_en_name,'en') as code_en_name," 
			              + "t.cpny_id,t.code_flag " 
			              + "from evs_code t WHERE t.status!='0' and T.CODE_ID='"+ this.evscodeid + "' ";
		//String ORDER_SQL = " ORDER BY ev_year,ev_period_id";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.setEvscodeid(rs.getString("code_id"));
			    this.setEvscodename(rs.getString("code_name"));
			    this.setEvsencodename(rs.getString("CODE_EN_NAME"));
			    this.setCpnyid(rs.getString("cpny_id"));
				//this.setEvYear(rs.getString("ev_year"));
				//this.setlEvTypes(this.getEvTypeByEvPeriodId(this.evPeriodID));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsPeriodByID statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getEvsPeriodByID statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}

	}
	
	/**
	 *  删除设置  打状态
	 * @throws DataAccessException
	 */
	public void deleteEvsCraftById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE evs_code t  set t.status ='0' ,t.DETELETD_BY = ? ,delete_date =  sysdate WHERE t.code_id=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			pstmt.setString(++i, this.uempid);
			pstmt.setString(++i, this.evscodeid);
			pstmt.executeUpdate();

			con.commit();
			//this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	/**
	 *  更新机种设置
	 * @throws DataAccessException
	 */
	public void updateEvsCraftById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE evs_code t  set t.code_name = ?,t.code_en_name = ? ,"
			+"t.updated_by = ? ,CPNY_ID = ? ,update_date =  sysdate WHERE t.code_id=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			pstmt.setString(++i, this.evscodename);
			pstmt.setString(++i, this.evsencodename);
			pstmt.setString(++i, this.uempid);
			pstmt.setString(++i, this.cpnyid);
			pstmt.setString(++i, this.evscodeid);
			pstmt.executeUpdate();

			con.commit();
			//this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	public void updateEvsCraftlineById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE evs_code t  set t.code_name = ?,t.code_en_name = ? ,t.parent_code = ?,"
			+"t.updated_by = ? ,CPNY_ID = ? ,update_date =  sysdate WHERE t.code_id=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			pstmt.setString(++i, this.evscodename);
			pstmt.setString(++i, this.evsencodename);
			pstmt.setString(++i, this.evsparentcode);
			pstmt.setString(++i, this.uempid);
			pstmt.setString(++i, this.cpnyid);
			pstmt.setString(++i, this.evscodeid);
			pstmt.executeUpdate();

			con.commit();
			//this.addEvsPeriodType();

		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for updateEvTypeByCode statistics",
					sqle);
		} catch (ServiceLocatorException sle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for updateEvTypeByCode statistics",
					sle);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	
	/**
	 *  删除机种设置
	 * @throws DataAccessException
	 */
	public String batchDelAll() throws DataAccessException {
		Connection con = null;
		try {
			con = services.getConnection();
			con.setAutoCommit(false);
				this.delEvsCraft(con);
			 
			con.commit();
		} catch (DataAccessException e1) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
		} catch (SQLException sqle) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchDel");
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			this.message = "操作失败";
			throw new DataAccessException(
					"cant get connection for adding batchDel");
		} finally {
			SqlUtil.close(con);
		}
		return message;

	}
	
	/**
	 * 删除机种
	 * 
	 * @param con
	 * @throws DataAccessException
	 */
	private final static String DEL_EV_PERIOD = " DELETE evs_code t WHERE t.code_id=?";
	
	private void delEvsCraft(Connection con) throws DataAccessException {
		PreparedStatement pstmt = null;
	
		try {
			pstmt = con.prepareStatement(DEL_EV_PERIOD);
			pstmt.setString(1, this.evscodeid);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant  delEvPeriod exception; ", ex);

		} finally {
			SqlUtil.close(null, pstmt);
		}

	}
	
	/**
	 * 根据机种parentID取得相应evs_codeID——名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsParCodename(String code_name) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT ev.*,cpny.code_name cpnyname FROM EVS_CODE ev,SY_CODE cpny "
			+"WHERE  EV.STATUS !='0' AND ev.cpny_id = cpny.code_id(+) and ev.CODE_FLAG = 'CT' AND ev.PARENT_CODE = 'CRAFT'";
		//String ORDER_SQL = " ORDER BY ev_year,ev_period_id";
		if (!code_name.trim().equals("")) {
			SELECT_SQL = SELECT_SQL + "  and ev.code_name='" + code_name + "'";
		}
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsCraft(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	/**
	 * 根据权限类型名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsRightCodename(String code_name) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "SELECT ev.code_no,ev.cpny_id,ev.created_by,ev.CREATE_DATE,ev.descriptio,ev.UPDATE_DATE,ev.UPDATED_BY,ev.code_id,ev.code_name,cpny.code_name cpnyname,hr.chinesename CODE_EN_NAME,hr.empid "
			+"FROM EVS_CODE ev,hr_employee hr,SY_CODE cpny "
			+"WHERE  EV.STATUS !='0' AND ev.cpny_id = cpny.code_id(+) and ev.CODE_FLAG = 'RS' "
			+"and ev.parent_code = hr.person_id ";
		//String ORDER_SQL = " ORDER BY ev_year,ev_period_id";
		if (!code_name.trim().equals("")) {
			SELECT_SQL = SELECT_SQL + "  and ev.depth='" + code_name + "'";
		}
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsRSCraft(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	
	/**
	 * 
	 * 添加工种期间
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public void addEvsCraft() throws SQLException, DataAccessException {
        AdminBean admin  = new AdminBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		final String ADD_EV_PERIOD = 
			" INSERT INTO EVS_CODE(CODE_NO,code_id,code_name,parent_code,depth,create_date,created_by,activity,code_en_name,cpny_id,code_flag) "
		  + " VALUES(EVS_CODE_SEQ.NEXTVAL,?,?,?,?,sysdate,?,?,?,?,?)";
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			int i=0;
			pstmt = con.prepareStatement(ADD_EV_PERIOD);
		  	pstmt.setString(++i, this.evscodeid);
		  	pstmt.setString(++i, this.evscodename);
		  	pstmt.setString(++i,"CRAFT"); 
		  	pstmt.setString(++i, "0");
		  	pstmt.setString(++i, admin.getAdminID());
		  	pstmt.setString(++i,"1"); 
		  	pstmt.setString(++i, this.evsencodename);
		  	pstmt.setString(++i, this.cpnyid);
		  	pstmt.setString(++i,"CT"); 
			pstmt.executeUpdate();
			
			con.commit();

		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			ex.printStackTrace();
			throw new DataAccessException("cant add addPeriod exception; ", ex);
		} catch (ServiceLocatorException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
			throw new DataAccessException(
					"cant get connection for adding addPeriod");
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}
	
	
	/**
	 * 
	 * 查询现有的 工种设置
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
		public List SelectEvsCraft() throws DataAccessException,
				ServiceLocatorException {
			List lEvscode = new Vector();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			final String GET_EVSCODE_LIST = "select t.code_id from evs_code t where t.code_flag = 'CT'";

			try {
				con = services.getConnection();
				pstmt = con.prepareStatement(GET_EVSCODE_LIST);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lEvscode.add(rs.getString("code_id"));
					/*lEvscode.add(rs.getString("code_name"));
					lEvscode.add(rs.getString("parent_code"));
					lEvscode.add(rs.getString("created_by"));
					lEvscode.add(rs.getString("cpny_id"));
					lEvscode.add(rs.getString("code_flag"));*/
				}
				return lEvscode;

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new DataAccessException(
						"cant execute query for getEvsYearList statistics", sqle);
			} finally {
				SqlUtil.close(rs, pstmt, con);
			}
		}
	
	/**
	 * 取得 Line 列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeLineList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSLINECODE_LIST = " SELECT distinct t.code_id FROM EVS_CODE T WHERE T.CODE_FLAG  = 'LE'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSLINECODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	public List getEvscodeLineListb(String craft) throws DataAccessException,
	ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 String GET_EVSLINECODE_LIST = " SELECT  t.code_id as code_name FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG  = 'LE'";
		
		try {
			if(craft!=null)
				GET_EVSLINECODE_LIST = GET_EVSLINECODE_LIST +" and t.parent_code ='"+craft+"' ";
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSLINECODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
}
	public List getEvscodeJcListb(String line) throws DataAccessException,
	ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 String GET_EVSLINECODE_LIST = " SELECT  t.code_id as code_name FROM EVS_CODE T WHERE t.status!='0' and T.CODE_FLAG  = 'JC'";
		
		try {
			if(line!=null)
				GET_EVSLINECODE_LIST = GET_EVSLINECODE_LIST +" and t.parent_code ='"+line+"' ";
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSLINECODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
}

	/**
	 * 取得 技能类型 列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeStypeList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSLINECODE_LIST = " SELECT distinct t.code_name FROM EVS_CODE T WHERE T.CODE_FLAG  = 'ST'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSLINECODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	
	/**
	 * 取得机种 列表
	 * 
	 * @return
	 * @throws DataAccessException
	 * @throws ServiceLocatorException
	 */
	public List getEvscodeAircraftList() throws DataAccessException,
			ServiceLocatorException {
		List lEvcodeid = new Vector();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String GET_EVSLINECODE_LIST = " SELECT distinct t.code_name FROM EVS_CODE T WHERE T.CODE_FLAG  = 'AF'";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(GET_EVSLINECODE_LIST);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lEvcodeid.add(rs.getString("code_name"));
			}
			return lEvcodeid;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getEvsYearList statistics", sqle);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
	}
	
	
	/**
	 * 根据机种descriptio取得相应evs_codeID——名称 对应的Line线
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsLineCodename(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.code_name as parentcode ,e.*," 
						  + " E.CODE_NAME as code_name,CPNY.CODE_NAME CPNYNAME " 
					      + " FROM EVS_CODE T, evs_code E, EVS_CODE CPNY  WHERE E.STATUS!='0' AND E.parent_code = T.CODE_ID " 
					      + " AND E.CODE_FLAG = 'LE' AND E.CPNY_ID = CPNY.CODE_ID(+) ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(code_name.equals("") || code_name == null)
			{
				SELECT_SQL = SELECT_SQL + "  and E.code_name='" + code_name1 + "'";
			}else
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_name='" + code_name + "'";
			}
		}
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsCraft2(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	
	/**
	 * 根据机种descriptio取得相应evs_codeID——名称 对应的技能类型名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsStypeCodename(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.code_name as parentcode ,e.*," 
						  + " E.CODE_NAME as code_name,CPNY.CODE_NAME CPNYNAME " 
					      + " FROM EVS_CODE T, evs_code E, SY_CODE CPNY  WHERE E.parent_code = T.CODE_ID " 
					      + " AND E.CODE_FLAG = 'ST' AND E.CPNY_ID = CPNY.CODE_ID(+) ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(code_name.equals("") || code_name == null)
			{
				SELECT_SQL = SELECT_SQL + "  and E.code_name='" + code_name1 + "'";
			}else
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_name='" + code_name + "'";
			}
		}
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsCraft2(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	
	
	/**
	 * 根据机种descriptio取得相应evs_codeID——名称 对应的技能类型名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsTypeOpaCodename(String code_name) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT e.*, E.CODE_NAME as code_name, " +
					        " CPNY.CODE_NAME CPNYNAME FROM evs_code E, EVS_CODE CPNY " +
				            " WHERE E.CODE_FLAG = 'TO' AND E.CPNY_ID = CPNY.CODE_ID(+)";
		if ( !code_name.trim().equals("")) {
			 
				SELECT_SQL = SELECT_SQL + "  and E.code_name='" + code_name + "'";
			} 
		
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsCraft(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	
	
	
	/**
	 * 根据机种descriptio取得相应evs_codeID——名称 对应的Line线
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsAircraftCodename(String code_name1,String code_name2,String code_name3) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT R1.CODE_NAME AS PARENTCODEC, " 
						  + " t2.code_name as parentcodel, e3.*, " 
			              + " E3.CODE_NAME as code_name, CPNY.CODE_NAME CPNYNAME " 
			              + " FROM EVS_CODE T2, evs_code E3, EVS_CODE CPNY, EVS_CODE R1  " 
			              + " WHERE E3.parent_code = T2.CODE_ID AND T2.PARENT_CODE = R1.CODE_ID " 
			              + " AND E3.CODE_FLAG = 'AF'  AND E3.CPNY_ID = CPNY.CODE_ID(+)";
		if (!code_name1.trim().equals("") || !code_name2.trim().equals("") || !code_name3.trim().equals("")) {
			if((code_name2.equals("") || code_name2 == null) & ( code_name3.equals("") || code_name3 == null ))
			{
				SELECT_SQL = SELECT_SQL + "  and R1.code_name='" + code_name1 + "'";
			}else if((code_name1.equals("") || code_name1 == null )& (code_name3.equals("") || code_name3 == null ))
			{
				SELECT_SQL = SELECT_SQL + "  and T2.code_name='" + code_name2 + "'";
			}
			else
			{
				SELECT_SQL = SELECT_SQL + "  and E3.code_name='" + code_name3 + "'";
			}
				
		}
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsCraft3(rs));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new DataAccessException(
					"cant execute query for getPeriodByYear statistics", sqle);
		} catch (ServiceLocatorException sle) {
			throw new DataAccessException(
					"cant get connection for getPeriodByYear statistics", sle);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return levsPeriod;
	}
	
	
	/**
	 * 初始化
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public EvsCraft createEvsCraft(ResultSet rs) throws DataAccessException {
		EvsCraft levsPeriod = new EvsCraft();
		try {

			levsPeriod.setEvscodeid((rs.getString("code_id") != null) ? rs
					.getString("code_id") : "");
			levsPeriod.setEvscodename((rs.getString("code_name") != null) ? rs
							.getString("code_name")
							: "");
			levsPeriod.setCpnyid((rs.getString("cpny_id") != null) ? rs
					.getString("cpny_id")
					: "");
			levsPeriod.setEvsencodename((rs.getString("code_en_name") != null) ? rs
					.getString("code_en_name")
					: "");
			levsPeriod.setCreatetime((rs.getString("create_date") != null) ? rs
					.getString("create_date")
					: "");
			levsPeriod.setEmpid((rs.getString("created_by") != null) ? rs
					.getString("created_by")
					: "");
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs
					.getString("update_date")
					: "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs
					.getString("updated_by")
					: "");
			levsPeriod.setCpnyname((rs.getString("cpnyname") != null) ? rs
					.getString("cpnyname")
					: "");
			levsPeriod.setDescriptio((rs.getString("descriptio") != null) ? rs
					.getString("descriptio")
					: "");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}
	
	/**
	 * 初始化
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public EvsCraft createEvsRSCraft(ResultSet rs) throws DataAccessException {
		EvsCraft levsPeriod = new EvsCraft();
		try {

			levsPeriod.setEvscodeid((rs.getString("code_id") != null) ? rs
					.getString("code_id") : "");
			levsPeriod.setEvscodename((rs.getString("code_name") != null) ? rs
							.getString("code_name")
							: "");
			levsPeriod.setCpnyid((rs.getString("cpny_id") != null) ? rs
					.getString("cpny_id")
					: "");
			levsPeriod.setEvsencodename((rs.getString("code_en_name") != null) ? rs
					.getString("code_en_name")
					: "");
			levsPeriod.setCreatetime((rs.getString("create_date") != null) ? rs
					.getString("create_date")
					: "");
			levsPeriod.setEmpid((rs.getString("created_by") != null) ? rs
					.getString("created_by")
					: "");
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs
					.getString("update_date")
					: "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs
					.getString("updated_by")
					: "");
			levsPeriod.setCpnyname((rs.getString("cpnyname") != null) ? rs
					.getString("cpnyname")
					: "");
			levsPeriod.setDescriptio((rs.getString("descriptio") != null) ? rs
					.getString("descriptio")
					: "");
			levsPeriod.setEvsparentcode((rs.getString("empid") != null) ? rs
					.getString("empid")
					: "");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}
	
	/**
	 * 初始化2
	 * 
	 * @param year
	 * @returnlevsPeriod.setEvscodename((rs.getString("code_name") != null) ? rs
							.getString("code_name")
							: "");
	 * @throws DataAccessException
	 */
	public EvsCraft createEvsCraft2(ResultSet rs) throws DataAccessException {
		EvsCraft levsPeriod = new EvsCraft();
		try {

			levsPeriod.setEvscodeid((rs.getString("code_id") != null) ? rs
					.getString("code_id") : "");
			levsPeriod.setEvscodename((rs.getString("code_name") != null) ? rs
							.getString("code_name")
							: "");
			levsPeriod.setEvsparentcode((rs.getString("parentcode") != null) ? rs
					.getString("parentcode")
					: "");
			levsPeriod.setCpnyid((rs.getString("cpny_id") != null) ? rs
					.getString("cpny_id")
					: "");
			levsPeriod.setEvsencodename((rs.getString("code_en_name") != null) ? rs
					.getString("code_en_name")
					: "");
			levsPeriod.setCreatetime((rs.getString("create_date") != null) ? rs
					.getString("create_date")
					: "");
			levsPeriod.setEmpid((rs.getString("created_by") != null) ? rs
					.getString("created_by")
					: "");
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs
					.getString("update_date")
					: "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs
					.getString("updated_by")
					: "");
			levsPeriod.setCpnyname((rs.getString("cpnyname") != null) ? rs
					.getString("cpnyname")
					: "");
			levsPeriod.setStatus((rs.getString("status") != null) ? rs
					.getString("status")
					: "");

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}
	
	/**
	 * 初始化3
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public EvsCraft createEvsCraft3(ResultSet rs) throws DataAccessException {
		EvsCraft levsPeriod = new EvsCraft();
		try {
			
			levsPeriod.setEvscodeid((rs.getString("code_id") != null) ? rs
					.getString("code_id") : "");
			levsPeriod.setEvsparentcode2((rs.getString("PARENTCODEC") != null) ? rs
					.getString("PARENTCODEC")
					: "");
			levsPeriod.setEvscodename((rs.getString("code_name") != null) ? rs
							.getString("code_name")
							: "");
			levsPeriod.setEvsparentcode((rs.getString("parentcodel") != null) ? rs
					.getString("parentcodel")
					: "");
			levsPeriod.setCpnyid((rs.getString("cpny_id") != null) ? rs
					.getString("cpny_id")
					: "");
			levsPeriod.setEvsencodename((rs.getString("code_en_name") != null) ? rs
					.getString("code_en_name")
					: "");
			levsPeriod.setCreatetime((rs.getString("create_date") != null) ? rs
					.getString("create_date")
					: "");
			levsPeriod.setEmpid((rs.getString("created_by") != null) ? rs
					.getString("created_by")
					: "");
			levsPeriod.setUpdatetime((rs.getString("update_date") != null) ? rs
					.getString("update_date")
					: "");
			levsPeriod.setUempid((rs.getString("updated_by") != null) ? rs
					.getString("updated_by")
					: "");
			levsPeriod.setCpnyname((rs.getString("cpnyname") != null) ? rs
					.getString("cpnyname")
					: "");
			

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DataAccessException("cant execute createEvEmp ", ex);
		}
		return levsPeriod;
	}
	
	
	public String getEvscodeid() {
		return evscodeid;
	}


	public void setEvscodeid(String evscodeid) {
		this.evscodeid = evscodeid;
	}


	public String getEvscodename() {
		return evscodename;
	}


	public void setEvscodename(String evscodename) {
		this.evscodename = evscodename;
	}


	public String getEvsparentcode() {
		return evsparentcode;
	}


	public void setEvsparentcode(String evsparentcode) {
		this.evsparentcode = evsparentcode;
	}


	public String getEvsdepth() {
		return evsdepth;
	}


	public void setEvsdepth(String evsdepth) {
		this.evsdepth = evsdepth;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getEvsencodename() {
		return evsencodename;
	}

	public void setEvsencodename(String evsencodename) {
		this.evsencodename = evsencodename;
	}

	public String getCpnyid() {
		return cpnyid;
	}


	public void setCpnyid(String cpnyid) {
		this.cpnyid = cpnyid;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCpnyname() {
		return cpnyname;
	}

	public void setCpnyname(String cpnyname) {
		this.cpnyname = cpnyname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescriptio() {
		return descriptio;
	}

	public void setDescriptio(String descriptio) {
		this.descriptio = descriptio;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getEvsparentcode2() {
		return evsparentcode2;
	}

	public void setEvsparentcode2(String evsparentcode2) {
		this.evsparentcode2 = evsparentcode2;
	}

	public String getUempid() {
		return uempid;
	}

	public void setUempid(String uempid) {
		this.uempid = uempid;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	 
	
	
}