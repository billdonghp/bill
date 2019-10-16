package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.ar.bean.ArbcdaInfo;
import com.ait.sy.bean.AdminBean;
import com.ait.util.SqlUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;

public class ArbcdaBean {
    public ArbcdaBean() {
    }
    AdminBean admin=ApplicationContext.getTheadLocal();
    public ArrayList getList(int itemNo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList list = null;
        StringBuffer sql = new StringBuffer();
        sql.append("\n SELECT ar_item_param.*,ar_dynamic_group.*,sy_code.code_name as stat_type,wages.Code_Name AS wages_type,ar_shift010.Shift_Name,CPNY.CODE_NAME CPNYNAME");
        sql.append("\n   FROM ar_item_param,        ");
        sql.append("\n        ar_dynamic_group,     ");
        sql.append("\n        sy_code,              ");
        sql.append("\n        sy_code wages,        ");
        sql.append("\n        ar_shift010 ,          ");
        sql.append("\n        SY_CODE CPNY         ");
        sql.append("\n  WHERE ar_item_param.ar_item_no = ? ");
        sql.append("\n 	  AND to_char(ar_dynamic_group.group_no(+)) = ar_item_param.ar_group_no   ");
        sql.append("\n 	  AND ar_item_param.stat_type_code = sy_code.code_id ");
        sql.append("\n 	  AND ar_item_param.CPNY_ID = CPNY.code_id(+) ");
        sql.append("\n 	  AND ar_item_param.Wages_Type_Code = wages.Code_Id  ");
        sql.append("\n 	  AND ar_item_param.Shift_No = to_char(ar_shift010.shift_no(+)) ");
        sql.append("\n 	  AND ar_item_param.cpny_id = ? ");
        sql.append("\n  ORDER BY ar_item_param.stat_type_code,ar_group_no  DESC ");

        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            stmt = conn.prepareStatement(sql.toString());          
            stmt.setInt(1, itemNo);
            stmt.setString(2, admin.getCpnyId());
            rs = stmt.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                ArItem arItem = new ArItem();
                arItem.setArParamNo(rs.getInt("AR_PARAM_NO")) ;
                arItem.setItemNo(rs.getInt("ar_item_no"));
                arItem.setAr_group_no(rs.getString("ar_group_no"));
                arItem.setAr_group_no_name(rs.getString("group_name"));
                arItem.setAr_group_no_en_name(rs.getString("group_en_name"));
                arItem.setCard_flag(rs.getString("card_flag"));
                arItem.setDate_type(rs.getString("date_type"));
                arItem.setApply_flag(rs.getString("apply_flag"));
                arItem.setActivity(rs.getInt("activity"));
                arItem.setStat_type(rs.getString("stat_type")) ;
                arItem.setStat_type_code(rs.getString("stat_type_code")) ;
                arItem.setWages_type(rs.getString("wages_type")) ;
                arItem.setWages_type_code(rs.getString("wages_type_code")) ;
                arItem.setShiftNo(rs.getString("shift_no")) ;
                arItem.setShiftName(rs.getString("Shift_Name")) ;
                arItem.setCpnyId(rs.getString("CPNY_ID"));
                arItem.setCpnyName(rs.getString("CPNYNAME"));
                list.add(arItem);
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } catch (Exception e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, stmt, conn);
        }
        return list;

    }

    public String getGroup(String group) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String name = "&nbsp;";
        String sql = "SELECT GROUP_NAME " + "FROM AR_DYNAMIC_GROUP "
                + "WHERE GROUP_NO='" + group + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("GROUP_NAME");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return name;
    }

    public String getBasic(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String code = "&nbsp;";
        String sql = "select schedule_item_yn " + "from ar_item_basic "
                + "where item_No='" + itemNo + "' " + "and group_No='"
                + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                code = rs.getString("schedule_item_yn");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return code;
    }

    public String getB(String code) {
        if (code.equals("Y"))
            return "是";
        else if (code.equals("N"))
            return "否";
        return code;
    }

    public String getC(String code) {
        if (code.equals("1"))
            return "是";
        else if (code.equals("0"))
            return "否";
        return code;
    }

    public String getD(String code) {
        code = code.replaceAll("1", "平常");
        code = code.replaceAll("2", "周末");
        code = code.replaceAll("3", "国家节假日");
        return code;
    }

    public String getA(String code) {
        if (code.equals("1"))
            return "是";
        else if (code.equals("0"))
            return "否";

        return code;

    }

    public String getCard(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String code = "&nbsp;";
        String sql = "select flag " + "from ar_item_card " + "where item_No='"
                + itemNo + "' " + "and group_No='" + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                code = Integer.toString(rs.getInt("flag"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return code;
    }

    public String getDateType(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String code = "&nbsp;";
        String sql = "select datatype " + "from ar_item_datatype "
                + "where item_No='" + itemNo + "' " + "and group_No='"
                + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                code = rs.getString("datatype");
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return code;
    }

    public String getApply(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String code = "&nbsp;";
        String sql = "select flag " + "from ar_item_apply " + "where item_No='"
                + itemNo + "' " + "and group_No='" + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                code = Integer.toString(rs.getInt("flag"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return code;
    }

    public String getIsRe(int itemNo, String groupNo, String tablename) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String type = null;
        String sql = "select 'up' as types " + "from " + tablename + " "
                + "where group_no='" + groupNo + "' " + "and item_no in("
                + "select item_no " + "from  " + tablename + " "
                + "where (group_no='" + groupNo + "' "
                + "or group_no='constant'" + ")" + ")union("
                + "select 'in' as types " + "from " + tablename + " "
                + "where group_no='constant' "
                + "and item_no not in(select item_no " + "from " + tablename
                + " " + "where group_no='" + groupNo + "'" + ")" + ") ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                type = rs.getString("types");
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return type;
    }

    public String getGroupName(String group) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        // String type = null;
        String name = "&nbsp;";
        String sql = "SELECT GROUP_NAME " + "FROM AR_DYNAMIC_GROUP "
                + "WHERE GROUP_NO='" + group + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("GROUP_NAME");
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return name;
    }

    public ArbcdaInfo getArCard(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArbcdaInfo info = new ArbcdaInfo();
        String sql = "SELECT " + "FLAG, " + "FROM_FLAG, " + "FROM_RELATION, "
                + "FROM_OFFSET, " + "TO_FLAG, " + "TO_RELATION, "
                + "TO_OFFSET, " + "UNIT, " + "ACTIVITY " + "FROM AR_ITEM_CARD "
                + "WHERE ITEM_NO ='" + itemNo + "' " + "AND GROUP_NO='"
                + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);

            while (rs.next()) {
                info.setCard_flag(rs.getInt("FLAG"));
                info.setCard_from_flag(rs.getInt("FROM_FLAG"));
                info.setCard_from_relation(rs.getString("FROM_RELATION"));
                info.setCard_from_offset(rs.getInt("FROM_OFFSET"));
                info.setCard_to_flag(rs.getInt("TO_FLAG"));
                info.setCard_to_relation(rs.getString("TO_RELATION"));
                info.setCard_to_offset(rs.getInt("TO_OFFSET"));
                info.setCard_unit(rs.getString("UNIT"));
                info.setActivity(rs.getInt("ACTIVITY"));
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return info;
    }

    public int editArItemCard(ArrayList values, int itemNo, String groupNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        // String sql="insert into ar_item_card
        // (item_No,group_No,flag,relation,offset,activity,from_flag,to_flag)";
        // sql+=" values
        // ("+itemNo+",'"+groupNo+"',"+sk+",'"+relation+"',"+tolerant+",";
        // sql+=activity+","+from_time+","+to_time+")";
        String sql = "UPDATE AR_ITEM_CARD SET " + "ITEM_NO="
                + values.get(0).toString() + ", " + "GROUP_NO='"
                + values.get(1).toString() + "', " + "FLAG='"
                + values.get(2).toString() + "', " + "FROM_FLAG='"
                + values.get(3).toString() + "', " + "FROM_RELATION='"
                + values.get(4).toString() + "', " + "FROM_OFFSET='"
                + values.get(5).toString() + "', " + "TO_FLAG='"
                + values.get(6).toString() + "', " + "TO_RELATION='"
                + values.get(7).toString() + "', " + "TO_OFFSET='"
                + values.get(8).toString() + "', " + "UNIT='"
                + values.get(9).toString() + "', " + "ACTIVITY='"
                + values.get(10).toString() + "' " + "WHERE ITEM_NO=" + itemNo
                + " " + "AND GROUP_NO='" + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public ArbcdaInfo getArApply(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "FLAG, " + "CARD_OR_APP, " + "APPLY_TYPE, "
                + "VALUESS, " + "ACTIVITY " + "FROM AR_ITEM_APPLY "
                + "WHERE ITEM_NO ='" + itemNo + "' " + "AND GROUP_NO='"
                + groupNo + "'";
        Logger.getLogger(getClass()).debug(sql);
        ArbcdaInfo info = new ArbcdaInfo();
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                info.setApply_flag(rs.getInt("FLAG"));
                info.setApply_or(rs.getInt("CARD_OR_APP"));
                info.setApply_type(rs.getString("APPLY_TYPE"));
                info.setApply_values(rs.getInt("VALUESS"));
                info.setActivity(rs.getInt("ACTIVITY"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return info;
    }

    public int editArItemApply(ArrayList values, int itemNo, String groupNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        // String sql="insert into ar_item_apply
        // (item_no,group_No,flag,apply_type,activity,valuess,card_or_app)";
        // sql+=" values(?,?,?,?,?,?,?)";
        String sql = "UPDATE AR_ITEM_APPLY SET " + "FLAG='"
                + values.get(0).toString() + "', " + "APPLY_TYPE='"
                + values.get(1).toString() + "', " + "ACTIVITY='"
                + values.get(2).toString() + "', " + "VALUESS='"
                + values.get(3).toString() + "', " + "CARD_OR_APP='"
                + values.get(4).toString() + "' " + "WHERE ITEM_NO=" + itemNo
                + " " + "AND GROUP_NO='" + groupNo + "'";
        Logger.getLogger(getClass()).debug("return sql : " + sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public ArbcdaInfo getArDatatype(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT * " + "FROM AR_ITEM_DATATYPE "
                + "WHERE ITEM_NO ='" + itemNo + "' " + "AND GROUP_NO='"
                + groupNo + "'";
        Logger.getLogger(getClass()).debug("return sql : " + sql);
        ArbcdaInfo info = new ArbcdaInfo();
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                info.setDatatype(rs.getString("datatype"));
                info.setActivity(rs.getInt("activity"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return info;
    }

    public int editArItemDataType(ArrayList values, int itemNo, String groupNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "update ar_item_datatype set " + "datatype='"
                + values.get(0).toString() + "', " + "activity='"
                + values.get(1).toString() + "' " + "where item_no=" + itemNo
                + " " + "and group_No='" + groupNo + "' ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public ArbcdaInfo getArBasic(int itemNo, String groupNo) {
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        String sql = "SELECT " + "UNIT, " + "TOLERANT, " + "MAX, " + "MIN, "
                + "SCHEDULE_ITEM_YN, " + "ACTIVE_STAT, " + "REPLACES "
                + "FROM AR_ITEM_BASIC " + "WHERE ITEM_NO ='" + itemNo + "' "
                + "AND GROUP_NO='" + groupNo + "' ";
        Logger.getLogger(getClass()).debug(sql);
        ArbcdaInfo info = new ArbcdaInfo();
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                info.setBasic_unit(rs.getString("UNIT"));
                info.setBasic_tolerant(rs.getInt("TOLERANT"));
                info.setBasic_max(rs.getInt("MAX"));
                info.setBasic_min(rs.getInt("MIN"));
                info.setBasic_schedule(rs.getString("SCHEDULE_ITEM_YN"));
                info.setBasic_active_stat(rs.getString("ACTIVE_STAT"));
                info.setBasic_replaces(rs.getString("REPLACES"));
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.toString());
        } finally {
            SqlUtil.close(rs, state, conn);
        }
        return info;
    }

    public int editArItemBasic(ArrayList values, int itemNo, String groupNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        String sql = "UPDATE AR_ITEM_BASIC SET " + "UNIT='"
                + values.get(0).toString() + "', " + "TOLERANT='"
                + values.get(1).toString() + "', " + "MAX='"
                + values.get(2).toString() + "', " + "MIN='"
                + values.get(3).toString() + "', " + "SCHEDULE_ITEM_YN='"
                + values.get(4).toString() + "', " + "ACTIVE_STAT='"
                + values.get(5).toString() + "', " + "REPLACES='"
                + values.get(6).toString() + "' " + "WHERE ITEM_NO='" + itemNo
                + "' " + "AND GROUP_NO='" + groupNo + "'";
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();
            affRow = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }

    public int delAllArbcda(int itemNo, String groupNo) {
        int affRow = 0;
        Connection conn = null;
        Statement state = null;
        try {
            conn = ConnBean.getConn();
            state = conn.createStatement();

            String sql = "delete " + "from ar_item_basic " + "where item_no='"
                    + itemNo + "' " + "and group_No='" + groupNo + "' ";
            Logger.getLogger(getClass()).debug(sql);
            state.addBatch(sql);

            sql = "delete " + "from ar_item_card " + "where item_no='" + itemNo
                    + "' " + "and group_No='" + groupNo + "' ";
            Logger.getLogger(getClass()).debug(sql);
            state.addBatch(sql);

            sql = "delete " + "from ar_item_datatype " + "where item_no='"
                    + itemNo + "' " + "and group_No='" + groupNo + "'";
            Logger.getLogger(getClass()).debug(sql);
            state.addBatch(sql);

            sql = "delete " + "from ar_item_apply " + "where item_no='"
                    + itemNo + "' " + "and group_No='" + groupNo + "'";
            Logger.getLogger(getClass()).debug(sql);
            state.addBatch(sql);

            // 计算影响行总数，如果成功应该返回4
            int[] affRowArray = state.executeBatch();
            for (int i = 0; i < affRowArray.length; i++) {
                affRow += affRowArray[i];
            }
        } catch (SQLException sqlex) {
            Logger.getLogger(getClass()).error(sqlex.toString());
        } finally {
            SqlUtil.close(state, conn);
        }
        return affRow;
    }
}
