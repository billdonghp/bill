package com.ait.ar.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.ait.ar.bean.DynamicGroup;
import com.ait.ar.bean.GroupMember;
import com.ait.ar.dao.DynamicGroupDAO;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

//import com.sun.rsasign.s;

public class DynamicGroupDAOImpl implements DynamicGroupDAO {
    private static ServiceLocator services;
    
    private String cnpyID ;
    
    public DynamicGroupDAOImpl() {
        try {
            services = ServiceLocator.getInstance();
        } catch (ServiceLocatorException ex) {
        }
    }

    // 设定登陆人ID
    public void setLoginID(String loginID) {
    }
    
 // 设定登陆人公司ID
    public void setCnpyID(String cnpyID) {
    	this.cnpyID = cnpyID ;
    }
    
    /**
     * addDynamicGroup <br>
     * 添加一个动态组，同时此动态组的组成条件也被添加到数据库中。
     * 
     * @param group
     *            DynamicGroup
     */
    public int addDynamicGroup(DynamicGroup group, String message)
            throws DataAccessException {
        Connection con = null;
        CallableStatement cs = null;
        String sql = "{ call ar_add_dynamicGroup(?,?,?,?,?,?,?,?,?,?) }";
        int status = -1;
        try {
            Logger.getLogger(getClass()).debug(sql);
            Logger.getLogger(getClass()).debug(
                    "parameter1:[" + group.getGroupName() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter2:[" + group.getDescription() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter3:[" + group.getCreatedBy() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter4:[" + Types.INTEGER + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter5:[" + group.getGroupProperty() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter6:[" + group.getGroupQualification() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter7:[" + group.getSysGroupType() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter8:[" + group.getGroupEnName() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter9:[" + group.getGroupKoName() + "]");
            Logger.getLogger(getClass()).debug(
                    "parameter10:[" + group.getCpny_id() + "]");
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.setString(1, group.getGroupName());
            cs.setString(2, group.getDescription());
            cs.setString(3, group.getCreatedBy());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.setString(5, group.getGroupProperty());
            cs.setString(6, group.getGroupQualification());
            cs.setString(7, group.getSysGroupType());
            cs.setString(8, group.getGroupEnName());
            cs.setString(9, group.getGroupKoName());
            cs.setString(10, group.getCpny_id());
            cs.execute();
            status = cs.getInt(4);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            throw new DataAccessException(" cant add group ! " + e);
        } catch (ServiceLocatorException e) {
            throw new DataAccessException(
                    " cant get connection for adding group! " + e);
        } finally {
            SqlUtil.close(cs, con);
        }
        return status;
    }

    /**
     * deleteGroup <br>
     * 根据输入的组号，删除一个动态组，在删除组本身之前，要删除所有关联于此组的条件。
     * 
     * @param groupNo
     *            int
     * @return int
     */
    public int deleteGroup(int groupNo) throws DataAccessException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete from ar_group_conditions where group_no = ?";
        String sql1 = "delete from  ar_emp_group where group_no = ?";
        String sql2 = "delete from ar_dynamic_group where group_no = ?";
        Logger.getLogger(getClass()).debug(sql);
        Logger.getLogger(getClass()).debug("parameter1:[" + groupNo + "]");
        int isSuc = 0;
        try {
            con = services.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
            int i = 1;
            pstmt.setInt(i++, groupNo);
            isSuc = pstmt.executeUpdate();
            pstmt = con.prepareStatement(sql1);
            i = 1;
            pstmt.setInt(i++, groupNo);
            isSuc = pstmt.executeUpdate();
            pstmt = con.prepareStatement(sql2);
            i = 1;
            pstmt.setInt(i++, groupNo);
            isSuc = pstmt.executeUpdate();
            if (isSuc > 0) {
                con.commit();
            } else {
                con.rollback();
            }
        } catch (ServiceLocatorException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DataAccessException(
                    "cant get connection for deleting dynamic group!" + e);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DataAccessException("cant delete dynamic group" + e);
        } finally {
            SqlUtil.close(pstmt, con);
        }
        return isSuc;
    }

    /**
     * getGroupSql
     * 
     * @param groupNo
     *            int
     * @return String
     */
    public String getGroupSql(int groupNo) throws DataAccessException {
        Connection con = null;
        String sql = "{ call ?:=ar_get_dy_emp(?)}";
        Logger.getLogger(getClass()).debug(sql);
        Logger.getLogger(getClass())
                .debug("parameter1:[" + Types.VARCHAR + "]");
        Logger.getLogger(getClass()).debug("parameter2:[" + groupNo + "]");
        CallableStatement cs = null;
        String returnSql = "";
        try {
            con = services.getConnection();
            cs = con.prepareCall(sql);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setInt(2, groupNo);
            cs.execute();
            returnSql = cs.getString(1);
        } catch (ServiceLocatorException e) {
            throw new DataAccessException(
                    "cant get connection for getting group members!", e);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            throw new DataAccessException(" \n cant get group members !", e);
        } finally {
            SqlUtil.close(cs, con);
        }
        return returnSql;
    }

    // 获取动态组中的人员列表
    private String getGroupSql(int groupNo, int conditionNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String returnSqlStr = "";
        String sqlString = "SELECT GROUP_CONDITION_NO, GROUP_NO, "
                + "FIELD_NAME, RELATION, VALUE1, VALUE2 "
                + "FROM AR_GROUP_CONDITIONS WHERE GROUP_NO = '" + groupNo
                + "' AND GROUP_CONDITION_NO = '" + conditionNo + "'";
        Logger.getLogger(getClass()).debug(sqlString);
        try {
            conn = services.getConnection();
            state = conn.createStatement();
            rs = state.executeQuery(sqlString);
            if (rs.next()) {
                if (rs.getString("RELATION").equalsIgnoreCase("in")) {// 包含
					returnSqlStr += rs.getString("FIELD_NAME") + " IN (";
					StringTokenizer st = new StringTokenizer(rs.getString("VALUE1"), ",");
					while (st.hasMoreElements()) {
						returnSqlStr += "'" + st.nextToken() + "',";
					}
					returnSqlStr = returnSqlStr.substring(0, returnSqlStr.length() - 1) + ") ";
				} else if (rs.getString("RELATION").equalsIgnoreCase("between")) {// 在两者之间
					returnSqlStr += rs.getString("FIELD_NAME") + " between '" + rs.getString("VALUE1") + "' and '" + rs.getString("VALUE2") + "' ";
				} else {
					returnSqlStr += rs.getString("FIELD_NAME") + " " + rs.getString("RELATION") + "'" + rs.getString("VALUE1") + "' ";
				}
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } catch (ServiceLocatorException e) {
            Logger.getLogger(getClass()).error(e.toString());
        }
        finally {
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

        returnSqlStr = "SELECT EMPID, CHINESENAME,PERSON_ID,"
        		+ "PINYINNAME AS CHINESE_PINYIN, DEPARTMENT AS DEPTNAME, DEPTID, GROUP_NAME, GROUP_EN_NAME, GROUP_KOR_NAME "
                + "FROM AR_EMP_INFO_V WHERE CPNY_ID = '" + this.cnpyID + "' AND " + returnSqlStr + " ORDER BY DEPARTMENT";
        
        Logger.getLogger(getClass()).debug(
                "return sql String is : " + returnSqlStr);
        return returnSqlStr;
    }

    /**
     * getGroupMembers <br>
     * 根据输入的组号，得出所有符合本组条件的员工，将员工的工号和姓名存储到list中，供其它操作使用。
     * 
     * @param groupNo
     *            int
     * @return List
     */
    public List getGroupMembers(int groupNo,String cony_id) throws DataAccessException {
        List list = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            sql = "SELECT HR_EMPLOYEE.EMPID, HR_EMPLOYEE.PERSON_ID,"
                    + "HR_EMPLOYEE.DEPTID, "
                    + "HR_EMPLOYEE.CHINESENAME, "
                    + "HR_EMPLOYEE.CHINESE_PINYIN, HR_EMPLOYEE.ENGLISHNAME, HR_EMPLOYEE.KOREANNAME, "
                    + "HR_GET_FULL_DEPTNAME(HR_EMPLOYEE.DEPTID) FULL_DEPTNAME, "
                    + "HR_DEPARTMENT.DEPTNAME, HR_DEPARTMENT.DEPT_EN_NAME, HR_DEPARTMENT.DEPT_KOR_NAME, "
                    + "'' GROUP_NAME " + "FROM AR_EMP_GROUP, HR_EMPLOYEE, HR_DEPARTMENT "
                    + "WHERE AR_EMP_GROUP.EMPID = HR_EMPLOYEE.Person_Id "
                    + "AND HR_EMPLOYEE.ACTIVITY = 1 "
                    + "AND HR_EMPLOYEE.DEPTID = HR_DEPARTMENT.DEPTID "
                    + "AND AR_EMP_GROUP.GROUP_NO = ? AND HR_EMPLOYEE.CPNY_ID = ?";
            Logger.getLogger(getClass()).debug(sql);
            con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, groupNo);
            pstmt.setString(2, cony_id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(createGroupMember(rs));
            }
        } catch (ServiceLocatorException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (Exception e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, pstmt, con);
        }
        return list;
    }

    public List getGroupMembers(int groupNo, int conditionNo)
            throws DataAccessException {
        List list = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = this.getGroupSql(groupNo, conditionNo);
        try {
            Logger.getLogger(getClass()).debug(sql);
            con = services.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(createGroupMember(rs));
            }
        } catch (ServiceLocatorException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (Exception e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, stmt, con);
        }
        return list;
    }

    /**
     * getGroupList <br>
     * 得出所有已经创建的组，并且按照组的拼音顺序排序。
     * 
     * @return List
     */
    public List getGroupList(String cpny_id) throws DataAccessException {
        List list = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT GROUP_NO, GROUP_NAME, GROUP_EN_NAME, GROUP_KOR_NAME, "
                + "DESCRIPTION,GROUP_PROPERTY," + "QUALIFICATION, GROUP_TYPE "
                + "FROM AR_DYNAMIC_GROUP where cpny_id='"+cpny_id+"' ORDER BY GROUP_NAME DESC";
        Logger.getLogger(getClass()).debug(sql);
        try {
            con = services.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(this.createDynamicGroup(rs));
            }
        } catch (ServiceLocatorException e) {
            throw new DataAccessException("cant get connection" + e);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            throw new DataAccessException(" cant execute grouplist query! " + e);
        } finally {
            SqlUtil.close(rs, stmt, con);
        }
        return list;
    }

    /**
     * getGroupByNo
     * 
     * @param groupNo
     *            int
     * @return DynamicGroup
     */
    public DynamicGroup getGroupByNo(int groupNo) throws DataAccessException {
        DynamicGroup dynamicGroup = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String sql = "select GROUP_NO, GROUP_NAME, GROUP_EN_NAME, GROUP_KOR_NAME, "
                + "DESCRIPTION,GROUP_PROPERTY," + "QUALIFICATION, GROUP_TYPE "
                + "from AR_DYNAMIC_GROUP " + "where group_no ='" + groupNo
                + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            con = services.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                dynamicGroup = createDynamicGroup(rs);
            }
        } catch (ServiceLocatorException e) {
            throw new DataAccessException("cant get connection" + e);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            throw new DataAccessException(" cant execute grouplist query! " + e);
        } finally {
            SqlUtil.close(rs, stmt, con);
        }
        return dynamicGroup;
    }

    /**
     * createDynamicGroup
     * 
     * @param rs
     *            ResultSet
     * @return DynamicGroup
     */
    private DynamicGroup createDynamicGroup(ResultSet rs) {
        DynamicGroup dynamicGroup = new DynamicGroup();
        try {
            dynamicGroup
                    .setDescription(rs.getString("DESCRIPTION") != null ? rs
                            .getString("DESCRIPTION") : "");
            dynamicGroup.setGroupName(rs.getString("GROUP_NAME") != null ? rs
                    .getString("GROUP_NAME") : "");
            dynamicGroup.setGroupEnName(rs.getString("GROUP_EN_NAME") != null ? rs
                    .getString("GROUP_EN_NAME") : "");
            dynamicGroup.setGroupKoName(rs.getString("GROUP_KOR_NAME") != null ? rs
                    .getString("GROUP_KOR_NAME") : "");
            dynamicGroup.setGroupNo(rs.getInt("GROUP_NO"));
            dynamicGroup.setGroupProperty(StringUtil.checkNull(rs
                    .getString("GROUP_PROPERTY")));
            dynamicGroup.setGroupQualification(StringUtil.checkNull(rs
                    .getString("QUALIFICATION")));
            dynamicGroup.setSysGroupType(StringUtil.checkNull(rs
                    .getString("GROUP_TYPE")));
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        }
        return dynamicGroup;
    }

    /**
     * createGroupMember
     * 
     * @param rs
     *            ResultSet
     * @return GroupMember
     */
    private GroupMember createGroupMember(ResultSet rs) {
        GroupMember groupMember = new GroupMember();
        try {
            groupMember.setChinesename(rs.getString("CHINESENAME"));
            groupMember.setChinesePinyin(StringUtil.checkNull(rs.getString("CHINESE_PINYIN")));
            groupMember.setDeptid(StringUtil.checkNull(rs.getString("DEPTID")));
            groupMember.setDeptname(StringUtil.checkNull(rs.getString("DEPTNAME")));
            groupMember.setEmpId(StringUtil.checkNull(rs.getString("EMPID")));
            groupMember.setGroupname(rs.getString("GROUP_NAME"));
            groupMember.setPerson_id(StringUtil.checkNull(rs.getString("PERSON_ID")));
            //groupMember.setGroupEnName(StringUtil.checkNull(rs.getString("GROUP_EN_NAME")));
            //groupMember.setGroupKoName(StringUtil.checkNull(rs.getString("GROUP_KOR_NAME")));
        } catch (SQLException e) {
        	e.printStackTrace() ;
            Logger.getLogger(getClass()).debug(e.toString());
        }
        return groupMember;
    }

    public String addOrDeleteGroupMember(String conditionNo, String operation)
            throws DataAccessException {
        Connection con = null;
        CallableStatement proc = null;
        String flag = null;
        String sql = "call ar_group_members ( ?, ?, ? )";
        Logger.getLogger(getClass()).debug(sql);
        try {
            con = services.getConnection();
            proc = con.prepareCall("{ call ar_group_members ( ?, ?, ? )  }");
            int i = 1;
            proc.setString(i++, conditionNo);
            proc.setString(i++, this.cnpyID);
            proc.setString(i++, operation);
            proc.registerOutParameter(3, Types.VARCHAR);
            Logger.getLogger(getClass()).debug(conditionNo);
            Logger.getLogger(getClass()).debug(this.cnpyID);
            Logger.getLogger(getClass()).debug(operation);
            if (proc.executeUpdate() == 1)
                flag = proc.getString(3);
            else
                flag = "[操作失败]<br>未知错误";
        } catch (ServiceLocatorException e) {
            throw new DataAccessException("cant get connection" + e);
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
            throw new DataAccessException(" cant execute grouplist query! " + e);
        } finally {
            SqlUtil.close(proc, con);
        }
        return flag;
    }

    //删除指定工号的员工分组信息 by Pennix
    public String deleteGroupMembers(String[] empid) {
        Connection con = null;
        PreparedStatement ps = null;
        String flag = null;
        String sql = "DELETE FROM AR_EMP_GROUP WHERE EMPID = ?";
        try {
            con = services.getConnection();
            ps = con.prepareStatement(sql);
            if (empid == null)
            	return flag;
            for(int i=0;i<empid.length;i++){
                ps.setString(1, empid[i]);
                ps.executeUpdate();
            }
        } catch (ServiceLocatorException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
        	SqlUtil.close(ps, con);
        }
        Logger.getLogger(getClass())
                .debug(new Integer(empid.length).toString());
        return flag;
    }
}
