package com.ait.ar.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ait.ar.bean.GroupCondition;
import com.ait.ar.dao.GroupConditionDAO;
import com.ait.util.DataAccessException;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;

public class GroupConditionDAOImpl implements GroupConditionDAO {
	private static ServiceLocator services;

	public GroupConditionDAOImpl() {
		try {
			services = ServiceLocator.getInstance();
		} catch (ServiceLocatorException ex) {
		}
	}

	/**
	 * addGroupCondition <br>
	 * 为动态组添加条件，所有条件之间的关系是 intersection 关系。
	 * 
	 * @param condition
	 *            GroupCondition
	 * @throws DataAccessException
	 * @return int
	 */
	public int addGroupCondition(GroupCondition condition)
			throws DataAccessException {
		return 0;
	}

	/**
	 * addGroupCondition <br>
	 * 为动态组批量添加条件，所有条件之间的关系是 intersection 关系。 向特定
	 * 
	 * @param condition
	 *            String[]
	 * @param relation
	 *            String[]
	 * @param field1
	 *            String[]
	 * @param field2
	 *            String[]
	 * @throws DataAccessException
	 * @return int
	 */
	public int addGroupCondition(String groupNo, String[] condition,
			String[] relation, String[] field1, String[] field2) {
		String sql = "{ call ar_add_group_condition_pr(?,?,?,?,?) }";
		Logger.getLogger(getClass()).debug(sql);
		Connection con = null;
		CallableStatement cs = null;
		int n = 0;
		try {
			con = services.getConnection();
			// 添加获取原有员工列表value1
			String sqlString = null;
			String tmpValue = null;
			Statement state = con.createStatement();

			cs = con.prepareCall(sql);
			if (condition.length > 0 && field1.length > 0
					&& !groupNo.equals("")) {
				cs.setString(1, groupNo);
				for (int i = 0; i < condition.length; i++) {
					if (!field1[i].equals("")) {
						Logger.getLogger(getClass()).debug(sql);
						Logger.getLogger(getClass()).debug("parameter1:" + groupNo);
						Logger.getLogger(getClass()).debug("parameter2:" + condition[i]);
						Logger.getLogger(getClass()).debug("parameter3:" + relation[i]);
						Logger.getLogger(getClass()).debug("parameter4:" + StringUtil.toCN(field1[i]));
						Logger.getLogger(getClass()).debug("parameter5:" + StringUtil.toCN(field2[i]));
						cs.setString(2, condition[i]);
						cs.setString(3, relation[i]);
						sqlString = "select ar_group_conditions.value1 "
								+ "from ar_group_conditions, "
								+ "ar_condition_list "
								+ "where ar_group_conditions.field_name = ar_condition_list.field_id "
								+ "and ar_group_conditions.group_no = '"
								+ groupNo + "' "
								+ "and ar_condition_list.condition_no = '"
								+ condition[i].trim() + "'";
						Logger.getLogger(getClass()).debug(sqlString);
						ResultSet rs = state.executeQuery(sqlString);
						if (rs != null && rs.next()) {
							tmpValue = rs.getString("value1");
						}
						rs.close();
						state.close();
						if (tmpValue == null) {
							tmpValue = StringUtil.toCN(field1[i]);
						} else if(tmpValue.indexOf(StringUtil.toCN(field1[i])) < 0) {
                            Logger.getLogger(getClass()).debug("Old Field1 : " + tmpValue);
							tmpValue = tmpValue + "," + StringUtil.toCN(field1[i]);
						}
						Logger.getLogger(getClass()).debug(
								"value1 : " + tmpValue);
						tmpValue = tmpValue.replaceAll("null,", "");// clear
						// null
						// value
						cs.setString(4, tmpValue);
						cs.setString(5, StringUtil.toCN(field2[i]));
						cs.execute();
						n += 1;
					}
				}
			}
		} catch (ServiceLocatorException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		} finally {
			SqlUtil.close(cs, con);
		}
		return n;
	}

