package com.ait.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ait.ar.bean.ArItem;
import com.ait.util.ServiceLocator;
import com.ait.util.ServiceLocatorException;
import com.ait.util.SqlUtil;
import com.ait.util.StringUtil;
import com.ait.utils.ConnBean;
import com.ait.web.ApplicationContext;
import com.ait.sy.bean.AdminBean;

public class ArItemBean {
    private DBConnection db = new DBConnection();

    private static ServiceLocator services;

    private ResultSet rs = null;
    
    

    /**
     * 获得ARITEM表里的所有值
     * 
     * @return ArrayList
     */
    public ArrayList getItemList() {
    	AdminBean admin=ApplicationContext.getTheadLocal();
        Connection conn = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList list = null;
        String sql = "SELECT A.*,B.CODE_NAME ITEM_GROUP_NAME,B.CODE_EN_NAME ITEM_GROUP_EN_NAME,B.CODE_KOR_NAME ITEM_GROUP_KOR_NAME, CPNY.CODE_NAME CPNYNAME  FROM AR_ITEM A, SY_CODE B,SY_CODE CPNY WHERE A.ITEM_GROUP_CODE = B.CODE_ID(+)  AND A.CPNY_ID=CPNY.CODE_ID(+) AND (A.CPNY_ID IS NULL OR A.CPNY_ID='"+admin.getCpnyId()+"') ORDER BY B.CODE_ID,SHORT_NAME  ";
        Logger.getLogger(getClass()).debug(sql);
        try {
            conn = db.getConnectionOracle();
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            list = new ArrayList();
            while (rs.next()) {
                ArItem info = new ArItem();
                info.setItemNo(rs.getInt("item_no"));
                info.setItemId(rs.getString("item_id"));
                info.setItemName(this.returnNull(rs.getString("item_name")));
                info.setItemEnName(this.returnNull(rs.getString("item_en_name")));
                info.setItemKorName(this.returnNull(rs.getString("item_kor_name")));
                info.setShortName(this.returnNull(rs.getString("short_name")));
                info.setItemGroupCode(this.returnNull(rs.getString("ITEM_GROUP_CODE")));
                info.setItemGroupName(this.returnNull(rs.getString("ITEM_GROUP_NAME")));
                info.setItemGroupEnName(this.returnNull(rs.getString("ITEM_GROUP_EN_NAME")));
                info.setItemGroupKorName(this.returnNull(rs.getString("ITEM_GROUP_KOR_NAME")));
                info.setDescription(this
                        .returnNull(rs.getString("description")));
                info.setActivity(rs.getInt("activity"));
                info.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
                info.setCpnyName(StringUtil.checkNull(rs.getString("CPNYNAME")));
                list.add(info);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
        	db.closeConnection();
            ///SqlUtil.close(rs, state, conn);
        }
        return list;
    }

    public String returnNull(String str) {
        if (str == null || str.trim().length() == 0)
            return "&nbsp;";
        return str;
    }

    /**
     * 根据编号得到考勤项目
     * 
     * @param itemNo
     *            int
     * @return ArItem
     */
    public ArItem getArItemInfo(int itemNo) {
        ArItem info = null;
        String sql = "SELECT A.*,B.CODE_NAME ITEM_GROUP_NAME,CPNY.CODE_NAME CPNYNAME FROM AR_ITEM A, SY_CODE B,SY_CODE CPNY WHERE A.ITEM_GROUP_CODE = B.CODE_ID(+) AND A.CPNY_ID=CPNY.CODE_ID(+) AND A.ITEM_NO = " + itemNo;
        Logger.getLogger(getClass()).debug(sql);
        try {
            db.getConnectionOracle();
            rs = db.getResultSet(sql);
            while (rs.next()) {
                info = new ArItem();
                info.setItemNo(rs.getInt("item_no"));
                info.setItemId(rs.getString("item_id"));
                info.setItemName(this.returnNull(rs.getString("item_name")));
                info.setItemEnName(this.returnNull(rs.getString("item_en_name")));
                info.setItemKorName(this.returnNull(rs.getString("item_kor_name")));
                info.setShortName(this.returnNull(rs.getString("short_name")));
                info.setItemGroupCode(this.returnNull(rs.getString("ITEM_GROUP_CODE")));
                info.setItemGroupName(this.returnNull(rs.getString("ITEM_GROUP_NAME")));
                info.setDescription(this
                        .returnNull(rs.getString("description")));
                info.setActivity(rs.getInt("activity"));
                info.setCpnyId(StringUtil.checkNull(rs.getString("CPNY_ID")));
                info.setCpnyName(StringUtil.checkNull(rs.getString("CPNYNAME"),"公共"));
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            db.closeConnection();
        }
        return info;
    }

    /**
     * 修改考勤项目
     * 
     * @param values
     *            ArrayList
     */
    public void editArItem(ArrayList values) {
        int itemNo = ((Integer) values.get(0)).intValue();
        String itemName = (String) values.get(1);
        String itemEnName = (String) values.get(2);
        String itemKorName = (String) values.get(3);
        String shortName = (String) values.get(4);
        String description = (String) values.get(5);
        int activity = ((Integer) values.get(6)).intValue();
        String itempGroupCode = (String) values.get(7);
        String itemId = (String) values.get(8);
        String adminId = (String) values.get(9);
        String sql = "update ar_item set item_name='" + itemName
        		+ "',item_id='" + itemId
			    + "',item_en_name='" + itemEnName
			    + "',item_kor_name='" + itemKorName
                + "',short_name='" + shortName + "',";
        sql += "description='" + description + "',activity=" + activity + ",item_group_code='" + itempGroupCode + "', UPDATE_DATE=sysdate, UPDATED_BY = '"+ adminId +"'";
        sql += "where item_no=" + itemNo;
        Logger.getLogger(getClass()).debug(sql);
        try {
            db.getConnectionOracle();
            db.update(sql);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            db.closeConnection();
        }

    }

    /**
     * 验证考勤项目名称
     * 
     * @param values
     *  ArItem
     */
    public int validateItemNameInsert(ArItem arItem) {
    	int count = 0 ;
    	Connection conn = null; // 数据库连接对象
        PreparedStatement ps = null; // 带参数的SQL语句对象
        ResultSet rs = null; // 结果集
    	
        String sql = "SELECT COUNT(ITEM_ID) FROM AR_ITEM WHERE (INSTR(AR_ITEM.ITEM_NAME,?) = 1 OR INSTR(?,AR_ITEM.ITEM_NAME) = 1) AND CPNY_ID='"+arItem.getCpnyId()+"'";
        Logger.getLogger(getClass()).debug(sql);
        Logger.getLogger(getClass()).debug(arItem.getItemName());
        try {
        	conn = ConnBean.getConn();
            ps = conn.prepareStatement(sql) ;
            ps.setString(1, arItem.getItemName()) ;
            ps.setString(2, arItem.getItemName()) ;
            rs = ps.executeQuery() ;
            if(rs.next())
            {
            	count = rs.getInt(1) ;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, ps, conn) ;
        }
        return count ;
    }
    
    /**
     * 验证考勤项目名称
     * 
     * @param values
     *  ArItem
     */
    public int validateItemNameUpdate(ArItem arItem) {
    	int count = 0 ;
    	Connection conn = null; // 数据库连接对象
        PreparedStatement ps = null; // 带参数的SQL语句对象
        ResultSet rs = null; // 结果集
    	
        String sql = "SELECT COUNT(ITEM_ID) FROM AR_ITEM WHERE (INSTR(AR_ITEM.ITEM_NAME,?) = 1 OR INSTR(?,AR_ITEM.ITEM_NAME) = 1) AND ITEM_NO != ?";
        Logger.getLogger(getClass()).debug(sql);
        try {
        	conn = ConnBean.getConn();
            ps = conn.prepareStatement(sql) ;
            ps.setString(1, arItem.getItemName()) ;
            ps.setString(2, arItem.getItemName()) ;
            ps.setInt(3, arItem.getItemNo()) ;
            rs = ps.executeQuery() ;
            if(rs.next())
            {
            	count = rs.getInt(1) ;
            }
            
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            SqlUtil.close(rs, ps, conn) ;
        }
        return count ;
    }
    
    /**
     * 添加考勤项目
     * 
     * @param values
     *            ArrayList
     */
    public void addItem(ArrayList values) {
        String seq = (String) values.get(0);
        String itemName = (String) values.get(1);
        String itemEnName = (String) values.get(2);
        String itemKorName = (String) values.get(3);
        String shortName = (String) values.get(4);
        String description = (String) values.get(5);
        int activity = ((Integer) values.get(6)).intValue();
        String itempGroupCode = (String) values.get(7);
        String itemId = (String) values.get(8);
        String adminId = (String) values.get(9);
        String cpnyId = (String) values.get(10);
        String sql = "insert into ar_item (item_no,item_id,item_name,item_en_name,item_kor_name,short_name,description,activity,item_group_code,CREATE_DATE, CREATED_BY,CPNY_ID) values(";
        sql += seq + ",'" + itemId + "','" + itemName + "','" + itemEnName + "','" + itemKorName + "','" + shortName + "','" + description
                + "'," + activity + ",'" + itempGroupCode + "', sysdate, '"+ adminId +"','"+cpnyId+"')";
        Logger.getLogger(getClass()).debug(sql);
        try {
            db.getConnectionOracle();
            db.update(sql);
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            db.closeConnection();
        }
    }

    /**
     * addItem overload
     * 
     * @param values
     *            ArrayList
     * @param transation
     *            String
     * @return String
     */
    public String addItem(ArrayList values, String transation) {
        int itemNo = Integer.parseInt(values.get(0).toString());
        String itemName = (String) values.get(1);
        String shortName = (String) values.get(2);
        String description = (String) values.get(3);
        int activity = ((Integer) values.get(4)).intValue();
        String sql = "insert into ar_item (item_no,item_name,short_name,description,activity) values(";
        sql += itemNo + ",'" + itemName + "','" + shortName + "','"
                + description + "'," + activity + ")";
        Logger.getLogger(getClass()).debug("return sql : " + sql);
        return sql;
    }

    /**
     * getMaxSeq
     */
    public int getMaxSeq() {
        int max = 0;
        String sql = "select max(item_no) as max from ar_item";
        Logger.getLogger(getClass()).debug(sql);
        try {
            db.getConnectionOracle();
            rs = db.getResultSet(sql);
            while (rs.next()) {
                max = rs.getInt("max");
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass()).error(ex.toString());
        } finally {
            db.closeConnection();
        }
        return max;
    }

    public int delItem(int itemNo) {
        int success = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            services = ServiceLocator.getInstance();
            conn = services.getConnection();
            String sql = "DELETE FROM AR_ITEM_PARAM WHERE AR_ITEM_NO = ?";
            pstmt = conn.prepareStatement(sql);
            Logger.getLogger(getClass()).debug(new Integer(itemNo));
            Logger.getLogger(getClass()).debug(sql);
            pstmt.setInt(1, itemNo);
            pstmt.executeUpdate();
            sql = "DELETE FROM AR_ITEM WHERE ITEM_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemNo);
            success = pstmt.executeUpdate();
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	SqlUtil.close(pstmt, conn);
        }
        return success;
    }
}
