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

import com.ait.ar.bean.ArDetailBack;
import com.ait.evs.business.EvsServices;
import com.ait.sqlmap.util.SimpleMap;
import com.ait.sy.bean.AdminBean;
import com.ait.sysparam.EssSysparam;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;

public class EvsGxjndj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ServiceLocator services;
	
	private EssSysparam essSysparam = null;
	
	public int codeNo = 0;
	public int getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(int codeNo) {
		this.codeNo = codeNo;
	}
	private Integer pkNo;
	
	private Integer pkNo1;
	private String evscodeid;
	private String djjfto;
	private String djjffrom;	
	private String dengji;  
	private String djjfto1;
	private String djjffrom1;	
	private String dengji1;  
	private String cpnyid;
	private String activity;
	private String cpnyname;
	private String empid;
	private String createtime;
	private String uempid;
	private String updatetime;
	private String descriptio;
	private String descriptio1;
	private String flag;
	private String message;// 返回值说明操作状态
	private String evsencodename;
	private String status;
	private String codeflag;
	
    private String deptId;
	
	private String remark;
	
	private String remark1;
	
	private Integer affirmLevel;
	
	private Integer affirm_flag;
	
	private Integer up_flag;
	
	private Integer next_flag;
	
	private Integer maxLevel_flag;
	
	private Integer max_affirm_flag;
	
	private String chineseName;
	
    private Integer opFlag = -1; // 可操作状态 -1 不可操作; 0 可通过/否决; 1 可通过; 2 可否决
	
	private ArrayList AffirmorList; // 决裁者列表

	private Integer affirmorCount; // 决裁者数量
 
	public EvsGxjndj() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EvsGxjndj(String evscodeid,String djjffrom,String evsencodename,String cpnyid) {
		 this.evsencodename = evsencodename;
		 this.evscodeid = evscodeid;
		 this.djjffrom = djjffrom;
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
	
	public EvsGxjndj( String evscodeid,String djjffrom, 
			String djjfto,String dengji,String cpnyid,String cpnyname,String empid,String createtime,String flag) {
		 this.evscodeid = evscodeid;
		 this.djjffrom = djjffrom;
		 this.djjfto = djjfto;
		 this.dengji = dengji; 
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
	 

 
	public EvsGxjndj(String evscodeid) {
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
	 * 在更新页面取得相应的工种设置数据
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public void getEvsGxjndjByID() throws DataAccessException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = "select t.* " 
			              + "from EVS_GXJNDJ t WHERE t.status!='0' and T.CODE_NO='"+ this.codeNo + "' ";
		//String ORDER_SQL = " ORDER BY ev_year,ev_period_id";

		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				this.setCodeNo(rs.getInt("code_NO"));
			    this.setDjjffrom(rs.getString("Djjffrom"));
			    this.setDjjfto(rs.getString("djjfto"));
			    this.setDengji(rs.getString("dengji"));
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
	public void deleteEvsGxjndjById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE EVS_GXJNDJ t  set t.status ='0' ,t.DETELETD_BY = ? ,delete_date =  sysdate WHERE t.code_id=? ";

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
	public void updateEvsGxjndjById() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		final String UPDATE_EV_PERIOD_BY_ID = " UPDATE EVS_GXJNDJ t  set t.status = '2' , t.DJJFFROM = ? ,t.DJJFTO = ? ,"
			+"t.updated_by = ? ,update_date =  sysdate WHERE t.code_no=? ";

		try {
			con = services.getConnection();
			con.setAutoCommit(false);
			int i=0;
			pstmt = con.prepareStatement(UPDATE_EV_PERIOD_BY_ID);
			
			
			pstmt.setString(++i, this.djjffrom);
			pstmt.setString(++i, this.djjfto);
			pstmt.setString(++i, this.uempid);
			pstmt.setInt(++i, this.codeNo);
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
				this.delEvsGxjndj(con);
			 
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
	private final static String DEL_EV_PERIOD = " DELETE EVS_GXJNDJ t WHERE t.code_id=?";
	
	private void delEvsGxjndj(Connection con) throws DataAccessException {
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
	 * 
	 * 添加工种期间
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public void addEvsGxjndj() throws SQLException, DataAccessException {
        AdminBean admin  = new AdminBean();
		Connection con = null;
		PreparedStatement pstmtb = null;
		PreparedStatement pstmt = null;
		String ADD_EV_PERIODb = 
			" update EVS_GXJNDJ_BACK set activity ='2' where flag is not null and code_no =? ";
		final String ADD_EV_PERIOD = 
			" INSERT INTO EVS_GXJNDJ_BACK(PK_NO,CODE_NO,code_id,DJJFFROM,DJJFTO,DENGJI,create_date,created_by,activity,cpny_id,code_flag) "
		  + " VALUES(EVS_GXJNDJ_BACK_SEQ.NEXTVAL,?,?,?,?,?,sysdate,?,?,?,?)";
		try {
			con = services.getConnection();
			con.setAutoCommit(false);

			pstmtb = con.prepareStatement(ADD_EV_PERIODb);
			pstmtb.setInt(1, this.codeNo);
			pstmtb.executeUpdate();
			
			int i=0;
			pstmt = con.prepareStatement(ADD_EV_PERIOD);
			pstmt.setInt(++i, this.codeNo);
		  	pstmt.setString(++i, this.evscodeid);
		  	pstmt.setString(++i, this.djjffrom1);
		  	pstmt.setString(++i, this.djjfto1);
		  	pstmt.setString(++i, this.dengji);
		  	pstmt.setString(++i, this.uempid);
		  	pstmt.setString(++i,"1"); 
		  	pstmt.setString(++i, this.cpnyid);
		  	pstmt.setString(++i,"UPDATE"); 
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
	 * 根据工种取得相应职业资格等级名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsDJCodename(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL = " SELECT  t.CODE_NO,to_char(t.djjffrom, 'FM990.0') djjffrom,to_char(DJJFTO, 'FM990.0') DJJFTO,"
						  + " t.DENGJI,t.CREATE_DATE,t.CREATED_BY,t.UPDATE_DATE,t.UPDATED_BY,t.ACTIVITY,t.CODE_FLAG," 
						  + " t.CPNY_ID,t.DELETE_DATE,t.DETELETD_BY,t.STATUS,t.CODE_ID,t.FLAG," 
						  + " CPNY.CODE_NAME CPNYNAME " 
					      + " FROM EVS_GXJNDJ T, SY_CODE CPNY  WHERE status!='0' and CODE_FLAG ='CT' AND " 
					      + "  t.CPNY_ID = CPNY.CODE_ID(+) ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(!code_name1.equals("") && code_name1 != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.DENGJI ='" + code_name1 + "'";
			}
			if(!code_name.equals("") && code_name != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_ID='" + code_name + "'";
			}
		}
		SELECT_SQL = SELECT_SQL +"order by t.code_id,t.DJJFFROM DESC ";
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsGxjndj(rs));
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
	 * 取得工序技能等级名称
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsGXDJCodename(String code_name,String code_name1) throws DataAccessException {
		List levsPeriod = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String SELECT_SQL =  " SELECT  t.CODE_NO,to_char(t.djjffrom, 'FM990.0') djjffrom,to_char(DJJFTO, 'FM990.0') DJJFTO,"
						  + " t.DENGJI,t.CREATE_DATE,t.CREATED_BY,t.UPDATE_DATE,t.UPDATED_BY,t.ACTIVITY,t.CODE_FLAG," 
						  + " t.CPNY_ID,t.DELETE_DATE,t.DETELETD_BY,t.STATUS,t.CODE_ID,t.FLAG," 
						  + " CPNY.CODE_NAME CPNYNAME " 
					      + " FROM EVS_GXJNDJ T, SY_CODE CPNY  WHERE status!='0' and CODE_FLAG ='JC' AND  " 
					      + "  t.CPNY_ID = CPNY.CODE_ID(+) ";
		if (!code_name1.trim().equals("") || !code_name.trim().equals("")) {
			if(!code_name1.equals("") && code_name1 != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.DENGJI ='" + code_name1 + "'";
			}
			if(!code_name.equals("") && code_name != null)
			{
				SELECT_SQL = SELECT_SQL + "  and T.code_ID='" + code_name + "'";
			}
		}
		SELECT_SQL = SELECT_SQL+" order by t.djjffrom ";
		System.out.println(SELECT_SQL);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(SELECT_SQL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				levsPeriod.add(this.createEvsGxjndj(rs));
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
	 * 根据工种取得相应职业资格等级修改列表
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsDJCodenameconfirm(SimpleMap parameterObject) throws DataAccessException {
		EvsServices evsservices = EvsServices.getInstance();
		
		List zyzgdjModifyList = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		 AdminBean admin  = new AdminBean();
		String adminId = admin.getAdminID();
		String cpnyId = admin.getCpnyId();
		
		try {
			
			
			 zyzgdjModifyList = evsservices.retrieveZyzgdjModifyAffirmList(parameterObject);
			 for (int i = 0; i < zyzgdjModifyList.size(); i++) {
				 EvsGxjndj arModify = (EvsGxjndj) zyzgdjModifyList.get(i);
					//parameterObject.setInt("applyNo", leave.getLeaveNo());
					///parameterObject.setInt("level", leave.getAffirmLevel());
					
					// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
					// ;
					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if ((arModify.getNext_flag() == 0 && arModify.getUp_flag() == 1 
					//		&& arModify.getActivity() != 2
							)|| arModify.getMaxLevel_flag() == 1) {
						// 决裁状态为"待决裁"
						if (arModify.getAffirm_flag() == 0)
							// 可进行"通过"和"否决"
							arModify.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
					//	else if (arModify.getAffirm_flag() == 1)
							// 可进行"否决"
						//	arModify.setOpFlag(1);
						// 决裁后可进行修改并且状态为"否决"
					//	else if ( arModify.getAffirm_flag() == 2)
							// 可进行"通过"
						//	arModify.setOpFlag(1);
					}
					arModify.setAffirmorList((ArrayList) evsservices.getZyzgdjModifyAffirmorList(arModify.getPkNo1()));
					
				}
			//con = services.getConnection();
			//pstmt = con.prepareStatement(SELECT_SQL);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return zyzgdjModifyList;
	}	
	/**
	 * 根据评价成绩列表
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsSetupCodeconfirm(SimpleMap parameterObject) throws DataAccessException {
		EvsServices evsservices = EvsServices.getInstance();
		
		List ModifyList = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		 AdminBean admin  = new AdminBean();
		String adminId = admin.getAdminID();
		String cpnyId = admin.getCpnyId();
		
		try {
			
			
			ModifyList = evsservices.retrieveSetupcodeAffirmList(parameterObject);
			 for (int i = 0; i < ModifyList.size(); i++) {
				 Evsupcode arModify = (Evsupcode) ModifyList.get(i);
					//parameterObject.setInt("applyNo", leave.getLeaveNo());
					///parameterObject.setInt("level", leave.getAffirmLevel());
					
					// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
					// ;
					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if ((arModify.getNext_flag() == 0 && arModify.getUp_flag() == 1 
					//		&& arModify.getActivity() != 2
							)|| arModify.getMaxLevel_flag() == 1) {
						// 决裁状态为"待决裁"
						if (arModify.getAffirm_flag() == 0)
							// 可进行"通过"和"否决"
							arModify.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
					//	else if (arModify.getAffirm_flag() == 1)
							// 可进行"否决"
						//	arModify.setOpFlag(1);
						// 决裁后可进行修改并且状态为"否决"
					//	else if ( arModify.getAffirm_flag() == 2)
							// 可进行"通过"
						//	arModify.setOpFlag(1);
					}
					arModify.setAffirmorList((ArrayList) evsservices.getZyzgdjModifyAffirmorList(arModify.getPkNo1()));
					
				}
			//con = services.getConnection();
			//pstmt = con.prepareStatement(SELECT_SQL);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return ModifyList;
	}	
	/**
	 * 根据工匠技师决裁列表
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsGjjsconfirm(SimpleMap parameterObject) throws DataAccessException {
		EvsServices evsservices = EvsServices.getInstance();
		
		List ModifyList = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		 AdminBean admin  = new AdminBean();
		String adminId = admin.getAdminID();
		String cpnyId = admin.getCpnyId();
		
		try {
			
			
			ModifyList = evsservices.retrieveGjjsAffirmList(parameterObject);
			 for (int i = 0; i < ModifyList.size(); i++) {
				 Gxjsdj arModify = (Gxjsdj) ModifyList.get(i);
					//parameterObject.setInt("applyNo", leave.getLeaveNo());
					///parameterObject.setInt("level", leave.getAffirmLevel());
					
					// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
					// ;
					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if ((arModify.getNext_flag() == 0 && arModify.getUp_flag() == 1 
					//		&& arModify.getActivity() != 2
							)|| arModify.getMaxLevel_flag() == 1) {
						// 决裁状态为"待决裁"
						if (arModify.getAffirm_flag() == 0)
							// 可进行"通过"和"否决"
							arModify.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
					//	else if (arModify.getAffirm_flag() == 1)
							// 可进行"否决"
						//	arModify.setOpFlag(1);
						// 决裁后可进行修改并且状态为"否决"
					//	else if ( arModify.getAffirm_flag() == 2)
							// 可进行"通过"
						//	arModify.setOpFlag(1);
					}
					arModify.setAffirmorList((ArrayList) evsservices.getZyzgdjModifyAffirmorList(arModify.getPkNo1()));
					
				}
			//con = services.getConnection();
			//pstmt = con.prepareStatement(SELECT_SQL);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return ModifyList;
	}	
	/**
	 * 根据工序修改列表
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public List getEvsGXCodenameconfirm(SimpleMap parameterObject) throws DataAccessException {
		EvsServices evsservices = EvsServices.getInstance();
		
		List zyzgdjModifyList = new Vector();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		 AdminBean admin  = new AdminBean();
		String adminId = admin.getAdminID();
		String cpnyId = admin.getCpnyId();
		
		try {
			
			
			 zyzgdjModifyList = evsservices.retrieveGxModifyAffirmList(parameterObject);
			 for (int i = 0; i < zyzgdjModifyList.size(); i++) {
				 EvsGxjndj arModify = (EvsGxjndj) zyzgdjModifyList.get(i);
					//parameterObject.setInt("applyNo", leave.getLeaveNo());
					///parameterObject.setInt("level", leave.getAffirmLevel());
					
					// leave.setNext_flag(essServices.retrieveApplyAffirmNextFlag(parameterObject))
					// ;
					// 上级决裁"已通过"或者不存在上级决裁, 并且人事未否决时才能进行操作
					if ((arModify.getNext_flag() == 0 && arModify.getUp_flag() == 1 
					//		&& arModify.getActivity() != 2
							)|| arModify.getMaxLevel_flag() == 1) {
						// 决裁状态为"待决裁"
						if (arModify.getAffirm_flag() == 0)
							// 可进行"通过"和"否决"
							arModify.setOpFlag(0);
						// 决裁后可进行修改并且状态为"已通过"
					//	else if (arModify.getAffirm_flag() == 1)
							// 可进行"否决"
						//	arModify.setOpFlag(1);
						// 决裁后可进行修改并且状态为"否决"
					//	else if ( arModify.getAffirm_flag() == 2)
							// 可进行"通过"
						//	arModify.setOpFlag(1);
					}
					arModify.setAffirmorList((ArrayList) evsservices.getZyzgdjModifyAffirmorList(arModify.getPkNo1()));
					
				}
			//con = services.getConnection();
			//pstmt = con.prepareStatement(SELECT_SQL);

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return zyzgdjModifyList;
	}	
	/**
	 * 
	 * 查询现有的 职业资格等级 根据工种
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public List SelectEvsZyzgdj(String code_name) throws DataAccessException,
				ServiceLocatorException {
			List lEvsGxjndj = new Vector();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			final String GET_EvsGxjndj_LIST = "select t.dengji from EVS_GXJNDJ t where t.code_flag = 'CT' and code_id =? ";

			try {
				con = services.getConnection();
				pstmt = con.prepareStatement(GET_EvsGxjndj_LIST);
				pstmt.setString(1, code_name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lEvsGxjndj.add(rs.getString("dengji"));
					/*lEvsGxjndj.add(rs.getString("code_name"));
					lEvsGxjndj.add(rs.getString("parent_code"));
					lEvsGxjndj.add(rs.getString("created_by"));
					lEvsGxjndj.add(rs.getString("cpny_id"));
					lEvsGxjndj.add(rs.getString("code_flag"));*/
				}
				return lEvsGxjndj;

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new DataAccessException(
						"cant execute query for getEvsYearList statistics", sqle);
			} finally {
				SqlUtil.close(rs, pstmt, con);
			}
		}
	/**
	 * 
	 * 查询现有的 工序技能等级 
	 * 
	 * @throws SQLException
	 * @throws DataAccessException
	 */
	public List SelectEvsGxjndj() throws DataAccessException,
				ServiceLocatorException {
			List lEvsGxjndj = new Vector();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			final String GET_EvsGxjndj_LIST = "select t.dengji from EVS_GXJNDJ t where status!='0' and t.code_flag = 'JC'";

			try {
				con = services.getConnection();
				pstmt = con.prepareStatement(GET_EvsGxjndj_LIST);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lEvsGxjndj.add(rs.getString("dengji"));
					/*lEvsGxjndj.add(rs.getString("code_name"));
					lEvsGxjndj.add(rs.getString("parent_code"));
					lEvsGxjndj.add(rs.getString("created_by"));
					lEvsGxjndj.add(rs.getString("cpny_id"));
					lEvsGxjndj.add(rs.getString("code_flag"));*/
				}
				return lEvsGxjndj;

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw new DataAccessException(
						"cant execute query for getEvsYearList statistics", sqle);
			} finally {
				SqlUtil.close(rs, pstmt, con);
			}
		}		
	/**
	 * 初始化
	 * 
	 * @param year
	 * @return
	 * @throws DataAccessException
	 */
	public EvsGxjndj createEvsGxjndj(ResultSet rs) throws DataAccessException {
		EvsGxjndj levsPeriod = new EvsGxjndj();
		try {
			levsPeriod.setCodeNo(rs.getInt("code_no"));
			levsPeriod.setEvscodeid((rs.getString("code_id") != null) ? rs
					.getString("code_id") : "");
			levsPeriod.setDjjffrom((rs.getString("Djjffrom") != null) ? rs
							.getString("Djjffrom")
							: "");
			levsPeriod.setDjjfto((rs.getString("Djjfto") != null) ? rs
					.getString("Djjfto")
					: "");
			levsPeriod.setDengji((rs.getString("Dengji") != null) ? rs
					.getString("Dengji")
					: "");
			
			levsPeriod.setCpnyid((rs.getString("cpny_id") != null) ? rs
					.getString("cpny_id")
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
	
	
	

	public String getEvscodeid() {
		return evscodeid;
	}

	public void setEvscodeid(String evscodeid) {
		this.evscodeid = evscodeid;
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

	

	public String getDjjfto() {
		return djjfto;
	}

	public void setDjjfto(String djjfto) {
		this.djjfto = djjfto;
	}

	public String getDjjffrom() {
		return djjffrom;
	}

	public void setDjjffrom(String djjffrom) {
		this.djjffrom = djjffrom;
	}

	public String getDengji() {
		return dengji;
	}

	public void setDengji(String dengji) {
		this.dengji = dengji;
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
	public ArrayList getAffirmorList() {
		return AffirmorList;
	}

	public void setAffirmorList(ArrayList affirmorList) {
		AffirmorList = affirmorList;
	}

	public Integer getAffirmorCount() {
		return affirmorCount;
	}

	public void setAffirmorCount(Integer affirmorCount) {
		this.affirmorCount = affirmorCount;
	}
	 
	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public EssSysparam getEssSysparam() {
		return essSysparam;
	}

	public void setEssSysparam(EssSysparam essSysparam) {
		this.essSysparam = essSysparam;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public Integer getAffirmLevel() {
		return affirmLevel;
	}

	public void setAffirmLevel(Integer affirmLevel) {
		this.affirmLevel = affirmLevel;
	}

	public Integer getAffirm_flag() {
		return affirm_flag;
	}

	public void setAffirm_flag(Integer affirmFlag) {
		affirm_flag = affirmFlag;
	}

	public Integer getUp_flag() {
		return up_flag;
	}

	public void setUp_flag(Integer upFlag) {
		up_flag = upFlag;
	}

	public Integer getNext_flag() {
		return next_flag;
	}

	public void setNext_flag(Integer nextFlag) {
		next_flag = nextFlag;
	}

	public Integer getMaxLevel_flag() {
		return maxLevel_flag;
	}

	public void setMaxLevel_flag(Integer maxLevelFlag) {
		maxLevel_flag = maxLevelFlag;
	}

	public Integer getMax_affirm_flag() {
		return max_affirm_flag;
	}

	public void setMax_affirm_flag(Integer maxAffirmFlag) {
		max_affirm_flag = maxAffirmFlag;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Integer getPkNo() {
		return pkNo;
	}

	public void setPkNo(Integer pkNo) {
		this.pkNo = pkNo;
	}

	public Integer getPkNo1() {
		return pkNo1;
	}

	public void setPkNo1(Integer pkNo1) {
		this.pkNo1 = pkNo1;
	}

	public String getDjjfto1() {
		return djjfto1;
	}

	public void setDjjfto1(String djjfto1) {
		this.djjfto1 = djjfto1;
	}

	public String getDjjffrom1() {
		return djjffrom1;
	}

	public void setDjjffrom1(String djjffrom1) {
		this.djjffrom1 = djjffrom1;
	}

	public String getDengji1() {
		return dengji1;
	}

	public void setDengji1(String dengji1) {
		this.dengji1 = dengji1;
	}

	public String getDescriptio1() {
		return descriptio1;
	}

	public void setDescriptio1(String descriptio1) {
		this.descriptio1 = descriptio1;
	}

	public String getCodeflag() {
		return codeflag;
	}

	public void setCodeflag(String codeflag) {
		this.codeflag = codeflag;
	}
	
}