	/***************************************************************************
	 * overwrite at 2005-12-22 by lvhongbin
	 * 
	 */
    public int addGroupCondition(String groupNo, String[] condition, String[] relation, String[] field1, String[] field2, String state) {
        return 0;
    }

    public int modGroupCondition(String conditionNo, String field1, String field2) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE AR_GROUP_CONDITIONS SET VALUE1 = ?, VALUE2 = ? WHERE GROUP_CONDITION_NO = ?";
        try {
            conn = services.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, StringUtil.toCN(field1));
            ps.setString(2, StringUtil.toCN(field2));
            ps.setInt(3, Integer.parseInt(conditionNo));
            ps.executeUpdate();
        } catch (ServiceLocatorException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } catch (SQLException e) {
            Logger.getLogger(getClass()).debug(e.toString());
        } finally {
        	SqlUtil.close(ps, conn);
        }
        Logger.getLogger(getClass()).debug("Condition Modification Unfinished.............");
        return 0;
    }

	/**
	 * getConditionList <br>
	 * 根据输入的组编号，来导出所有关联于此组的条件。
	 * 
	 * @param groupNo
	 *            int
	 * @return List
	 */
	public List getConditionList(int groupNo) throws DataAccessException {
		List list = new ArrayList();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select A.GROUP_CONDITION_NO, A.GROUP_NO, "
				+ "B.FIELD_NAME, B.FIELD_EN_NAME, "
				+ "A.RELATION, " + "A.VALUE1, A.VALUE2 "
				+ "from AR_GROUP_CONDITIONS A, AR_CONDITION_LIST B " 
				+ "where A.FIELD_NAME = B.FIELD_ID AND GROUP_NO='" + groupNo + "'";
		Logger.getLogger(getClass()).debug(sql);
		try {
			con = services.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.createCondition(rs));
			}
		} catch (ServiceLocatorException e) {
			throw new DataAccessException("cant get connection" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new DataAccessException(" cant execute grouplist query! " + e);
		} finally {
			SqlUtil.close(rs, pstmt, con);
		}
		return list;

	}

	/**
	 * deleteGroupCondition <br>
	 * 根据条件号，来删除某个关联动态组的条件,其中 conditionNo 是条件的编号。
	 * 
	 * @param groupNo
	 *            String
	 */
	public void deleteGroupCondition(int conditionNo) throws DataAccessException {
        String sql = "Delete From Ar_Group_Conditions Where GROUP_CONDITION_NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = services.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, conditionNo);
			pstmt.executeUpdate();
		} catch (ServiceLocatorException e) {
			throw new DataAccessException(
					"cant get connection for deleting group condition!" + e);
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
			throw new DataAccessException("cant not delete group condition!"
					+ e);
		} finally {
			SqlUtil.close(pstmt, con);
		}
	}

	/**
	 * createDynamicGroup
	 * 
	 * @param rs
	 *            ResultSet
	 * @return DynamicGroup
	 */
	private GroupCondition createCondition(ResultSet rs) {
		GroupCondition groupCondition = new GroupCondition();
		try {
			groupCondition
					.setField_name(rs.getString("FIELD_NAME") != null ? rs
							.getString("FIELD_NAME") : "");
			groupCondition
					.setField_en_name(rs.getString("FIELD_EN_NAME") != null ? rs
							.getString("FIELD_EN_NAME") : "");
			groupCondition.setRelation(rs.getString("RELATION") != null ? rs
					.getString("RELATION") : "");
			groupCondition.setValue1(rs.getString("VALUE1") != null ? rs
					.getString("VALUE1") : "");
			groupCondition.setValue2(rs.getString("VALUE2") != null ? rs
					.getString("VALUE2") : "");
			groupCondition.setGroup_condition_no(rs
					.getInt("GROUP_CONDITION_NO"));
			groupCondition.setGroup_no(rs.getInt("GROUP_NO"));
		} catch (SQLException e) {
			Logger.getLogger(getClass()).error(e.toString());
		}
		return groupCondition;
	}
}
