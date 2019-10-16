package com.ait.sy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.ait.sy.bean.Affirm;
import com.ait.util.DataAccessException;
import com.ait.util.DebugUtil;
import com.ait.util.NumberUtil;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.PageControl;

public class AffirmDAONew {
	private static ServiceLocator services;

	public AffirmDAONew() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	public void updateAffirmRelation(Affirm affirm) throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call sy_update_affirm_new(?,?,?,?,?,?) }";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, affirm.getAffirmLevel());
			//cs.setString(2, affirm.getAffirmObject());
			cs.setString(2, affirm.getCpnyIdGz());
			cs.setString(3, affirm.getAffirmorID());
			cs.setString(4, affirm.getAffirmTypeID());
			
			cs.setString(5, affirm.getCreatedBy());
			String TypeIdGz= affirm.getTypeIdGz();
			if(TypeIdGz == null || TypeIdGz.equals("")){
				TypeIdGz="0";
			}
			cs.setString(6, TypeIdGz);
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for updating affirm relation" + e);
		} catch (SQLException e) {
			throw new DataAccessException("cant update affirm relation" + e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void updateAgentAffirmRelation(Affirm affirm) throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{ call sy_update_agentaffirm(?,?,?,?,?) }";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, affirm.getAffirmLevel());
			cs.setString(2, affirm.getAffirmObject());
			cs.setString(3, affirm.getAffirmorID());
			cs.setString(4, affirm.getAffirmTypeID());
			cs.setString(5, affirm.getCreatedBy());
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for updating affirm relation" + e);
		} catch (SQLException e) {
			throw new DataAccessException("cant update affirm relation" + e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

    public Vector getDeptList(String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID, "
	                +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
	                +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
	                +"      FROM SY_AFFIRM_RELATION_TB_MGR, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
	                +" WHERE SY_AFFIRM_RELATION_TB_MGR.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID = HDT.CPNY_ID "
	                +"   AND HDT.FOURTHDEPT = HD.DEPTID AND HD.cpny_id=(select cpny_id from hr_employee where person_id='" + PERSON_ID + "')) "
	                +" ORDER BY FOURTHDEPTID,DEPTID ";
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }

    public Vector getDeptAgentList(String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
	                +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
	                +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
	                +"      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
	                +" WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID "
	                +"   AND HDT.FOURTHDEPT = HD.DEPTID AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '" + PERSON_ID + "') AND HD.cpny_id=(select cpny_id from hr_employee where person_id='" + PERSON_ID + "')) "
	                +" ORDER BY FOURTHDEPTID,DEPTID ";
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }

    public Vector getEmpList1(String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID COMPANY_ID, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.Chinese_Pinyin  AFFIRM_OBJECT_EN_NAME " +
            ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB_MGR, HR_EMPLOYEE " +
            "WHERE SY_AFFIRM_RELATION_TB_MGR.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB_MGR.AFFIRMOR_ID = HR_EMPLOYEE.person_id and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='" + PERSON_ID + "')";
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);  
        }
        return vector;  
    }

    public Vector getEmpList2(String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.Chinese_Pinyin  AFFIRM_OBJECT_EN_NAME " +
            ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE " +
            "WHERE SY_AFFIRM_RELATION_TB.AFFIRM_FLAG = 1 AND SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.person_id  AND ( SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '" + PERSON_ID + "') and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='" + PERSON_ID + "')";
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);  
        }
        return vector;  
    }
    
    public Vector getEmpListByAffirm(String affirmId,String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String affirmSql="";
        if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
        String SQL = " SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID COMPANY_ID, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME " +
            " ,'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB_MGR, HR_EMPLOYEE " +
            " WHERE SY_AFFIRM_RELATION_TB_MGR.AFFIRMOR_ID = HR_EMPLOYEE.PERSON_ID "+affirmSql+" and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='" + PERSON_ID + "')";
            
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public Vector getCpnyListByAffirm() {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String SQL = " SELECT CPNY_ID COMPANY_ID, CPNY_LOCATION AFFIRM_OBJECT_NAME,CPNY_NAME AFFIRM_OBJECT_EN_NAME " +
            " ,'' AS FOURTHDEPTNAME FROM HR_COMPANY WHERE ACTIVITY = 1 ";
            
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public Vector getEmpListByAffirmCp(String affirmId,String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String affirmSql="";
        if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
        String SQL = " SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID COMPANY_ID, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME " +
            " ,'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB_MGR, HR_EMPLOYEE " +
            " WHERE SY_AFFIRM_RELATION_TB_MGR.AFFIRMOR_ID = HR_EMPLOYEE.PERSON_ID "+affirmSql+" and SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID=(select DISTINCT CPNY_ID from HR_EMPLOYEE where CPNY_ID='" + PERSON_ID + "')";
            
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public Vector getEmpListByAgentAffirm(String affirmId,String PERSON_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String affirmSql="";
        if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
        String SQL = " SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME " +
            " ,'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB, HR_EMPLOYEE " +
            " WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HR_EMPLOYEE.PERSON_ID AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID in ('LeaveApply','OtApply','ArModifyApply') AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '" 
            + PERSON_ID 
            //+ "' or SY_AFFIRM_RELATION_TB.AFFIRMOR_ID_BACK = '" + PERSON_ID 
            + "')"+affirmSql+" and HR_EMPLOYEE.CPNY_ID=(select CPNY_ID from HR_EMPLOYEE where person_id='" + PERSON_ID + "')";
            
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public Vector getDeptListByAffirm(String affirmId,PageControl pc ,String cnpy_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String affirmSql="";
        if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
        String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB_MGR, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID = HDT.CPNY_ID "
            +"   AND HDT.FOURTHDEPT = HD.DEPTID " + affirmSql +" and HD.CPNY_ID='"+cnpy_ID
            +"' )ORDER BY FOURTHDEPTID,DEPTID " ;
        Logger.getLogger(getClass()).debug(SQL);
        try {
        	pc.seti();
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();
            while ((pc.getI() < pc.getIntPagedSize())&& rs.next()) {
                vector.add(createAffirmRelationDept(rs));
                pc.setiii();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public Vector getDeptListByAgentAffirm(String personId,String affirmId,PageControl pc ,String cnpy_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String affirmSql="";
        if(!affirmId.equals("")){
            affirmSql=" AND AFFIRMOR_ID='"+affirmId+"'";
        }
        String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB.AFFIRM_OBJECT = HDT.DEPTID AND SY_AFFIRM_RELATION_TB.AFFIRM_TYPE_ID in ('LeaveApply','OtApply','ArModifyApply') AND (SY_AFFIRM_RELATION_TB.AFFIRMOR_ID = '" 
            +personId
            //+ "' or SY_AFFIRM_RELATION_TB.AFFIRMOR_ID_BACK = '" + personId
            + "') AND HDT.FOURTHDEPT = HD.DEPTID " + affirmSql +" and HD.CPNY_ID='"+cnpy_ID
            +"' )ORDER BY FOURTHDEPTID,DEPTID " ;
        Logger.getLogger(getClass()).debug(SQL);
        try {
        	pc.seti();
            conn = services.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            for (int i = 0; i < pc.getI(); i++) {
                rs.next();
            }
            pc.setii();
            while ((pc.getI() < 20)&& rs.next()) {
                vector.add(createAffirmRelationDept(rs));
                pc.setiii();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return vector;
    }
    
    public void udpateAffirm(String oldId,String newId,String affirmRelationNo, String affirmTypeId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Logger.getLogger(getClass()).debug("update affirm");
            conn = services.getConnection();
            String sql="";
            if(affirmRelationNo.equals("")||affirmRelationNo==null){
            	sql=" UPDATE sy_affirm_relation_tb_mgr SET affirmor_id='"+newId+"'  WHERE  affirmor_id='"+oldId+"' ";
            }else{
            	sql=" UPDATE sy_affirm_relation_tb_mgr SET affirmor_id='"+newId+"'  WHERE  affirmor_id='"+oldId+"'  and affirm_relation_no='"+affirmRelationNo+"'";
            }
            
            if(affirmTypeId != null && affirmTypeId.length() > 0){
            	sql += " and AFFIRM_TYPE_ID = '"+ affirmTypeId +"'" ;
            }
            
            Logger.getLogger(getClass()).debug("update affirm sql " + sql);
            stmt=conn.prepareStatement(sql);            
            stmt.executeUpdate();
           /// conn.commit();
            
            Logger.getLogger(getClass()).debug("update affirm ok ");
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
            try{
                conn.rollback();
            }catch(SQLException e){
                Logger.getLogger(getClass()).debug(e.toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(stmt, conn);
        }
    }
    /*
    public void udpateAffirm(String oldId,String newId,String isUpdate){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Logger.getLogger(getClass()).debug("update affirm");
            conn = services.getConnection();
            conn.setAutoCommit(false);
            
            if("1".equals(isUpdate)){
                stmt=conn.prepareStatement(" UPDATE ess_affirm SET affirmor_id=? WHERE affirmor_id=? AND  affirm_flag=0 ");
                stmt.setString(1, newId);
                stmt.setString(2, oldId);
                stmt.executeUpdate();
            }
            stmt=conn.prepareStatement(" UPDATE sy_affirm_relation_tb SET affirmor_id=?  WHERE  affirmor_id=? ");
            stmt.setString(1, newId);
            stmt.setString(2, oldId);
            stmt.executeUpdate();
            conn.commit();
            
            Logger.getLogger(getClass()).debug("update affirm ok ");
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
            try{
                conn.rollback();
            }catch(SQLException e){
                Logger.getLogger(getClass()).debug(e.toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(stmt, conn);
        }
    }
    */
    
    public boolean validateUdpateAffirm_ESS(String oldId,String newId)
    {
    	boolean flag = true ;
    	
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = services.getConnection();
            
            String sql = "SELECT HR.EMPID AS empID, HR.CHINESENAME AS chineseName " +
            		     "  FROM HR_EMPLOYEE HR " +
            		     " WHERE HR.Person_Id = ? " +
            			 "   AND EXISTS (SELECT SA.AFFIRM_OBJECT " +
            			 "  			   FROM SY_AFFIRM_RELATION_TB SA " +
                         "				  WHERE SA.AFFIRMOR_ID_BACK = ? " +
                         "					AND (HR.Person_Id > ? OR HR.Person_Id < ? ) " +
                  		 "				    AND (HR.DEPTID = SA.AFFIRM_OBJECT OR HR.Person_Id = SA.AFFIRM_OBJECT))" ;
            
            Logger.getLogger(getClass()).debug("sql = " + sql);
            
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, newId);
            stmt.setString(2, oldId);
            stmt.setString(3, oldId);
            stmt.setString(4, oldId);
            
            rs = stmt.executeQuery() ;
            if (!rs.next())
            {
            	flag = false ;
            }
            
        } catch (Exception e) {
        	flag = false ;
        	Logger.getLogger(getClass()).error(e.toString());
            e.printStackTrace() ;
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
 
    	return flag ;
    }
    
    public ResultSet selectChinesename(String adminId)
    {
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = services.getConnection();
            
            String sql = "SELECT HR.EMPID, HR.CHINESENAME, HR.EMAIL, E.DEPTNAME, Y.CPNY_NAME " +
            		     "  FROM HR_EMPLOYEE HR, HR_DEPARTMENT E, HR_COMPANY Y  " +
            		     " WHERE HR.DEPTID = E.DEPTID AND HR.CPNY_ID = Y.CPNY_ID AND HR.PERSON_ID = ? " ;
            Logger.getLogger(getClass()).debug("sql = " + sql);
            
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, adminId);
            
            rs = stmt.executeQuery() ;
            
        } catch (Exception e) {
        	Logger.getLogger(getClass()).error(e.toString());
            e.printStackTrace() ;
        } 
 
    	return rs ;
    }
    
    public boolean validateUdpateAffirm_ESS2(String oldId,String newId)
    {
    	boolean flag = true ;
    	
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	// 查找，是否存在，oldId和newId，同在一组决裁，newId已经通过了，就不能在进行替换，否则会出现重复
            conn = services.getConnection();
            String []tablename={"ESS_AFFIRM","AR_DETAIL_AFFIRM"};//修改加班、勤态、考勤修改决裁
            for(int i=0;i<tablename.length;i++){
            	String sql = "";
            	if(tablename[i].equals("AR_DETAIL_AFFIRM")){
            		sql = "SELECT A.AR_DETAIL_NO,A.AFFIRMOR_ID " +
       			     "  FROM "+tablename[i]+" A, " +
					 "		(SELECT C.AR_DETAIL_NO, MAX(C.AFFIRM_LEVEL) AS MAX_AFFIRM_LEVEL " +
			         "         FROM "+tablename[i]+" C WHERE C.AFFIRM_FLAG < 2 GROUP BY C.AR_DETAIL_NO) D " +
					 " WHERE A.AR_DETAIL_NO = D.AR_DETAIL_NO " +
					 "   AND A.AFFIRMOR_ID = ? " +
					 "   AND A.AFFIRM_LEVEL = D.MAX_AFFIRM_LEVEL " +
					 "   AND A.AFFIRM_FLAG = 0 " +
					 "   AND NOT EXISTS (SELECT B.AR_DETAIL_NO FROM "+tablename[i]+" B WHERE A.AR_DETAIL_NO = B.AR_DETAIL_NO AND AFFIRM_FLAG = 2) " +
					 "   AND EXISTS ( " +
					 "             SELECT E.AR_DETAIL_NO " +
					 "                FROM "+tablename[i]+" E " +
					 "               WHERE A.AR_DETAIL_NO = E.AR_DETAIL_NO " +
					 "                 AND E.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1 " +
					 "                 AND E.AFFIRM_FLAG = 1 " +
					 "                 AND E.AFFIRMOR_ID = ? ) " ;
            	}else{
            sql = "SELECT A.APPLY_NO,A.AFFIRMOR_ID " +
            			 "  FROM "+tablename[i]+" A, " +
						 "		(SELECT C.APPLY_NO, MAX(C.AFFIRM_LEVEL) AS MAX_AFFIRM_LEVEL " +
				         "         FROM "+tablename[i]+" C WHERE C.AFFIRM_FLAG < 2 GROUP BY C.APPLY_NO) D " +
						 " WHERE A.APPLY_NO = D.APPLY_NO " +
						 "   AND A.AFFIRMOR_ID = ? " +
						 "   AND A.AFFIRM_LEVEL = D.MAX_AFFIRM_LEVEL " +
						 "   AND A.AFFIRM_FLAG = 0 " +
						 "   AND NOT EXISTS (SELECT B.APPLY_NO FROM "+tablename[i]+" B WHERE A.APPLY_NO = B.APPLY_NO AND AFFIRM_FLAG = 2) " +
						 "   AND EXISTS ( " +
						 "             SELECT E.APPLY_NO " +
						 "                FROM "+tablename[i]+" E " +
						 "               WHERE A.APPLY_NO = E.APPLY_NO " +
						 "                 AND E.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1 " +
						 "                 AND E.AFFIRM_FLAG = 1 " +
						 "                 AND E.AFFIRMOR_ID = ? ) " ;
            	}
            Logger.getLogger(getClass()).debug("sql = " + sql);
            
            stmt=conn.prepareStatement(sql);
            
            stmt.setString(1, oldId);
            stmt.setString(2, newId);
            
            rs = stmt.executeQuery() ;
           
            if (rs.next())
            {
            	flag = false ;
            }
            }
            
        } catch (Exception e) {
        	flag = false ;
        	Logger.getLogger(getClass()).error(e.toString());
            e.printStackTrace() ;
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
 
    	return flag ;
    }
    
    
    public void udpateAffirm_ESS(String oldId,String newId,String adminId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Logger.getLogger(getClass()).debug("update affirm");
            conn = services.getConnection();
            conn.setAutoCommit(false);
            String sql="";
            String []tablename={"ESS_AFFIRM","AR_DETAIL_AFFIRM"};
            for(int i=0;i<tablename.length;i++){
	             // 当出现，oldId和newId，在同一申请决裁中，
	             // 把大于oldId的决裁级别 - 1，为发邮件做准备
            	if(tablename[i].equals("AR_DETAIL_AFFIRM")){

   	             sql = " UPDATE "+tablename[i]+" A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATE_BY=? " + 
   	            		     "  WHERE A.AFFIRM_FLAG = 0 " + 
   	            			 "    AND EXISTS( " + 
   	                         "               SELECT * " + 
   	                         "  			   FROM "+tablename[i]+" E " + 
   	                         " 				  WHERE E.AFFIRMOR_ID = ? " + 
   	                         "   			    AND A.AR_DETAIL_NO = E.AR_DETAIL_NO AND E.AFFIRM_FLAG = 0 " + 
   	                         "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL " +  
   	                         "   				AND EXISTS (SELECT * FROM "+tablename[i]+" T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) " +
   	
   	            			 " 				) " ;
   	            
   	            Logger.getLogger(getClass()).debug("sql = " + sql);
   	            
   	            stmt=conn.prepareStatement(sql);
   	            stmt.setString(1, adminId);
   	            stmt.setString(2, oldId);
   	            stmt.setString(3, newId);
   	            stmt.executeUpdate();           
   	            
   	            // 删除重复的决裁者
   	            sql = " DELETE "+tablename[i]+" E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 " +
   	              	  "    AND EXISTS (SELECT * FROM "+tablename[i]+" T WHERE T.AFFIRMOR_ID = ? AND E.AR_DETAIL_NO = T.AR_DETAIL_NO ) " ;
   	            Logger.getLogger(getClass()).debug("sql = " + sql);
   	            
   	            stmt=conn.prepareStatement(sql);
   	            stmt.setString(1, oldId);
   	            stmt.setString(2, newId);
   	            stmt.executeUpdate();

	            // 更新蕨菜信息表
	            stmt=conn.prepareStatement(" UPDATE "+tablename[i]+" SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATE_BY=?  WHERE affirmor_id in(select affirmor_id from sy_affirm_relation_tb where AFFIRMOR_ID_BACK=? and affirm_type_id in ('LeaveApply','OtApply','ArModifyApply')) AND  affirm_flag=0 ");
	            stmt.setString(1, newId);
	            stmt.setString(2, adminId);
	            stmt.setString(3, oldId);
	            stmt.executeUpdate();
            	}else{
	             sql = " UPDATE "+tablename[i]+" A SET A.AFFIRM_LEVEL = A.AFFIRM_LEVEL - 1, UPDATE_DATE=sysdate, UPDATED_BY=? " + 
	            		     "  WHERE A.AFFIRM_FLAG = 0 " + 
	            			 "    AND EXISTS( " + 
	                         "               SELECT * " + 
	                         "  			   FROM "+tablename[i]+" E " + 
	                         " 				  WHERE E.AFFIRMOR_ID = ? " + 
	                         "   			    AND A.APPLY_NO = E.APPLY_NO AND E.AFFIRM_FLAG = 0 " + 
	                         "   				AND A.AFFIRM_LEVEL > E.AFFIRM_LEVEL " +  
	                         "   				AND EXISTS (SELECT * FROM "+tablename[i]+" T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) " +
	
	            			 " 				) " ;
	            
	            Logger.getLogger(getClass()).debug("sql = " + sql);
	            
	            stmt=conn.prepareStatement(sql);
	            stmt.setString(1, adminId);
	            stmt.setString(2, oldId);
	            stmt.setString(3, newId);
	            stmt.executeUpdate();           
	            
	            // 删除重复的决裁者
	            sql = " DELETE "+tablename[i]+" E WHERE E.AFFIRMOR_ID = ? AND E.AFFIRM_FLAG = 0 " +
	              	  "    AND EXISTS (SELECT * FROM "+tablename[i]+" T WHERE T.AFFIRMOR_ID = ? AND E.APPLY_NO = T.APPLY_NO ) " ;
	            Logger.getLogger(getClass()).debug("sql = " + sql);
	            
	            stmt=conn.prepareStatement(sql);
	            stmt.setString(1, oldId);
	            stmt.setString(2, newId);
	            stmt.executeUpdate();
	            // 更新蕨菜信息表
	            stmt=conn.prepareStatement(" UPDATE "+tablename[i]+" SET affirmor_id=?,UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE affirmor_id in(select affirmor_id from sy_affirm_relation_tb where AFFIRMOR_ID_BACK=? AND affirm_type_id in ('LeaveApply','OtApply','ArModifyApply')) AND  affirm_flag=0 ");
	            stmt.setString(1, newId);
	            stmt.setString(2, adminId);
	            stmt.setString(3, oldId);
	            stmt.executeUpdate();
            	}        
            }
            
            // 更新系统决裁表
            sql = "UPDATE SY_AFFIRM_RELATION_TB S SET S.AFFIRM_FLAG = 0, UPDATE_DATE=sysdate, UPDATED_BY=? " + 
				  " WHERE S.AFFIRM_RELATION_NO IN ( " +
				  "   SELECT SA.AFFIRM_RELATION_NO " +
				  "     FROM SY_AFFIRM_RELATION_TB SA " +
				  "     WHERE SA.AFFIRMOR_ID = ? " + 
				  "       AND EXISTS ( SELECT * " +
				  "                	    FROM SY_AFFIRM_RELATION_TB T " + 
				  "               	   WHERE SA.AFFIRM_TYPE_ID = T.AFFIRM_TYPE_ID " +
				  "                 	     AND SA.AFFIRM_OBJECT = T.AFFIRM_OBJECT " +
				  "                 		 AND T.AFFIRMOR_ID = ? " +
				  "     		 )) " +
				  "       AND  Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') " ;  
            
            Logger.getLogger(getClass()).debug("sql = " + sql);
            
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, adminId);
            stmt.setString(2, oldId);
            stmt.setString(3, newId);
            stmt.executeUpdate();
            
            stmt=conn.prepareStatement(" UPDATE sy_affirm_relation_tb SET affirmor_id=?, UPDATE_DATE=sysdate, UPDATED_BY=?  WHERE  AFFIRMOR_ID_BACK=? AND AFFIRM_FLAG = 1 AND Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') ");
            stmt.setString(1, newId);
            stmt.setString(2, adminId);
            stmt.setString(3, oldId);
            stmt.executeUpdate();
            conn.commit();
            
            Logger.getLogger(getClass()).debug("update affirm ok ");
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
            try{
                conn.rollback();
            }catch(SQLException e){
                Logger.getLogger(getClass()).debug(e.toString());
            }
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(stmt, conn);
        }
    }
    
    public void recoverAffirm_ESS(String affirmId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Logger.getLogger(getClass()).debug("recover affirm");
            conn = services.getConnection();
            
            stmt=conn.prepareStatement(" UPDATE sy_affirm_relation_tb SET affirmor_id = AFFIRMOR_ID_BACK, AFFIRM_FLAG = 1,UPDATE_DATE=sysdate, UPDATED_BY=? WHERE  AFFIRMOR_ID_BACK=? AND Affirm_Type_Id in ('LeaveApply','OtApply','ArModifyApply') ");
            stmt.setString(1, affirmId);
            stmt.setString(2, affirmId);
            stmt.executeUpdate();
            
            Logger.getLogger(getClass()).debug("recover affirm ok ");
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(stmt, conn);
        }
    }


	/**
	 * 
	 * @param deptId
	 * @return
	 */
    public Vector getDeptList(String deptId,PageControl pc,String cnpy_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String SQL = "SELECT * FROM (SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID, "
            +"      HDT.DEPTNAME AFFIRM_OBJECT_NAME, HDT.DEPT_EN_NAME AFFIRM_OBJECT_EN_NAME, "
            +"      HDT.DEPTID, HD.DEPTID AS FOURTHDEPTID, HD.DEPTNAME AS FOURTHDEPTNAME "
            +"      FROM SY_AFFIRM_RELATION_TB_MGR, HR_DEPT_TREE_V HDT, HR_DEPARTMENT HD "
            +" WHERE SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID = HDT.CPNY_ID AND HDT.DEPTID = HD.DEPTID and HD.cpny_ID=?) T "
            +" WHERE EXISTS (SELECT B1.DEPTID FROM HR_DEPARTMENT B1 WHERE B1.DEPTID = T.DEPTID "
            +" AND B1.DEPTID = ? START WITH B1.DEPTID = ? CONNECT BY PRIOR B1.DEPTID = B1.PARENT_DEPT_ID) "
            +" ORDER BY FOURTHDEPTID,DEPTID ";
        
        Logger.getLogger(getClass()).debug(SQL + " param: " + deptId);
        try {
        	pc.seti();
            conn = services.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, cnpy_ID);
            pstmt.setString(2, deptId);
            pstmt.setString(3, deptId);
            rs = pstmt.executeQuery();
            pc.setii();
            while ((pc.getI() < pc.getIntPagedSize())&& rs.next()) {           
                vector.add(createAffirmRelationDept(rs));
                pc.setiii();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return vector;
    }

    public Vector getEmpList(String empId, String cnpy_ID) {
        Vector vector = new Vector();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT DISTINCT SY_AFFIRM_RELATION_TB_MGR.COMPANY_ID, HR_EMPLOYEE.CHINESENAME AFFIRM_OBJECT_NAME,HR_EMPLOYEE.CHINESE_PINYIN AFFIRM_OBJECT_EN_NAME " +
        ",'' AS FOURTHDEPTNAME FROM SY_AFFIRM_RELATION_TB_MGR, HR_EMPLOYEE " +
        "WHERE SY_AFFIRM_RELATION_TB_MGR.AFFIRMOR_ID = HR_EMPLOYEE.Person_Id and HR_EMPLOYEE.Cpny_Id='" + cnpy_ID + "'"+
        "AND HR_EMPLOYEE.Person_Id = ?";
        Logger.getLogger(getClass()).debug(SQL);
        try {
            conn = services.getConnection();
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, empId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vector.add(createAffirmRelationDept(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } catch (ServiceLocatorException ex) {
            Logger.getLogger(getClass()).debug(ex.toString());
        } finally {
            SqlUtil.close(rs, pstmt, conn);
        }
        return vector;
    }

    public List getAffirmRelation(String companyid, String affirmtypeid ) {
		List list = new ArrayList();
		Connection conn = null;		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V_AOTU v, hr_employee e WHERE COMPANY_ID =? and AFFIRM_TYPE_ID =? and v.affirmor_id = e.person_id(+) order by AFFIRM_LEVEL";
		//Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, companyid);
			pstmt.setString(2, affirmtypeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelation(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
    
	public List getAffirmRelation(String deptid, String affirmtypeid, String affirmFlag) {
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V v, hr_employee e WHERE AFFIRM_FLAG = ? AND AFFIRM_OBJECT =? and AFFIRM_TYPE_ID =? and e.person_id(+) = v.affirmor_id order by AFFIRM_LEVEL";
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V_AOTU v, hr_employee e WHERE AFFIRM_FLAG = ? AND COMPANY_ID =? and AFFIRM_TYPE_ID =? and e.person_id(+) = v.affirmor_id order by AFFIRM_LEVEL";

		//Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, affirmFlag);
			pstmt.setString(2, deptid);
			pstmt.setString(3, affirmtypeid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelation(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	public List getAffirmRelationGz(String companyid, String affirmtypeid, String typeGz) {
		List list = new ArrayList();
		Connection conn = null;		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT * FROM SY_AFFIROM_RELATION_V_AOTU v, hr_employee e WHERE COMPANY_ID =? and AFFIRM_TYPE_ID =? and affirm_object =? and v.affirmor_id = e.person_id(+) order by AFFIRM_LEVEL";
		//Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, companyid);
			pstmt.setString(2, affirmtypeid);
			pstmt.setString(3, typeGz);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(createAffirmRelation(rs));
			}
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public String getDeptidByEmpid(String empid ) {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = " SELECT deptid hr_employee e WHERE e.person_id = ?";
		//Logger.getLogger(getClass()).debug(SQL);
		try {
			conn = services.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			while(rs.next()){
                return StringUtil.checkNull(rs.getString("deptid"));
            }
		} catch (SQLException ex) {
		} catch (ServiceLocatorException ex) {
		} finally {
			SqlUtil.close(rs, pstmt, conn);
		}
		return new String("");
	}
	
	private Affirm createAffirmRelationDept(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm.setAffirmObject(rs.getString("COMPANY_ID") != null ? rs.getString("COMPANY_ID") : "");
			affirm.setAffirmObjectName(rs.getString("AFFIRM_OBJECT_NAME") != null ? rs.getString("AFFIRM_OBJECT_NAME") : "");
			affirm.setAffirmObjectEnName(rs.getString("AFFIRM_OBJECT_EN_NAME") != null ? rs.getString("AFFIRM_OBJECT_EN_NAME") : "");
			affirm.setFourthDeptName(StringUtil.checkNull(rs.getString("FOURTHDEPTNAME")));
			
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;             
	}

	private Affirm createAffirmRelation(ResultSet rs) {
		Affirm affirm = new Affirm();
		try {
			affirm.setAffirmRelationNo(rs.getString("AFFIRM_RELATION_NO") != null ? rs.getString("AFFIRM_RELATION_NO") : "");
			affirm.setCompanyID(rs.getString("COMPANY_ID") != null ? rs.getString("COMPANY_ID") : "");
			affirm.setAffirmorID(rs.getString("AFFIRMOR_ID") != null ? rs.getString("AFFIRMOR_ID") : "");
			affirm.setAffirmorName(rs.getString("AFFIRMOR_NAME") != null ? rs.getString("AFFIRMOR_NAME") : "空缺");
			affirm.setAffirmorEnName(rs.getString("CHINESE_PINYIN") != null ? rs.getString("CHINESE_PINYIN") : "");
			affirm.setAffirmTypeID(rs.getString("AFFIRM_TYPE_ID") != null ? rs.getString("AFFIRM_TYPE_ID") : "");
			affirm.setAffirmLevel(rs.getInt("AFFIRM_LEVEL"));
			affirm.setAffirmObject(rs.getString("AFFIRM_OBJECT") != null ? rs.getString("AFFIRM_OBJECT") : "");
			affirm.setAffirmObjectName(rs.getString("AFFIRM_OBJECT_NAME") != null ? rs.getString("AFFIRM_OBJECT_NAME") : "");
			affirm.setAffirmFlag(rs.getString("AFFIRM_FLAG") != null ? rs.getString("AFFIRM_FLAG") : "");
			affirm.setCreateDate(rs.getString("CREATE_DATE") != null ? rs.getString("CREATE_DATE") : "");
			affirm.setCreatedBy(rs.getString("CREATED_BY") != null ? rs.getString("CREATED_BY") : "");
			affirm.setUpdateDate(rs.getString("UPDATE_DATE") != null ? rs.getString("UPDATE_DATE") : "");
			affirm.setUpdatedBy(rs.getString("UPDATED_BY") != null ? rs.getString("UPDATED_BY") : "");
			affirm.setActivity(rs.getString("ACTIVITY") != null ? rs.getString("ACTIVITY") : "");
			affirm.setChinesePinYin(rs.getString("CHINESE_PINYIN") != null ? rs.getString("CHINESE_PINYIN") : "");
			affirm.setAffirmorDeptName(rs.getString("DEPTNAME") != null ? rs.getString("DEPTNAME") : "");
			affirm.setEmpId(rs.getString("EMPID") != null ? rs.getString("EMPID") : "");
			affirm.setAffirmorDeptEnName(rs.getString("DEPT_EN_NAME") != null ? rs.getString("DEPT_EN_NAME") : "");  
		} catch (SQLException ex) {
			DebugUtil.printStackTrace(ex);
		}
		return affirm;
	}

	/**
	 * getAffirmorList <br>
	 * 根据输入的员工工号和申请类型，找到此员工的所有决裁者。
	 * 
	 * @param objectId
	 *            String
	 * @param applyType
	 *            String
	 * @throws DataAccessException
	 * @return List
	 */
	public List getAffirmorList(String objectId, String applyType)
			throws DataAccessException {
		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_get_affirmor(?,?,?,?,?)}";
		List list = new ArrayList();
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setString(1, objectId);
			cs.setString(2, applyType);
			for (int i = 1;; i++) {
				cs.setInt(3, i);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.execute();
				Map map = new HashMap();
				map.put("affirmorID", StringUtil.checkNull(cs.getString(4)));
				map.put("affirmor", StringUtil.checkNull(cs.getString(5)));
				if (cs.getString(4) == null)
					break;
				list.add(map);
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
		return list;
	}

	public void affirmLevelUp(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_upward_affirmor(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void affirmLevelDown(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_downward_affirmor(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

	public void affirmLevelDelete(String affirmRelationNo) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		String sql = "{call sy_delete_affirmor_new(?)}";
		try {
			con = services.getConnection();
			cs = con.prepareCall(sql);
			cs.setInt(1, NumberUtil.parseInt(affirmRelationNo));
			cs.execute();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for getting affirmor list!", e);
		} catch (SQLException e) {
			throw new DataAccessException("cant get  affirmor list!", e);
		} finally {
			SqlUtil.close(cs, con);
		}
	}

}
