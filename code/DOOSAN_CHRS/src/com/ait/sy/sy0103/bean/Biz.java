package com.ait.sy.sy0103.bean;

/**
 * <p>Title: AIT  INTRANET</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: AIT</p>
 * @author AIT
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.utils.NotExistException;
import com.ait.utils.PageControl;
import com.ait.sy.sy0103.bean.Ent;

public class Biz {

	Vector Vlist = new Vector(); // 产生Vector的对象Vlist

	Connection Conn = null; // 产生连接初始化的对象Conn

	int i = 0; // 初始化循环Vector的变量值

	HttpSession m_session = null;

	public Biz() {
	}

	String LIST_SQL_0 = "SELECT CODE_NO ,CODE_ID ,CODE_NAME ,PARENT_CODE ,to_char(CREATE_DATE,'YYYYMMDD') ,CREATED_BY ,to_char(UPDATE_DATE,'YYYYMMDD') ,UPDATED_BY ,ACTIVITY ,ORDERNO ,DEPTH ,CODE_EN_NAME,CPNY_ID from SY_CODE 	        ";

	String UPDATE_SQL = "UPDATE SY_CODE SET" + " CODE_ID = ?," + " CODE_NAME = ?," + " CODE_EN_NAME = ?," + " UPDATE_DATE = sysdate," + " UPDATED_BY = ?,"+ "CPNY_ID = ?" + " WHERE CODE_ID = ?";

	String DETAIL_SQL = "WHERE CODE_ID = ?";

	String ORDER_SQL = " ORDER BY CODE_NO desc";

	// 删除记录的sql语句，"?"代表参数变量
	String DELETE_SQL = "DELETE FROM SY_CODE WHERE CODE_ID= ?";

	public Connection Conn() throws Exception {
		Connection Conn = null; // 建立参数声明
		try {
			Conn = ConnBean.getConn(); // 得到连接
		} catch (Exception e) {
			System.out.print("Conn Error:" + e);
		}
		return Conn;
	}

	// 选出所有记录列表的sql语句
	public Vector List(PageControl pc) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		try {
			pc.seti();
			pstmt = conn.prepareStatement(LIST_SQL_0 + ORDER_SQL); // 执行select与排序语句
			Logger.getLogger(getClass()).debug(LIST_SQL_0 + ORDER_SQL);
			rs = pstmt.executeQuery();
			for (int i = 0; i < pc.getI(); i++) {
				rs.next();
			}
			pc.setii();
			while ((pc.getI() < pc.getIntPagedSize()) && rs.next()) { // 循环数据列表信息
				Ent Ent = new Ent(); // 产生实体对象
				Ent.setCodeNo(rs.getInt(1));
				Ent.setBasicCode(rs.getString(2));
				Ent.setBasicName(rs.getString(3));
				Ent.setParentCode(rs.getString(4));
				Ent.setCreateDate(rs.getString(5));
				Ent.setCreatorID(rs.getString(6));
				Ent.setModifyDate(rs.getString(7));
				Ent.setModifierID(rs.getString(8));
				Ent.setActivity(rs.getInt(9));
				Ent.setOrderNo(rs.getInt(10));
				Ent.setDepth(rs.getInt(11));
				Vlist.addElement(Ent); // 形成一条记录，插入记录列表
				pc.setiii();
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return Vlist; // 返回记录列表
	}

	public Vector getLevelSecond(String parentcode) throws Exception {
		Logger.getLogger(getClass()).debug("getlevelsecond");
		Vector vector = new Vector();
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "select sy.*,cpny.code_name cpnyname from sy_code sy,sy_code cpny where sy.PARENT_CODE=? and sy.cpny_id=cpny.code_id(+)  order by sy.code_id asc,sy.cpny_id  asc ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, parentcode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Ent Ent = new Ent();   
				Ent.setBasicCode(rs.getString("CODE_ID") != null ? rs.getString("CODE_ID") : "");
				Ent.setCodeNo(rs.getInt("CODE_NO"));
				Ent.setBasicName(rs.getString("CODE_NAME") != null ? rs.getString("CODE_NAME") : "");
				Ent.setCodeEnName(rs.getString("CODE_EN_NAME") != null ? rs.getString("CODE_EN_NAME") : "");
				Ent.setParentCode(rs.getString("PARENT_CODE") != null ? rs.getString("PARENT_CODE") : "");
				Ent.setCreateDate(rs.getString("CREATE_DATE") != null ? rs.getString("CREATE_DATE") : "");
				Ent.setCreatorID(rs.getString("CREATED_BY") != null ? rs.getString("CREATED_BY") : "");
				Ent.setModifyDate(rs.getString("UPDATE_DATE") != null ? rs.getString("UPDATE_DATE") : "");
				Ent.setModifierID(rs.getString("UPDATED_BY") != null ? rs.getString("UPDATED_BY") : "");
				Ent.setActivity(rs.getInt("ACTIVITY"));
				Ent.setOrderNo(rs.getInt("ORDERNO"));
				Ent.setDepth(rs.getInt("DEPTH"));
				Ent.setCpnyname(rs.getString("cpnyname")!=null?rs.getString("cpnyname"):"");
				vector.add(Ent);
			}
		} catch (SQLException ex) {
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return vector;
	}

	public Vector getLevelFirst() {
		Vector vList = null;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT     * ");
		sql.append("      FROM (SELECT * ");
		sql.append("              FROM sy_code ");
		sql.append("             WHERE DEPTH < 2 AND activity = 1) ");
		sql.append(" START WITH DEPTH = 0 ");
		sql.append(" CONNECT BY PRIOR code_id = parent_code");
		sql.append(" order  siblings by code_en_name ");
		Logger.getLogger(getClass()).debug(sql.toString());
		try {
			conn = ConnBean.getConn();
			state = conn.createStatement();
			rs = state.executeQuery(sql.toString());
			vList = new Vector();
			while (rs.next()) {
				Ent ent = new Ent();
				ent.setBasicCode(rs.getString("code_id"));
				ent.setBasicName(rs.getString("code_name"));
				ent.setCodeEnName(rs.getString("CODE_EN_NAME"));
				ent.setDepth(rs.getInt("depth"));
				ent.setParentCode(rs.getString("parent_code"));
				vList.add(ent);
			}
		} catch (SQLException ex) {
			Logger.getLogger(getClass()).error(ex.toString());
		} finally {
			SqlUtil.close(rs, state, conn);
		}
		return vList;
	}

	public Vector List() throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		try {
			pstmt = conn.prepareStatement(LIST_SQL_0 + ORDER_SQL); // 执行select与排序语句
			Logger.getLogger(getClass()).debug(LIST_SQL_0 + ORDER_SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 循环数据列表信息
				Ent Ent = new Ent(); // 产生实体对象
				Ent.setCodeNo(rs.getInt(1));
				Ent.setBasicCode(rs.getString(2));
				Ent.setBasicName(rs.getString(3));
				Ent.setParentCode(rs.getString(4));
				Ent.setCreateDate(rs.getString(5));
				Ent.setCreatorID(rs.getString(6));
				Ent.setModifyDate(rs.getString(7));
				Ent.setModifierID(rs.getString(8));
				Ent.setActivity(rs.getInt(9));
				Ent.setOrderNo(rs.getInt(10));
				Ent.setDepth(rs.getInt(11));
				Vlist.addElement(Ent); // 形成一条记录，插入记录列表
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return Vlist; // 返回记录列表
	}

	public Vector Detail(String no) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null; // 建立参数声明
		ResultSet rs = null; // 建立数据集
		try {
			pstmt = conn.prepareStatement(LIST_SQL_0 + DETAIL_SQL); // 执行select与排序语句
			Logger.getLogger(getClass()).debug(LIST_SQL_0 + DETAIL_SQL);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 循环数据列表信息
				Ent Ent = new Ent(); // 产生实体对象
				Ent.setCodeNo(rs.getInt(1));
				Ent.setDepth(rs.getInt("DEPTH"));
				Ent.setBasicCode(rs.getString(2));
				Ent.setBasicName(rs.getString(3));
				Ent.setParentCode(rs.getString(4));
				Ent.setCodeEnName(rs.getString("CODE_EN_NAME"));
				Ent.setCpnyId(rs.getString("CPNY_ID"));
				Vlist.addElement(Ent); // 形成一条记录，插入记录列表
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return Vlist; // 返回记录列表
	}

	public void Insert(Ent Ent, String depth) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		String INSERT_SQL = "  INSERT INTO SY_CODE ( CODE_NO,CODE_ID,CODE_NAME,CODE_EN_NAME,PARENT_CODE,CREATE_DATE,CREATED_BY, ACTIVITY,ORDERNO,DEPTH,cpny_id) " 
			              + "  VALUES(SY_CODENO_SEQ.NEXTVAL,?,?,?,?,sysDate,?,1,SY_CODENO_SEQ.NEXTVAL,?,?) ";
		try {
			Logger.getLogger(getClass()).debug(INSERT_SQL);
			pstmt = conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, Ent.getBasicCode());
			pstmt.setString(2, Ent.getBasicName());
			pstmt.setString(3, Ent.getCodeEnName());
			pstmt.setString(4, Ent.getParentCode());
			pstmt.setString(5, Ent.getCreatorID());
			pstmt.setString(6, depth);
			pstmt.setString(7, Ent.getCpnyId());
			if (pstmt.executeUpdate() == 0) {
				throw new NotExistException("No data exists");
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug("fail  " + e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	// 返回搜索结果
	// 删除信息方法
	public void Delete(String No) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null; // 建立参数声明
		try {
			Logger.getLogger(getClass()).debug("Delete_sql : " + DELETE_SQL);
			pstmt = conn.prepareStatement(DELETE_SQL);
			pstmt.setString(1, No);
			if (pstmt.executeUpdate() == 0) // 调用上面形成的DELETE语句，为?即参数变量赋值
				throw new NotExistException("No data exists");
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug(e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void Update(Ent ent) throws Exception {
		Connection conn = Conn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(UPDATE_SQL); // 执行更新数据方法
			Logger.getLogger(getClass()).debug(UPDATE_SQL);
			pstmt.setString(1, ent.getBasicCode());
			pstmt.setString(2, ent.getBasicName());
			pstmt.setString(3, ent.getCodeEnName());
			pstmt.setString(4, ent.getModifierID());
			pstmt.setString(5, ent.getCpnyId());
			pstmt.setString(6, ent.getCreatorID());
			
			if (pstmt.executeUpdate() == 0) {
				throw new NotExistException("Update No data exists");
			}
		} catch (SQLException e) {
			Logger.getLogger(getClass()).debug("Update Error:" + e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	public void update(int codeNo, String codeName, String codeEnName,String cpnyId,String adminID) {
		String sql = "update sy_code set code_name = ? ,CODE_EN_NAME=? ,cpny_id=?,UPDATE_DATE=sysDate,UPDATED_BY=? where code_no = ?";
		Logger.getLogger(getClass()).debug(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = Conn();
			pstmt = conn.prepareStatement(sql); // 执行更新数据方法
			pstmt.setString(1, codeName);
			pstmt.setString(2, codeEnName);
			pstmt.setString(3, cpnyId);
			pstmt.setString(4, adminID);
			pstmt.setInt(5, codeNo);
			
			pstmt.executeUpdate();            
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(getClass()).debug("Update Error:" + e.toString());
		} finally {
			SqlUtil.close(pstmt, conn);
		}
	}

	//根据代码编号获取一条记录
	public static synchronized Ent select(String codeNo) {
		String sql = "SELECT " + "CODE_ID, " + "CODE_NAME, " +
		//				"DESCRIPTION, " + //详细说明，暂时不用
				"CODE_EN_NAME, " + "PARENT_CODE, to_char(CREATE_DATE,'yyyy-mm-dd') as CREATE_DATE, " + "CREATED_BY, to_char(UPDATE_DATE,'yyyy-mm-dd') as UPDATE_DATE, " + "UPDATED_BY, ACTIVITY, " + "ORDERNO , DEPTH ,cpny_id " + "FROM SY_CODE " + "WHERE CODE_NO = '" + codeNo + "'";
		Logger.getLogger(Biz.class).debug(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Ent ent = null;
		try {
			conn = ConnBean.getConn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				ent = new Ent();
				ent.setCodeNo(Integer.parseInt(codeNo));          
				ent.setBasicCode(rs.getString("CODE_ID"));
				ent.setBasicName(rs.getString("CODE_NAME"));
				ent.setCodeEnName(rs.getString("CODE_EN_NAME") != null ? rs.getString("CODE_EN_NAME") : "");
				ent.setParentCode(rs.getString("PARENT_CODE"));
				ent.setCreateDate(rs.getString("CREATE_DATE"));
				ent.setCreatorID(rs.getString("CREATED_BY"));
				ent.setModifyDate(rs.getString("UPDATE_DATE"));
				ent.setModifierID(rs.getString("UPDATED_BY"));
				ent.setActivity(rs.getInt("ACTIVITY"));
				ent.setOrderNo(rs.getInt("ORDERNO"));
				ent.setDepth(rs.getInt("DEPTH"));
				ent.setCpnyId(rs.getString("cpny_id"));
			}
		} catch (SQLException e) {
			Logger.getLogger(Biz.class).error(e.toString());
			e.printStackTrace();
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return ent;
	}
}
